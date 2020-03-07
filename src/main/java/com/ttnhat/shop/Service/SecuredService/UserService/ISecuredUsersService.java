package com.ttnhat.shop.Service.SecuredService.UserService;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.data.domain.Page;

public interface ISecuredUsersService {
    Page<UsersEntity> getAllUserByPage(Integer pageNum, Integer pageSize, String name);
    UsersEntity getUserByUserName(String username);
    UsersEntity updateUser(UsersEntity user);
}
