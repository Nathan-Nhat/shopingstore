package com.ttnhat.shop.Controller.PublicController;

import com.ttnhat.shop.Controller.AdminController.SecuredUsersController;
import com.ttnhat.shop.Entity.Category;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Service.AdminService.ProductService.ISecuredProductService;
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
    @GetMapping(value = "/all-products")
    public ResponseEntity<Page<Product>> getAllUserByPage(@RequestParam(name = "page")Integer pageNum,
                                                          @RequestParam(name = "size") Integer pageSize)
    {
        Page<Product> usersEntityList = publicService.getAllUProductByPage(pageNum, pageSize);
        return ResponseEntity.ok(usersEntityList);
    }

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(name = "id") String id){
        return ResponseEntity.ok(publicService.findProductById(id));
    }
    @GetMapping(value = "/products/search")
    public ResponseEntity<Page<Product>> getProductBySearch(@RequestParam(name = "query", required = false)String name, @RequestParam(name = "page")
                                                            Integer page, @RequestParam(name = "size") Integer size, @RequestParam(name = "category") String category){
        System.out.println(page);
        return ResponseEntity.ok(publicService.getProductBySearchName(name, page, size, category));
    }
    @GetMapping(value = "/category")
    public ResponseEntity<List<Category>> getAllCategory(){
        return ResponseEntity.ok(securedProductService.getAllCategory());
    }
}
