package kozlovskiy.blog.controllers;

import kozlovskiy.blog.models.Tag;
import kozlovskiy.blog.services.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags/")
public class TagController {

    private TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public ResponseEntity<List<Tag>> getTags() {
        List<Tag> body = tagService.getTags();

        return body != null && !body.isEmpty()
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("{id}")
    public ResponseEntity<Tag> getTagById(@PathVariable(name = "id") Long id) {
        Tag body = tagService.getTagById(id);

        return body != null
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public void createTag(@RequestBody Tag tag) {
        tagService.createTag(tag);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateTag(@RequestBody Tag tag, @PathVariable(name = "id") Long id) {
        return tagService.updateTag(tag, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteTag(@PathVariable(name = "id") Long id) {
        return tagService.deleteTag(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
