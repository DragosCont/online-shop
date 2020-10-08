package org.fasttrackit.onlineshop.persistance;

import org.fasttrackit.onlineshop.domain.ProductReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductReviewRepository  extends JpaRepository<ProductReview, Long> {

    //query by nested property
    Page<ProductReview> findByProductId(long productId, Pageable pageable);
}
