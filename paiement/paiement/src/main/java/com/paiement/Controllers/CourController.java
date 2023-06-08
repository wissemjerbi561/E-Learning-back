package com.paiement.Controllers;

import com.paiement.Entities.Cart;
import com.paiement.Entities.Cour;
import com.paiement.Repositories.CartRepository;
import com.paiement.Repositories.CourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "/payment/cour")
@CrossOrigin(value = "*")
public class CourController {
    @Autowired
    CourRepository courRepository;

    public CourController(CourRepository courRepository) {

        this.courRepository = courRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<Cour>saveCart(@RequestBody Cour cour){

        return ResponseEntity.ok(courRepository.save(cour));
    }
    @GetMapping("/cours")
    public ResponseEntity getAllCours(){

        return  ResponseEntity.ok(this.courRepository.findAll());
    }
}
