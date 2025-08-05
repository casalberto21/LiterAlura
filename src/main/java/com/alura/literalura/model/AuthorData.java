package com.alura.literalura.model;

import com.alura.literalura.DTO.RAuthorData;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "Authors")
public class AuthorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<BookData> books = new ArrayList<>();

    public AuthorData() {}

    public AuthorData(RAuthorData rAuthorData) {
        this.name = rAuthorData.name();
        this.birthYear = rAuthorData.birthYear();
        this.deathYear = rAuthorData.deathYear();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<BookData> getBooks() {
        return books;
    }

    public void setBooks(List<BookData> books) {
        books.forEach(b -> b.setAuthor(this));
        this.books = books;
    }

    public List<String> getBookTitles() {
        return books.stream()
                .map(BookData::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "\n Nome: " + name +
                "\n Ano de Nascimento: " + birthYear +
                "\n Ano de Falecimento: " + deathYear + "\n";
    }
}