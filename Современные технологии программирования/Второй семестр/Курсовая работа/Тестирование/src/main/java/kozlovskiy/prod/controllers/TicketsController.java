package kozlovskiy.prod.controllers;

import kozlovskiy.prod.entities.Session;
import kozlovskiy.prod.entities.Ticket;
import kozlovskiy.prod.entities.User;
import kozlovskiy.prod.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/tickets")
public class TicketsController {

    @Autowired
    private TicketService service;

    /**
     * @param userId is {@link User} id.
     * @return OK with List of {@link Ticket} where user_id = userId, else - NOT_FOUND.
     */
    @GetMapping("/user_tickets")
    @ResponseBody
    public ResponseEntity<List<Ticket>> getTicketsByUserId(@RequestParam("user_id") Long userId) {
        List<Ticket> tickets = service.getTicketsByUserId(userId);
        return tickets != null && !tickets.isEmpty()
                ? new ResponseEntity<>(tickets, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * @param sessionId is {@link Session} id.
     * @return OK and List of {@link Ticket} where session_id = sessionId, else - NOT_FOUND.
     */
    @GetMapping("/session_tickets")
    @ResponseBody
    public ResponseEntity<List<Ticket>> getTicketsBySessionId(@RequestParam("session_id") Long sessionId) {
        List<Ticket> tickets = service.getTicketsBySessionId(sessionId);
        return tickets != null && !tickets.isEmpty()
                ? new ResponseEntity<>(tickets, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
