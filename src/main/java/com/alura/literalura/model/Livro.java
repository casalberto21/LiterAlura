package com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Integer downloads;

    @ManyToOne
    private Autor autor;

    public Livro() {}

    // Construtor que recebe DadosLivro e cria o livro
    public Livro(DadosLivro dados) {
        this.titulo = dados.titulo();
        this.idioma = dados.idiomas().isEmpty() ? "Desconhecido" : dados.idiomas().get(0);
        this.downloads = dados.numeroDownloads();
        if (!dados.autores().isEmpty()) {
            this.autor = new Autor(dados.autores().get(0));
        }
    }

    // Construtor que recebe DadosLivro e Autor
    public Livro(DadosLivro dados, Autor autor) {
        this.titulo = dados.titulo();
        this.idioma = dados.idiomas().isEmpty() ? "Desconhecido" : dados.idiomas().get(0);
        this.downloads = dados.numeroDownloads();
        this.autor = autor;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo + " | Autor: " + (autor != null ? autor.getNome() : "Desconhecido");
    }
}
