package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.OrderedProduct;

import java.util.List;

public interface IOrderRepository {
    void addOrder(List<OrderedProduct> orderedProduct, String username);
    List<CustomerOrder> getOrderByUser(String username);
    CustomerOrder getOrderById(Integer id);
    void changeStatusOrder(Integer id, Integer status);
}
