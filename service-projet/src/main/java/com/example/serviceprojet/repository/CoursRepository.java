package com.example.serviceprojet.repository;

import com.example.serviceprojet.entity.Cours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository extends JpaRepository<Cours,Long> {
    List<Cours> findCoursByMemberId(Integer memberId);

}
