package com.Ecommerce.main.controler;

import com.Ecommerce.main.model.Cart;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("${api.prefix}/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping ("/getBtId/{id}")
    public ResponseEntity<ApiResponse> getByID(@PathVariable("id") Long cartId) {
        try {
            Cart cart =cartService.getCartById(cartId);
            return ResponseEntity.ok(new ApiResponse("successfully fetched",cart));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }

    }

    @DeleteMapping("/delteById/{id}")
    public ResponseEntity<ApiResponse> deleteByID(@PathVariable("id") Long id){

        try {
            cartService.clearCart(id);
            return ResponseEntity.ok(new ApiResponse("successfully deleted",null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }

    @GetMapping("/total/{id}")
    public ResponseEntity<ApiResponse> getTotal(@PathVariable("id") Long cartId){
        try {
            BigDecimal Total=cartService.getTotalPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("successfully fetched",Total));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }

    }




}
