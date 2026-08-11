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
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.ProblemDetail;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.client.RestTestClient;
import org.springframework.web.client.RestClient;

import java.util.List;

@AutoConfigureRestTestClient
@Import(TestContainersConfiguration.class)
@MockitoBean(types = {RestClient.class, MovieClient.class})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerServiceApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(CustomerServiceApplicationTests.class);

    @Autowired
    private RestTestClient restTestClient;

    @Autowired
    private MovieClient movieClient;

    @Test
    public void health() {
        this.restTestClient.get()
                           .uri("/actuator/health")
                           .exchange()
                           .expectStatus().is2xxSuccessful();
    }

    @Test
    public void customerWithMovies() {
        // mock movie service response
        Mockito.when(movieClient.getMovies(Mockito.any(Genre.class))).thenReturn(List.of(
                new MovieDto(1, "movie-1", 1990, Genre.ACTION),
                new MovieDto(2, "movie-2", 1991, Genre.ACTION)
        ));

        var customerDto = this.restTestClient.get()
                                             .uri("/api/customers/1")
                                             .exchange()
                                             .expectStatus().is2xxSuccessful()
                                             .returnResult(CustomerDto.class)
                                             .getResponseBody();

        Assertions.assertNotNull(customerDto);
        Assertions.assertEquals("sam", customerDto.name());
        Assertions.assertEquals(2, customerDto.recommendedMovies().size());
    }

    @Test
    public void customerNotFound() {
        var problemDetail = this.restTestClient.get()
                                               .uri("/api/customers/10")
                                               .exchange()
                                               .expectStatus().is4xxClientError()
                                               .returnResult(ProblemDetail.class)
                                               .getResponseBody();

        log.info("problem detail: {}", problemDetail);
        Assertions.assertNotNull(problemDetail);
        Assertions.assertEquals("Customer Not Found", problemDetail.getTitle());
    }

    @Test
    public void updateGenre() {
        var genreUpdateRequest = new GenreUpdateRequest(Genre.DRAMA);
        this.restTestClient.patch()
                           .uri("/api/customers/1/genre")
                           .body(genreUpdateRequest)
                           .exchange()
                           .expectStatus().isNoContent() // 204
                           .expectBody().isEmpty();
    }

}
