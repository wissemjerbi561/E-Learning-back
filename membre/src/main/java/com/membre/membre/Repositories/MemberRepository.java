package com.membre.membre.Repositories;

import com.membre.membre.Entities.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    @Query("SELECT m FROM Member m JOIN m.positions p WHERE p.name = :name")
    List<Member> findByPositionName(@Param("name") String name);
    @Query("SELECT m FROM Member m JOIN m.positions p WHERE p.code = :code")
    List<Member> findByPositionCode(@Param("code") String code);
    @Query("SELECT COUNT(m) FROM Member m JOIN m.positions p WHERE p.name = 'Apprenant'")
    long countByPositionNameApprenant();

     Member findMemberByUserId(@Param("id") Long userId);




}
