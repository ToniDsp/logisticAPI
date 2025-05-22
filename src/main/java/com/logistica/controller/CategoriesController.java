package com.logistica.controller;

import com.logistica.data.model.Categories;
import com.logistica.domain.CategoriesService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Categories")
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @GetMapping("/")
    @Operation(summary = "Get all categories")
    public ResponseEntity<List<Categories>> getCategories() {
        var categories = categoriesService.findAll();
        return ResponseEntity.ok(categories);
    }

    @PostMapping
    @Operation(summary = "Create a new category")
    public ResponseEntity<Categories> createCategory(@Valid @RequestBody Categories categories) {
        return ResponseEntity.ok(categoriesService.save(categories));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a category")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        categoriesService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a category")
    public ResponseEntity<Categories> updateCategory(@PathVariable("id") Long id, @RequestBody Categories categories) {
        categories.setId(id);
        Categories update = categoriesService.save(categories);
        return ResponseEntity.ok(update);
    }
}
