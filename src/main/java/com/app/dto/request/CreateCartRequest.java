package com.app.dto.request;

public class CreateCartRequest {
    private String productId;
    private String userName;

    public CreateCartRequest(String productId, String userName) {
        this.productId = productId;
        this.userName = userName;
    }

    public CreateCartRequest() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "CreateCartRequest{" +
                "productId='" + productId + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}
