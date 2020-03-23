package com.ttnhat.shop.Controller.ResponseObject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AllOrderUserDTO {
    private Integer id;
    private String customerName;
    private Date dateCreate;
    private double totalPrice;
    List<String> image=new ArrayList<>();

    public AllOrderUserDTO(Integer id, String customerName, Date dateCreate, double totalPrice, List<String> image) {
        this.id = id;
        this.customerName = customerName;
        this.dateCreate = dateCreate;
        this.image = image;
        this.totalPrice = totalPrice;
    }

    public Integer getId() {
        return id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public String getCustomerName() {
        return customerName;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public List<String> getImage() {
        return image;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public void setImage(List<String> image) {
        this.image = image;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
