package com.kozlovskiy.demo.controller;

import com.kozlovskiy.demo.model.Blog;
import com.kozlovskiy.demo.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    @Autowired
    BlogService blogService;

    @GetMapping(value = "/blogs")
    private ResponseEntity<List<Blog>> processAllBlogsRequest() {
        List<Blog> body = blogService.getAllBlogs();
        if (body != null && !body.isEmpty()) {
            return new ResponseEntity<>(body, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/blogs/{id}")
    private ResponseEntity<Blog> processBlogRequest(@PathVariable(name = "id") Long id) {
        Blog body = blogService.getBlogById(id);

        if (body != null) {
            return new ResponseEntity<>(body, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/blogs")
    private ResponseEntity<Blog> processPostBlogRequest(@RequestBody Blog blog) {
        Blog body = blogService.createBlog(blog);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PutMapping(value = "/blogs/{id}")
    private ResponseEntity<?> processPutBlogRequest(@RequestBody Blog blog, @PathVariable(name = "id") Long id) {
        if (blogService.updateBlog(blog, id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/blogs/{id}")
    private ResponseEntity<?> processDeleteBlogRequest(@PathVariable(name = "id") Long id) {
        if (blogService.deleteBlogById(id))
            return new ResponseEntity<>(HttpStatus.OK);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
