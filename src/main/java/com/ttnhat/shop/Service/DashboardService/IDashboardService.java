package com.ttnhat.shop.Service.DashboardService;

import com.ttnhat.shop.Controller.ResponseObject.AnalystClickDTO;
import com.ttnhat.shop.Controller.ResponseObject.AnalystOrdersDTO;
import com.ttnhat.shop.Controller.ResponseObject.AnalystRevenueDTO;
import com.ttnhat.shop.Controller.ResponseObject.UserDashBoardDTO;

import java.util.List;

public interface IDashboardService {
    Long getTotalClick(Integer numDays);
    Long getTotalOrders(Integer numDays);

    Long getTotalUsers(Integer numDays);

    Long getRevenue(Integer numDays);

    List<AnalystClickDTO> getAnalystClick(Integer numDays);

    List<AnalystOrdersDTO> getAnalystOrders(Integer numDays);
    List<AnalystRevenueDTO> getAnalystRevenue(Integer numDays);
    List<UserDashBoardDTO> getTopUsersOrder(Integer numDays, Integer top);
}
