# DesafioGFXIsrael

Aplicativo Android em **Java** para listagem de produtos consumindo a API pública Fake Store.

## Arquitetura

- MVVM (`ui`, `viewmodel`, `repository`, `network`, `model`, `domain`)
- Injeção de dependência manual por construtor
- Retrofit + Gson para consumo da API
- RecyclerView com cards e filtro por categoria
- Teste unitário em Java (JUnit)

## API utilizada

- Base URL: `https://fakestoreapi.com/`
- Endpoint: `GET /products`

## Requisitos de ambiente

- Android Studio (recomendado: Hedgehog+)
- JDK 11
- Android SDK:
  - `compileSdk`: 36
  - `targetSdk`: 36
  - `minSdk`: 24
- Gradle Wrapper (já incluído no projeto)

## Como rodar

1. Abra o projeto no Android Studio.
2. Aguarde o sync do Gradle.
3. Execute no emulador/dispositivo Android (API 24+).

## Como testar

No terminal da raiz do projeto:

```bash
./gradlew test
```

## Tratamento de erros implementado

- Sem internet (Wi-Fi/dados móveis indisponíveis)
- Erro de servidor (HTTP 5xx)
- Erro de API/resposta inválida
- Resposta vazia da API
- Mensagens amigáveis para o usuário
- Erro de carregamento de imagem com fallback textual

## Observação

Projeto implementado sem Kotlin, sem Jetpack Compose e sem frameworks de DI (Hilt/Dagger/Koin), conforme solicitado no desafio.