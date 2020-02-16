package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUsersRepository extends JpaRepository<UsersEntity, Integer> {
    Page<UsersEntity> findAll(Pageable pageable);
}
