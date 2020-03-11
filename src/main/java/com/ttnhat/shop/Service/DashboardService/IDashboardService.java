package com.ttnhat.shop.Service.DashboardService;

import com.ttnhat.shop.Controller.ResponseObject.AnalystClickDTO;
import com.ttnhat.shop.Controller.ResponseObject.AnalystOrdersDTO;

import java.util.List;

public interface IDashboardService {
    Long getTotalClick(Integer numDays);
    Long getTotalOrders(Integer numDays);

    Long getTotalUsers(Integer numDays);

    Long getRevenue(Integer numDays);

    List<AnalystClickDTO> getAnalystClick(Integer numDays);

    List<AnalystOrdersDTO> getAnalystOrders(Integer numDays);
}
