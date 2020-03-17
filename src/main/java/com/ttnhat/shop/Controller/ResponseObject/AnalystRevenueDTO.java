package com.ttnhat.shop.Controller.ResponseObject;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class AnalystRevenueDTO {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", timezone = JsonFormat.DEFAULT_TIMEZONE)
    private Date date;
    private Long totalIncome;

    public AnalystRevenueDTO(Date date, Long totalIncome) {
        this.date = date;
        this.totalIncome = totalIncome;
    }

    public Date getDate() {
        return date;
    }

    public Long getTotalIncome() {
        return totalIncome;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTotalIncome(Long totalIncome) {
        this.totalIncome = totalIncome;
    }

    @Override
    public String toString() {
        return "AnalystRevenueDTO{" +
                "date=" + date +
                ", totalIncome=" + totalIncome +
                '}';
    }
}
