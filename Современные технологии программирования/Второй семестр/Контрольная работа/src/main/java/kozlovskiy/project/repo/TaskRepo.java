package kozlovskiy.project.repo;

import kozlovskiy.project.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepo extends JpaRepository<Task, Long> {
}
