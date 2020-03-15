package com.eteration.site.Services;

import com.eteration.site.Model.Product;
import com.eteration.site.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }

    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> findByCategoryId(Long id) {
        return productRepository.findByCategoryId(id);
    }
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByName(String name){
        return productRepository.findByName(name);
    }
}
