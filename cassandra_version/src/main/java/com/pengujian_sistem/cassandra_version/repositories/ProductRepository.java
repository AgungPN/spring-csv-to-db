package com.pengujian_sistem.cassandra_version.repositories;


import com.pengujian_sistem.cassandra_version.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends CrudRepository<Product, UUID> {
    Optional<Object> findById(Integer productId);
}
