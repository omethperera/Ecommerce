package com.ecommerce.orderservice.feign;

import com.ecommerce.orderservice.dto.ProductResponse;
import com.ecommerce.orderservice.dto.StockReduceRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "product-service")
public interface ProductFeignClient {

    @GetMapping("/api/products/internal/{productId}")
    ProductResponse getProductById(@PathVariable("productId") Long productId);

    @PostMapping("/api/products/internal/stock/reduce")
    void reduceStock(@RequestBody StockReduceRequest request);
}