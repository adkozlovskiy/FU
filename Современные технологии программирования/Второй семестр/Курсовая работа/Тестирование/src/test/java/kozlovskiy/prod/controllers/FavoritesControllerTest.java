package kozlovskiy.prod.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import kozlovskiy.prod.entities.Favorite;
import kozlovskiy.prod.repo.FavoriteRepo;
import kozlovskiy.prod.service.FavoriteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(FavoritesController.class)
class FavoritesControllerTest {

    @MockBean
    private FavoriteService favoriteService;

    @MockBean
    private FavoriteRepo favoriteRepo;

    @Autowired
    private MockMvc mockMvc;

    private static String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void postUserFavorite() throws Exception {
        Date current = new Date();

        Favorite given = new Favorite();
        given.setAddDate(current);

        mockMvc.perform(post("/api/favorites")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(given)))
                .andExpect(status().isCreated())
                .andReturn();
    }
}