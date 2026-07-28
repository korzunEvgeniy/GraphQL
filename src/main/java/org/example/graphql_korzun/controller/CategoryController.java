package org.example.graphql_korzun.controller;

import org.example.graphql_korzun.model.Category;
import org.example.graphql_korzun.service.CategoryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @QueryMapping(name = "categoryById")
    public Category getCategoryById(@Argument UUID id) {
        return categoryService.getCategoryById(id);
    }

    @QueryMapping(name = "allCategories")
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @MutationMapping(name = "addCategory")
    public Category createCategory(
            @Argument String name,
            @Argument String description) {
        return categoryService.createCategory(name, description);
    }

    @MutationMapping(name = "updateCategory")
    public Category updateCategory(
            @Argument UUID id,
            @Argument String name,
            @Argument String description) {
        return categoryService.updateCategory(id, name, description);
    }

    @MutationMapping(name = "deleteCategory")
    public Boolean deleteCategory(@Argument UUID id) {
        categoryService.deleteCategory(id);
        return true;
    }
}
