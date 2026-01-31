package com.ravi.e_commerce.service;

import com.ravi.e_commerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product);

    public List<Product> getAllProducts();

    public Boolean deleteProduct(int id);

    public Product getProductById(int id);

    public Product updateProduct(Product product, MultipartFile file);

    public List<Product> getAllActiveProduct(String category);

    public List<Product> searchProducts(String ch);

    public Page<Product> getAllActiveProductPagination(Integer pageNo, Integer pageSize, String category);

    public Page<Product> searchProductPagination(Integer pageNo, Integer pageSize, String ch);

    public Page<Product> getAllProductPagination(Integer pageNo, Integer pageSize);

    public Page<Product> searchActiveProductPagination(Integer pageNo, Integer pageSize, String category, String ch);
}
