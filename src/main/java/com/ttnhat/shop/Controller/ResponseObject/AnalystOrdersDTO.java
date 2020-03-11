package com.ttnhat.shop.Controller.ResponseObject;

import java.util.Date;

public class AnalystOrdersDTO {
    private Date date;
    private Long numberOrders;

    public AnalystOrdersDTO(Date date, Long numberOrders) {
        this.date = date;
        this.numberOrders = numberOrders;
    }

    public Date getDate() {
        return date;
    }

    public Long getNumberOrders() {
        return numberOrders;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNumberOrders(Long numberOrders) {
        this.numberOrders = numberOrders;
    }
}
