package com.ttnhat.shop.Controller.PublicController;

import com.ttnhat.shop.Controller.AdminController.AdminUsersController;
import com.ttnhat.shop.Entity.Product;
import com.ttnhat.shop.Service.AdminService.UserService.IAdminUsersService;
import com.ttnhat.shop.Service.PublicService.IPublicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/public")
@CrossOrigin
public class PublicController {
    @Autowired
    private IPublicService publicService;
    Logger logger = LoggerFactory.getLogger(AdminUsersController.class);
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
}
