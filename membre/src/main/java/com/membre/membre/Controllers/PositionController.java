package com.membre.membre.Controllers;

import com.membre.membre.Entities.Position;
import com.membre.membre.Repositories.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value= "/api/member/position")
@CrossOrigin(value = "*")
public class PositionController  {
    @Autowired
    PositionRepository positionRepository;

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

}
