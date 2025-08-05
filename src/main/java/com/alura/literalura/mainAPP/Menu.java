package com.alura.literalura.mainAPP;

public class Menu {

    public String showDefaultMenu() {
        var menu = """
                _______________________________________________
                Escolha o número da sua opção
                1 - Buscar livro pelo título
                2 - Listar livros registrados
                3 - Listar autores registrados
                4 - Listar autores vivos em um determinado ano
                5 - Listar livros em um determinado idioma
                6 - Top 5 livros mais baixados
                0 - Sair
                _______________________________________________
                """;
        return menu;
    }

    public String showSmallMenu() {
        var menu = """
                ____________________________
                Digite o nome do livro que deseja buscar:
                """;
        return menu;
    }

    public String showLanguageMenu() {
        var menu = """
                ____________________________
                Digite um número para escolher um idioma para busca:
                1. Português
                2. Inglês
                3. Francês
                4. Espanhol
                5. Alemão
                """;
        return menu;
    }
}