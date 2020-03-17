package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Controller.ResponseObject.OrdersSummaryDTO;
import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.OrderedProduct;

import java.util.List;

public interface IOrderRepository {
    void addOrder(List<OrderedProduct> orderedProduct, String username);
    List<CustomerOrder> getOrderByUser(String username, String status, String orderType);
    CustomerOrder getOrderById(Integer id);
    void changeStatusOrder(Integer id, String status);

    List<OrderedProduct> getAllOrder(String category, String status, String orderType);
    List<OrdersSummaryDTO> getOrdersSummary(Integer top);
}
