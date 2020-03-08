package com.ttnhat.shop.Service.SecuredService.ProductService;

import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Entity.ProductDate;
import com.ttnhat.shop.Object.UpdateImageDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISecuredProductService {
    Product addSingleProduct(MultipartFile[] files, Product product);
    List<Category> getAllCategory();
    Product editProduct(Product product);
    Product findProductById(String id);
    void updateImage(UpdateImageDetail updateImageDetail);
    void deleteProductById(String id);
    void addTestProduct(Product product);
    List<ProductDate> getDataByDate(Integer date, String productId);
    String saveImage(String id, MultipartFile file, Product product);
}
