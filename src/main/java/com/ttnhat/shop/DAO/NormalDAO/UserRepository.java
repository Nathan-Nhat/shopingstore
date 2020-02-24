package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUsersRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional
    public Page<UsersEntity> findAllUser(Pageable pageable) {
        String sql = "select u from UsersEntity u";
        String sql1 = "select count(u.id) from UsersEntity u";
        List<UsersEntity> allUser = entityManager.createQuery(sql, UsersEntity.class).getResultList();
        Long total = entityManager.createQuery(sql1, Long.class).getSingleResult();

        Page<UsersEntity> pageUsers = new PageImpl<UsersEntity>(allUser, pageable, total);
        return pageUsers;
    }

    @Override
    @Transactional
    public Optional<UsersEntity> findByUsername(String username) {
        String sql = "select u from UsersEntity u where u.username = :username";
        Optional<UsersEntity> user = Optional.ofNullable(entityManager.createQuery(sql, UsersEntity.class)
                .setParameter("username", username)
                .getSingleResult());
        return user;
    }

    @Override
    public UsersEntity save(UsersEntity usersEntity) {
        return null;
    }
}
