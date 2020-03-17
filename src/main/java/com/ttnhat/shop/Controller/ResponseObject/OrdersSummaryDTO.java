package com.ttnhat.shop.Controller.ResponseObject;

import java.util.Date;

public class OrdersSummaryDTO {
    private Integer id;
    private String username;
    private Date dateUpdate;
    private long totalPrice;
    private String status;

    public OrdersSummaryDTO(Integer id, String username, Date dateUpdate, long totalPrice, String status) {
        this.id = id;
        this.username = username;
        this.dateUpdate = dateUpdate;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public Date getDateUpdate() {
        return dateUpdate;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDateUpdate(Date dateUpdate) {
        this.dateUpdate = dateUpdate;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
