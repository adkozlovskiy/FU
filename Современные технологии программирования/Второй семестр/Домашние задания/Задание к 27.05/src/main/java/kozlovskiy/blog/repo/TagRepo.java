package kozlovskiy.blog.repo;

import kozlovskiy.blog.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepo extends JpaRepository<Tag, Long> {
}
