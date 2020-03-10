package com.ttnhat.shop.Service.DashboardService;

import com.ttnhat.shop.DAO.NormalDAO.IDashboardRepository;
import com.ttnhat.shop.Tools.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

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
}
