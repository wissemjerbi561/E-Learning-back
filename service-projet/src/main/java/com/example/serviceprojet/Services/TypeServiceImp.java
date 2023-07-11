package com.example.serviceprojet.Services;

import com.example.serviceprojet.entity.Type;

import com.example.serviceprojet.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TypeServiceImp implements TypeService{
    @Autowired
    TypeRepository typeRepository;

    @Override
    public Type createType(Type type) {
        return typeRepository.save(type);
    }

    @Override
    public Type getType(Long typeId) {
        return typeRepository.findById(typeId).get();
    }

    @Override
    public List<Type> getAllTypes() {
        List<Type> listTypes= typeRepository.findAll();
        return listTypes;
    }

    @Override
    public Type updateType(Long typeId) {
        Type type= typeRepository.findById(typeId).get();
        return typeRepository.save(type);
    }

    @Override
    public void delete(Long typeId) {
        Type type= typeRepository.findById(typeId).get();
        typeRepository.delete(type);
    }
}
