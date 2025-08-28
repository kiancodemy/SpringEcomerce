package com.Ecommerce.main.controler;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.service.cart.CartItemService;
import com.Ecommerce.main.service.cart.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.prefix}/cartitem")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;
    private final CartService cartService;


    @PostMapping("/addCartitem")
    public ResponseEntity<ApiResponse> addCartItem(@RequestParam(required = false)  Long cartId ,@RequestParam  Long productId, @RequestParam  int quantity){
        try {
            if(cartId==null){
                cartId=cartService.addRandomId();
            }
            cartItemService.addCartItem(cartId,productId,quantity);
            return ResponseEntity.ok().body(new ApiResponse("added successfully",null));
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse("failed",e.getMessage()));
        }

    }
    @DeleteMapping("/removeCartItem/{cartId}/{productId}")
    public ResponseEntity<ApiResponse> removeCartItem(@PathVariable("cartId") Long cartId,@PathVariable("productId") Long productId) {
        try {
            cartItemService.removeItemFromCart(cartId, productId);
            return ResponseEntity.ok().body(new ApiResponse("Item removed successfully", null));
        }
         catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to remove item", e.getMessage()));
        }
    }

    @PutMapping("/updateCartItem/{cartId}/{productId}/{quantity}")
    public ResponseEntity<ApiResponse> updateCartItem(@PathVariable("cartId") Long cartId,@PathVariable("productId") Long productId,@PathVariable("quantity") int quantity) {
        try {
            cartItemService.updateItemQuantity(cartId, productId, quantity);
            return ResponseEntity.ok().body(new ApiResponse("Item quantity updated successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Failed to update item", e.getMessage()));
        }
    }}


