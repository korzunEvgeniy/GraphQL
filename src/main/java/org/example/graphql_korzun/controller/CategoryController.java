package org.example.graphql_korzun.controller;

import org.example.graphql_korzun.model.Category;
import org.example.graphql_korzun.service.CategoryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @SchemaMapping(typeName = "Query", field = "category")
    public CategoryOperations category() {
        return new CategoryOperations();
    }

    @SchemaMapping(typeName = "Mutation", field = "category")
    public CategoryOperations categoryMutation() {
        return new CategoryOperations();
    }

    public class CategoryOperations {

        @QueryMapping
        public List<Category> allCategories() {
            return categoryService.getAllCategories();
        }

        @QueryMapping
        public Category categoryById(@Argument UUID id) {
            return categoryService.getCategoryById(id);
        }

        @MutationMapping
        public Category addCategory(@Argument String name, @Argument String description) {
            return categoryService.createCategory(name, description);
        }

        @MutationMapping
        public Category updateCategory(@Argument UUID id, @Argument String name, @Argument String description) {
            return categoryService.updateCategory(id, name, description);
        }

        @MutationMapping
        public Boolean deleteCategory(@Argument UUID id) {
            categoryService.deleteCategory(id);
            return true;
        }
    }
}
