# Forum Hub

## Descrição do Projeto

O **Forum Hub** é uma API REST que desenvolvi como parte do desafio da Alura na trilha de Java com Spring Boot. A ideia é criar um fórum simples, mas funcional, onde dá pra criar, listar, atualizar e apagar tópicos, além de cadastrar e autenticar usuários. Tudo isso seguindo boas práticas, principalmente no quesito segurança.

## Status do Projeto

- ✅ Funcional: API funcional com autenticação JWT.

## Funcionalidades

- **Tópicos**: Criação de tópicos associados a um curso e usuário, listar, detalhar, atualizar e excluir.
- **Usuários**: Cadastro e login com autenticação via JWT.
- **Cursos**: Cadastro dos cursos para relacionar com os tópicos.
- **Validações**: Implementação de validações nas entradas de dados.

## Como acessar

- Documentação da API: [Swagger UI](http://localhost:8080/swagger-ui/index.html) *(após execução local)*

## Tecnologias Utilizadas

- **Backend**: Java 17, Spring Boot 3
- **Banco de Dados**: MySQL
- **Segurança**: Spring Security, JWT
- **Validações**: Jakarta Validation
- **Documentação**: SpringDoc 3
- **Ferramentas**: Flyway para migrações, Lombok pra facilitar o código e Insomnia para testar as APIs

## O que aprendi com esse projeto

Durante o desenvolvimento, consegui entender melhor vários pontos importantes no backend moderno:

- A parte de autenticação e segurança, principalmente com JWT no Spring Security, foi bem desafiadora. Tive que estudar bastante pra conseguir configurar filtros, validar tokens e integrar tudo certinho no fluxo do Spring.
- Usar o Flyway pra gerenciar as migrações do banco me ajudou a garantir que as mudanças fossem seguras e fáceis de aplicar.
- Também gostei muito de usar o SpringDoc para gerar a documentação automática da API. Isso facilita bastante a visualização e o teste dos endpoints.

## Onde tive mais dificuldade

- Configurar a autenticação JWT não foi fácil. Entender como o Spring Security trata filtros e exceções exigiu bastante paciência e tentativa e erro.
- Fazer o mapeamento correto entre as entidades do meu sistema e o objeto de segurança do Spring (UserDetails) gerou alguns erros chatos de cast que precisei resolver com cuidado.

No geral, esse projeto foi muito importante para eu ganhar experiência real com desenvolvimento backend em Java, especialmente com foco em segurança e arquitetura bem estruturada.

## Pessoas Contribuidoras

- Projeto desenvolvido por Robson Durães

## Licença

Este projeto está licenciado sob a Licença MIT.

---

