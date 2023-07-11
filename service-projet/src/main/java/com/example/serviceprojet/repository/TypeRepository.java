package com.example.serviceprojet.repository;


import com.example.serviceprojet.entity.Type;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TypeRepository extends JpaRepository<Type,Long> {
}
