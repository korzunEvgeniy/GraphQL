package org.example.graphql_korzun.service;

import org.example.graphql_korzun.model.Category;
import org.example.graphql_korzun.model.Product;
import org.example.graphql_korzun.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final static String PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE = "Product not found";
    private final static String PRODUCT_EXIST_EXCEPTION_MESSAGE = "Product with this name already exists";

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public ProductService(ProductRepository productRepository, CategoryService categoryService) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(UUID id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(PRODUCT_NOT_FOUND_EXCEPTION_MESSAGE));
    }

    public Product createProduct(String name, BigDecimal price, Integer stock, UUID categoryId) {
        productRepository.findByName(name).ifPresent(
                p -> {
                    throw new RuntimeException(PRODUCT_EXIST_EXCEPTION_MESSAGE);
                }
        );

        var category = categoryService.getCategoryById(categoryId);

        return productRepository.save(
                Product.builder()
                        .name(name)
                        .price(price)
                        .stock(stock)
                        .category(category)
                        .build()
        );
    }

    public Product updateProduct(UUID id, String name, BigDecimal price, Integer stock, UUID categoryId) {
        var product = getProductById(id);
        product.setName(name);
        product.setPrice(price);
        product.setStock(stock);

        var category = categoryService.getCategoryById(categoryId);
        product.setCategory(category);

        return productRepository.save(product);
    }

    public void deleteProduct(UUID id) {
        productRepository.deleteById(id);
    }

    public Map<Product, Category> getCategoriesForProducts(List<Product> products) {
        var categoryIds = products.stream()
                .map(p -> p.getCategory().getId())
                .distinct()
                .toList();

        var categoryMap = categoryService.getAllCategoriesByIds(categoryIds).stream()
                .collect(Collectors.toMap(Category::getId, Function.identity()));

        return products.stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        product -> categoryMap.get(product.getCategory().getId())
                ));
    }
}
