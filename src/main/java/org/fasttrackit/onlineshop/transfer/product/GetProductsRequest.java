package org.fasttrackit.onlineshop.transfer.product;

public class GetProductsRequest {

    private String partialName;
    private String partialDescription;
    private Integer minQuantity;
    private Double price;


    public String getPartialName() {
        return partialName;
    }

    public void setPartialName(String partialName) {
        this.partialName = partialName;
    }

    public String getPartialDescription() {
        return partialDescription;
    }

    public void setPartialDescription(String partialDescription) {
        this.partialDescription = partialDescription;
    }

    public Integer getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(Integer minQuantity) {
        this.minQuantity = minQuantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "GetProductsRequest{" +
                "partialName='" + partialName + '\'' +
                ", partialDescription='" + partialDescription + '\'' +
                ", minQuantity=" + minQuantity +
                ", price=" + price +
                '}';
    }
}
