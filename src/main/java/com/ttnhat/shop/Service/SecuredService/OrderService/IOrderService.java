package com.ttnhat.shop.Service.SecuredService.OrderService;

import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.OrderedProduct;

import java.util.List;

public interface IOrderService {
    void addOrder(List<OrderedProduct> orderedProduct, String username);
    List<CustomerOrder> getOrdersOfUser(String username);
    CustomerOrder getOrderById(Integer id);
    void changeStatusOrder(Integer id, Integer status);
}