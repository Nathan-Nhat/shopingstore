package com.ttnhat.shop.Service.AdminService;

import com.ttnhat.shop.DAO.NormalDAO.IUsersRepository;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private IUsersRepository usersRepository;
    @Override
    public Page<UsersEntity> getAllUserByPage(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return usersRepository.findAll(pageable);
    }
}
