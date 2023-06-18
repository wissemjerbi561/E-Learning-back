package com.example.forum.Repositories;

import com.example.forum.Entities.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    @Query( value = "SELECT t.* FROM publications t  WHERE t.parent_id=:parentId ", nativeQuery = true)
    public List<Publication> getByParent(Long parentId);

    @Query( value = "SELECT t.* FROM publications t  WHERE t.parent_id is Null ", nativeQuery = true)
    public List<Publication> getPublications();

}
