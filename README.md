# API de Cadastro com Spring Boot
Esta é uma API REST simples de cadastro de pessoas, desenvolvida com Spring Boot, utilizando a arquitetura Clean Architecture. A API oferece funcionalidades CRUD (Create, Read, Update, Delete) para gerenciar dados de pessoas com nome, telefone e e-mail.

## 🚀 Tecnologias Utilizadas
Java 21

Spring Boot 3.3.x

Spring Web (para construção da API REST)

Spring Data JPA (para persistência de dados)

H2 Database (banco de dados em memória)

Lombok (para reduzir código repetitivo)

Maven (gerenciador de dependências)

## 🏗️ Arquitetura do Projeto
O projeto segue os princípios da Clean Architecture, com o código organizado em camadas para garantir a separação de responsabilidades e a independência da lógica de negócio.

controller: Responsável por receber as requisições HTTP e orquestrar as chamadas para a camada de serviço.

service: Contém a lógica de negócio da aplicação, incluindo as validações dos dados antes de interagir com o repositório.

domain: A camada central, que define as entidades (Pessoa) e as interfaces de repositório (PessoaRepository).

## 🛠️ Pré-requisitos:

Certifique-se de ter os seguintes softwares instalados:

Java Development Kit (JDK) 21
Maven 3.6+
Uma IDE de sua preferência (IntelliJ IDEA, VS Code, etc.)

## ⚙️ Configuração e Execução

* 1 - Passo
Clone o repositório:
```git clone [URL_DO_SEU_REPOSITORIO]```
```cd [NOME_DO_PROJETO]```

* 2 - Passo
Abra o projeto em sua IDE.

* 3 - Passo
Execute a classe principal da aplicação, CadastroApplication.java.

🚀 Endpoints da API
A API está disponível na URL base http://localhost:8080/api/pessoas. Abaixo estão os endpoints disponíveis.

POST /api/pessoas - Criar um novo cadastro
Cria um novo registro de pessoa no sistema. O telefone e o e-mail são validados automaticamente.

* **Método:** `POST`
* **Corpo da Requisição (JSON):**
    ```json
    {
      "nome": "João Silva",
      "telefone": "+55(11)9999-8888",
      "email": "joao.silva@email.com"
    }
    ```

Respostas:
201 Created: Cadastro criado com sucesso, retorna o objeto completo com o id.
400 Bad Request: Dados inválidos (e.g., e-mail sem @, telefone com mais de 14 caracteres).

GET /api/pessoas - Listar todos os cadastros
Retorna uma lista de todas as pessoas cadastradas.

Método: GET

Respostas:
200 OK: Retorna um array JSON com todos os cadastros.

GET /api/pessoas/{id} - Buscar um cadastro por ID
Busca um cadastro específico pelo seu id.

Método: GET

Parâmetro de URL: {id} - ID do cadastro (ex: /api/pessoas/1).

Respostas:
200 OK: Encontrado, retorna o objeto JSON do cadastro.
404 Not Found: Cadastro não encontrado.

PUT /api/pessoas/{id} - Atualizar telefone e e-mail
Atualiza o telefone e/ou e-mail de um cadastro existente.

* **Método:** `PUT`
* **Corpo da Requisição (JSON):**
    ```json
    {
      "novoTelefone": "+55(11)8888-7777",
      "novoEmail": "jsilva@novoemail.com"
    }
    ```

Respostas:
200 OK: Cadastro atualizado com sucesso.
400 Bad Request: Dados inválidos ou ID não encontrado.

DELETE /api/pessoas/{id} - Deletar um cadastro por ID
Remove um cadastro específico pelo seu id.

Método: DELETE

Parâmetro de URL: {id} - ID do cadastro.

Respostas:
204 No Content: Cadastro deletado com sucesso.
400 Bad Request: ID não encontrado.

DELETE /api/pessoas - Deletar todos os cadastros
Remove todos os cadastros da base de dados.

Método: DELETE

Respostas:
204 No Content: Todos os cadastros deletados com sucesso.

H2 Database Console
O projeto utiliza um banco de dados H2 em memória, que é ideal para desenvolvimento e testes. Você pode acessar o console web do H2 para visualizar os dados diretamente.
