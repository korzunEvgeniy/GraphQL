package org.example.graphql_korzun.controller;

import org.example.graphql_korzun.model.Category;
import org.example.graphql_korzun.model.Product;
import org.example.graphql_korzun.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping(name = "allProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @QueryMapping(name = "productById")
    public Product getProductById(@Argument UUID id) {
        return productService.getProductById(id);
    }

    @MutationMapping(name = "addProduct")
    public Product createProduct(
            @Argument String name,
            @Argument BigDecimal price,
            @Argument Integer stock,
            @Argument UUID categoryId) {
        return productService.createProduct(name, price, stock, categoryId);
    }

    @MutationMapping(name = "updateProduct")
    public Product updateProduct(
            @Argument UUID id,
            @Argument String name,
            @Argument BigDecimal price,
            @Argument Integer stock,
            @Argument UUID categoryId) {
        return productService.updateProduct(id, name, price, stock, categoryId);
    }

    @MutationMapping(name = "deleteProduct")
    public Boolean deleteProduct(@Argument UUID id) {
        productService.deleteProduct(id);
        return true;
    }

    @BatchMapping(field = "category", typeName = "Product")
    public Map<Product, Category> category(List<Product> products) {
        return productService.getCategoriesForProducts(products);
    }
}
