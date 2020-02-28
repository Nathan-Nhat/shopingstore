package com.ttnhat.shop.Controller.AdminController;

import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import com.ttnhat.shop.Service.AdminService.ProductService.IAdminProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin")
public class AdminProductController {
    @Autowired
    private IAdminProductService adminProductService;

    @PostMapping(value = "/products")
    public ResponseEntity<Product> addSingleProducts(@RequestPart(name = "files", required = false) MultipartFile file,
                                                     @RequestPart(name = "properties") Product product, @RequestPart(name = "detailFiles", required = false) MultipartFile[] multipartFiles){
        System.out.println(product.getCategory());

        return ResponseEntity.ok(adminProductService.addSingleProduct(file, product, multipartFiles));
    }

    @PutMapping(value = "/products")
    public ResponseEntity<Product> editSingleProducts(@RequestPart(name = "files", required = false) MultipartFile file,
                                                     @RequestPart(name = "properties") Product product, @RequestPart(name = "detailFiles", required = false) MultipartFile[] multipartFiles){
        System.out.println(product.getCategory());

        return ResponseEntity.ok(adminProductService.editProduct(file, product, multipartFiles));
    }

    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok(adminProductService.getAllCategory());
    }

    @GetMapping(value = "/all-products")
    public ResponseEntity<Page<Product>> getAllUserByPage(@RequestParam(name = "page")Integer pageNum,
                                                              @RequestParam(name = "size") Integer pageSize)
    {
        Page<Product> usersEntityList = adminProductService.getAllUProductByPage(pageNum, pageSize);
        return ResponseEntity.ok(usersEntityList);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(adminProductService.findProductById(id));
    }


}
