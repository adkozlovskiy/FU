package kozlovskiy.prod.controllers;

import kozlovskiy.prod.entities.User;
import kozlovskiy.prod.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class AuthController {

    @Autowired
    private AuthService service;

    /**
     * @param userId is {@link User} id.
     * @return data about the {@link User} by his id.
     * Password and salt are nullified.
     */
    @GetMapping("users")
    public ResponseEntity<User> getUserData(@RequestParam("user_id") Long userId) {
        User body = service.getUserData(userId);

        if (body != null) {
            body.setSalt(null);
            body.setPassword(null);
            return new ResponseEntity<>(body, HttpStatus.OK);

        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * @param user is {@link User} data from register form.
     * @return OK if user successful created, else - NO_CONTENT.
     */
    @PostMapping("reg")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        User body = service.registerUser(user);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }


    /**
     * @param user is {@link User} data from auth form.
     * @return OK if user successful authorized, else - NOT_FOUND.
     */
    @PostMapping("auth")
    public ResponseEntity<User> authorizeUser(@RequestBody User user) {
        User body = service.authorizeUser(user);

        return new ResponseEntity<>(body, HttpStatus.OK);
    }
}
