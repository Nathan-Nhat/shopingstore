package com.ttnhat.shop.Service.SecuredService.UserService;

import com.ttnhat.shop.DAO.NormalDAO.IUsersRepository;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import com.ttnhat.shop.Tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecuredUsersService implements ISecuredUsersService {
    @Autowired
    private IUsersRepository usersRepository;
    @Override
    public Page<UsersEntity> getAllUserByPage(Integer pageNum, Integer pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        String fullTextName = null;
        if (name.length() == 0){
            fullTextName = "";
        } else {
            fullTextName = Tools.exactNameToFullText(name);
        }
        Page<UsersEntity> pageUsers= usersRepository.findAllUser(pageable, fullTextName);
        return pageUsers;
    }

    @Override
    public UsersEntity getUserByUserName(String username) {
        return usersRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
    }

    @Override
    public UsersEntity updateUser(UsersEntity user) {
        return  usersRepository.save(user);
    }
}
