package kozlovskiy.prod.service;

import kozlovskiy.prod.entities.Ticket;
import kozlovskiy.prod.repo.TicketRepo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.doReturn;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TicketServiceTest {

    @Autowired
    private TicketService ticketService;

    @MockBean
    private TicketRepo ticketRepo;

    @Test
    public void getTicketsByUserId() {
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        Ticket ticket3 = new Ticket();

        List<Ticket> expected = List.of(ticket1, ticket2, ticket3);

        doReturn(expected)
                .when(ticketRepo)
                .findTicketsByUserId(1L);

        List<Ticket> actual = ticketService.getTicketsByUserId(1L);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTicketsBySessionId() {
        Ticket ticket1 = new Ticket();
        Ticket ticket2 = new Ticket();
        Ticket ticket3 = new Ticket();

        List<Ticket> expected = List.of(ticket1, ticket2, ticket3);

        doReturn(expected)
                .when(ticketRepo)
                .findTicketsBySessionId(1L);

        List<Ticket> actual = ticketService.getTicketsBySessionId(1L);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addTicket() {
        Ticket given = new Ticket();
        Ticket expected = new Ticket();
        expected.setId(1L);

        Mockito.doReturn(expected)
                .when(ticketRepo)
                .save(given);

        Ticket actual = ticketService.addTicket(given);
        Assert.assertEquals(expected, actual);
    }
}