package com.ttnhat.shop.Controller.ResponseObject;

import java.util.Date;

public class AnalystClickDTO {
    private Date date;
    private Long numberClick;

    public AnalystClickDTO(Date date, Long numberClick) {
        this.date = date;
        this.numberClick = numberClick;
    }

    public Date getDate() {
        return date;
    }

    public Long getNumberClick() {
        return numberClick;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setNumberClick(Long numberClick) {
        this.numberClick = numberClick;
    }
}
