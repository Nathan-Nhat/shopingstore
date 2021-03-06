package com.ttnhat.shop.Service.DashboardService;

import com.ttnhat.shop.Controller.ResponseObject.AnalystClickDTO;
import com.ttnhat.shop.Controller.ResponseObject.AnalystOrdersDTO;
import com.ttnhat.shop.Controller.ResponseObject.AnalystRevenueDTO;
import com.ttnhat.shop.Controller.ResponseObject.UserDashBoardDTO;
import com.ttnhat.shop.DAO.NormalDAO.IDashboardRepository;
import com.ttnhat.shop.Tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class DashboardService implements IDashboardService{
    @Autowired
    private IDashboardRepository dashboardRepository;
    @Override
    public Long getTotalClick(Integer numDays) {
        Date newDate = Tools.findBeforeDays(numDays);
        return dashboardRepository.getTotalClick(newDate);
    }

    @Override
    public Long getTotalOrders(Integer numDays) {
        Date newDate = Tools.findBeforeDays(numDays);
        return dashboardRepository.getTotalOrders(newDate);
    }

    @Override
    public Long getTotalUsers(Integer numDays) {
        Date newDate = Tools.findBeforeDays(numDays);
        return dashboardRepository.getTotalUsers(newDate);
    }

    @Override
    public Long getRevenue(Integer numDays) {
        Date newDate = Tools.findBeforeDays(numDays);
        return dashboardRepository.getRevenue(newDate);
    }

    @Override
    public List<AnalystClickDTO> getAnalystClick(Integer numDays) {
        Date newDate = Tools.findBeforeDays(numDays);
        return dashboardRepository.getAnalystClick(newDate);
    }

    @Override
    public List<AnalystOrdersDTO> getAnalystOrders(Integer numDays) {
        Date newDate = Tools.findBeforeDays(numDays);
        List<AnalystOrdersDTO> tempList = dashboardRepository.getAnalystOrders(newDate);
        return Tools.fillEmptyDateOrders(tempList, newDate);
    }

    @Override
    public List<AnalystRevenueDTO> getAnalystRevenue(Integer numDays) {
        Date newDate = Tools.findBeforeDays(numDays);
        List<AnalystRevenueDTO> tempList = dashboardRepository.getAnalystRevenue(newDate);
        return Tools.fillEmptyDateRevenue(tempList, newDate);
    }

    @Override
    public List<UserDashBoardDTO> getTopUsersOrder(Integer numDays, Integer top) {
        Date newDate = Tools.findBeforeDays(numDays);
        List<UserDashBoardDTO> userDashBoardDTOS = dashboardRepository.getTopUsersOrder(newDate, top);
        return userDashBoardDTOS;
    }
}
