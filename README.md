# DesafioGFXIsrael

Aplicativo Android nativo em **Java** para consumir uma API pública de produtos, aplicar regra de filtro e exibir os dados em lista com `RecyclerView`.

## Objetivo

Este projeto foi desenvolvido para atender ao desafio prático mobile com foco em:

- arquitetura limpa e separação de responsabilidades;
- implementação em Java;
- consumo assíncrono de API REST;
- testabilidade da regra de negócio.

## Funcionalidades implementadas

- Listagem de produtos vindos da API pública Fake Store.
- Exibição em `RecyclerView` com card de item.
- Filtro de regra de negócio por categoria (seleção via `Spinner`).
- Estados de carregamento e lista vazia.
- Tratamento de falhas de rede/API com mensagens amigáveis.
- Fallback visual para erro de carregamento de imagem.

## Arquitetura e organização

O app segue **MVVM** com responsabilidades separadas:

- **View (`ui`)**: `MainActivity` observa estado da tela e renderiza UI.
- **ViewModel (`viewmodel`)**: `ProductViewModel` coordena carregamento e aplicação de filtro.
- **Repository (`repository`)**: `ProductRepository` decide como obter e transformar os dados.
- **Service/Data Source (`network`)**: `ApiService` (Retrofit) para chamada HTTP.
- **Model/Data (`model`)**: `ProductModel` representa payload da API.
- **Domain (`domain`)**: `Product` e `Category` representam regra e entidade de negócio.
- **DI (`di`)**: `AppComponent`, `NetworkModule` e `RepositoryModule` com Dagger 2.

## Requisitos do desafio x implementação

- **Consumo de API pública**: implementado com Retrofit (`GET /products`).
- **Visualização em lista**: implementado com `RecyclerView`.
- **Regra de negócio (filtro)**: implementado no `Repository` (`filterByCategory`).
- **Assincronismo em Java**: implementado com callback assíncrono do Retrofit (`enqueue`).
- **Linguagem principal Java**: regras, ViewModel, Repository e Activity em Java.
- **Padrão arquitetural**: MVVM implementado.
- **Teste unitário**: `ProductRepositoryTest` com JUnit 4 para lógica de filtro.

## API utilizada

- Base URL: `https://fakestoreapi.com/`
- Endpoint principal: `GET /products`

## Requisitos de ambiente

- Android Studio (recomendado versão recente)
- JDK 11
- Android SDK:
  - `compileSdk`: 36
  - `targetSdk`: 36
  - `minSdk`: 24
- Gradle Wrapper (já incluído no projeto)

## Como executar a aplicação

1. Clone o repositório:

```bash
git clone <URL_DO_REPOSITORIO>
```

2. Abra o projeto no Android Studio.
3. Aguarde o sync do Gradle terminar.
4. Conecte um dispositivo Android ou inicie um emulador (API 24+).
5. Clique em **Run** para instalar e abrir o app.

## Como usar a aplicação

1. Ao abrir o app, os produtos são carregados automaticamente.
2. Use o `Spinner` de filtro para selecionar uma categoria:
   - All
   - Men's clothing
   - Women's clothing
   - Jewelery
   - Electronics
3. A lista será atualizada conforme o filtro selecionado.
4. Em caso de erro (sem internet, API indisponível etc.), uma mensagem será exibida.

## Testes

Para executar os testes unitários:

```bash
./gradlew test
```

Teste implementado:

- `ProductRepositoryTest`: valida comportamento do filtro por categoria.

## Tratamento de erros implementado

- Sem internet (`NO_INTERNET`)
- Servidor indisponível (`SERVER_ERROR`, HTTP 5xx)
- Falha de API (`API_ERROR`)
- Resposta vazia (`EMPTY_RESPONSE`)
- Erro desconhecido (`UNKNOWN`)
- Falha de imagem com fallback textual no item da lista

## Tecnologias e bibliotecas

- Java 11
- AndroidX + Material Components
- RecyclerView
- Lifecycle (`ViewModel`, `LiveData`)
- Retrofit + Gson
- Glide
- Dagger 2
- JUnit 4

## Observações finais

- A regra de negócio do filtro foi aplicada por **categoria** (alternativa válida ao filtro por ID par previsto no enunciado).
- O projeto está estruturado para facilitar evolução e inclusão de novos testes.