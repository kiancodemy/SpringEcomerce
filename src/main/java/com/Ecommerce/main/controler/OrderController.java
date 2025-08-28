package com.Ecommerce.main.controler;
import com.Ecommerce.main.model.Order;
import com.Ecommerce.main.response.ApiResponse;
import com.Ecommerce.main.service.order.OrderService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("${api.prefix}/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;


    @GetMapping("/getAllOrders/{id}")
    public ResponseEntity<ApiResponse> getOrder(@PathVariable("id") Long id) {
        try {
            Order order=orderService.getOrderById(id);
            return ResponseEntity.ok(new ApiResponse("Order found",order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));

        }

    }
    @PostMapping("/createOrder")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody Long id ) {
        try {
            Order order=orderService.placeorder(id);
            return ResponseEntity.ok(new ApiResponse("Order created",order));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }

    }


}
