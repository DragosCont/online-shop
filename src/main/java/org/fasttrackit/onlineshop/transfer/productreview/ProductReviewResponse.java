package org.fasttrackit.onlineshop.transfer.productreview;

public class ProductReviewResponse {

    private long id;
    private String content;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ProductReviewResponse{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
