package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Controller.ResponseObject.OrdersSummaryDTO;
import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.OrderedProduct;
import com.ttnhat.shop.ExceptionHandler.Exception.SQLException;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import com.ttnhat.shop.Tools.Mapping;
import com.ttnhat.shop.Tools.Tools;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class OrderRepository implements IOrderRepository{

    private Logger logger = LoggerFactory.getLogger(OrderRepository.class);
    @Autowired
    private EntityManagerFactory emf;

    private EntityManager getEntityManager(){
        return emf.createEntityManager();
    }

    @Override
    public void addOrder(List<OrderedProduct> orderedProduct, String username) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String findUsers = "select u from UsersEntity u where u.username = :username";
            List<UsersEntity> usersList = entityManager.createQuery(findUsers, UsersEntity.class)
                        .setParameter("username", username)
                    .getResultList();
            System.out.println(usersList.get(0));
            CustomerOrder customerOrder = new CustomerOrder();
//            customerOrder.setDateCreate(new Date());
            //fake orders
            Integer day = Math.toIntExact(Math.round(Math.random() * 7));
            Date dateFake = Tools.findBeforeDays(day);
            customerOrder.setDateCreate(dateFake);
            //
            customerOrder.setUsersEntity(usersList.get(0));
            customerOrder.setIsDone("INPROGRESS");
            for (OrderedProduct element : orderedProduct){
                element.setCustomerOrder(customerOrder);
                customerOrder.getOrderedProductList().add(element);
            }
            usersList.get(0).getCustomerOrderList().add(customerOrder);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getCause().getMessage());
        }
    }

    @Override
    public List<CustomerOrder> getOrderByUser(String username, String status, String orderType) {
        EntityManager entityManager = getEntityManager();
        try{
            entityManager.getTransaction().begin();
            String sql = "select u from CustomerOrder u where u.usersEntity.username = :username and u.isDone = :status " +orderType;
            List<CustomerOrder>  customerOrderList = entityManager.createQuery(sql, CustomerOrder.class)
                    .setParameter("username", username)
                    .setParameter("status", status)
                    .getResultList();

            entityManager.getTransaction().commit();
            entityManager.close();
            return customerOrderList;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getCause().getMessage());
        }
    }

    @Override
    public CustomerOrder getOrderById(Integer id) {
        EntityManager entityManager = getEntityManager();
        try{
            entityManager.getTransaction().begin();
            String sql = "select u from CustomerOrder u where u.id = :id";
            List<CustomerOrder> customerOrderList = entityManager.createQuery(sql, CustomerOrder.class)
                    .setParameter("id", id)
                    .getResultList();
            System.out.println(customerOrderList.get(0).getOrderedProductList());
            entityManager.getTransaction().commit();
            entityManager.close();
            return customerOrderList.get(0);
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public void changeStatusOrder(Integer id, String status) {
        EntityManager entityManager = getEntityManager();
        try{
            entityManager.getTransaction().begin();
            String sql = "select u from CustomerOrder u where u.id = :id";
            List<CustomerOrder> customerOrderList = entityManager.createQuery(sql, CustomerOrder.class)
                    .setParameter("id", id)
                    .getResultList();
            customerOrderList.get(0).setIsDone(status);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public List<OrderedProduct> getAllOrder(String category, String status, String typeSort) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String sql = "select u from OrderedProduct u";
            List<OrderedProduct> orderedProductList = entityManager.createQuery(sql, OrderedProduct.class)
                    .getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return orderedProductList;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public List<OrdersSummaryDTO> getOrdersSummary(Integer top) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String sql = "select u from CustomerOrder u order by u.dateUpdate desc";
            List<CustomerOrder> customerOrderList = entityManager.createQuery(sql, CustomerOrder.class)
                    .setMaxResults(top)
                    .getResultList();
            List<OrdersSummaryDTO> ordersSummaryDTOList = Mapping.convertToOrdersDTO(customerOrderList);
            entityManager.getTransaction().commit();
            entityManager.close();
            return ordersSummaryDTOList;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }
}
