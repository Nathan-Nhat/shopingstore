package com.ttnhat.shop.DAO.NormalDAO;

import java.util.Date;

public interface IDashboardRepository {
    Long getTotalClick(Date newDate);
    Long getTotalOrders(Date newDate);
}
