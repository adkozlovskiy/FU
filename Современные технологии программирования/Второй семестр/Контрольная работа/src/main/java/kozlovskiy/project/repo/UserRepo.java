package kozlovskiy.project.repo;

import kozlovskiy.project.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
