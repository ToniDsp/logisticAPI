package com.logistica.domain;

import com.logistica.data.model.Categories;
import com.logistica.data.repository.CategoriesRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoriesService {
    @Autowired
    CategoriesRepository categoriesRepository;

    public List<Categories> findAll() {
        val iterable = categoriesRepository.findAll();
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Categories> findById(Long id) {
        return categoriesRepository.findById(id);
    }

    public Categories save(Categories categories) {
        return categoriesRepository.save(categories);
    }

    public void delete(Long id) {
        categoriesRepository.deleteById(id);
    }

}
