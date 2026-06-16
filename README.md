# Projeto LEDA - Unidade I

## Análise Experimental de Algoritmos de Ordenação e Busca

### Integrantes

* Ane Aille Silva Souza
* Anna Caroline Barbosa da Silva
* Jose Everton Barbosa
* Ryan Matheus Souto Vidal de Negreiros


### Disciplina

Laboratório de Estruturas de Dados (LEDA)

### Descrição

Este projeto tem como objetivo implementar, executar e analisar experimentalmente diferentes algoritmos de ordenação e busca utilizando registros de estudantes.

Os experimentos avaliam o desempenho dos algoritmos em diferentes cenários de entrada, permitindo comparar seus comportamentos práticos e suas complexidades teóricas.

---

## Estrutura do Projeto

### Classe de Domínio

**Estudante.java**

Representa um estudante contendo:

* Matrícula
* Nome
* Nota

A ordenação segue o seguinte critério:

1. Nota em ordem decrescente;
2. Nome em ordem crescente;
3. Matrícula em ordem crescente.

---

## Algoritmos de Ordenação Implementados

### Bubble Sort

* Versão tradicional
* Versão otimizada (interrompe quando não ocorrem trocas)

### Selection Sort

* Versão tradicional
* Versão estável

### Insertion Sort

* Implementação clássica

### Merge Sort

* Implementação clássica
* Comparação com o TimSort da biblioteca Java

### Quick Sort

* Versão clássica
* Versão com Shuffle (Fisher-Yates)
* Versão utilizando Dual Pivot QuickSort do Java

### Counting Sort

* Implementação adaptada para notas de 0 a 10
* Mantém estabilidade dos registros

---

## Algoritmos de Busca Implementados

### Busca Linear

* Iterativa
* Recursiva
* Duas Pontas

### Busca Binária

* Iterativa
* Recursiva

---

## Geração dos Dados

Os vetores são gerados automaticamente através da classe:

**GeradorVetores.java**

São produzidos três tipos de entrada:

* Vetor aleatório
* Vetor ordenado
* Vetor invertido

Os estudantes possuem:

* Matrículas sequenciais
* Nomes gerados aleatoriamente
* Notas entre 0 e 10

---

## Configuração dos Experimentos

### Tamanhos dos Vetores

* 25.000 elementos
* 50.000 elementos
* 100.000 elementos

### Execuções

* 25 execuções por experimento
* 5 execuções de warm-up descartadas
* 20 execuções válidas utilizadas na análise

### Buscas

* 10 elementos-alvo selecionados para cada experimento

---

## Métricas Avaliadas

Para cada algoritmo são coletados:

* Tempo médio de execução
* Melhor caso observado
* Pior caso observado
* Comparação entre diferentes tipos de entrada

---

## Organização dos Arquivos

```text
projeto_ledaU1/
│
├── Main.java
├── Estudante.java
├── GeradorVetores.java
│
├── Buscas.java
│
├── BubbleSort.java
├── SelectionSort.java
├── InsertionSort.java
├── MergeSort.java
├── QuickSort.java
├── CountingSort.java
│
└── README.md
```

---

## Como Executar

### Compilação

```bash
javac *.java
```

### Execução

```bash
java Main
```

---

## Complexidades Teóricas

| Algoritmo      | Melhor Caso | Caso Médio | Pior Caso  |
| -------------- | ----------- | ---------- | ---------- |
| Bubble Sort    | O(n)        | O(n²)      | O(n²)      |
| Selection Sort | O(n²)       | O(n²)      | O(n²)      |
| Insertion Sort | O(n)        | O(n²)      | O(n²)      |
| Merge Sort     | O(n log n)  | O(n log n) | O(n log n) |
| Quick Sort     | O(n log n)  | O(n log n) | O(n²)      |
| Counting Sort  | O(n + k)    | O(n + k)   | O(n + k)   |
| Busca Linear   | O(1)        | O(n)       | O(n)       |
| Busca Binária  | O(1)        | O(log n)   | O(log n)   |

---

## Objetivo Acadêmico

O projeto busca relacionar os resultados experimentais obtidos com a teoria estudada em sala de aula, permitindo observar na prática o comportamento dos algoritmos de ordenação e busca em diferentes cenários e volumes de dados.
