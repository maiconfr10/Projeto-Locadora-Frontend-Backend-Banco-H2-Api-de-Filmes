📽️ Fullstack Locadora – Sistema Completo de Empréstimo de Filmes

Projeto Fullstack desenvolvido para simular um sistema de locadora de filmes, incluindo backend poderoso em Java + Spring Boot e frontend em HTML/CSS/JavaScript, com integração total entre as camadas e consumo de API externa de filmes.

🚀 Tecnologias Utilizadas
Backend

Java

Spring Boot

Spring Web

Spring Validation

Spring Data JPA

DTOs

CORS configurado

Integração com API externa de filmes

CRUD completo (Cliente, Filme, Empréstimo)

Frontend

HTML

CSS

JavaScript

Fetch API

Integração direta com o backend

Integração com API externa de filmes

🏗️ Arquitetura do Projeto

O projeto segue o padrão MVC e boas práticas do Spring:

Models: Representam entidades como Cliente, Filme e Empréstimo

Repositories: Comunicação com o banco de dados

Services: Regras de negócio

Controllers: Pontos de entrada da API

DTOs: Utilizados para organizar dados de entrada e saída

CORS: Configurado para permitir o acesso do frontend

Integração com API Externa: Utilizada para obter informações reais de filmes

🎬 Funcionalidades Implementadas
✅ Backend

Cadastro, listagem, atualização e exclusão de clientes

Cadastro, listagem, atualização e exclusão de filmes

Registro de empréstimos e devoluções

Busca de filmes pela API externa

DTOs para entrada/saída padronizada

Respostas estruturadas

Tratamento de erros

CORS liberado para o frontend

🌐 Frontend

Tela para cadastro de clientes

Tela para cadastro de filmes

Tela de empréstimos

Consumo do backend via fetch

Requisições à API externa de filmes

Interface simples, organizada e responsiva

Confirmações e alertas amigáveis ao usuário

🔌 Integração com API Externa de Filmes

Tanto o backend quanto o frontend utilizam uma API pública de filmes para:

Buscar detalhes reais dos filmes

Preencher automaticamente informações como:

Nome

Categoria

Descrição

Imagem

Ano

Avaliação

Isso torna o sistema mais dinâmico e próximo de um ambiente real de produção.

📁 Estrutura Geral do Projeto

(Opcional — posso gerar automaticamente se quiser)

/backend
  /src
    /main/java
      /controllers
      /services
      /repositories
      /models
      /dtos
      /config
    application.properties

/frontend
  index.html
  styles.css
  script.js

▶️ Como Executar
1. Backend
mvn spring-boot:run


Backend sobe na porta:

http://localhost:8080

2. Frontend

Basta abrir o arquivo:

index.html


ou rodar em um servidor local:

npx live-server

📡 Principais Rotas da API
Clientes

GET /cliente

POST /cliente

PUT /cliente/{id}

DELETE /cliente/{id}

Filmes

GET /filme

POST /filme

PUT /filme/{id}

DELETE /filme/{id}

API externa de filmes

GET /filme/api/{titulo}

🎯 Objetivo do Projeto

Este sistema foi criado para fins de estudo e prática de:

Integração de frontend + backend

Consumo de API externa

Arquitetura limpa em projetos fullstack

Comunicação entre serviços

Desenvolvimento de CRUD completo

Uso de DTOs e boas práticas do Spring

📌 Status do Projeto

✔️ Finalizado
📦 Totalmente funcional
🔧 Aberto para melhorias e novas features

🤝 Contribuições

Sinta-se livre para abrir issues, sugerir melhorias ou enviar pull requests.

⭐ Autor

Maicon Felipe Ramos
📌 Desenvolvedor Fullstack
🚀 Sempre evoluindo!
