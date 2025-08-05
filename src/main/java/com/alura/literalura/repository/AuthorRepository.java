package com.alura.literalura.repository;

import com.alura.literalura.model.AuthorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface AuthorRepository extends JpaRepository<AuthorData, Long> {
    AuthorData findByName(String name);

    @Query("SELECT a FROM AuthorData a WHERE a.birthYear <= :year AND a.deathYear >= :year")
    List<AuthorData> findAuthorAliveInYear(Integer year);
}