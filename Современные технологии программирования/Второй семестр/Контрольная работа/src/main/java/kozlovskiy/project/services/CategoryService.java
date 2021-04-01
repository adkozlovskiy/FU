package kozlovskiy.project.services;

import kozlovskiy.project.models.Category;
import kozlovskiy.project.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    public Category findCategoryById(Long id) {
        return categoryRepo.findById(id).orElse(null);
    }

    public Category createCategory(Category category) {
        return categoryRepo.save(category);
    }

    public boolean deleteById(Long id) {
        if (findCategoryById(id) != null) {
            categoryRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateCategory(Category category, Long id) {
        if (findCategoryById(id) != null) {
            category.setId(id);
            categoryRepo.save(category);
            return true;
        }
        return false;
    }
}
