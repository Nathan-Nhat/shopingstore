package com.ttnhat.shop.Service.AdminService.ProductService;

import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Entity.ProductDate;
import com.ttnhat.shop.Object.UpdateImageDetail;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAdminProductService {
    Product addSingleProduct(MultipartFile file, Product product, MultipartFile multipartFile1, MultipartFile multipartFile2, MultipartFile multipartFile3,
    MultipartFile multipartFile4, MultipartFile multipartFile5, MultipartFile multipartFile6);
    List<Category> getAllCategory();
    Page<Product> getAllUProductByPage(Integer page, Integer size);
    Product editProduct(MultipartFile file, Product product, MultipartFile multipartFile1, MultipartFile multipartFile2, MultipartFile multipartFile3,
                        MultipartFile multipartFile4, MultipartFile multipartFile5, MultipartFile multipartFile6);
    Product findProductById(String id);
    void updateImage(UpdateImageDetail updateImageDetail);
    void deleteProductById(String id);
    void addTestProduct(Product product);
    List<ProductDate> getDataByDate(Integer date, String productId);
}
