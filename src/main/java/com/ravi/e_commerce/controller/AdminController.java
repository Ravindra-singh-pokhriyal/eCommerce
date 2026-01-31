package com.ravi.e_commerce.controller;

import com.ravi.e_commerce.model.Category;
import com.ravi.e_commerce.model.Product;
import com.ravi.e_commerce.model.ProductOrder;
import com.ravi.e_commerce.model.UserDtls;
import com.ravi.e_commerce.service.*;

import com.ravi.e_commerce.util.CommonUtil;
import com.ravi.e_commerce.util.OrderStatus;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private CommonUtil commonUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @ModelAttribute
    public void getUserDetails(Principal p, Model model){
        if (p!=null) {
            String email = p.getName();
            UserDtls userDtls = userService.getUserByEmail(email);
            model.addAttribute("user", userDtls);
            Integer countCart = cartService.getCountCart(userDtls.getId());
            model.addAttribute("countCart", countCart);
        }

        List<Category> allActivCategory = categoryService.getAllActiveCategory();

        model.addAttribute("categorys", allActivCategory);
    }

    @GetMapping("/")
    public String index(){
        return "admin/index";
    }

    @GetMapping("/loadAddProduct")
    public String loadAddProduct(Model model){
        List<Category> categories = categoryService.getAllCategory();
        model.addAttribute("categories",categories);
        return "admin/add_product";
    }

    @GetMapping("/category")
    public String category(Model model, @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                           @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize){
//        model.addAttribute("categorys", categoryService.getAllCategory());
        Page<Category> page = categoryService.getAllCategoryPagination(pageNo, pageSize);

        List<Category> categorys = page.getContent();
        model.addAttribute("categorys", categorys);

        model.addAttribute("pageNo", page.getNumber());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("TotalElements", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("isFirst", page.isFirst());
        model.addAttribute("isLast", page.isLast());

        return "admin/category";
    }

    @PostMapping("/saveCategory")
    public String saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file,
                               HttpSession session) throws IOException {

        String imageName = file != null ? file.getOriginalFilename() : "default.jpg";
        category.setImageName(imageName);
        Boolean existCategory = categoryService.existCategory(category.getName());

        if(existCategory){ //checking if the catgory is alredy exist(True) in the database.
            session.setAttribute("errorMsg","Category Name already exists.");
        }else {
            //Otherwise save the catgory in the database.
            Category saveCategory = categoryService.saveCategory(category);

            //Checking if the category saved successfully of not. If not then errorMsg otherwise successMsg.
            if(ObjectUtils.isEmpty(saveCategory)){      //  OR if(saveCategory == null) {}
                session.setAttribute("errorMsg", "Not saved : Internal server problem.");
            }else {

                File saveFile = new ClassPathResource("static/images").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "category_img" + File.separator
                        + file.getOriginalFilename());

                System.out.println(path);
                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

                session.setAttribute("successMsg", "Saved Successfully.");
            }
        }
        return "redirect:/admin/category";
    }

    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id, HttpSession session){

        Boolean deleteCategory = categoryService.deleteCategory(id);

        if(deleteCategory){
            session.setAttribute("successMsg", "Categroy delete Successfully.");
        } else {
            session.setAttribute("errorMsg", "Something wrong with server.");
        }
        return "redirect:/admin/category";
    }

    @GetMapping("/loadEditCategory/{id}")
    public String loadEditCategory(@PathVariable int id, Model model){
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "admin/edit_category";
    }

    @PostMapping("/updateCategory")
    public String updateCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file,
                                 HttpSession session) throws IOException {

        Category oldCategory = categoryService.getCategoryById(category.getId());
        String imageName = file.isEmpty() ? oldCategory.getImageName() : file.getOriginalFilename();

        if (!ObjectUtils.isEmpty(category)) {

            oldCategory.setName(category.getName());
            oldCategory.setIsActive(category.getIsActive());
            oldCategory.setImageName(imageName);
        }

        Category updateCategory = categoryService.saveCategory(oldCategory);

        if (!ObjectUtils.isEmpty(updateCategory)) {

            if (!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/images").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "category_img" + File.separator
                        + file.getOriginalFilename());

                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }

            session.setAttribute("successMsg", "Category update success");
        } else {
            session.setAttribute("errorMsg", "something wrong on server");
        }

        return "redirect:/admin/loadEditCategory/" + category.getId();
    }

    @PostMapping("/saveProduct")
    public String saveProduct(@ModelAttribute Product product,@RequestParam("file") MultipartFile image,
                              HttpSession session) throws IOException{
        String imageName = image.isEmpty() ? "default.jpg" : image.getOriginalFilename();
        product.setImage(imageName);

        product.setDiscount(0);
        product.setDiscountPrice(product.getPrice());

        Product saveProduct = productService.saveProduct(product);


        if(!ObjectUtils.isEmpty(saveProduct)){

            File saveFile = new ClassPathResource("static/images").getFile();

            Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "product_img" + File.separator
                    + image.getOriginalFilename());

            Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

            session.setAttribute("successMsg", "Product saved Successfully.");
        } else {
            session.setAttribute("errorMsg", "Something went wrong!!");
        }
        return "redirect:/admin/loadAddProduct";
    }


    @GetMapping("/products")
    public String loadViewProduct(@RequestParam(defaultValue = "") String ch, Model model, @RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                                  @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize){
//        List<Product> products = null;
//        if(ch !=null && ch.length() > 0) {
//            products = productService.searchProducts(ch);
//        } else {
//            products = productService.getAllProducts();
//        }
//        model.addAttribute("products", products);

        Page<Product> page = null;
        if(ch !=null && ch.length() > 0) {
            page = productService.searchProductPagination(pageNo,pageSize,ch);
        } else {
            page = productService.getAllProductPagination(pageNo,pageSize);
        }
//        model.addAttribute("products", page.);

        List<Product> products = page.getContent();
        model.addAttribute("products", products);

        model.addAttribute("pageNo", page.getNumber());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("TotalElements", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("isFirst", page.isFirst());
        model.addAttribute("isLast", page.isLast());

        return "admin/products";
    }

    @GetMapping("/deleteProduct/{id}")
    public String deleteProduct(@PathVariable int id, HttpSession session) {
        Boolean deleteProduct = productService.deleteProduct(id);
        if (deleteProduct) {
            session.setAttribute("successMsg", "Product delete success");
        } else {
            session.setAttribute("errorMsg", "Something wrong on server");
        }
        return "redirect:/admin/products";
    }

    @GetMapping("/editProduct/{id}")
    public String editProduct(Model model, @PathVariable int id){
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategory());
        return "admin/edit_product";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile image,
                                HttpSession session, Model model){

        if(product.getDiscount() < 0 || product.getDiscount() > 100) {
            session.setAttribute("errorMsg", "Invalid Discount.");
        } else {
            Product updateProduct = productService.updateProduct(product, image);

            if(!ObjectUtils.isEmpty(updateProduct)){
                session.setAttribute("successMsg", "Product update Successfully.");
            } else {
                session.setAttribute("errorMsg" , "Something wrong with server!!");
            }
        }
        return "redirect:/admin/editProduct/" +product.getId();

    }

    @GetMapping("/users")
    public String getAllUsers(Model model, @RequestParam Integer type,@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize) {
//        List<UserDtls> user = userService.getUsers("ROLE_USER");
//        model.addAttribute("users", user);

        Page<UserDtls> page = null;
        if (type == 1) {
            page = userService.getUserPagination("ROLE_USER", pageNo, pageSize);
        } else {
            page = userService.getUserPagination("ROLE_ADMIN", pageNo, pageSize);
        }

        model.addAttribute("Usertype", type);
        model.addAttribute("users", page.getContent());

        model.addAttribute("pageNo", page.getNumber());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("TotalElements", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("isFirst", page.isFirst());
        model.addAttribute("isLast", page.isLast());
        return "/admin/users";
    }

    @GetMapping("/updateSts")
    public String updateAccountStatus(@RequestParam Boolean status, @RequestParam Integer id,@RequestParam Integer type, HttpSession session) {

        Boolean f = userService.updateAccountStatus(id,status);
        if(f) {
            session.setAttribute("successMsg", "Account Status updated.");
        } else{
            session.setAttribute("errorMsg" , "Something wrong with server!!");
        }
        return "redirect:/admin/users?type="+type;
    }

    @GetMapping("/orders")
    public String getAllOrder(Model model,@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                              @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize) {
//        List<ProductOrder> allOrders = orderService.getAllOrders();
//        model.addAttribute("orders", allOrders);
//        model.addAttribute("search", false);

        Page<ProductOrder> page = orderService.getAllOrdersPagination(pageNo,pageSize);
        model.addAttribute("orders", page.getContent());
        model.addAttribute("search", false);

        model.addAttribute("pageNo", page.getNumber());
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("TotalElements", page.getTotalElements());
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("isFirst", page.isFirst());
        model.addAttribute("isLast", page.isLast());
        return "/admin/orders";
    }

    @PostMapping("update-order-status")
    public String updateOrderStatus(@RequestParam Integer id, @RequestParam Integer st, HttpSession session) {

        // Map the integer status id to the corresponding enum name
        OrderStatus[] values = OrderStatus.values();
        String status = null;

        for(OrderStatus orderStatus : values) {
            if (orderStatus.getId().equals(st)){
                status = orderStatus.getName();
                break; // once matched, break out
            }
        }

        // If no matching status found, set an error message and redirect
        if (status == null) {
            session.setAttribute("errorMsg", "Invalid status selected.");
            return "redirect:/admin/orders";
        }

        // Update the order status in the database and receive the updated entity back
        ProductOrder updateOrder = orderService.updateOrderStatus(id, status);

        // Ensure we have the saved updated order before sending email
        if (updateOrder != null) {
            try{
                // Send the email with the updated order details and final status
                commonUtil.sendMailForProductOrder(updateOrder, status);
            } catch (Exception e){
                e.printStackTrace();
            }

            session.setAttribute("successMsg", "Status Updated");
        } else {
            // If update failed, show an error
            session.setAttribute("errorMsg", "Something went wrong");
        }


        return "redirect:/admin/orders";
    }

    @GetMapping("/search-ordrer")
    public String searchProduct(@RequestParam String orderId, Model model, HttpSession session,@RequestParam(name = "pageNo", defaultValue = "0") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "9") Integer pageSize) {

        if(orderId !=null && orderId.length() > 0) {
            ProductOrder order = orderService.getOrderByOrderId(orderId);

            if (ObjectUtils.isEmpty(order)) {
                session.setAttribute("errorMsg", "Order ID not found");
                model.addAttribute("orders", null);
            } else {
                model.addAttribute("order", order);
            }

            model.addAttribute("search", true);
        } else {
//            List<ProductOrder> allOrders = orderService.getAllOrders();
//            model.addAttribute("orders", allOrders);
//            model.addAttribute("search", false);

            Page<ProductOrder> page = orderService.getAllOrdersPagination(pageNo,pageSize);
            model.addAttribute("orders", page);
            model.addAttribute("search", false);

            model.addAttribute("pageNo", page.getNumber());
            model.addAttribute("pageSize", pageSize);
            model.addAttribute("TotalElements", page.getTotalElements());
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("isFirst", page.isFirst());
            model.addAttribute("isLast", page.isLast());
        }

        return "/admin/orders";
    }

    @GetMapping("/add-admin")
    public String loadAddAdmin(){
        return "/admin/add_admin";
    }

    @PostMapping("/save-admin")
    public String saveAdmin(@ModelAttribute UserDtls user, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        String imageName = file.isEmpty() ? "default.jpg" : file.getOriginalFilename();
        user.setProfileImage(imageName);
        UserDtls saveUser = userService.saveAdmin(user);

        if(!ObjectUtils.isEmpty(saveUser)) {
            if(!file.isEmpty()) {
                File saveFile = new ClassPathResource("static/images").getFile();

                Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + "profile_img" + File.separator
                        + file.getOriginalFilename());

                Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
            }
            session.setAttribute("successMsg", "Admin Created Successfully.");
        } else {
            session.setAttribute("errorMsg", "Something went wrong!!");
        }
        return "redirect:/admin/add-admin";
    }

    @GetMapping("/profile")
    public String profile() {
        return "/admin/profile";
    }

    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute UserDtls user, @RequestParam MultipartFile img, HttpSession session) {
        UserDtls updatedUserProfile = userService.updateUserProfile(user, img);

        if (ObjectUtils.isEmpty(updatedUserProfile)) {
            session.setAttribute("errorMsg", "Profile not updated");
        } else {
            session.setAttribute("successMsg", "Profile updated successfully");
        }
        return "redirect:/admin/profile";
    }

    @PostMapping("/change-password")
    public String changePassword(@RequestParam String newPassword, @RequestParam String currentPassword, Principal principal, HttpSession session) {

        UserDtls user = commonUtil.getLoggedInUserDetails(principal);

        boolean matches = passwordEncoder.matches(currentPassword, user.getPassword());

        if(matches) {
            String encodePassword = passwordEncoder.encode(newPassword);
            user.setPassword(encodePassword);
            UserDtls updateUser = userService.updateUser(user);

            if(ObjectUtils.isEmpty(updateUser)) {
                session.setAttribute("errorMsg", "Something went wrong. Password not changed.");
            } else {
                session.setAttribute("successMsg", "Password changed successfully.");
            }
        } else {
            session.setAttribute("errorMsg", "Wrong password");
        }
        return "redirect:/admin/profile";
    }

}
