package com.ttnhat.shop.Controller.SecuredController;

import com.ttnhat.shop.Annotation.IsAdmin;
import com.ttnhat.shop.Annotation.IsCustomer;
import com.ttnhat.shop.Controller.ResponseObject.ResponseObject;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Entity.ProductDate;
import com.ttnhat.shop.Object.UpdateImageDetail;
import com.ttnhat.shop.Service.SecuredService.ProductService.ISecuredProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/secured")
public class SecuredProductController {
    @Autowired
    private ISecuredProductService securedProductService;

    @IsAdmin
    @PostMapping(value = "/products")
    public ResponseEntity<Product> addSingleProducts(@RequestPart(name = "files", required = false) MultipartFile[] files,
                                                     @RequestPart(name = "properties") Product product
    ){
        System.out.println(files.length);
        System.out.println("test===========================");
        return ResponseEntity.ok(securedProductService.addSingleProduct(files, product));
    }
    @IsAdmin
    @PutMapping(value = "/products")
    public ResponseEntity<Product> editSingleProducts(@RequestBody Product product){
        System.out.println(product.getId());
        return ResponseEntity.ok(securedProductService.editProduct(product));
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
    @GetMapping(value = "/products}")
    public ResponseEntity<Product> getProductById(@RequestParam(name = "id") String id){
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
        return ResponseEntity.ok(securedProductService.getDataByDate(numDate, productId));
    }

    @IsAdmin
    @PostMapping(value = "/products/{id}/images")
    public ResponseEntity<ResponseObject> saveImage(@PathVariable(name = "id") String id, @RequestPart(name= "file") MultipartFile file,
                                                    @RequestPart(name = "properties") Product product){
        String filepath = securedProductService.saveImage(id, file, product);
        return ResponseEntity.ok(new ResponseObject<String, String>("Success", filepath));
    }
}
