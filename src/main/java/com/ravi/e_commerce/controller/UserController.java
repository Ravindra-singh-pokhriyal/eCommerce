package com.ravi.e_commerce.controller;

import java.security.Principal;
import java.util.List;

import com.ravi.e_commerce.model.*;
import com.ravi.e_commerce.service.CartService;
import com.ravi.e_commerce.service.OrderService;
import com.ravi.e_commerce.util.OrderStatus;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.ravi.e_commerce.service.CategoryService;
import com.ravi.e_commerce.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String home(){
        return "user/home";
    }

    @ModelAttribute
    public void getUserDetails(Principal p, Model model){
        if (p!=null) {
            String email = p.getName();
            UserDtls userDtls = userService.getUserByEmail(email);
            model.addAttribute("user", userDtls);
            Integer countCart = cartService.getCountCart(userDtls.getId());
            model.addAttribute("countCart", countCart);
        }
        List<Category> allActiveCategory = categoryService.getAllActiveCategory();

        model.addAttribute("categorys", allActiveCategory);
    }

    //Add to cart
    @GetMapping("/addCart")
    public String addToCart(@RequestParam Integer pid, @RequestParam Integer uid, HttpSession session){

        Cart saveCart = cartService.saveCart(pid, uid);

        if(ObjectUtils.isEmpty(saveCart)) {
            session.setAttribute("errorMsg", "Product add to cart Failed.");
        } else {
            session.setAttribute("successMsg", "Product added to Cart.");
        }
        return "redirect:/product/"+pid;
    }

    @GetMapping("/cart")
    public String loadCartPage(Principal p, Model model){
        UserDtls user = getLoggedInUserDetails(p);
        List<Cart> carts = cartService.getCartsByUser(user.getId());
        model.addAttribute("carts", carts);
        if(carts.size() > 0) {
            Double totalOrderPrice = carts.get(carts.size() - 1).getTotalOrderPrice();
            model.addAttribute("totalOrderPrice", totalOrderPrice);
        }

        return "user/cart";
    }

    //updataing the quantiy of the cart page items--> end point
    @GetMapping("/cartQuantityUpdate")
    public String updateCartQuantity(@RequestParam String sy, @RequestParam Integer cid){
        cartService.updateQuantity(sy,cid);
        return "redirect:/user/cart";
    }

    //method to get logged in user details
    private UserDtls getLoggedInUserDetails(Principal p) {
        String email = p.getName();
        UserDtls user = userService.getUserByEmail(email);
        return user;
    }

    @GetMapping("/orders")
    public String loadOrderPage(Principal p, Model model) {

        UserDtls user = getLoggedInUserDetails(p);
        List<Cart> carts = cartService.getCartsByUser(user.getId());
        model.addAttribute("carts", carts);
        int tax = 50;
        int deliveryCharge = 200;
        if(carts.size() > 0) {
            Double orderPrice = carts.get(carts.size() - 1).getTotalOrderPrice();
            Double totalOrderPrice = carts.get(carts.size() - 1).getTotalOrderPrice() + tax + deliveryCharge;
            model.addAttribute("orderPrice", orderPrice);
            model.addAttribute("totalOrderPrice", totalOrderPrice);
        }
        return "/user/order";
    }

    @PostMapping("/save-order")
    public String saveOrder(@ModelAttribute OrderRequest request,Principal principal) {
       // System.out.println(request);

        UserDtls user = getLoggedInUserDetails(principal);
        orderService.saveOrder(user.getId(), request);

        return "redirect:/user/success";
    }

    @GetMapping("/success")
    public String loadSuccess() {
        return "/user/success";
    }

    @GetMapping("/user-orders")
    public String myOrder(Model model, Principal principal) {
        UserDtls loginUser = getLoggedInUserDetails(principal);
        List<ProductOrder> orders = orderService.getOrdersByUser(loginUser.getId());
        int tax = 50;
        int deliveryCharge = 200;
        model.addAttribute("tax", tax);
        model.addAttribute("deliveryCharge", deliveryCharge);
        model.addAttribute("orders", orders);
        return "/user/my_orders";
    }

    @GetMapping("update-status")
    public String updateOrderStatus(@RequestParam Integer id, @RequestParam Integer st, HttpSession session) {

        OrderStatus[] values = OrderStatus.values();
        String status = null;

        for(OrderStatus orderStatus : values) {
            if (orderStatus.getId().equals(st)){
                status = orderStatus.getName();
            }
        }

//        System.out.println("Values: "+ values);
        Boolean updateOrder = orderService.updateOrderStatus(id, status);

        if(updateOrder) {
            session.setAttribute("successMsg", "Status Updated");
        } else {
            session.setAttribute("errorMsg", "Something went wrong");
        }


        return "redirect:/user/user-orders";
    }


}
