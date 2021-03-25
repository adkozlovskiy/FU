package com.kozlovskiy.demo.repo;

import com.kozlovskiy.demo.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepo extends JpaRepository<Blog, Long> {
}
