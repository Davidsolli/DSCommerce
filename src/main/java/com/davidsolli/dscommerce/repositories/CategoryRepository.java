package com.davidsolli.dscommerce.repositories;

import com.davidsolli.dscommerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
