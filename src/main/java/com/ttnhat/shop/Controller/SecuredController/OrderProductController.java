package com.ttnhat.shop.Controller.SecuredController;

import com.ttnhat.shop.Annotation.IsAdmin;
import com.ttnhat.shop.Annotation.IsCustomer;
import com.ttnhat.shop.Controller.ResponseObject.ResponseObject;
import com.ttnhat.shop.Entity.CustomerOrder;
import com.ttnhat.shop.Entity.OrderedProduct;
import com.ttnhat.shop.Service.SecuredService.OrderService.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@Controller
@CrossOrigin
@RequestMapping(value = "/api/secured")
public class OrderProductController {

    @Autowired
    private IOrderService orderService;
    @PostMapping(value = "/orders")
    public ResponseEntity<ResponseObject> orderProduct(@RequestBody List<OrderedProduct> orderedProduct, HttpServletRequest request, Principal principal){
        String username = principal.getName();
        orderService.addOrder(orderedProduct, username);
        return ResponseEntity.ok(new ResponseObject("Success", "Ordered Successfully"));
    }

    @GetMapping(value = "/users/{username}/orders")
    public ResponseEntity<List<CustomerOrder>> getOrdersOfUser(@PathVariable(name = "username") String username,
                                                               @RequestParam(name = "status") String status,
                                                               @RequestParam(name = "type") String type,
                                                               @RequestParam(name = "sort") String sort){
        return ResponseEntity.ok(orderService.getOrdersOfUser(username, status, type, sort));
    }

    @GetMapping(value = "/orders/id/{id}")
    public ResponseEntity<CustomerOrder> getOrderByOrderId(@PathVariable(name = "id") Integer id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
    @IsAdmin
    @PutMapping(value = "/orders/status")
    public ResponseEntity<ResponseObject> changeStatusOrder(@RequestParam(name = "id") Integer id, @RequestParam(name = "status") String status){
        orderService.changeStatusOrder(id, status);
        return ResponseEntity.ok(new ResponseObject("Success", "Change status of Order Successfully"));
    }

    @IsAdmin
    @GetMapping(value = "/orders")
    public ResponseEntity<List<OrderedProduct>> getAllOrder(@RequestParam(name = "category") String category, @RequestParam(name = "status") String status,
                                                            @RequestParam(name = "type") String type, @RequestParam(name = "sort") String sort){
        return ResponseEntity.ok(orderService.getAllOrder(category, status, type, sort));
    }
}
