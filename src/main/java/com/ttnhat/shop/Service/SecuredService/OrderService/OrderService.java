package com.ttnhat.shop.Service.SecuredService.OrderService;

import com.ttnhat.shop.DAO.NormalDAO.IOrderRepository;
import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.OrderedProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService{
    @Autowired
    IOrderRepository orderRepository;
    @Override
    public void addOrder(List<OrderedProduct> orderedProduct, String username) {
        orderRepository.addOrder(orderedProduct, username);
    }

    @Override
    public List<CustomerOrder> getOrdersOfUser(String username) {
        return orderRepository.getOrderByUser(username);
    }

    @Override
    public CustomerOrder getOrderById(Integer id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public void changeStatusOrder(Integer id, Integer status) {
        orderRepository.changeStatusOrder(id, status);
    }
}
