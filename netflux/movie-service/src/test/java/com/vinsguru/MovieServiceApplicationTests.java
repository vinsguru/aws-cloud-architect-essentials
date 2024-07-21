package com.vinsguru;

import com.vinsguru.domain.Genre;
import com.vinsguru.dto.MovieDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;

import java.net.URI;
import java.util.List;

@Import(TestContainersConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieServiceApplicationTests {

	private static final Logger log = LoggerFactory.getLogger(MovieServiceApplicationTests.class);

	@Autowired
	private TestRestTemplate template;

	@Test
	public void health(){
		var responseEntity = this.template.getForEntity("/actuator/health", Object.class);
		Assertions.assertTrue(responseEntity.getStatusCode().is2xxSuccessful());
	}

	@Test
	void allMovies() {
		var movies = getMovies("/api/movies");
		Assertions.assertEquals(6, movies.size());
	}

	@Test
	void moviesByGenre() {
		var movies = getMovies("/api/movies/ACTION");
		Assertions.assertEquals(3, movies.size());
		Assertions.assertTrue(movies.stream().map(MovieDto::genre).allMatch(Genre.ACTION::equals));
	}

	private List<MovieDto> getMovies(String uri){
		var requestEntity = new RequestEntity<>(HttpMethod.GET, URI.create(uri));
		var responseEntity = this.template.exchange(requestEntity, new ParameterizedTypeReference<List<MovieDto>>() {
		});
		log.info("response: {}", responseEntity.getBody());
		Assertions.assertNotNull(responseEntity.getBody());
		return responseEntity.getBody();
	}

}
