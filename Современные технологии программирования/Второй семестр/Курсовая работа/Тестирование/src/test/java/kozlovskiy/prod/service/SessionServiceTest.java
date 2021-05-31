package kozlovskiy.prod.service;

import kozlovskiy.prod.entities.Session;
import kozlovskiy.prod.repo.SessionRepo;
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
public class SessionServiceTest {

    @Autowired
    private SessionService sessionService;

    @MockBean
    private SessionRepo sessionRepo;

    @Test
    public void getSessionsByMovieId() {
        Session session1 = new Session();
        Session session2 = new Session();
        Session session3 = new Session();

        List<Session> expected = List.of(session1, session2, session3);

        doReturn(expected)
                .when(sessionRepo)
                .findByMovieId(1L);

        List<Session> actual = sessionService.getSessionsByMovieId(1L);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getSession() {
        Session expected = new Session();
        expected.setId(1L);

        doReturn(expected)
                .when(sessionRepo)
                .getSession(1L);

        Session actual = sessionService.getSession(1L);
        Assert.assertEquals(expected, actual);
    }
}