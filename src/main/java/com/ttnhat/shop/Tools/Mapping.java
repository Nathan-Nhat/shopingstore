package com.ttnhat.shop.Tools;

import com.ttnhat.shop.Controller.ResponseObject.OrdersSummaryDTO;
import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.OrderedProduct;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Mapping {
    public static List<OrdersSummaryDTO> convertToOrdersDTO(List<CustomerOrder> customerOrderList){
        List<OrdersSummaryDTO> ordersSummaryDTOList = new ArrayList<>();
        for (CustomerOrder element : customerOrderList){
           Integer id = element.getId();
           String name = element.getUsersEntity().getUserDetails().getName();
           Date updateTime = element.getDateUpdate();
           List<String> imageProduct = new ArrayList<>();
           long totalPrice = 0;
           for (OrderedProduct orderedProduct : element.getOrderedProductList()){
               long price = (long) (orderedProduct.getQuantity() * orderedProduct.getProduct().getPrice());
               totalPrice = totalPrice + price;
               imageProduct.add(orderedProduct.getProduct().getImageMain());
           }
           String isDone = element.getIsDone();
           OrdersSummaryDTO ordersSummaryDTO = new OrdersSummaryDTO(id, name, updateTime, totalPrice, isDone);
           ordersSummaryDTO.setImage(imageProduct);
           ordersSummaryDTOList.add(ordersSummaryDTO);
        }
        return ordersSummaryDTOList;
    }
}
