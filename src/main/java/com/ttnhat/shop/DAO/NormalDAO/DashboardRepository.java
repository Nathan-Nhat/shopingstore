package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Controller.ResponseObject.AnalystClickDTO;
import com.ttnhat.shop.Controller.ResponseObject.AnalystOrdersDTO;
import com.ttnhat.shop.ExceptionHandler.Exception.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Override
    public Long getTotalUsers(Date newDate) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String sql = "select count(id) from users where date_created > :newDate";
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
    public Long getRevenue(Date newDate) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String sql = "select sum(price * quantity) from ordered_product inner join product on ordered_product.product_id = product.id\n" +
                    "inner join customer_order on ordered_product.customer_order_id = customer_order.id\n" +
                    "where customer_order.date_create > :newDate ";
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
    public List<AnalystClickDTO> getAnalystClick(Date newDate) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String sql = "select product_date.date, sum(product_date.count) from product_date\n" +
                    "where product_date.date > :newDate\n" +
                    "group by cast(product_date.date as date) ";
            List<Object[]> listAnalystClick = entityManager.createNativeQuery(sql)
                    .setParameter("newDate", newDate)
                    .getResultList();
            List<AnalystClickDTO> analystClickDTOS = new ArrayList<>();
            for (Object[] element : listAnalystClick){
                AnalystClickDTO analystClickDTO = new AnalystClickDTO((Date)element[0], new Long(element[1].toString()));
                analystClickDTOS.add(analystClickDTO);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return analystClickDTOS;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public List<AnalystOrdersDTO> getAnalystOrders(Date newDate) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String sql = "select cast(customer_order.date_create as date), count(customer_order.id)\n" +
                    "from customer_order\n" +
                    "where customer_order.date_create > cast(:newDate as date)\n" +
                    "group by cast(customer_order.date_create as date) order by customer_order.date_create asc";
            List<Object[]> analystOrdersDTOS = entityManager.createNativeQuery(sql)
                    .setParameter("newDate", newDate)
                    .getResultList();
            List<AnalystOrdersDTO> analystOrdersDTOList = new ArrayList<>();
            for(Object[] objects : analystOrdersDTOS){
                Date date = (Date)objects[0];
                AnalystOrdersDTO analystOrdersDTO = new AnalystOrdersDTO(new Date(date.getTime()), new Long(objects[1].toString()));
                analystOrdersDTOList.add(analystOrdersDTO);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return analystOrdersDTOList;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }
}
