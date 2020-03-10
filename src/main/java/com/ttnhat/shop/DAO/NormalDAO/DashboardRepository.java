package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.ExceptionHandler.Exception.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Repository
public class DashboardRepository implements IDashboardRepository{
    Logger logger = LoggerFactory.getLogger(ProductRepository.class);
    @Autowired
    private EntityManagerFactory emf;

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }
    @Override
    public Long getTotalClick(Date newDate) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String sql = "select sum(count) from product_date where date > :newDate";
            List<BigDecimal> totalNumber = entityManager.createNativeQuery(sql)
                    .setParameter("newDate", newDate)
                    .getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return new Long(String.valueOf(totalNumber.get(0)));
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public Long getTotalOrders(Date newDate) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String sql = "select count(id) from customer_order where date_create > :newDate";
            List<BigDecimal> totalNumber = entityManager.createNativeQuery(sql)
                    .setParameter("newDate", newDate)
                    .getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return new Long(String.valueOf(totalNumber.get(0)));
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }
}
