package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.OrderedProduct;
import com.ttnhat.shop.ExceptionHandler.Exception.SQLException;
import com.ttnhat.shop.Sercurity.Entity.UsersEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.Date;
import java.util.List;

@Repository
public class OrderRepository implements IOrderRepository{

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
            customerOrder.setDateCreate(new Date());
            customerOrder.setUsersEntity(usersList.get(0));
            for (OrderedProduct element : orderedProduct){
                element.setCustomerOrder(customerOrder);
                customerOrder.getOrderedProductList().add(element);
            }
            usersList.get(0).getCustomerOrderList().add(customerOrder);
            entityManager.getTransaction().commit();
            entityManager.close();
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }

    @Override
    public List<CustomerOrder> getOrderByUser(String username) {
        EntityManager entityManager = getEntityManager();
        try{
            entityManager.getTransaction().begin();
            String sql = "select u from CustomerOrder u where u.usersEntity.username = :username";
            List<CustomerOrder> customerOrderList = entityManager.createQuery(sql, CustomerOrder.class)
                    .setParameter("username", username)
                    .getResultList();
            entityManager.getTransaction().commit();
            entityManager.close();
            return customerOrderList;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
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
    public void changeStatusOrder(Integer id, Integer status) {
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
}
