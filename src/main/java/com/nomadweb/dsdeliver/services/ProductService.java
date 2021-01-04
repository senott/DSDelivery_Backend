package com.nomadweb.dsdeliver.services;

import com.nomadweb.dsdeliver.dtos.ProductDTO;
import com.nomadweb.dsdeliver.entities.Product;
import com.nomadweb.dsdeliver.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    public List<ProductDTO> findAll() {
        List<Product> productList = productRepository.findAllByOrderByNameAsc();

        return productList.stream().map(ProductDTO::new).collect(Collectors.toList());
    }
}
