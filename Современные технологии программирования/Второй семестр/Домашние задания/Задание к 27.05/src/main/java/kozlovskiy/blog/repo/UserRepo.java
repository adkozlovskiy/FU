package kozlovskiy.blog.repo;

import kozlovskiy.blog.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
}
