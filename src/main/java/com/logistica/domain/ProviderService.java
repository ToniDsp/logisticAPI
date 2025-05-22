package com.logistica.domain;

import com.logistica.controller.contract.ProviderResponse;
import com.logistica.data.model.Product;
import com.logistica.data.model.Provider;
import com.logistica.data.repository.ProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@AllArgsConstructor
public class ProviderService {
    ProviderRepository providerRepository;

    public List<ProviderResponse> findAll() {
        var iterable = providerRepository.findAll();
        return StreamSupport
                .stream(iterable.spliterator(), false)
                .map(this::convert)
                .collect(Collectors.toList());
    }


    public Optional<Provider> findById(Long id) {
        return providerRepository.findById(id);
    }

    private ProviderResponse convert(Provider provider) {
        List<Long> productIds = provider.getProduct()
                .stream()
                .map(Product::getId)
                .collect(Collectors.toList());
        return new ProviderResponse(provider.getId(), provider.getName(), provider.getContact(), productIds);
    }

    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

    public void delete(long id) {
        providerRepository.deleteById(id);
    }
}
