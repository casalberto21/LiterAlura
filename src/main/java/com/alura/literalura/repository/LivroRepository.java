package com.alura.literalura.repository;

import com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    // ✅ Verifica se já existe um livro com esse título
    boolean existsByTitulo(String titulo);

    // ✅ Busca um livro pelo título (retorna Optional para evitar erro de null)
    Optional<Livro> findByTitulo(String titulo);

    // ✅ Busca todos os livros em um idioma específico (case-insensitive)
    List<Livro> findByIdiomaContainingIgnoreCase(String idioma);

    // ✅ Retorna os 5 livros mais baixados (ordem decrescente)
    List<Livro> findTop5ByOrderByDownloadsDesc();
}
