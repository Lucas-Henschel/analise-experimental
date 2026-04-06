### UNIVERSIDADE REGIONAL DE BLUMENAU

### CENTRO DE CIÊNCIAS EXATAS E NATURAIS

### DEPARTAMENTO DE SISTEMAS E COMPUTAÇÃO

**Professor:** Gabriel Castellani de Oliveira
**Disciplina:** Análise de Algoritmos

---

# Lista de Exercícios 01

Desenvolva uma aplicação para comparar experimentalmente o desempenho de duas estruturas de dados lineares: uma baseada em vetor dinâmico e outra baseada em lista encadeada.

A aplicação deve permitir avaliar o comportamento dessas estruturas em diferentes tipos de operações, medindo o tempo de execução e possibilitando a análise dos resultados obtidos.

O objetivo deste exercício é identificar, na prática, como diferentes implementações de listas se comportam em cenários distintos de uso, relacionando os resultados experimentais com a teoria de complexidade de algoritmos e estruturas de dados.

---

## Estruturas por linguagem

Cada linguagem deverá utilizar suas estruturas equivalentes:

* **Java:** ArrayList e LinkedList
* **.NET (C#):** List<T> e LinkedList<T>
* **Python:** list e uma implementação de lista encadeada criada pelo aluno
* **Node.js (JavaScript):** Array e uma implementação de lista encadeada criada pelo aluno

---

## Entrega

Entregar no AVA:

* As classes desenvolvidas
* Documento mostrando os resultados entre as estruturas

---

## Questão 1

Compare a velocidade de inserção de novos elementos ao final das duas estruturas.

1. Após 20 testes:

    * Qual foi o tempo médio que cada lista demorou para ser preenchida?
    * Qual delas foi mais rápida?
    * Quantas vezes ela foi mais rápida que a outra?

2. No caso do ArrayList, avalie se alterar a capacidade inicial influencia no desempenho.

Realize 10 execuções para cada uma das seguintes capacidades iniciais:

* 10
* 1000
* 100.000

Calcule a média dos tempos e responda:

* Qual configuração foi mais rápida?
* Por que isso acontece?

---

## Questão 2

Repita o experimento anterior, mas agora cada inserção deve ocorrer em uma posição aleatória válida da lista.

1. Após 20 testes:

    * Qual foi o tempo médio que cada lista demorou para ser preenchida?
    * Qual delas foi mais rápida?
    * Quantas vezes ela foi mais rápida?

---

## Questão 3

Com as duas listas já preenchidas, avalie o tempo necessário para remover todos os elementos.

1. Removendo sempre o **primeiro elemento**:

    * Após 20 testes, qual foi o tempo médio para esvaziar cada estrutura?
    * Qual delas foi mais rápida?
    * Quantas vezes mais rápida?

2. Removendo sempre o **último elemento**:

    * Após 20 testes, qual foi o tempo médio para esvaziar cada estrutura?
    * Qual delas foi mais rápida?
    * Quantas vezes mais rápida?

---

## Questão 4

Com as listas preenchidas, remova elementos de índices aleatórios até que fiquem vazias.

1. Após 20 testes:

    * Qual foi o tempo médio que cada lista demorou para ser esvaziada?
    * Qual delas foi mais rápida?
    * Quantas vezes mais rápida?

---

## Questão 5

Com as listas preenchidas, realize **10.000 acessos a índices aleatórios válidos**.

1. Após 20 testes:

    * Qual foi o tempo médio para executar os acessos em cada estrutura?
    * Qual foi mais rápida?
    * Explique o resultado com base na organização interna de cada lista.

---

## Questão 6

Com base em todos os testes:

* Em quais cenários o ArrayList foi mais vantajoso?
* Em quais cenários o LinkedList foi mais vantajoso?
* Os resultados experimentais confirmaram a teoria esperada?
* Quais fatores práticos podem ter influenciado os resultados?
