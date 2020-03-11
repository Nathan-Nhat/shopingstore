package com.ttnhat.shop.DAO.NormalDAO;

import com.ttnhat.shop.Controller.ResponseObject.AnalystClickDTO;
import com.ttnhat.shop.Controller.ResponseObject.AnalystOrdersDTO;

import java.util.Date;
import java.util.List;

public interface IDashboardRepository {
    Long getTotalClick(Date newDate);
    Long getTotalOrders(Date newDate);

    Long getTotalUsers(Date newDate);

    Long getRevenue(Date newDate);

    List<AnalystClickDTO> getAnalystClick(Date newDate);

    List<AnalystOrdersDTO> getAnalystOrders(Date newDate);
}
