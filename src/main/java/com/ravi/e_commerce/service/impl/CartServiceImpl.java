package com.ravi.e_commerce.service.impl;

import com.ravi.e_commerce.model.Cart;
import com.ravi.e_commerce.model.Product;
import com.ravi.e_commerce.model.UserDtls;
import com.ravi.e_commerce.repository.CartRepository;
import com.ravi.e_commerce.repository.ProductRepository;
import com.ravi.e_commerce.repository.UserRepository;
import com.ravi.e_commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Cart saveCart(Integer productId, Integer userId) {
        UserDtls userDtls = userRepository.findById(userId).get();
        Product product = productRepository.findById(productId).get();

        Cart cartStatus = cartRepository.findByProductIdAndUserId(productId, userId);

        Cart cart = null;

        if(ObjectUtils.isEmpty(cartStatus)){
            cart = new Cart();
            cart.setProduct(product);
            cart.setUser(userDtls);
            cart.setQuantity(1);
            cart.setTotalPrice(1 * product.getDiscountPrice());
        } else {
            cart = cartStatus;
            cart.setQuantity(cart.getQuantity() + 1);
            cart.setTotalPrice(cart.getQuantity() * cart.getProduct().getDiscountPrice());
        }

        Cart saveCart = cartRepository.save(cart);
        return saveCart;
    }

    //total price calculation
    @Override
    public List<Cart> getCartsByUser(Integer userId) {
        List<Cart> carts = cartRepository.findByUserId(userId);

        Double totalOrderPrice = 0.0;

        List<Cart> updateCarts = new ArrayList<>();

        for(Cart cart : carts){
            Double totalPrice = (cart.getProduct().getDiscountPrice() * cart.getQuantity());
            cart.setTotalPrice(totalPrice);

            totalOrderPrice += totalPrice;
            cart.setTotalOrderPrice(totalOrderPrice);
            updateCarts.add(cart);
        }

        return updateCarts;
    }

    @Override
    public Integer getCountCart(Integer userId) {
        Integer countByUserId = cartRepository.countByUserId(userId);
        return countByUserId;
    }

    //updataing the quantiy of the cart page items--> Logic
    @Override
    public void updateQuantity(String sy, Integer cid) {

        Cart cart = cartRepository.findById(cid).get();

        int updateQuantity;
        
        if(sy.equalsIgnoreCase("de")){
            updateQuantity = cart.getQuantity() - 1;

            if(updateQuantity <= 0){
                cartRepository.delete(cart);
            } else {
                cart.setQuantity(updateQuantity);
                cartRepository.save(cart);
            }
        }else {
            updateQuantity = cart.getQuantity() + 1;
            cart.setQuantity(updateQuantity);
            cartRepository.save(cart);
        }

    }
}
