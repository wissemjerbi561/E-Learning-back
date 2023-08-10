package com.membre.membre.Controllers;

import com.membre.membre.Entities.Position;
import com.membre.membre.Repositories.PositionRepository;
import com.membre.membre.Services.PositionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value= "/api/member/position")
@CrossOrigin(value = "*")
public class PositionController  {
    @Autowired
    PositionRepository positionRepository;
    @Autowired
    PositionServiceImp positionServiceImp;

    public PositionController(PositionRepository positionRepository){

        this.positionRepository=positionRepository;
    }
    @PostMapping("/create")
    public ResponseEntity<Position> savePosition(@RequestBody Position position){

        return ResponseEntity.ok(positionRepository.save(position));
    }
    @GetMapping("/positions")
    public ResponseEntity getAllPositions(){

        return  ResponseEntity.ok(this.positionRepository.findAll());
    }
    @GetMapping("/{id}")
    public Position getPositionById(@PathVariable Long id){

        return  positionRepository.findById(id).orElse(null);
    }
    @PutMapping("/update/{id}")
    public void  updatePosition(@PathVariable Long id,@RequestBody Position position) {
        Position position1 = positionRepository.findById(id).orElse(null);
        if (position1 != null) {
            position1.setPositionId(position.getPositionId());
            position1.setName(position.getName());

            positionRepository.save(position1);
        }
    }
    @DeleteMapping("/delete/{id}")
    public void deletePositionById(@PathVariable Long id){

        positionRepository.deleteById(id);}

    @PutMapping("/desactiver/{positionId}")
    public ResponseEntity<Position> deactiverPosition(@PathVariable Long positionId, @RequestBody Position position) {
        Position position1 = positionRepository.findById(positionId).orElseThrow(() -> new ResourceNotFoundException("projet not exist with id" + positionId));
        position1.setStatus(true); // Désactiver le projet

        Position updatePosition = positionRepository.save(position1);
        return ResponseEntity.ok(updatePosition);
    }
    @GetMapping("/positions/statusFalse")
    public List<Position> obtenirPositionStatusFalse() {
        return positionServiceImp.obtenirPositionStatusFalse();
    }

}
