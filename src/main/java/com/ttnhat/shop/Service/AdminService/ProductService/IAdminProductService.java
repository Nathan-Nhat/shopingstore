package com.ttnhat.shop.Service.AdminService.ProductService;

import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAdminProductService {
    Product addSingleProduct(MultipartFile file, Product product, MultipartFile[] multipartFiles);
    List<Category> getAllCategory();
    Page<Product> getAllUProductByPage(Integer page, Integer size);
    Product editProduct(MultipartFile file, Product product, MultipartFile[] multipartFiles);
    Product findProductById(String id);
}
