package com.ravi.e_commerce.service;

import com.ravi.e_commerce.model.OrderRequest;
import com.ravi.e_commerce.model.ProductOrder;

import java.util.List;

public interface OrderService {

    public void saveOrder(Integer userId, OrderRequest orderRequest) throws  Exception;

    public List<ProductOrder> getOrdersByUser(Integer userId);

    public ProductOrder updateOrderStatus(Integer id, String status);

    public List<ProductOrder> getAllOrders();

    public ProductOrder getOrderByOrderId(String orderId);
}
