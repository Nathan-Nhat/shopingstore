package com.ttnhat.shop.Controller.DashboardController;

import com.ttnhat.shop.Controller.ResponseObject.ResponseObject;
import com.ttnhat.shop.Service.DashboardService.IDashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;

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
}
