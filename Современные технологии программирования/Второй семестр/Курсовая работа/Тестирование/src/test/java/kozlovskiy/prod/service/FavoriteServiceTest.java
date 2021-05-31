package kozlovskiy.prod.service;

import kozlovskiy.prod.entities.Favorite;
import kozlovskiy.prod.repo.FavoriteRepo;
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
public class FavoriteServiceTest {

    @Autowired
    private FavoriteService favoriteService;

    @MockBean
    private FavoriteRepo favoriteRepo;

    @Test
    public void getUserFavorites() {
        Favorite favorite1 = new Favorite();
        Favorite favorite2 = new Favorite();

        List<Favorite> expected = List.of(favorite1, favorite2);

        doReturn(expected)
                .when(favoriteRepo)
                .findByUserId(1L);

        List<Favorite> actual = favoriteService.getUserFavorites(1L);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void postUserFavorite() {
        Favorite given = new Favorite();
        Favorite expected = new Favorite();

        doReturn(expected)
                .when(favoriteRepo)
                .save(given);

        Favorite actual = favoriteService.addFavorite(given);
        Assert.assertEquals(expected, actual);
    }
}