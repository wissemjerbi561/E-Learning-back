package com.paiement.Controllers;

import com.paiement.Entities.OrderItem;
import com.paiement.Repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "/orderItem")
@CrossOrigin(value = "*")
public class OrderItemController {
    @Autowired
    OrderItemRepository orderItemRepository;
    public OrderItemController(OrderItemRepository orderItemRepository){

        this.orderItemRepository=orderItemRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<OrderItem> saveOrderItem(@RequestBody OrderItem orderItem){

        return ResponseEntity.ok(orderItemRepository.save(orderItem));
    }
    @GetMapping("/orderItems")
    public ResponseEntity getAllOrderItem(){

        return  ResponseEntity.ok(this.orderItemRepository.findAll());
    }
    @GetMapping("/{id}")
    public OrderItem getOrderItemById(@PathVariable Long id){
        return orderItemRepository.findById(id).orElse(null);
    }
    @PutMapping("/update/{id}")
    public void  updateOrderItem(@PathVariable Long id,@RequestBody OrderItem orderItem) {
        OrderItem orderItem1 = orderItemRepository.findById(id).orElse(null);
        if (orderItem1 != null) {
            orderItem1.setId(orderItem1.getId());

            orderItemRepository.save(orderItem1);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteOrderItemById(@PathVariable Long id){

        orderItemRepository.deleteById(id);
    }

}
