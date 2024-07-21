package com.vinsguru;

import com.vinsguru.client.MovieClient;
import com.vinsguru.domain.Genre;
import com.vinsguru.dto.CustomerDto;
import com.vinsguru.dto.GenreUpdateRequest;
import com.vinsguru.dto.MovieDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.http.ProblemDetail;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.util.List;

@Import(TestContainersConfiguration.class)
@MockBean({RestClient.class, MovieClient.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerServiceApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(CustomerServiceApplicationTests.class);

	@Autowired
	private TestRestTemplate template;

	@Autowired
	private MovieClient movieClient;

	@Test
	public void health(){
		var responseEntity = this.template.getForEntity("/actuator/health", Object.class);
		Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
	}

	@Test
	public void customerWithMovies(){
		// mock recommended movies
		Mockito.when(movieClient.getMovies(Mockito.any(Genre.class))).thenReturn(List.of(
				new MovieDto(1, "movie-1", 1990, Genre.ACTION),
				new MovieDto(2, "movie-2", 1991, Genre.ACTION)
		));

		var responseEntity = this.template.getForEntity("/api/customers/1", CustomerDto.class);
		Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
		var customerDto = responseEntity.getBody();
		Assertions.assertNotNull(customerDto);
		Assertions.assertEquals("sam", customerDto.name());
		Assertions.assertEquals(2, customerDto.recommendedMovies().size());
	}

	@Test
	public void customerNotFound(){
		var responseEntity = this.template.getForEntity("/api/customers/10", ProblemDetail.class);
		Assertions.assertTrue(responseEntity.getStatusCode().is4xxClientError());
		var problemDetail = responseEntity.getBody();
		log.info("problem detail: {}", problemDetail);
		Assertions.assertNotNull(problemDetail);
		Assertions.assertEquals("Customer Not Found", problemDetail.getTitle());
	}

	@Test
	public void updateGenre(){
		var genreUpdateRequest = new GenreUpdateRequest(Genre.DRAMA);
		var requestEntity = new RequestEntity<>(genreUpdateRequest, HttpMethod.PATCH, URI.create("/api/customers/1/genre"));
		var responseEntity = this.template.exchange(requestEntity, Void.class);
		Assertions.assertEquals(204, responseEntity.getStatusCode().value());
	}

}
