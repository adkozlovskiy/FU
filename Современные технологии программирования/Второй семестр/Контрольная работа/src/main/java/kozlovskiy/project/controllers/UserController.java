package kozlovskiy.project.controllers;

import kozlovskiy.project.models.User;
import kozlovskiy.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/users")
    private ResponseEntity<List<User>> processAllUsersRequest() {
        List<User> body = userService.findAllUsers();
        return body != null && !body.isEmpty()
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/users/{id}")
    private ResponseEntity<User> processUserRequest(@PathVariable(name = "id") Long id) {
        User body = userService.findUserById(id);
        return body != null
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/users")
    private ResponseEntity<User> processPostUserRequest(@RequestBody User user) {
        User body = userService.createUser(user);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PutMapping(value = "/users/{id}")
    private ResponseEntity<?> processPutUserRequest(@RequestBody User user, @PathVariable(name = "id") Long id) {
        return userService.updateUser(user, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/users/{id}")
    private ResponseEntity<?> processDeleteUserRequest(@PathVariable(name = "id") Long id) {
        return userService.deleteUserById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
