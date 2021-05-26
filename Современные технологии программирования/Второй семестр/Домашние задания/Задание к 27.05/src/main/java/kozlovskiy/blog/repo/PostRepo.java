package kozlovskiy.blog.repo;

import kozlovskiy.blog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
