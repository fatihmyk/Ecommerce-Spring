package com.eteration.site.Repository;

import com.eteration.site.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    List<Category> findByParentIsNull();
    Category findByName(String name);
}
