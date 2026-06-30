package com.ecommerce.orderservice.dto;

import java.util.List;

public class StockReduceRequest {

    private List<StockReduceItemRequest> items;

    public StockReduceRequest() {
    }

    public List<StockReduceItemRequest> getItems() {
        return items;
    }

    public void setItems(List<StockReduceItemRequest> items) {
        this.items = items;
    }
}