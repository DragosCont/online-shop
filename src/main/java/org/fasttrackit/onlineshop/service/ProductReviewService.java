package org.fasttrackit.onlineshop.service;


import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.domain.ProductReview;
import org.fasttrackit.onlineshop.persistance.ProductReviewRepository;
import org.fasttrackit.onlineshop.transfer.productreview.ProductReviewResponse;
import org.fasttrackit.onlineshop.transfer.productreview.SaveProductReviewRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductReviewService.class);

    private final ProductReviewRepository productReviewRepository;

    private final ProductService productService;

    @Autowired
    public ProductReviewService(ProductReviewRepository productReviewRepository, ProductService productService) {
        this.productReviewRepository = productReviewRepository;
        this.productService = productService;
    }

    @Transactional
    public ProductReviewResponse createReview(SaveProductReviewRequest request) {
        LOGGER.info("Creating product review: {}", request);

        Product product = productService.getProduct(request.getProductId());

        ProductReview review = new ProductReview();
        review.setContent(request.getContent());
        review.setProduct(product);

        ProductReview savedReview = productReviewRepository.save(review);

        return  mapProductReviewResponse(savedReview);

    }

    private ProductReviewResponse mapProductReviewResponse(ProductReview review) {
        ProductReviewResponse response = new ProductReviewResponse();
        response.setId(review.getId());
        response.setContent(review.getContent());

        return response;
    }
}
