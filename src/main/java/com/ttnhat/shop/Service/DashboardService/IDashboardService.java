package com.ttnhat.shop.Service.DashboardService;

import java.util.Date;

public interface IDashboardService {
    Long getTotalClick(Integer numDays);
    Long getTotalOrders(Integer numDays);
}
