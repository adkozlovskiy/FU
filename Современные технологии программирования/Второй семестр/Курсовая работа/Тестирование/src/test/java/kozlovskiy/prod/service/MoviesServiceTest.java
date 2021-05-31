package kozlovskiy.prod.service;

import kozlovskiy.prod.entities.Movie;
import kozlovskiy.prod.repo.MovieRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MoviesServiceTest {

    @Autowired
    private MoviesService moviesService;

    @MockBean
    private MovieRepo movieRepo;

    @Test
    public void getMovies() {
        Movie movie1 = new Movie();
        Movie movie2 = new Movie();
        Movie movie3 = new Movie();

        List<Movie> expected = List.of(movie1, movie2, movie3);

        doReturn(expected)
                .when(movieRepo)
                .findAll();

        List<Movie> actual = moviesService.getMovies();
        Assert.assertEquals(expected, actual);
    }
}