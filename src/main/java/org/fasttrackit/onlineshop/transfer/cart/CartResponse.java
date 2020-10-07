package org.fasttrackit.onlineshop.transfer.cart;

import java.util.HashSet;
import java.util.Set;

public class CartResponse {

    private long id;
    private Set<ProductInCart> products;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<ProductInCart> getProducts() {
        return products;
    }

    public void setProducts(Set<ProductInCart> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "CartResponse{" +
                "id=" + id +
                ", products=" + products +
                '}';
    }
}
