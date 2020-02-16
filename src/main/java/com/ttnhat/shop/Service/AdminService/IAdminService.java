package com.ttnhat.shop.Service.AdminService;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;

public interface IAdminService {
    Page<UsersEntity> getAllUserByPage(Integer pageNum, Integer pageSize);
}
