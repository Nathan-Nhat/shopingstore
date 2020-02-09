package com.ttnhat.shop.Sercurity.AuthenticationService;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class CustomsUserDetaill implements UserDetails {

    private UsersEntity usersEntity;
    public CustomsUserDetaill(UsersEntity usersEntity){
        this.usersEntity = usersEntity;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(this.usersEntity.getRoles().split(",")).stream().map(
                SimpleGrantedAuthority::new
        ).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.usersEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return this.usersEntity.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
