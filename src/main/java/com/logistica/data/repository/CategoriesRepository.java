package com.logistica.data.repository;

import com.logistica.data.model.Categories;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Categories, Long> {
}
