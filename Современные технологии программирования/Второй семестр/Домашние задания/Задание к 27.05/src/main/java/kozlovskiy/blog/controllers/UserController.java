package kozlovskiy.blog.controllers;

import kozlovskiy.blog.models.User;
import kozlovskiy.blog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> body = userService.getUsers();

        return body != null && !body.isEmpty()
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        User body = userService.getUserById(id);

        return body != null
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.createUser(user);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable(name = "id") Long id) {
        return userService.updateUser(user, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long id) {
        return userService.deleteUser(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
