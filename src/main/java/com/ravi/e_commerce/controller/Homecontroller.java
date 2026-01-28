package com.ravi.e_commerce.controller;


import com.ravi.e_commerce.model.Category;
import com.ravi.e_commerce.model.Product;
import com.ravi.e_commerce.model.UserDtls;
import com.ravi.e_commerce.service.CartService;
import com.ravi.e_commerce.service.CategoryService;
import com.ravi.e_commerce.service.ProductService;
import com.ravi.e_commerce.service.UserService;
import com.ravi.e_commerce.util.CommonUtil;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.UUID;

@Controller
@AllArgsConstructor
@NoArgsConstructor
public class Homecontroller {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private CartService cartService;

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

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/signin")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/products")
    public String products(Model model, @RequestParam(value = "category", defaultValue = "") String category){
        List<Category> categories = categoryService.getAllActiveCategory();
        List<Product> products = productService.getAllActiveProduct(category);

        model.addAttribute("categories", categories);
        model.addAttribute("products", products);
        model.addAttribute("paramValue", category);
        return "product";
    }

    @GetMapping("/product/{id}")
    public String Product(@PathVariable int id, Model model){
        Product productById = productService.getProductById(id);
        model.addAttribute("product", productById);
        return "view_product";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDtls user, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
        user.setProfileImage(imageName);
        UserDtls saveUser = userService.saveUser(user);

        if(!ObjectUtils.isEmpty(saveUser)) {
            if(!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/images").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + File.separator
                        + file.getOriginalFilename());

                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            session.setAttribute("successMsg", "Register Successfully.");
        } else {
            session.setAttribute("errorMsg", "Something went wrong!!");
        }
        return "redirect:/register";
    }

    //Forgot Password

    @GetMapping("/forgot-password")
    public String showForgotPasswordPage(){
        return "forgot_password.html";
    }

    @PostMapping("forgot-password")
    public String processForgotPassword(@RequestParam String email, HttpSession session, HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        UserDtls userByEmail = userService.getUserByEmail(email);

        if(ObjectUtils.isEmpty(userByEmail)) {
            session.setAttribute("errorMsg", "Invalid email");
        } else {
            String resetToken = UUID.randomUUID().toString();
            userService.updateUserResetToken(email,resetToken);

            //Generate URL http://localhost:8080/reset-password?token=<resetToken><sjahkahkhas>

            String url = CommonUtil.generateUrl(request)+ "/reset-password?token=" + resetToken;

            Boolean sendMail = commonUtil.sentMail(url,email);

            if (sendMail) {
                session.setAttribute("successMsg", "Please Check your email.. Password reset link sent.");
            } else {
                session.setAttribute("errorMsg", "Something wrong on server!! Email not send.");
            }
        }

        return "redirect:/forgot-password";
    }

    //Reset Password
    @GetMapping("/reset-password")
    public String showResetPassword(@RequestParam String token, HttpSession session, Model model) {

        UserDtls userByToken = userService.getUserByToken(token);

        if (userByToken == null) {
            model.addAttribute("msg", "Your link is invalid or expired !!");
            return "message";
        }
        model.addAttribute("token", token);
        return "reset_password";
    }

    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String password, HttpSession session,
                                Model model) {

        UserDtls userByToken = userService.getUserByToken(token);
        if (userByToken == null) {
            model.addAttribute("errorMsg", "Your link is invalid or expired !!");
            return "message";
        } else {
            userByToken.setPassword(passwordEncoder.encode(password));
            userByToken.setResetToken(null);
            userService.updateUser(userByToken);
            // session.setAttribute("succMsg", "Password change successfully");
            model.addAttribute("msg", "Password change successfully");

            return "message";
        }
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam String ch, Model model) {
        List<Product> products = productService.searchProducts(ch);
        model.addAttribute("products", products);
        List<Category> categories = categoryService.getAllActiveCategory();
        model.addAttribute("categories", categories);
        return "product";
    }
}
