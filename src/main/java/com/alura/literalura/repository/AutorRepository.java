package com.alura.literalura.repository;

import com.alura.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // ✅ Buscar autor pelo nome (evita duplicados)
    Optional<Autor> findByNome(String nome);

    // ✅ Consulta customizada: autores vivos em determinado ano
    @Query("SELECT a FROM Autor a WHERE a.anoNascimento <= :ano AND (a.anoFalecimento IS NULL OR a.anoFalecimento > :ano)")
    List<Autor> buscarAutoresVivosPorAno(int ano);
}
