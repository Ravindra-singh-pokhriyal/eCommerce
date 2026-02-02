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
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
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
    public String home(Model model) {
        List<Category> allActiveCategory = categoryService.getAllActiveCategory().stream()
                .sorted((c1,c2)->c2.getId().compareTo(c1.getId()))
                .limit(6).toList();
        List<Product> allActiveProduct = productService.getAllActiveProduct("").stream()
                .sorted((p1,p2)->p2.getId().compareTo(p1.getId()))
                .limit(8).toList();

        model.addAttribute("categorys", allActiveCategory);
        model.addAttribute("products",allActiveProduct);
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
    public String products(Model model,@RequestParam(defaultValue = "") String ch,@RequestParam(value = "category", defaultValue = "") String category,
                           @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo, @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize){

        List<Category> categories = categoryService.getAllActiveCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("paramValue", category);


//        List<Product> products = productService.getAllActiveProduct(category);
//        model.addAttribute("products", products);
        Page<Product> page = null;
        if(StringUtils.isEmpty(ch)) {
            page = productService.getAllActiveProductPagination(pageNo, pageSize, category);
        } else {
            page = productService.searchActiveProductPagination(pageNo, pageSize, category, ch);
        }


        List<Product> products = page.getContent();
        model.addAttribute("products", products);
        model.addAttribute("productSize", products.size());

        model.addAttribute("pageNo", page.getNumber());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("TotalElements", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("isFirst", page.isFirst());
        model.addAttribute("isLast", page.isLast());
        return "product";
    }

    //product details page
    @GetMapping("/product/{id}")
    public String Product(@PathVariable int id, Model model){
        Product productById = productService.getProductById(id);
        model.addAttribute("product", productById);
        return "view_product";
    }

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute UserDtls user, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {

        Boolean existsEmail = userService.existsEmail(user.getEmail());

        if(existsEmail) {
            session.setAttribute("errorMsg", "Email already exist");
        } else {
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

    /*
    @GetMapping("/search")
    public String searchProduct(@RequestParam String ch, Model model) {
        // if search string is empty, show regular products page
        if (ch == null || ch.trim().isEmpty()) {
            return "redirect:/products";
        }

        List<Product> products = productService.searchProducts(ch);
        model.addAttribute("products", products);
        model.addAttribute("productSize", products.size());

        // Provide minimal pagination attributes expected by product.html
        model.addAttribute("pageNo", 0);
        model.addAttribute("pageSize", products.size() > 0 ? products.size() : 6);
        model.addAttribute("TotalElements", products.size());
        model.addAttribute("totalPages", 1);
        model.addAttribute("isFirst", true);
        model.addAttribute("isLast", true);

        List<Category> categories = categoryService.getAllActiveCategory();
        model.addAttribute("categories", categories);
        model.addAttribute("paramValue", "");
        return "product";
    }  */
}
