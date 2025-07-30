package com.alura.literalura.principal;

import com.alura.literalura.model.*;
import com.alura.literalura.repository.LivroRepository;
import com.alura.literalura.repository.AutorRepository;
import com.alura.literalura.service.ConsumoApi;
import com.alura.literalura.service.ConverteDados;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {

    private final Scanner leitura = new Scanner(System.in);
    private final ConsumoApi consumo = new ConsumoApi();
    private final ConverteDados conversor = new ConverteDados();

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;

    private final String ENDERECO = "https://gutendex.com/books/?search=";

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            System.out.println("""
                    --------LITERALURA--------
                    Escolha um número de sua opção :
                    
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma 
                    6 - Top 5 livros mais baixados
                    0 - Sair
                    ------------------------------
                    """);

            System.out.print("Digite a opção desejada: ");

            if (leitura.hasNextInt()) {
                opcao = leitura.nextInt();
                leitura.nextLine();
            } else {
                System.out.println("⚠️ Digite um número válido.");
                leitura.nextLine(); // limpa entrada inválida
                continue;
            }

            switch (opcao) {
                case 1 -> buscarLivroNaApi();
                case 2 -> listarLivros();
                case 3 -> listarAutores();
                case 4 -> listarAutoresVivosPorAno();
                case 5 -> listarLivrosPorIdioma();
                case 6 -> listarTop5LivrosMaisBaixados();
                case 0 -> System.out.println("Saindo do programa...");
                default -> System.out.println("⚠ Opção inválida!");
            }
        }
    }


    private void buscarLivroNaApi() {
        System.out.print("\nDigite o nome do livro ou autor para buscar: ");
        var busca = leitura.nextLine();

        var json = consumo.obterDadosApi(ENDERECO + busca.replace(" ", "+"));
        DadosGutendex dadosGutendex = conversor.obterDados(json, DadosGutendex.class);

        if (dadosGutendex != null && !dadosGutendex.results().isEmpty()) {
            DadosLivro dadosLivro = dadosGutendex.results().get(0); // pega o primeiro resultado

            // Verifica se autor já existe
            DadosAutor dadosAutor = dadosLivro.autores().get(0);
            Optional<Autor> autorExistente = autorRepository.findByNome(dadosAutor.nome());
            Autor autor;
            if (autorExistente.isPresent()) {
                autor = autorExistente.get();
                System.out.println("Autor já cadastrado: " + autor.getNome());
            } else {
                autor = new Autor(dadosAutor);
                autorRepository.save(autor);
                System.out.println("Novo autor salvo: " + autor.getNome());
            }

            // Salva o livro, se não existir
            Optional<Livro> livroExistente = livroRepository.findByTitulo(dadosLivro.titulo());
            if (livroExistente.isPresent()) {
                System.out.println("⚠️ Livro já existe no banco: " + dadosLivro.titulo());
            } else {
                Livro livro = new Livro(dadosLivro, autor);
                livroRepository.save(livro);
                System.out.println("Livro salvo: " + livro.getTitulo());
            }

        } else {
            System.out.println("⚠️ Nenhum resultado encontrado para: " + busca);
        }
    }

    //  Listar livros salvos no banco
    private void listarLivros() {
        List<Livro> livros = livroRepository.findAll();
        if (livros.isEmpty()) {
            System.out.println("📭 Nenhum livro salvo no banco.");
        } else {
            System.out.println("\n📚 LIVROS SALVOS:");
            livros.forEach(System.out::println);
        }
    }

    //  Listar autores salvos no banco
    private void listarAutores() {
        List<Autor> autores = autorRepository.findAll();
        if (autores.isEmpty()) {
            System.out.println("📭 Nenhum autor salvo no banco.");
        } else {
            System.out.println("\n✍️ AUTORES SALVOS:");
            autores.forEach(System.out::println);
        }
    }

    // Listar autores vivos em um determinado ano
    private void listarAutoresVivosPorAno() {
        System.out.print("Digite o ano para buscar autores vivos: ");
        int ano = leitura.nextInt();
        leitura.nextLine();

        List<Autor> autores = autorRepository.buscarAutoresVivosPorAno(ano);

        if (autores.isEmpty()) {
            System.out.println("⚠️ Nenhum autor encontrado vivo em " + ano);
        } else {
            System.out.println("\n📜 AUTORES VIVOS EM " + ano + ":");
            autores.forEach(System.out::println);
        }
    }

    // 🔹 Listar livros por idioma
    private void listarLivrosPorIdioma() {
        System.out.println("""
                Escolha um idioma:
                pt - Português
                en - Inglês
                es - Espanhol
                fr - Francês
                """);

        String idioma = leitura.nextLine();

        List<Livro> livros = livroRepository.findByIdiomaContainingIgnoreCase(idioma);

        if (livros.isEmpty()) {
            System.out.println(" Nenhum livro encontrado no idioma: " + idioma);
        } else {
            System.out.println("\n LIVROS EM " + idioma.toUpperCase() + ":");
            livros.forEach(System.out::println);
        }
    }

    // 🔹 Top 5 livros mais baixados
    private void listarTop5LivrosMaisBaixados() {
        List<Livro> top5 = livroRepository.findTop5ByOrderByDownloadsDesc();

        if (top5.isEmpty()) {
            System.out.println(" Nenhum livro cadastrado para exibir o ranking.");
        } else {
            System.out.println("\n TOP 5 LIVROS MAIS BAIXADOS:");
            for (int i = 0; i < top5.size(); i++) {
                Livro livro = top5.get(i);
                System.out.printf("%dº - %s | Downloads: %d%n", i + 1, livro.getTitulo(), livro.getDownloads());
            }
        }
    }
}
