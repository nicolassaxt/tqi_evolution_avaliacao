# API de empréstimo (desafio TQI)

Eu escolhi fazer uma API Spring Boot pelo que foi visto no bootcamp e pela familiaridade com o Java, também é uma fermenta muito utilizada hoje no mercado e junto com o Gradle que facilita as dependências utilizadas. Utilizei o Docker, também visto no bootcamp, para utilizar o banco de dados e facilitar as configurações e por fim utilizei a ferramenta do Postman para fazer todas as requisições e autenticações.

Essa API possui uma autenticação por Spring Security que será realizada no login por e-mail e senha, e as senhas serão criptografadas.

Ela foi criada pra ter dois tipos de usuários(Admin e User), é possível criar os dois tipos no cadastro. Os usuários são limitados a para fazer solicitação de empréstimos, listar empréstimos e detalhes do empréstimo, já o Admin tem liberdade para todas as funcionalidades. 

Eu criei uma autenticação Admin em memoria para TQI:

Login: tqi

senha: admin



## Requirements

- Java 8

- Docker

  

## Spring Boot

- https://start.spring.io/

- Java 8

- Gradle Project

- Spring Web

- Spring Data JPA

- Spring Security

- Mysql Connector
  

## DataBase

### MySQL

- [Mysql Docker Hub](https://hub.docker.com/_/mysql)

  ```
  docker run -e MYSQL_ROOT_PASSWORD=root --name mysql-emprestimo -d -p 3307:3306  mysql:8.0
  ```



## Postman documetação

https://documenter.getpostman.com/view/17952036/UVXdMxkU
