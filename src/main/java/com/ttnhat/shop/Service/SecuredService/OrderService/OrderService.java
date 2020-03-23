package com.ttnhat.shop.Service.SecuredService.OrderService;

import com.ttnhat.shop.Controller.ResponseObject.AllOrderUserDTO;
import com.ttnhat.shop.Controller.ResponseObject.OrdersSummaryDTO;
import com.ttnhat.shop.Controller.ResponseObject.OrdersSummaryOfUser;
import com.ttnhat.shop.DAO.NormalDAO.IOrderRepository;
import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.OrderedProduct;
import com.ttnhat.shop.Tools.Tools;
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
    public List<AllOrderUserDTO> getOrderByUsername(String username, String type) {
        List<AllOrderUserDTO> objects = orderRepository.getOrderByUsername(username, type);
        return objects;
    }

    @Override
    public CustomerOrder getOrderById(Integer id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public void changeStatusOrder(Integer id, String status) {
        orderRepository.changeStatusOrder(id, status);
    }

    @Override
    public List<OrderedProduct> getAllOrder(String category, String status, String type, String sort) {
        String orderType = Tools.convertStringToOrder(type, sort);
        return orderRepository.getAllOrder(category, status, orderType);
    }

    @Override
    public List<OrdersSummaryDTO> getOrdersSummary(Integer top) {
        return orderRepository.getOrdersSummary(top);
    }

    @Override
    public OrdersSummaryOfUser getSummaryOrdersOfUser(String username) {
        return orderRepository.getSummaryOrdersOfUser(username);
    }
}
