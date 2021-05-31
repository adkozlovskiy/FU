package kozlovskiy.prod.repo;

import kozlovskiy.prod.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarRepo extends JpaRepository<Menu, Long> {

}
