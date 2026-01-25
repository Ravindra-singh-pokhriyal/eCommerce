package com.ravi.e_commerce.service.impl;

import com.ravi.e_commerce.model.Cart;
import com.ravi.e_commerce.model.OrderAddress;
import com.ravi.e_commerce.model.OrderRequest;
import com.ravi.e_commerce.model.ProductOrder;
import com.ravi.e_commerce.repository.CartRepository;
import com.ravi.e_commerce.repository.ProductOrderRepository;
import com.ravi.e_commerce.service.OrderService;
import com.ravi.e_commerce.util.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductOrderRepository productOrderRepository;

    @Autowired
    private CartRepository cartRepository;

    @Override
    public void saveOrder(Integer userId, OrderRequest orderRequest) {

        List<Cart> carts = cartRepository.findByUserId(userId);

        for (Cart cart : carts) {
            ProductOrder order = new ProductOrder();
            order.setOrderId(UUID.randomUUID().toString());
            order.setOrderDate(LocalDate.now());

            order.setProduct(cart.getProduct());
            order.setPrice(cart.getProduct().getDiscountPrice());

            order.setQuantity(cart.getQuantity());
            order.setUser(cart.getUser());

            order.setStatus(OrderStatus.IN_PROGRESS.getName());
            order.setPaymentType(orderRequest.getPaymentType());

            OrderAddress orderAddress = new OrderAddress();
            orderAddress.setFirstName(orderRequest.getFirstName());
            orderAddress.setLastName(orderRequest.getLastName());
            orderAddress.setEmail(orderRequest.getEmail());
            orderAddress.setMobileNo(orderRequest.getMobileNo());
            orderAddress.setAddress(orderRequest.getAddress());
            orderAddress.setCity(orderRequest.getCity());
            orderAddress.setState(orderRequest.getState());
            orderAddress.setPincode(orderRequest.getPincode());

            order.setOrderAddress(orderAddress);
            productOrderRepository.save(order);
        }
    }

    @Override
    public List<ProductOrder> getOrdersByUser(Integer userId) {
        List<ProductOrder> orders = productOrderRepository.findByUserId(userId);
        return orders;
    }

    @Override
    public Boolean updateOrderStatus(Integer id, String status) {
        Optional<ProductOrder> findById = productOrderRepository.findById(id);

        if (findById.isPresent()) {
            ProductOrder productOrder = findById.get();
            productOrder.setStatus(status);
            productOrderRepository.save(productOrder);
            return true;
        }
        return false;
    }

    @Override
    public List<ProductOrder> getAllOrders() {
        return productOrderRepository.findAll();

    }
}
