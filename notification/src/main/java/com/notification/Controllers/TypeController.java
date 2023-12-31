package com.notification.Controllers;

import com.notification.Entities.Notification;
import com.notification.Entities.Type;
import com.notification.Services.TypeServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/type")
@CrossOrigin(value = "*")
public class TypeController {
    @Autowired
    TypeServiceImp typeService;

    @PostMapping("/create")
    public Type createType(@RequestBody Type type){
        return typeService.createType(type);
    }
    @GetMapping("/{typeId}")
    public Type getTypeById(@PathVariable Long typeId){
        return typeService.getType(typeId);
    }
    @GetMapping("/types")
    public List<Type> getAllTypes(){
        return typeService.getAllTypes();
    }
    @PutMapping("/update/{typeId}")
    public Type updateType(@PathVariable Long typeId){
        return typeService.updateType(typeId);
    }
    @DeleteMapping("/delete/{typeId}")
    public void deleteType(@PathVariable Long typeId){
        typeService.delete(typeId);
    }
}
