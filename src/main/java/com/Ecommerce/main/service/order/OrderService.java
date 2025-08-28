package com.Ecommerce.main.service.order;
import com.Ecommerce.main.Dto.OrderStatus;
import com.Ecommerce.main.exception.OrderNotFound;
import com.Ecommerce.main.model.Cart;
import com.Ecommerce.main.model.Order;
import com.Ecommerce.main.model.OrderItem;
import com.Ecommerce.main.model.Product;
import com.Ecommerce.main.repository.CartRepository;
import com.Ecommerce.main.repository.OrderRepository;
import com.Ecommerce.main.repository.ProductRepository;
import com.Ecommerce.main.service.cart.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderInterface {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartService cartService;


    @Override
    @Transactional
    public Order placeorder(Long userid) {
        Cart cart =cartRepository.findByUserId(userid);
        Order createOrder=createOrder(cart);
        Set<OrderItem> orderItems=createOrderItems(createOrder,cart);
        createOrder.setOrderItems(orderItems);
        createOrder.UpdateTotalAmount();
        Order savedOrder= orderRepository.save(createOrder);
        cartService.clearCart(cart.getId());
        return savedOrder;

    }

    @Override
    public Order getOrderById(Long id) {
        Order order= orderRepository.findById(id).orElseThrow(()->new OrderNotFound("Order not found"));
        order.UpdateTotalAmount();
        return order;
    }


    public Order createOrder(Cart cart) {
        Order order = new Order();
        order.setUser(cart.getUser());
        order.setOrderstatus(OrderStatus.PENDING);
        order.setOrderDate(LocalDate.now());
        return order;
    }


    public Set<OrderItem> createOrderItems(Order order, Cart cart) {
        return cart.getCartItems().stream().map(item->{
            Product product=item.getProduct();
            product.setInventory(product.getInventory()-item.getQuantity());
            productRepository.save(product);
            return new OrderItem(item.getQuantity(),item.getUnitPrice(),product,order);
        }).collect(Collectors.toSet());
    }

    @Override
    public List<Order> getUserOrders(Long userid){
        return  orderRepository.findByUserId(userid);
    }

}
