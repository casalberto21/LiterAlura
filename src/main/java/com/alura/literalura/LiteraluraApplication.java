package com.alura.literalura;

import com.alura.literalura.mainAPP.mainAPP;
import com.alura.literalura.repository.AuthorRepository;
import com.alura.literalura.repository.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BooksRepository booksRepository;

	@Override
	public void run(String... args) throws Exception {
		mainAPP app = new mainAPP(authorRepository, booksRepository);
		app.showMenu();
	}
}