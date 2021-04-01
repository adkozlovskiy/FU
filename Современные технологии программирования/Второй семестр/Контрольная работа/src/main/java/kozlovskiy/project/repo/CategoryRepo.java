package kozlovskiy.project.repo;

import kozlovskiy.project.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}
