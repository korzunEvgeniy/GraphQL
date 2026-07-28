package org.example.graphql_korzun.service;

import org.example.graphql_korzun.model.Category;
import org.example.graphql_korzun.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryService {

    private final static String CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE = "Category not found";
    private final static String CATEGORY_EXIST_EXCEPTION_MESSAGE = "Category with this name already exists";

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public List<Category> getAllCategoriesByIds(List<UUID> ids) {
        return categoryRepository.findAllById(ids);
    }

    public Category getCategoryById(UUID id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(CATEGORY_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    public Category createCategory(String name, String description) {
        categoryRepository.findByName(name).ifPresent(
                c -> {
                    throw new RuntimeException(CATEGORY_EXIST_EXCEPTION_MESSAGE);
                }
        );

        return categoryRepository.save(
                Category.builder()
                        .name(name)
                        .description(description)
                        .build()
        );
    }

    public Category updateCategory(UUID id, String name, String description) {
        var category = getCategoryById(id);
        category.setName(name);
        category.setDescription(description);
        return categoryRepository.save(category);
    }

    public void deleteCategory(UUID id) {
        categoryRepository.deleteById(id);
    }
}
