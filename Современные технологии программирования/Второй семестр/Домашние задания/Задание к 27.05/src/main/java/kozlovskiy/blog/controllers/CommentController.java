package kozlovskiy.blog.controllers;

import kozlovskiy.blog.models.Comment;
import kozlovskiy.blog.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments/")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public ResponseEntity<List<Comment>> getComments() {
        List<Comment> body = commentService.getComments();

        return body != null && !body.isEmpty()
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(name = "id") Long id) {
        Comment body = commentService.getCommentById(id);

        return body != null
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createComment(@RequestBody Comment comment) {
        commentService.createComment(comment);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateComment(@RequestBody Comment comment, @PathVariable(name = "id") Long id) {
        return commentService.updateComment(comment, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteComment(@PathVariable(name = "id") Long id) {
        return commentService.deleteComment(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}