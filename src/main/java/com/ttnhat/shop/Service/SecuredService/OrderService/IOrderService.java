package com.ttnhat.shop.Service.SecuredService.OrderService;

import com.ttnhat.shop.Controller.ResponseObject.OrdersSummaryDTO;
import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.OrderedProduct;

import java.util.List;

public interface IOrderService {
    void addOrder(List<OrderedProduct> orderedProduct, String username);
    List<CustomerOrder> getOrdersOfUser(String username, String status, String type, String sort);
    CustomerOrder getOrderById(Integer id);
    void changeStatusOrder(Integer id, String status);

    List<OrderedProduct> getAllOrder(String category, String status, String type, String sort);
    List<OrdersSummaryDTO> getOrdersSummary(Integer top);
}
