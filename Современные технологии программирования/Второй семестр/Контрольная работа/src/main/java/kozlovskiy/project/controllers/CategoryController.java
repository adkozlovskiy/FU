package kozlovskiy.project.controllers;


import kozlovskiy.project.models.Category;
import kozlovskiy.project.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping(value = "/categories")
    private ResponseEntity<List<Category>> processAllCategoriesRequest() {
        List<Category> body = categoryService.findAllCategories();
        return body != null && !body.isEmpty()
                ? new ResponseEntity<>(body, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/categories/{id}")
    private ResponseEntity<Category> processCategoryRequest(@PathVariable(name = "id") Long id) {
        Category body = categoryService.findCategoryById(id);
        return body == null
                ? new ResponseEntity<>(HttpStatus.NOT_FOUND)
                : new ResponseEntity<>(body, HttpStatus.OK);
    }

    @PostMapping(value = "/categories")
    private ResponseEntity<Category> processPostCategoryRequest(@RequestBody Category category) {
        Category body = categoryService.createCategory(category);
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

    @PutMapping(value = "/categories/{id}")
    private ResponseEntity<?> processPutCategoryRequest(@RequestBody Category category, @PathVariable(name = "id") Long id) {
        return categoryService.updateCategory(category, id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/categories/{id}")
    private ResponseEntity<?> processDeleteCategoryRequest(@PathVariable(name = "id") Long id) {
        return categoryService.deleteById(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

