package com.paiement.Controllers;

import com.paiement.Entities.Cart;
import com.paiement.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "/payment/cart")
@CrossOrigin(value = "*")
public class CartController {
    @Autowired
    CartRepository cartRepository;

    public CartController(CartRepository cartRepository){

        this.cartRepository=cartRepository;
    }

    @PostMapping("/create")
    public ResponseEntity<Cart>saveCart(@RequestBody Cart cart){

        return ResponseEntity.ok(cartRepository.save(cart));
    }
    @GetMapping("/carts")
    public ResponseEntity getAllCarts(){

        return  ResponseEntity.ok(this.cartRepository.findAll());
    }
    @GetMapping("/{id}")
    public Cart getCartById(@PathVariable Long id){

        return  cartRepository.findById(id).orElse(null);
    }
    @PutMapping("/update/{id}")
    public void  updateCart(@PathVariable Long id,@RequestBody Cart cart){
        Cart cart1 = cartRepository.findById(id).orElse(null);
        if (cart1 != null){
            cart1.setId(cart.getId());

            cartRepository.save(cart1);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deleteCartById(@PathVariable Long id){
        cartRepository.deleteById(id);
    }

}
