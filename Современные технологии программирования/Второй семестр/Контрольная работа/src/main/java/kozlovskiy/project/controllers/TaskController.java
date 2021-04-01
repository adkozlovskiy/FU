package kozlovskiy.project.controllers;

import kozlovskiy.project.models.Task;
import kozlovskiy.project.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TaskController {

    @Autowired
    TaskService taskService;

    @GetMapping(value = "tasks")
    private ResponseEntity<List<Task>> processAllTasksRequest() {
        List<Task> body = taskService.findAllTasks();
        return body != null && !body.isEmpty()
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/api/tasks/{id}")
    private ResponseEntity<Task> processTaskRequest(@PathVariable(name = "id") Long id) {
        Task body = taskService.findTaskById(id);
        return body == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping(value = "/api/tasks")
    private ResponseEntity<Task> processPostTaskRequest(@RequestBody Task task) {
        Task body = taskService.createTask(task);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PutMapping(value = "/api/tasks/{id}")
    private ResponseEntity<?> processPutTaskRequest(@RequestBody Task task, @PathVariable(name = "id") Long id) {
        return taskService.updateTask(task, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping(value = "/api/tasks/{id}")
    private ResponseEntity<?> processDeleteTaskRequest(@PathVariable(name = "id") Long id) {
        return taskService.deleteTaskById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
