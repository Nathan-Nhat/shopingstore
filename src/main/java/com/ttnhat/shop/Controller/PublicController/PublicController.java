package com.ttnhat.shop.Controller.PublicController;

import com.ttnhat.shop.Controller.SecuredController.SecuredUsersController;
import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import com.ttnhat.shop.Service.SecuredService.ProductService.ISecuredProductService;
import com.ttnhat.shop.Service.PublicService.IPublicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class PublicController {
    @Autowired
    private IPublicService publicService;
    @Autowired
    private ISecuredProductService securedProductService;
    Logger logger = LoggerFactory.getLogger(SecuredUsersController.class);

    @GetMapping(value = "/products")
    public ResponseEntity<Product> getProductById(@RequestParam(name = "id") String id){
        return ResponseEntity.ok(publicService.findProductById(id));
    }
    @GetMapping(value = "/products/search")
    public ResponseEntity<Page<Product>> getProductBySearch(@RequestParam(name = "query", required = false)String name, @RequestParam(name = "page")
                                                            Integer page, @RequestParam(name = "size") Integer size, @RequestParam(name = "category") String category,
                                                            @RequestParam(name = "type") String types, @RequestParam(name = "sort") String sorts
    ){
        System.out.println(page);
        return ResponseEntity.ok(publicService.getProductBySearchName(name, page, size, category, types, sorts));
    }
    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok(securedProductService.getAllCategory());
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<UsersEntity> signUpUser(@RequestBody UsersEntity usersEntity){
        System.out.println(usersEntity.getUserDetails());
        return ResponseEntity.ok(publicService.signUpUser(usersEntity));
    }
}
