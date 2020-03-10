package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Entity.UsersDetails;
import com.ttnhat.shop.ExceptionHandler.Exception.SQLException;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository implements IUsersRepository {

    @Autowired
    private EntityManagerFactory emf;
    Logger logger = LoggerFactory.getLogger(UserRepository.class);
    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    @Override
    public Page<UsersEntity> findAllUser(Pageable pageable, String name) {
        EntityManager entityManager = getEntityManager();
        String nameStr = name+"*";
        String sql = "select * from user where match (name) against (:name in boolean mode)";
        Page<UsersEntity> pageUsers = null;
        try{
            entityManager.getTransaction().begin();
            List<UsersEntity> allUser = entityManager.createNativeQuery(sql, UsersEntity.class)
                    .setParameter("nameStr", nameStr)
                    .getResultList();
            int total = allUser.size();
            pageUsers = new PageImpl<UsersEntity>(allUser, pageable, total);
            entityManager.getTransaction().commit();
            entityManager.close();
            return pageUsers;
        } catch (RuntimeException e)
        {
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Optional<UsersEntity> findByUsername(String username) {
        EntityManager entityManager = getEntityManager();
        UsersEntity user = null;
        String sql = "select u from UsersEntity u where u.username = :username";
        try {
            entityManager.getTransaction().begin();
            user = entityManager.createQuery(sql, UsersEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
            logger.info(user.getUserDetails().toString());
            entityManager.getTransaction().commit();
            entityManager.close();
            return Optional.ofNullable(user);
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }

    }

    @Override
    public UsersEntity save(UsersEntity usersEntity) {
        UsersEntity userTemp = null;
        String findSql = "select u from UsersEntity u where u.username = :username";
        EntityManager entityManager = getEntityManager();
        try{
            entityManager.getTransaction().begin();
            userTemp = entityManager.createQuery(findSql, UsersEntity.class).setParameter("username", usersEntity.getUsername())
                                .getSingleResult();
            userTemp.setStatus(usersEntity.getStatus());
            logger.info(userTemp.getUserDetails().toString());
            entityManager.getTransaction().commit();
            entityManager.close();
            return userTemp;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public UsersEntity signUp(UsersEntity usersEntity) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String sql = "select u from UsersEntity u where u.username = :username";
            System.out.println(usersEntity.getUserDetails());
            List<UsersEntity> tempUser = entityManager.createQuery(sql, UsersEntity.class)
                    .setParameter("username" , usersEntity.getUsername())
                    .getResultList();
            if (!tempUser.isEmpty()){
                throw new RuntimeException("User is existed. Please change fill again");
            }
            entityManager.flush();
            UsersDetails usersDetails = usersEntity.getUserDetails();
            usersDetails.setUsersEntity(usersEntity);
            usersEntity.setUserDetails(usersDetails);
            entityManager.persist(usersEntity);
            entityManager.getTransaction().commit();
            entityManager.close();
            return usersEntity;
        }
        catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException((e.getMessage()));
        }
    }
}
