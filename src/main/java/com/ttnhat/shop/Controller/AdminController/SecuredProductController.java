package com.ttnhat.shop.Controller.AdminController;

import com.ttnhat.shop.Annotation.IsAdmin;
import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Entity.ProductDate;
import com.ttnhat.shop.Object.UpdateImageDetail;
import com.ttnhat.shop.Service.AdminService.ProductService.ISecuredProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/secured-product")
public class SecuredProductController {
    @Autowired
    private ISecuredProductService securedProductService;

    @IsAdmin
    @PostMapping(value = "/products")
    public ResponseEntity<Product> addSingleProducts(@RequestPart(name = "files", required = false) MultipartFile file,
                                                     @RequestPart(name = "properties") Product product,
                                                     @RequestPart(name = "detailFiles1", required = false) MultipartFile multipartFile1,
                                                     @RequestPart(name = "detailFiles2", required = false) MultipartFile multipartFile2,
                                                     @RequestPart(name = "detailFiles3", required = false) MultipartFile multipartFile3,
                                                     @RequestPart(name = "detailFiles4", required = false) MultipartFile multipartFile4,
                                                     @RequestPart(name = "detailFiles5", required = false) MultipartFile multipartFile5,
                                                     @RequestPart(name = "detailFiles6", required = false) MultipartFile multipartFile6
    ){
        System.out.println(product.getCategory());

        return ResponseEntity.ok(securedProductService.addSingleProduct(file, product, multipartFile1, multipartFile2, multipartFile3, multipartFile4, multipartFile5, multipartFile6));
    }
    @IsAdmin
    @PutMapping(value = "/products")
    public ResponseEntity<Product> editSingleProducts(@RequestPart(name = "files", required = false) MultipartFile file,
                                                      @RequestPart(name = "properties") Product product,
                                                      @RequestPart(name = "detailFiles1", required = false) MultipartFile multipartFile1,
                                                      @RequestPart(name = "detailFiles2", required = false) MultipartFile multipartFile2,
                                                      @RequestPart(name = "detailFiles3", required = false) MultipartFile multipartFile3,
                                                      @RequestPart(name = "detailFiles4", required = false) MultipartFile multipartFile4,
                                                      @RequestPart(name = "detailFiles5", required = false) MultipartFile multipartFile5,
                                                      @RequestPart(name = "detailFiles6", required = false) MultipartFile multipartFile6){
        System.out.println(product.getCategory());

        return ResponseEntity.ok(securedProductService.editProduct(file, product, multipartFile1, multipartFile2, multipartFile3, multipartFile4, multipartFile5, multipartFile6));
    }
    @IsAdmin
    @PutMapping(value = "/products/updateImage")
    public ResponseEntity<UpdateImageDetail> updateImageDetail(@RequestBody UpdateImageDetail updateImageDetail){
        System.out.println(updateImageDetail);
        securedProductService.updateImage(updateImageDetail);
        return ResponseEntity.ok(updateImageDetail);
    }

    @IsAdmin
    @DeleteMapping(value = "/products/{id}")
    public void deleteProduct(@PathVariable String id){
        System.out.println(id);
        securedProductService.deleteProductById(id);
    }

    @IsAdmin
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(securedProductService.findProductById(id));
    }

    @IsAdmin
    @PostMapping(value = "/products/test")
    public void addSingleProducts(@RequestBody Product product){
        System.out.println(product.getCategory());
        securedProductService.addTestProduct(product);
    }

    @IsAdmin
    @GetMapping(value = "/products")
    public ResponseEntity<List<ProductDate>> getDataByDate(@RequestParam(name = "numDateBefore") Integer numDate,
                                                    @RequestParam(name = "product_id") String productId){
        System.out.println("1231231231`231231231231");
        return ResponseEntity.ok(securedProductService.getDataByDate(numDate, productId));
    }
}
