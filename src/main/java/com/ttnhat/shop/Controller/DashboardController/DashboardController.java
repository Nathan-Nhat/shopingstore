package com.ttnhat.shop.Controller.DashboardController;

import com.ttnhat.shop.Controller.ResponseObject.*;
import com.ttnhat.shop.Service.DashboardService.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/api/secured/")
public class DashboardController {

    @Autowired
    private IDashboardService dashboardService;
    @GetMapping(value = "/total-clicks")
    public ResponseEntity<ResponseObject> getTotalClick(@RequestParam(name = "num-days") Integer numDays){
        return ResponseEntity.ok(new ResponseObject("Success", dashboardService.getTotalClick(numDays)));
    }

    @GetMapping(value = "/total-orders")
    public  ResponseEntity<ResponseObject> getTotalOrders(@RequestParam(name = "num-days")Integer numDays){
        return ResponseEntity.ok(new ResponseObject("Success", dashboardService.getTotalOrders(numDays)));
    }

    @GetMapping(value = "/total-users")
    public ResponseEntity<ResponseObject> getTotalUsers(@RequestParam(name = "num-days") Integer numDays){
        return ResponseEntity.ok(new ResponseObject("Success", dashboardService.getTotalUsers(numDays)));
    }

    @GetMapping(value = "/revenue")
    public ResponseEntity<ResponseObject> getRevenue(@RequestParam(name = "num-days") Integer numDays){
        return ResponseEntity.ok(new ResponseObject("Success", dashboardService.getRevenue(numDays)));
    }

    @GetMapping(value = "/analyst/click")
    public ResponseEntity<List<AnalystClickDTO>> getAnalystClick(@RequestParam(name = "num-days") Integer numDays){
        return ResponseEntity.ok(dashboardService.getAnalystClick(numDays));
    }

    @GetMapping(value = "/analyst/orders")
    public ResponseEntity<List<AnalystOrdersDTO>> getAnalystOrders(@RequestParam(name = "num-days") Integer numDays){

        return ResponseEntity.ok(dashboardService.getAnalystOrders(numDays));
    }
    @GetMapping(value = "/analyst/revenue")
    public ResponseEntity<List<AnalystRevenueDTO>> getAnalystRevenue(@RequestParam(name = "num-days") Integer numDays){
        return ResponseEntity.ok(dashboardService.getAnalystRevenue(numDays));
    }
    @GetMapping(value = "/analyst/users")
    public ResponseEntity<List<UserDashBoardDTO>> getTopUsersOrder(@RequestParam(name = "num-days") Integer numDays,
    @RequestParam(name = "top") Integer top){
        return ResponseEntity.ok(dashboardService.getTopUsersOrder(numDays, top));
    }
}
