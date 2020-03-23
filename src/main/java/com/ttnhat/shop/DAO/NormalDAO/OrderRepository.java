package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Controller.ResponseObject.AllOrderUserDTO;
import com.ttnhat.shop.Controller.ResponseObject.OrdersSummaryDTO;
import com.ttnhat.shop.Controller.ResponseObject.OrdersSummaryOfUser;
import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.OrderedProduct;
import com.ttnhat.shop.Entity.Product;
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
import java.math.BigDecimal;
import java.math.BigInteger;
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
            customerOrder.setDateUpdate(dateFake);
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
    public List<AllOrderUserDTO> getOrderByUsername(String username, String type) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String mySql = "select u from CustomerOrder u where u.usersEntity.username = :username and u.isDone = :type";
            List<CustomerOrder> result = entityManager.createQuery(mySql)
                    .setParameter("username", username)
                    .setParameter("type", type)
                    .getResultList();
            List<AllOrderUserDTO> allOrderUserDTOS = new ArrayList<>();
            for (CustomerOrder element : result) {
                int id = element.getId();
                Date dateCreate = element.getDateCreate();
                String nameCustomer = element.getUsersEntity().getUserDetails().getName();
                double totalPrice = 0;
                for (OrderedProduct orderedProduct : element.getOrderedProductList()) {
                    totalPrice = orderedProduct.getQuantity() * orderedProduct.getProduct().getPrice();
                }
                List<String> image = new ArrayList<>();
                for (OrderedProduct orderedProduct : element.getOrderedProductList()) {
                    image.add(orderedProduct.getProduct().getImageMain());
                }
                AllOrderUserDTO allOrderUserDTO = new AllOrderUserDTO(id, nameCustomer, dateCreate, totalPrice, image);
                allOrderUserDTOS.add(allOrderUserDTO);
            }
            entityManager.getTransaction().commit();
            entityManager.close();
            return allOrderUserDTOS;
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

    @Override
    public OrdersSummaryOfUser getSummaryOrdersOfUser(String username) {
        EntityManager entityManager = getEntityManager();
        try {
            entityManager.getTransaction().begin();
            String sqlTotalOrder = "select count(*) from customer_order\n" +
                    "inner join users on users.id = customer_order.customer_id\n" +
                    "where users.username = :username";
            BigInteger totalOrders = (BigInteger) entityManager.createNativeQuery(sqlTotalOrder)
                    .setParameter("username", username)
                    .getSingleResult();
            String sqlCompletedOrder = "select count(*) from customer_order\n" +
                    "inner join users on users.id = customer_order.customer_id\n" +
                    "where users.username = :username and customer_order.is_done = 'COMPLETED'";
            BigInteger completed = (BigInteger) entityManager.createNativeQuery(sqlCompletedOrder)
                    .setParameter("username", username)
                    .getSingleResult();
            String sqlInProgressOrders = "select count(*) from customer_order\n" +
                    "inner join users on users.id = customer_order.customer_id\n" +
                    "where users.username = :username and customer_order.is_done = 'INPROGRESS'";
            BigInteger inprogress = (BigInteger) entityManager.createNativeQuery(sqlInProgressOrders)
                    .setParameter("username", username)
                    .getSingleResult();
            String sqlRejectedOrder = "select count(*) from customer_order\n" +
                    "inner join users on users.id = customer_order.customer_id\n" +
                    "where users.username = :username and customer_order.is_done = 'REJECTED'";
            BigInteger rejected = (BigInteger) entityManager.createNativeQuery(sqlRejectedOrder)
                    .setParameter("username", username)
                    .getSingleResult();
            String sqlTotalPaid = "select sum(product.price * ordered_product.quantity) from customer_order\n" +
                    "inner join users on users.id = customer_order.customer_id\n" +
                    "inner join ordered_product on ordered_product.customer_order_id = customer_order.id\n" +
                    "inner join product on product.id = ordered_product.product_id\n" +
                    "where users.username = :username and customer_order.is_done = 'INPROGRESS'";
            BigDecimal totalPaids = (BigDecimal) entityManager.createNativeQuery(sqlTotalPaid)
                    .setParameter("username", username)
                    .getSingleResult();
            OrdersSummaryOfUser ordersSummaryOfUser = new OrdersSummaryOfUser(totalOrders, inprogress, completed, rejected, totalPaids);
            entityManager.getTransaction().commit();
            entityManager.close();
            return ordersSummaryOfUser;
        } catch (RuntimeException e){
            entityManager.getTransaction().rollback();
            throw new SQLException(e.getMessage());
        }
    }
}
