package vttp.paf.workshop24.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import vttp.paf.workshop24.service.OrderService;

@RestController
public class OrderRestController {
    
    @Autowired
    OrderService orderService;

    @PostMapping(path = "/order", consumes = "application/json")
    public ResponseEntity<Boolean> createNewOrder(@RequestBody String jsonInput){

        boolean orderCreated = orderService.createOrderEntry(jsonInput);
        return ResponseEntity.ok().body(orderCreated);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        String x = orderService.test();
        return ResponseEntity.ok().body(x);
    }
}
