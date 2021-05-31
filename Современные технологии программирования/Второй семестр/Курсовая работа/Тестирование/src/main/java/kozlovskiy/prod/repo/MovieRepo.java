package kozlovskiy.prod.repo;

import kozlovskiy.prod.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepo extends JpaRepository<Movie, Long> {

}
