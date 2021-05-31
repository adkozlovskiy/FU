package kozlovskiy.prod.repo;

import kozlovskiy.prod.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepo extends JpaRepository<Ticket, Long> {

    /**
     * @param id the user id
     * @return List of {@link Ticket} WHERE user_id = id by descending buy date
     */
    @Query(value = "SELECT * FROM tickets WHERE user_id = :user_id ORDER BY buy_date DESC", nativeQuery = true)
    List<Ticket> findTicketsByUserId(@Param("user_id") Long id);

    /**
     * @param id the session id
     * @return List of {@link Ticket} WHERE session_id = id
     * Used to get all tickets on this session and calculate free seats.
     */
    @Query(value = "SELECT * FROM tickets WHERE session_id = :session_id", nativeQuery = true)
    List<Ticket> findTicketsBySessionId(@Param("session_id") Long id);

}
