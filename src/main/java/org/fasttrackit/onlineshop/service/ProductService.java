package org.fasttrackit.onlineshop.service;


import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.exception.ResourceNotFoundException;
import org.fasttrackit.onlineshop.persistance.ProductRepository;
import org.fasttrackit.onlineshop.transfer.product.GetProductsRequest;
import org.fasttrackit.onlineshop.transfer.product.ProductResponse;
import org.fasttrackit.onlineshop.transfer.product.SaveProductRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductResponse createProduct(SaveProductRequest request) {
        LOGGER.info("Creating product {}", request);

        Product product = new Product();
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setImageUrl(request.getImageUrl());
        product.setQuantity(request.getQuantity());

        Product savedProduct = productRepository.save(product);

        return mapProductResponse(savedProduct);
    }



    public Product getProduct (long id) {
        LOGGER.info("Retrieving product {}", id);

        return productRepository.findById(id).
                orElseThrow(() -> new ResourceNotFoundException("Product " + id + " does not exist"));

    }

    public ProductResponse getProductResponse(long id) {

        Product product = getProduct(id);


        return mapProductResponse(product);

    }

    public Page<ProductResponse> getProducts(GetProductsRequest request, Pageable pageable) {
        LOGGER.info("Retrieving products: {}", request);

        Product exampleProduct = new Product();
        exampleProduct.setName(request.getPartialName());
        exampleProduct.setDescription(request.getPartialDescription());
        exampleProduct.setQuantity(request.getMinQuantity());
        exampleProduct.setPrice(request.getPrice());
        exampleProduct.setCarts(null);


        Example<Product> example = Example.of(exampleProduct);

        Page<Product> productsPage = productRepository.findAll(example, pageable);

        List<ProductResponse> productDtos = new ArrayList<>();

        for (Product product : productsPage.getContent()) {
            ProductResponse productResponse = mapProductResponse(product);

            productDtos.add(productResponse);
        }

        return new PageImpl<>(productDtos, pageable, productsPage.getTotalElements());

    }

    private ProductResponse mapProductResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setName(product.getName());
        productResponse.setDescription(product.getDescription());
        productResponse.setPrice(product.getPrice());
        productResponse.setImageUrl(product.getImageUrl());
        productResponse.setQuantity(product.getQuantity());

        return productResponse;
    }
}
