package com.ttnhat.shop.Controller.AdminController;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import com.ttnhat.shop.Service.AdminService.IAdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/admin")
public class AdminController {
    Logger logger = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private IAdminService adminService;
    @GetMapping(value = "/1")
    public ResponseEntity<?> validateToken(){
        MapperEntity mapperEntity = this.restTemplate.getForObject("http://echo.jsontest.com/key/value/one/two", MapperEntity.class);
        return ResponseEntity.ok(mapperEntity);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<Page<UsersEntity>> getAllUserByPage(@RequestParam(name = "page")Integer pageNum,
                                                          @RequestParam(name = "size") Integer pageSize)
    {
        Page<UsersEntity> usersEntityList = adminService.getAllUserByPage(pageNum, pageSize);
        return ResponseEntity.ok(usersEntityList);
    }
}
