package kozlovskiy.prod.controllers;

import kozlovskiy.prod.entities.Ticket;
import kozlovskiy.prod.repo.TicketRepo;
import kozlovskiy.prod.service.TicketService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TicketsController.class)
public class TicketsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TicketRepo ticketRepo;

    @MockBean
    private TicketService ticketService;

    @Test
    public void getTicketsByUserId() throws Exception {
        Ticket ticket = new Ticket();
        List<Ticket> expected = List.of(ticket);

        Mockito.doReturn(expected)
                .when(ticketService)
                .getTicketsByUserId(1L);

        mockMvc.perform(get("/api/tickets/user_tickets")
                .param("user_id", "1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getTicketsBySessionId() throws Exception {
        Ticket ticket = new Ticket();
        List<Ticket> expected = List.of(ticket);

        Mockito.doReturn(expected)
                .when(ticketService)
                .getTicketsBySessionId(1L);

        mockMvc.perform(get("/api/tickets/session_tickets")
                .param("session_id", "1"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void getTicketsByUserIdFailed() throws Exception {
        Ticket ticket = new Ticket();
        List<Ticket> expected = List.of(ticket);

        Mockito.doReturn(expected)
                .when(ticketService)
                .getTicketsBySessionId(1L);

        mockMvc.perform(get("/api/tickets/user_tickets")
                .param("user_id", "2"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}