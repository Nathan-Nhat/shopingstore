package com.ttnhat.shop.Controller.AdminController;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import com.ttnhat.shop.Service.AdminService.UserService.IAdminUsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin")
public class AdminUsersController {
    Logger logger = LoggerFactory.getLogger(AdminUsersController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private IAdminUsersService adminService;
    @GetMapping(value = "/1")
    public ResponseEntity<?> validateToken(){
        MapperEntity mapperEntity = this.restTemplate.getForObject("http://echo.jsontest.com/key/value/one/two", MapperEntity.class);
        return ResponseEntity.ok(mapperEntity);
    }

    @GetMapping(value = "/all-users")
    public ResponseEntity<Page<UsersEntity>> getAllUserByPage(@RequestParam(name = "page")Integer pageNum,
                                                          @RequestParam(name = "size") Integer pageSize)
    {
        Page<UsersEntity> usersEntityList = adminService.getAllUserByPage(pageNum, pageSize);
        return ResponseEntity.ok(usersEntityList);
    }

    @GetMapping(value = "/users/{username}")
    public ResponseEntity<UsersEntity> getUserByUserName(@PathVariable(name = "username") String username){
        return ResponseEntity.ok(adminService.getUserByUserName(username));
    }
    @PutMapping(value = "/users/")
    public ResponseEntity<UsersEntity> upDateUser(@RequestBody UsersEntity usersEntity){
        return ResponseEntity.ok(adminService.updateUser(usersEntity));
    }

    @GetMapping("/products/addAll")
    public void addAllProduct(){

    }
}
