package com.kozlovskiy.demo.service;

import com.kozlovskiy.demo.model.Blog;
import com.kozlovskiy.demo.repo.BlogRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private BlogRepo blogRepo;

    public List<Blog> getAllBlogs() {
        return blogRepo.findAll();
    }

    public Blog getBlogById(Long id) {
        return blogRepo.findById(id).orElse(null);
    }

    public Blog createBlog(Blog blog) {
        return blogRepo.save(blog);
    }

    public boolean deleteBlogById(Long id) {
        if (getBlogById(id) != null) {
            blogRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateBlog(Blog blog, Long id) {
        if (getBlogById(id) != null) {
            blog.setId(id);
            blogRepo.save(blog);
            return true;
        }
        return false;
    }
}
