package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUsersRepository extends JpaRepository<UsersEntity, Integer> {
    @Query("select u.id, u.username, u.roles, u.password, u.status from UsersEntity u")
    Page<UsersEntity> findAllUser(Pageable pageable);
    Optional<UsersEntity> findByUsername(String username);
    UsersEntity save(UsersEntity usersEntity);
}
