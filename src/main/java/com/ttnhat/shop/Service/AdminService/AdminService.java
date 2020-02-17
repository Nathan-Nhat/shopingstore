package com.ttnhat.shop.Service.AdminService;

import com.ttnhat.shop.DAO.NormalDAO.IUsersRepository;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private IUsersRepository usersRepository;
    @Override
    public Page<UsersEntity> getAllUserByPage(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return usersRepository.findAll(pageable);
    }

    @Override
    public UsersEntity getUserByUserName(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
    }

    @Override
    public UsersEntity updateUser(UsersEntity user) {
        Optional<UsersEntity> optionalUsersEntity = usersRepository.findByUsername(user.getUsername());
        UsersEntity usersEntity = optionalUsersEntity.orElseThrow(()->new UsernameNotFoundException("Username not found"));
        if (!usersEntity.getRoles().equals("ADMIN")) {
            usersEntity.setRoles(user.getRoles());
            usersEntity.setStatus(user.getStatus());
            usersRepository.save(usersEntity);
        }
        return usersEntity;
    }
}
