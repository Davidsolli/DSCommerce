package com.davidsolli.dscommerce.repositories;

import com.davidsolli.dscommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
