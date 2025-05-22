package com.logistica.controller;

import com.logistica.controller.contract.ProviderResponse;
import com.logistica.data.model.Provider;
import com.logistica.domain.ProviderService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Providers")

public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping("/")
    @Operation(summary = "Get all providers")
    public ResponseEntity<List<ProviderResponse>> getProviders() {
        var providers = providerService.findAll();
        return ResponseEntity.ok(providers);
    }

    @PostMapping("/")
    @Operation(summary = "Create a new provider")
    public ResponseEntity<Provider> createProvider(@Valid @RequestBody Provider provider) {
        return ResponseEntity.ok(providerService.save(provider));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a provider")
    public ResponseEntity<Void> deleteProvider(@PathVariable("id") Long id) {
        providerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a provider")
    public ResponseEntity<Provider> updateProvider(@PathVariable("id") Long id, @RequestBody Provider provider) {
        provider.setId(id);
        Provider update = providerService.save(provider);
        return ResponseEntity.ok(update);
    }


}
