package kozlovskiy.blog.repo;

import kozlovskiy.blog.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
