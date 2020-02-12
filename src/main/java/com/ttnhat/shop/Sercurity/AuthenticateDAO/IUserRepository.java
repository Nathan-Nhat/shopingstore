package com.ttnhat.shop.Sercurity.AuthenticateDAO;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UsersEntity, Integer> {

    @Query(value = "SELECT  u FROM UsersEntity u WHERE u.username = :username")
    Optional<UsersEntity> findUserByUsername(@Param("username") String username);
}
