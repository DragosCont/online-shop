package org.fasttrackit.onlineshop.unittests;


import org.fasttrackit.onlineshop.domain.Cart;
import org.fasttrackit.onlineshop.domain.Product;
import org.fasttrackit.onlineshop.domain.User;
import org.fasttrackit.onlineshop.persistance.CartRepository;
import org.fasttrackit.onlineshop.service.CartService;
import org.fasttrackit.onlineshop.service.ProductService;
import org.fasttrackit.onlineshop.service.UserService;
import org.fasttrackit.onlineshop.transfer.cart.AddProductToCartRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CartServiceUnitTests {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    private CartService cartService;

    @BeforeEach
    public void setup(){
        cartService = new CartService(cartRepository, userService, productService);
    }

    @Test
    public void addProductToCart_whenExistingUserAndProduct_thenNoExceptionThrown(){
        when(cartRepository.findById(anyLong())).thenReturn(Optional.empty());

        User user = new User();
        user.setId(1);
        user.setFirstName("Test FN");
        user.setLastName("Test LN");

        when(userService.getUser(anyLong())).thenReturn(user);

        Product product = new Product();
        product.setId(10L);
        product.setPrice(20.4);
        product.setQuantity(30);
        product.setName("Test product");

        when(productService.getProduct(anyLong())).thenReturn(product);

        when(cartRepository.save(any(Cart.class))).thenReturn(null);

        AddProductToCartRequest request = new AddProductToCartRequest();
        request.setUserId(user.getId());
        request.setProductId(product.getId());

        cartService.addProductToCart(request);

        verify(cartRepository).findById(anyLong());
        verify(userService).getUser(anyLong());
        verify(productService).getProduct(anyLong());
        verify(cartRepository).save(any(Cart.class));

    }

}
