package com.ttnhat.shop.Controller.AdminController;

import com.ttnhat.shop.Annotation.IsAdmin;
import com.ttnhat.shop.Annotation.IsCustomer;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import com.ttnhat.shop.Service.AdminService.UserService.ISecuredUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/secured-user")
public class SecuredUsersController {
    Logger logger = LoggerFactory.getLogger(SecuredUsersController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ISecuredUsersService securedUserService;
    @GetMapping(value = "/1")
    public ResponseEntity<?> validateToken(){
        MapperEntity mapperEntity = this.restTemplate.getForObject("http://echo.jsontest.com/key/value/one/two", MapperEntity.class);
        return ResponseEntity.ok(mapperEntity);
    }

    @IsAdmin
    @GetMapping(value = "/all-users")
    public ResponseEntity<Page<UsersEntity>> getAllUserByPage(@RequestParam(name = "page")Integer pageNum,
                                                          @RequestParam(name = "size") Integer pageSize)
    {
        Page<UsersEntity> usersEntityList = securedUserService.getAllUserByPage(pageNum, pageSize);
        return ResponseEntity.ok(usersEntityList);
    }

    @IsAdmin
    @IsCustomer
    @GetMapping(value = "/users/{username}")
    public ResponseEntity<UsersEntity> getUserByUserName(@PathVariable(name = "username") String username){
        return ResponseEntity.ok(securedUserService.getUserByUserName(username));
    }
    @IsAdmin
    @PutMapping(value = "/users/")
    public ResponseEntity<UsersEntity> upDateUser(@RequestBody UsersEntity usersEntity){
        return ResponseEntity.ok(securedUserService.updateUser(usersEntity));
    }

    @IsAdmin
    @GetMapping("/products/addAll")
    public void addAllProduct(){

    }
}
