package com.ttnhat.shop.Controller.ResponseObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AnalystOrdersDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = JsonFormat.DEFAULT_TIMEZONE)
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

    @Override
    public String toString() {
        return "AnalystOrdersDTO{" +
                "date=" + date +
                ", numberOrders=" + numberOrders +
                '}';
    }
}
