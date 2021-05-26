package kozlovskiy.blog.controllers;

import kozlovskiy.blog.models.Post;
import kozlovskiy.blog.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts/")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts() {
        List<Post> body = postService.getPosts();

        return body != null && !body.isEmpty()
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPostById(@PathVariable(name = "id") Long id) {
        Post body = postService.getPostById(id);

        return body != null
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createPost(@RequestBody Post post) {
        postService.createPost(post);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updatePost(@RequestBody Post post, @PathVariable(name = "id") Long id) {
        return postService.updatePost(post, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deletePost(@PathVariable(name = "id") Long id) {
        return postService.deletePost(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}