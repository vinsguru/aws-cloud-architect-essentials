package com.vinsguru;

import com.vinsguru.domain.Genre;
import com.vinsguru.dto.MovieDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureRestTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.test.web.servlet.client.RestTestClient;

import java.util.List;

@Import(TestContainersConfiguration.class)
@AutoConfigureRestTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MovieServiceApplicationTests {

    private static final Logger log = LoggerFactory.getLogger(MovieServiceApplicationTests.class);

    @Autowired
    private RestTestClient testClient;

    @Test
    public void health() {
        this.testClient.get()
                       .uri("/actuator/health")
                       .exchange()
                       .expectStatus().is2xxSuccessful();
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

    private List<MovieDto> getMovies(String uri) {
        return this.testClient.get()
                              .uri(uri)
                              .exchange()
                              .expectStatus().is2xxSuccessful()
                              .expectBody(new ParameterizedTypeReference<List<MovieDto>>() {
                              })
                              .value(list -> log.info("response: {}", list))
                              .returnResult()
                              .getResponseBody();
    }

}
