package com.eteration.site.Repository;


import com.eteration.site.Model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByCategoryId(Long id);

    @Query("select p from Product p where name like %?1% or description like %?1%")
    List<Product> findByName(String name);

    @Query("select p from Product p")
    Page<Product> findAll(Pageable pageable);



}
