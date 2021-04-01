package kozlovskiy.project.services;

import kozlovskiy.project.models.Task;
import kozlovskiy.project.repo.TaskRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepo taskRepo;

    public List<Task> findAllTasks() {
        return taskRepo.findAll();
    }

    public Task findTaskById(Long id) {
        return taskRepo.findById(id).orElse(null);
    }

    public Task createTask(Task task) {
        return taskRepo.save(task);
    }

    public boolean deleteTaskById(Long id) {
        if (findTaskById(id) != null) {
            taskRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateTask(Task task, Long id) {
        if (findTaskById(id) != null) {
            task.setId(id);
            taskRepo.save(task);
            return true;
        }
        return false;
    }
}