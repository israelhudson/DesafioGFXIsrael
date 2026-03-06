# DesafioGFXIsrael
Desafio para vaga de emprego na GFX Consultoria

# Desafio Prático Mobile
Tempo Limite de Entrega: 3 Horas

Objetivo do Desafio
Desenvolver um aplicativo Android que consuma dados de uma API pública, aplique regras de negócio e apresente o resultado em uma lista. O foco da avaliação está na qualidade arquitetural em Java, na separação de responsabilidades (SOLID) e na testabilidade do código.

Requisitos Funcionais Obrigatórios
Consumo de API : Consumir uma API REST pública (Ex: JSONPlaceholder ou similar) para buscar uma lista de dados (Ex: Posts, Usuários, etc.).
Visualização : Exibir os dados da API em uma lista na tela (utilize RecyclerView).
Regra de Negócio (Filtro) : Implementar um filtro na tela que permita ao usuário exibir apenas os itens cujo ID seja par (ou outra regra simples que force lógica no Service ou Repository).
Assincronismo : Gerenciar a chamada de rede e a atualização da UI utilizando padrões de concorrência em Java (Ex: AsyncTask).

Requisitos Arquiteturais e de Qualidade
A implementação deve seguir os seguintes critérios:
Linguagem Principal : A maior parte da Lógica de Negócio, o Repository e a Activity/Fragment principal devem ser implementados em Java.
Padrão Arquitetural : O projeto deve ser estruturado utilizando o padrão MVVM (Model-View-ViewModel) ou MVP (Model-View-Presenter). O uso de ViewModel do Jetpack é fortemente incentivado.
Separação de Responsabilidades : Deve haver uma separação clara:
•⁠  ⁠View : Responsável apenas por desenhar a UI.
•⁠  ⁠Repository : Responsável por decidir a origem dos dados (API, local).
•⁠  ⁠Service/Data Source : Implementação da chamada de rede e conversão de dados.
Organização : Uso de convenções de nomenclatura e boa organização do código Java.

Requisitos de Testabilidade
Testes Unitários : Implementar pelo menos um Teste Unitário usando JUnit 4 ou 5 para a Lógica de Negócio no Repository ou Service (ex: testar se a função de filtro está correta). O teste deve ser em Java.

Entrega
O candidato deve enviar o link para um repositório GitHub contendo a solução completa e um arquivo compactado com todo código fonte.
A avaliação será baseada na funcionalidade, qualidade do design e na implementação dos testes, priorizando a aderência a boas práticas em Java.