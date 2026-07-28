package org.example.graphql_korzun.repository;

import org.example.graphql_korzun.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findByName(String name);
}
