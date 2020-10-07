package org.fasttrackit.onlineshop.transfer.cart;

public class CartResponse {

    private long id;



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "cartResponse{" +
                "id=" + id +
                '}';
    }
}
