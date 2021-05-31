package kozlovskiy.prod.service;

import kozlovskiy.prod.entities.Menu;
import kozlovskiy.prod.repo.BarRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BarServiceTest {

    @Autowired
    private BarService barService;

    @MockBean
    private BarRepo barRepo;

    @Test
    public void getMenu() {
        Menu menu1 = new Menu();
        Menu menu2 = new Menu();
        Menu menu3 = new Menu();

        List<Menu> expected = List.of(menu1, menu2, menu3);

        doReturn(expected)
                .when(barRepo)
                .findAll(Sort.by(Sort.Direction.ASC, "price"));

        List<Menu> actual = barService.getMenu();
        Assert.assertEquals(expected, actual);
    }
}