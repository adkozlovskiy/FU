package kozlovskiy.prod.repo;

import kozlovskiy.prod.entities.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SessionRepo extends JpaRepository<Session, Long> {

    /**
     * @param id the movie id
     * @return List of {@link Session} WHERE movie_id = id AND session_date > current date
     */
    @Query(value = "SELECT * FROM sessions WHERE movie_id = :movieId AND session_date > CURRENT_TIMESTAMP() ORDER BY session_date", nativeQuery = true)
    List<Session> findByMovieId(@Param("movieId") Long id);

    /**
     * @param id the session id
     * @return {@link Session} WHERE session_id = id AND session_date > current date
     * Used to figure out if the session is available.
     */
    @Query(value = "SELECT * FROM sessions WHERE id = :session_id AND session_date > CURRENT_TIMESTAMP()", nativeQuery = true)
    Session getSession(@Param("session_id") Long id);
}