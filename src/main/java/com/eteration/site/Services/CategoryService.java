package com.eteration.site.Services;

import com.eteration.site.Model.Category;
import com.eteration.site.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public void save(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> findAll() {
      return categoryRepository.findByParentIsNull();
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
