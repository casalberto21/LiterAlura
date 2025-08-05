package com.alura.literalura.mainAPP;

import com.alura.literalura.DTO.RApiData;
import com.alura.literalura.DTO.RAuthorData;
import com.alura.literalura.DTO.RBookData;
import com.alura.literalura.model.AuthorData;
import com.alura.literalura.model.BookData;
import com.alura.literalura.repository.AuthorRepository;
import com.alura.literalura.repository.BooksRepository;
import com.alura.literalura.service.DataConverter;
import com.alura.literalura.service.UsingAPI;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class mainAPP {

    private final Scanner READER = new Scanner(System.in);
    private final Menu MENUS = new Menu();
    private final String URL = "https://gutendex.com/books/?search=";
    private final DataConverter CONVERTER = new DataConverter();
    private final UsingAPI API = new UsingAPI();

    private final AuthorRepository authorRepository;
    private final BooksRepository booksRepository;

    public mainAPP(AuthorRepository authorRepository, BooksRepository booksRepository) {
        this.authorRepository = authorRepository;
        this.booksRepository = booksRepository;
    }

    public void showMenu() {
        int option = -1;
        while (option != 0) {
            System.out.println(MENUS.showDefaultMenu());
            try {
                option = Integer.parseInt(READER.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas o número da opção do menu.");
                continue;
            }

            switch (option) {
                case 1:
                    searchBookAPI();
                    break;
                case 2:
                    showSavedBooks();
                    break;
                case 3:
                    showSavedAuthors();
                    break;
                case 4:
                    searchAuthorsByYear();
                    break;
                case 5:
                    searchBooksByLanguage();
                    break;
                case 6:
                    showTop5DownloadedBooks();
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida! Por favor, escolha uma opção do menu.");
            }
        }
    }

    private void searchAuthorsByYear() {
        int year = -1;
        while (year < 0) {
            try {
                System.out.println("Digite ano para busca do autor:");
                year = Integer.parseInt(READER.nextLine());

                if (year < 0) {
                    System.out.println("Ano inválido. Digite valor com 4 digitos maior ou igual a 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números inteiros!");
            }
        }
        List<AuthorData> authorListByYear = authorRepository.findAuthorAliveInYear(year);
        if (authorListByYear.isEmpty()) {
            System.out.println("Nenhum autor encontrado para o ano buscado.");
        } else {
            System.out.println(authorListByYear);
        }
    }

    private void searchBooksByLanguage() {
        int languageOption = 0;
        System.out.println(MENUS.showLanguageMenu());
        try {
            languageOption = Integer.parseInt(READER.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Digite apenas números.");
            return;
        }

        switch (languageOption) {
            case 1:
                searchByLanguage("%pt%");
                break;
            case 2:
                searchByLanguage("%en%");
                break;
            case 3:
                searchByLanguage("%fr%");
                break;
            case 4:
                searchByLanguage("%es%");
                break;
            case 5:
                searchByLanguage("%de%");
                break;
            default:
                System.out.println("Opção não suportada");
        }
    }

    private void searchByLanguage(String language) {
        List<BookData> languageSearched = booksRepository.searchBookByLanguage(language);
        if (!languageSearched.isEmpty()) {
            System.out.println("Livros encontrados:" + languageSearched);
        } else {
            System.out.println("Nenhum livro encontrado para o idioma buscado.");
        }
    }

    private void showTop5DownloadedBooks() {
        List<BookData> topBooks = booksRepository.findTop5ByOrderByDownloadCountDesc(PageRequest.of(0, 5));

        if (topBooks.isEmpty()) {
            System.out.println("Nenhum livro registrado no banco de dados.");
            return;
        }

        System.out.println("\n📊 TOP 5 LIVROS MAIS BAIXADOS:");
        for (int i = 0; i < topBooks.size(); i++) {
            BookData book = topBooks.get(i);
            System.out.println((i+1) + ". " + book.getTitle() +
                    " - Downloads: " + book.getDownloadCount());
        }

        System.out.println("\n🔍 Detalhes completos:");
        topBooks.forEach(System.out::println);
    }

    private void searchBookAPI() {
        RBookData userbook = getBookData();
        if (userbook != null) {
            try {
                RAuthorData rAuthorData = userbook.authors().get(0);
                BookData bookData;
                AuthorData existedAuthor = authorRepository.findByName(rAuthorData.name());
                if (existedAuthor != null) {
                    bookData = new BookData(userbook, existedAuthor);
                } else {
                    AuthorData newAuthor = new AuthorData(rAuthorData);
                    bookData = new BookData(userbook, newAuthor);
                    authorRepository.save(newAuthor);
                }
                try {
                    booksRepository.save(bookData);
                    System.out.println("Livro salvo no banco de dados!" + bookData);
                } catch (DataIntegrityViolationException e) {
                    System.out.println("Livro já está cadastrado no banco:");
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Gutendex retornou uma busca vazia!");
            }
        } else {
            System.out.println("Nenhum livro encontrado no Gutendex. Realize nova busca!");
        }
    }

    public RBookData getBookData() {
        System.out.println(MENUS.showSmallMenu());
        String userBook = READER.nextLine();

        System.out.println("🔍 Consultando disponibilidade na Gutendex API...");

        String json = API.APIConnection(URL + userBook.replace(" ", "%20"));

        System.out.println("✅ Resposta recebida! Processando dados...");

        RApiData searchBook = CONVERTER.getData(json, RApiData.class);

        if (searchBook == null || searchBook.results() == null || searchBook.results().isEmpty()) {
            System.out.println("⚠️ Nenhum resultado encontrado na busca. Tente outro título.");
            return null;
        }

        System.out.println("🔍 Filtrando resultados...");

        Optional<RBookData> bookConverted = searchBook.results().stream()
                .filter(book -> book.title().toUpperCase().contains(userBook.toUpperCase()))
                .findFirst();

        return bookConverted.orElse(null);
    }

    private void showSavedBooks() {
        List<BookData> bookDataList = booksRepository.findAll();
        if (bookDataList.isEmpty()) {
            System.out.println("Nenhum livro registrado.");
        } else {
            System.out.println(bookDataList);
        }
    }

    private void showSavedAuthors() {
        List<AuthorData> authorDataList = authorRepository.findAll();
        if (authorDataList.isEmpty()) {
            System.out.println("Nenhum autor registrado.");
        } else {
            System.out.println(authorDataList);
        }
    }
}