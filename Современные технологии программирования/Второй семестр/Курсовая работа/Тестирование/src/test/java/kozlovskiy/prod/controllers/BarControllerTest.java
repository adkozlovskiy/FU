package kozlovskiy.prod.controllers;

import kozlovskiy.prod.entities.Menu;
import kozlovskiy.prod.repo.BarRepo;
import kozlovskiy.prod.service.BarService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BarController.class)
class BarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BarRepo barRepo;

    @MockBean
    private BarService barService;

    @Test
    void getMenu() throws Exception {
        Menu menu = new Menu();
        List<Menu> expected = List.of(menu);

        doReturn(expected)
                .when(barService)
                .getMenu();

        mockMvc.perform(get("/api/menu"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}