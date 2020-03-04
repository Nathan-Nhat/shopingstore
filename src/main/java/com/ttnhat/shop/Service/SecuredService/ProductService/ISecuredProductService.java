package com.ttnhat.shop.Service.SecuredService.ProductService;

import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Entity.ProductDate;
import com.ttnhat.shop.Object.UpdateImageDetail;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ISecuredProductService {
    Product addSingleProduct(MultipartFile file, Product product, MultipartFile multipartFile1, MultipartFile multipartFile2, MultipartFile multipartFile3,
    MultipartFile multipartFile4, MultipartFile multipartFile5, MultipartFile multipartFile6);
    List<Category> getAllCategory();
    Product editProduct(MultipartFile file, Product product, MultipartFile multipartFile1, MultipartFile multipartFile2, MultipartFile multipartFile3,
                        MultipartFile multipartFile4, MultipartFile multipartFile5, MultipartFile multipartFile6);
    Product findProductById(String id);
    void updateImage(UpdateImageDetail updateImageDetail);
    void deleteProductById(String id);
    void addTestProduct(Product product);
    List<ProductDate> getDataByDate(Integer date, String productId);
}
