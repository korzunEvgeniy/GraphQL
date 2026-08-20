package org.example.graphql_korzun.controller;

import org.example.graphql_korzun.model.Category;
import org.example.graphql_korzun.model.Product;
import org.example.graphql_korzun.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
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

    @SchemaMapping(typeName = "Query", field = "product")
    public ProductOperations product() {
        return new ProductOperations();
    }

    @SchemaMapping(typeName = "Mutation", field = "product")
    public ProductOperations productMutation() {
        return new ProductOperations();
    }

    public class ProductOperations {

        @QueryMapping
        public List<Product> allProducts() {
            return productService.getAllProducts();
        }

        @QueryMapping
        public Product productById(@Argument UUID id) {
            return productService.getProductById(id);
        }

        @MutationMapping
        public Product addProduct(
                @Argument String name,
                @Argument BigDecimal price,
                @Argument Integer stock,
                @Argument UUID categoryId) {
            return productService.createProduct(name, price, stock, categoryId);
        }

        @MutationMapping
        public Product updateProduct(
                @Argument UUID id,
                @Argument String name,
                @Argument BigDecimal price,
                @Argument Integer stock,
                @Argument UUID categoryId) {
            return productService.updateProduct(id, name, price, stock, categoryId);
        }

        @MutationMapping
        public Boolean deleteProduct(@Argument UUID id) {
            productService.deleteProduct(id);
            return true;
        }
    }

    @BatchMapping(field = "category", typeName = "Product")
    public Map<Product, Category> category(List<Product> products) {
        return productService.getCategoriesForProducts(products);
    }
}
