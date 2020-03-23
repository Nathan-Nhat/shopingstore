package com.ttnhat.shop.Controller.ResponseObject;

import java.math.BigDecimal;
import java.math.BigInteger;

public class OrdersSummaryOfUser {
    private BigInteger totalOrders;
    private BigInteger inProgress;
    private BigInteger completed;
    private BigInteger rejected;
    private BigDecimal totalPaid;

    public OrdersSummaryOfUser(BigInteger totalOrders, BigInteger inProgress, BigInteger completed, BigInteger rejected, BigDecimal totalPaid) {
        this.totalOrders = totalOrders;
        this.inProgress = inProgress;
        this.completed = completed;
        this.rejected = rejected;
        this.totalPaid = totalPaid;
    }

    public BigInteger getTotalOrders() {
        return totalOrders;
    }

    public BigInteger getInProgress() {
        return inProgress;
    }

    public BigInteger getCompleted() {
        return completed;
    }

    public BigInteger getRejected() {
        return rejected;
    }

    public BigDecimal getTotalPaid() {
        return totalPaid;
    }

    public void setTotalOrders(BigInteger totalOrders) {
        this.totalOrders = totalOrders;
    }

    public void setInProgress(BigInteger inProgress) {
        this.inProgress = inProgress;
    }

    public void setCompleted(BigInteger completed) {
        this.completed = completed;
    }

    public void setRejected(BigInteger rejected) {
        this.rejected = rejected;
    }

    public void setTotalPaid(BigDecimal totalPaid) {
        this.totalPaid = totalPaid;
    }
}
