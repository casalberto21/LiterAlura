📚 LiterAlura

Projeto desenvolvido como parte do Challenge LiterAlura (Alura + Oracle ONE).
O objetivo é criar um Catálogo de Livros que consome a API Gutendex, armazena os dados em um banco de dados PostgreSQL e permite interação via menu de console.

🚀 Tecnologias Utilizadas

Java 17
Spring Boot 3
Maven
PostgreSQL
Gutendex API (https://gutendex.com/books)
JPA/Hibernate

📋 Funcionalidades

✅ 1. Buscar livro pelo título

Faz uma requisição à API Gutendex.
Salva o livro no banco de dados junto com o autor (se ainda não estiver salvo).
✅ 2. Listar livros registrados

Exibe todos os livros que já foram salvos no banco.
✅ 3. Listar autores registrados

Lista todos os autores salvos, exibindo ano de nascimento e falecimento.
✅ 4. Listar autores vivos em um determinado ano

Filtra autores que estavam vivos no ano informado.
✅ 5. Listar livros em um determinado idioma

Filtra livros cadastrados no banco por idioma (ex: pt, en, es, fr).
✅ 6. Top 5 livros mais baixados

Mostra os 5 livros com maior número de downloads no banco.
✅ 0. Sair

Encerra a aplicação.

⚙️ Como Rodar o Projeto

🔹 1️⃣ Clonar o repositório
git clone https://github.com/SEU-USUARIO/literalura.git
cd literalura
🔹 2️⃣ Criar o banco de dados PostgreSQL
CREATE DATABASE literalura;
📌 Anote os dados de acesso (usuário, senha, porta).

🔹 3️⃣ Configurar o arquivo application.properties
No arquivo src/main/resources/application.properties, configure as credenciais do seu banco:
(Dica: para não colocar a senha no arquivo e aumentar a segurança, use variáveis de ambiente no sistema operacional e referencie aqui com ${VARIAVEL})

spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
🔹 4️⃣ Rodar o projeto
./mvnw spring-boot:run
Ou, se estiver usando uma IDE (IntelliJ/Eclipse), execute a classe LiterAluraApplication.

🖥️ Como Usar

Ao rodar a aplicação, será exibido um menu:

--------LITERALURA--------
1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado ano
5 - Listar livros em um determinado idioma
6 - Top 5 livros mais baixados
0 - Sair
------------------------------
Digite o número da opção desejada e siga as instruções.

📈 Exemplo de Uso

--------LITERALURA--------
1 - Buscar livro pelo título
2 - Listar livros registrados
3 - Listar autores registrados
4 - Listar autores vivos em um determinado ano
5 - Listar livros em um determinado idioma
6 - Top 5 livros mais baixados
0 - Sair
------------------------------
Digite a opção desejada: 1
Digite o nome do livro ou autor para buscar: Iracema
Autor já cadastrado: Alencar, José Martiniano de
📘 Livro salvo: Iracema: com uma noticia biographica do auctor

--------LITERALURA--------
Digite a opção desejada: 2
📚 Livros registrados:
Livro: Iracema: com uma noticia biographica do auctor | Autor: Alencar, José Martiniano de

--------LITERALURA--------
Digite a opção desejada: 3
✍️ AUTORES SALVOS:
Alencar, José Martiniano de (Nasc.: 1829, Falec.: 1877)

--------LITERALURA--------
Digite a opção desejada: 4
Digite o ano para buscar autores vivos: 2023
📜 AUTORES VIVOS EM 2023:
Autor Vivo Exemplo (Nasc.: 1980)

--------LITERALURA--------
Digite a opção desejada: 5
Escolha um idioma:
pt - Português
en - Inglês
es - Espanhol
fr - Francês
pt
📚 LIVROS EM PT:
Livro: Iracema: com uma noticia biographica do auctor | Autor: Alencar, José Martiniano de

--------LITERALURA--------
Digite a opção desejada: 6
🏆 TOP 5 LIVROS MAIS BAIXADOS:
1º - Pride and Prejudice | Downloads: 56919
2º - Alice's Adventures in Wonderland | Downloads: 48516
3º - Dracula | Downloads: 31954
4º - The History of Don Quixote, Volume 1, Complete | Downloads: 9805
5º - Don Quixote | Downloads: 8830

--------LITERALURA--------
Digite a opção desejada: 0
Saindo do programa...

📦 Estrutura do Projeto

literalura
├── src/main/java/com/alura/literalura
│   ├── model        # Entidades e DTOs (Livro, Autor, DadosLivro, DadosAutor)
│   ├── repository   # Interfaces JPA (LivroRepository, AutorRepository)
│   ├── service      # Serviços para consumo e conversão da API
│   ├── principal    # Classe Principal com o menu do sistema
│   └── LiterAluraApplication.java  # Classe main
└── src/main/resources
    └── application.properties
👤 Autor

Carlos Junior – Challenge LiterAlura (Alura + Oracle ONE)
