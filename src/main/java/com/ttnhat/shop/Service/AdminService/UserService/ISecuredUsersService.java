package com.ttnhat.shop.Service.AdminService.UserService;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface ISecuredUsersService {
    Page<UsersEntity> getAllUserByPage(Integer pageNum, Integer pageSize);
    UsersEntity getUserByUserName(String username);
    UsersEntity updateUser(UsersEntity user);
}
