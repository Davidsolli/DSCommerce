package com.davidsolli.dscommerce.services;

import com.davidsolli.dscommerce.DTO.ProductDTO;
import com.davidsolli.dscommerce.entities.Product;
import com.davidsolli.dscommerce.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).get();
        return new ProductDTO(product);
    }
}