package com.ttnhat.shop.Sercurity.AuthenticationService;

import com.ttnhat.shop.ExceptionHandler.ErrorType;
import com.ttnhat.shop.Sercurity.AuthenticateDAO.IUserRepository;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Primary
public class CustomUserDetailService implements UserDetailsService {

    private IUserRepository userRepository;

    @Autowired
    public CustomUserDetailService(IUserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsersEntity> userEntityOptional = userRepository.findByUsername(username);
        UsersEntity userEntity = userEntityOptional
                .orElseThrow(()->new UsernameNotFoundException(ErrorType.USER_NOTFOUND.toString()));
        UserDetails userDetails = new CustomsUserDetaill(userEntity);
        return userDetails;
    }
}
