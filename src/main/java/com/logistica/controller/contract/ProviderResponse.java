package com.logistica.controller.contract;

import java.util.List;

public record ProviderResponse(Long id, String name, String contacto, List<Long> productIds) {
}