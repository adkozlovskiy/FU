package kozlovskiy.prod.controllers;

import kozlovskiy.prod.entities.Movie;
import kozlovskiy.prod.entities.Session;
import kozlovskiy.prod.repo.SessionRepo;
import kozlovskiy.prod.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sessions")
public class SessionsController {

    @Autowired
    private SessionService service;

    /**
     * @param movieId is id of {@link Movie}.
     * @return List of {@link Session} found by this movieId in {@link SessionRepo}
     */
    @GetMapping("/on_movie")
    @ResponseBody
    public List<Session> getSessionsByMovieId(@RequestParam("movie_id") Long movieId) {
        return service.getSessionsByMovieId(movieId);
    }

    /**
     * @param sessionId is id of {@link Session}.
     * @return OK and {@link Session} if found in {@link SessionRepo}, else - NOT_FOUND.
     * It's used to find out if a session is currently valid.
     */
    @GetMapping
    @ResponseBody
    public ResponseEntity<Session> getSession(@RequestParam("session_id") Long sessionId) {
        Session body = service.getSession(sessionId);
        return body != null
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
