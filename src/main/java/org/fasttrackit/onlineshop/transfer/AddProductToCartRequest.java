package org.fasttrackit.onlineshop.transfer;

import javax.validation.constraints.NotNull;

public class AddProductToCartRequest {

    @NotNull
    private Long userId;
    @NotNull
    private Long productId;


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    @Override
    public String toString() {
        return "AddProductToCartRequest{" +
                "userId=" + userId +
                ", productId=" + productId +
                '}';
    }
}
