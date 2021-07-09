package com.app.dto.request;

public class BuyOrderRequest {
    private Long id;
    private String address;
    private String description;

    public BuyOrderRequest(Long id, String address, String description) {
        this.id = id;
        this.address = address;
        this.description = description;
    }

    public BuyOrderRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
