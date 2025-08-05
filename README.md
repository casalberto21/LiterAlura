# 📚 Literalura - Explorador de Livros do Domínio Público

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-F2F4F9?style=for-the-badge&logo=spring-boot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=postgresql&logoColor=white)
![Gutendex API](https://img.shields.io/badge/Gutendex_API-000000?style=flat&logo=json&logoColor=white)

Aplicação Java com Spring Boot para busca e catalogação de obras literárias através da API do Projeto Gutenberg.

## 🔗 Sobre a API e Fonte de Dados
- **API Gutendex**: [https://gutendex.com/](https://gutendex.com/)  
  _Interface moderna para acessar o catálogo do Projeto Gutenberg com metadados completos._
  
- **Projeto Gutenberg**: [https://www.gutenberg.org/](https://www.gutenberg.org/)  
  _Maior biblioteca digital de livros em domínio público (60k+ obras)._

## ✨ Funcionalidades Principais
- 🔍 Busca de livros por título, autor ou idioma
- 📊 Top 10 livros mais baixados
- 📥 Exportar dados para JSON
- 📈 Estatísticas de autores por período histórico

## 🛠️ Tecnologias Utilizadas
| Camada          | Tecnologias                          |
|-----------------|--------------------------------------|
| **Backend**     | Java 17, Spring Boot 3.2, Hibernate |
| **Banco**       | PostgreSQL 15                        |
| **API**         | Gutendex (Projeto Gutenberg)         |
| **Ferramentas** | Maven, Git, IntelliJ IDEA            |

## 🚀 Como Executar

### Pré-requisitos
- Java 17+
- PostgreSQL 15+ (rodando na porta 5432)
- Maven 3.8+

## 🚀 Como Executar

### 1. Clone o repositório
```bash
git clone https://github.com/casalberto21/literalura.git
cd literalura

2. Configure o banco de dados

spring.datasource.url=jdbc:postgresql://localhost:5432/literalura
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

Certifique-se de ter criado o banco de dados com o nome literalura

3.Execute o projeto

mvn spring-boot:run

4. Uso da aplicação
O menu interativo será exibido no terminal. Basta seguir as opções numéricas







