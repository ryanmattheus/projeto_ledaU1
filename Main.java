import java.util.Arrays;
//comentarioteste
public class Main {

    static final int[] TAMANHOS = { 25_000, 50_000, 100_000 };
    static final int TOTAL_EXEC = 25;
    static final int WARMUP = 5;
    static final int EXEC_VALIDAS = TOTAL_EXEC - WARMUP; // 20
    static final int AMOSTRAS_BUSCA = 10;

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("  EXPERIMENTOS DE ORDENACAO");
        System.out.println("  Tamanhos: 25k, 50k, 100k");
        System.out.println("  " + EXEC_VALIDAS + " execucoes validas (+"
                + WARMUP + " warm-up descartadas)");
        System.out.println("=================================================");

        for (int tamanho : TAMANHOS) {

            System.out.println("\n\n##############");
            System.out.println("  TAMANHO: " + tamanho + " elementos");
            System.out.println("##################");

            // Gera os três vetores base para este tamanho
            System.out.println("  Gerando vetores...");
            Estudante[] baseAleatorio = GeradorVetores.gerarAleatorio(tamanho, 42L);
            Estudante[] baseOrdenado = GeradorVetores.gerarOrdenado(tamanho);
            Estudante[] baseInvertido = GeradorVetores.gerarInvertido(tamanho);

            // ── Buscas ────────────────────────────────────────────────────
            cabecalho("BUSCAS");

            // Vetor desordenado — para as buscas lineares
            Estudante[] baseDesordenado = GeradorVetores.gerarAleatorio(tamanho, 42L);
            Estudante[] baseOrdenadoBusca = GeradorVetores.copiar(baseDesordenado);            // Vetor ordenado — obrigatório para as buscas binárias
            Arrays.sort(baseOrdenadoBusca);

            // Alvos: pega AMOSTRAS_BUSCA elementos que existem no vetor
            Estudante[] alvos = escolherAlvos(baseOrdenadoBusca, AMOSTRAS_BUSCA);

            // Buscas lineares (no vetor DESORDENADO)
            medirBusca("Linear Iterativa  ", "desordenado", baseDesordenado, alvos, 0);
            medirBusca("Linear Recursiva  ", "desordenado", baseDesordenado, alvos, 1);
            medirBusca("Linear Duas Pontas", "desordenado", baseDesordenado, alvos, 2);

            // Buscas binárias (no vetor ORDENADO)
            medirBusca("Binaria Iterativa ", "ordenado   ", baseOrdenadoBusca, alvos, 3);
            medirBusca("Binaria Recursiva ", "ordenado   ", baseOrdenadoBusca, alvos, 4);

            // Comparação extra: linear no vetor ordenado (para ver a diferença)
            medirBusca("Linear Iter (ord) ", "ordenado   ", baseOrdenadoBusca, alvos, 0);

            // ── Bubble Sort ────────────────────────────────────────────────
            cabecalho("BUBBLE SORT");
            medir("BubbleSort Normal   ", "aleatorio", baseAleatorio, 0);
            medir("BubbleSort Normal   ", "ordenado  ", baseOrdenado, 0);
            medir("BubbleSort Normal   ", "invertido ", baseInvertido, 0);
            medir("BubbleSort Otimizado", "aleatorio ", baseAleatorio, 1);
            medir("BubbleSort Otimizado", "ordenado  ", baseOrdenado, 1);
            medir("BubbleSort Otimizado", "invertido ", baseInvertido, 1);

            // ── Selection Sort ─────────────────────────────────────────────
            cabecalho("SELECTION SORT");
            medir("SelectionSort Normal ", "aleatorio", baseAleatorio, 2);
            medir("SelectionSort Normal ", "ordenado  ", baseOrdenado, 2);
            medir("SelectionSort Normal ", "invertido ", baseInvertido, 2);
            medir("SelectionSort Estavel", "aleatorio", baseAleatorio, 3);
            medir("SelectionSort Estavel", "ordenado  ", baseOrdenado, 3);
            medir("SelectionSort Estavel", "invertido ", baseInvertido, 3);

            // ── Insertion Sort ─────────────────────────────────────────────
            cabecalho("INSERTION SORT");
            medir("InsertionSort        ", "aleatorio", baseAleatorio, 4);
            medir("InsertionSort        ", "ordenado  ", baseOrdenado, 4);
            medir("InsertionSort        ", "invertido ", baseInvertido, 4);

            // ── Merge Sort / Tim Sort ──────────────────────────────────────
            cabecalho("MERGE SORT / TIM SORT");
            medir("MergeSort Classico   ", "aleatorio", baseAleatorio, 5);
            medir("MergeSort Classico   ", "ordenado  ", baseOrdenado, 5);
            medir("MergeSort Classico   ", "invertido ", baseInvertido, 5);
            medir("TimSort (Java)       ", "aleatorio", baseAleatorio, 6);
            medir("TimSort (Java)       ", "ordenado  ", baseOrdenado, 6);
            medir("TimSort (Java)       ", "invertido ", baseInvertido, 6);

            // ── Quick Sort ─────────────────────────────────────────────────
            cabecalho("QUICK SORT (OBJETOS)");
            medir("QuickSort Slide      ", "aleatorio", baseAleatorio, 7);
            medir("QuickSort Slide      ", "ordenado  ", baseOrdenado, 7);
            medir("QuickSort Slide      ", "invertido ", baseInvertido, 7);
            medir("QuickSort + Shuffle  ", "aleatorio", baseAleatorio, 8);
            medir("QuickSort + Shuffle  ", "ordenado  ", baseOrdenado, 8);
            medir("QuickSort + Shuffle  ", "invertido ", baseInvertido, 8);

            // ── Quick Sort (Extra Primitivos - int[]) ──────────────────────
            cabecalho("QUICK SORT (EXTRA PRIMITIVOS - int[])");
            int[] baseIntAleatorio = GeradorVetores.gerarInteiroAleatorio(tamanho);
            int[] baseIntOrdenado = GeradorVetores.gerarInteiroOrdenado(tamanho);
            int[] baseIntInvertido = GeradorVetores.gerarInteiroInvertido(tamanho);

            medirPrimitivo("QuickSort Primitivo  ", "aleatorio", baseIntAleatorio, 0);
            medirPrimitivo("QuickSort Primitivo  ", "ordenado  ", baseIntOrdenado, 0);
            medirPrimitivo("QuickSort Primitivo  ", "invertido ", baseIntInvertido, 0);
            medirPrimitivo("QuickSort Java Dual  ", "aleatorio", baseIntAleatorio, 1);
            medirPrimitivo("QuickSort Java Dual  ", "ordenado  ", baseIntOrdenado, 1);
            medirPrimitivo("QuickSort Java Dual  ", "invertido ", baseIntInvertido, 1);
        }

    }

    // ── Método central de medição ──────────────────────────────────────────
    static void medir(String nomeAlg, String cenario,
                      Estudante[] base, int tipo) {
        long somaTempos = 0;
        boolean estourou = false;

        for (int exec = 0; exec < TOTAL_EXEC; exec++) {
            Estudante[] copia = GeradorVetores.copiar(base);

            long inicio = System.nanoTime();
            try {
                switch (tipo) {
                    case 0:
                        BubbleSort.sort(copia);
                        break;
                    case 1:
                        BubbleSort.sortOtimizado(copia);
                        break;
                    case 2:
                        SelectionSort.sort(copia);
                        break;
                    case 3:
                        SelectionSort.sortEstavel(copia);
                        break;
                    case 4:
                        InsertionSort.sort(copia);
                        break;
                    case 5:
                        MergeSort.sort(copia);
                        break;
                    case 6:
                        MergeSort.sortJavaTimSort(copia);
                        break;
                    case 7:
                        QuickSort.sort(copia);
                        break;
                    case 8:
                        QuickSort.sortComShuffle(copia);
                        break;
                }
            } catch (StackOverflowError e) {
                estourou = true;
                break;
            }
            long fim = System.nanoTime();

            if (exec >= WARMUP) {
                somaTempos += (fim - inicio);
            }
        }

        if (estourou) {
            System.out.printf("  %-22s | %-9s | StackOverflowError (pilha estourou)%n",
                    nomeAlg, cenario);
        } else {
            double mediaMs = (somaTempos / (double) EXEC_VALIDAS) / 1_000_000.0;
            System.out.printf("  %-22s | %-9s | media: %10.3f ms%n",
                    nomeAlg, cenario, mediaMs);
        }
    }

    // Método para medir o experimento com int[]
    static void medirPrimitivo(String nomeAlg, String cenario, int[] base, int tipo) {
        long somaTempos = 0;
        boolean estourou = false;

        for (int exec = 0; exec < TOTAL_EXEC; exec++) {
            int[] copia = GeradorVetores.copiarInteiro(base);

            long inicio = System.nanoTime();
            try {
                switch (tipo) {
                    case 0:
                        QuickSort.sortPrimitivo(copia);
                        break;
                    case 1:
                        QuickSort.sortJavaPrimitivo(copia);
                        break;
                }
            } catch (StackOverflowError e) {
                estourou = true;
                break;
            }
            long fim = System.nanoTime();

            if (exec >= WARMUP) {
                somaTempos += (fim - inicio);
            }
        }

        if (estourou) {
            System.out.printf("  %-22s | %-9s | StackOverflowError (pilha estourou)%n",
                    nomeAlg, cenario);
        } else {
            double mediaMs = (somaTempos / (double) EXEC_VALIDAS) / 1_000_000.0;
            System.out.printf("  %-22s | %-9s | media: %10.3f ms%n",
                    nomeAlg, cenario, mediaMs);
        }
    }

    // tipo: 0=LinearIter  1=LinearRec  2=DuasPontas 3=BinariaIter 4=BinariaRec
    static void medirBusca(String nomeAlg, String cenario,
                           Estudante[] vetor, Estudante[] alvos, int tipo) {
        long somaTempos = 0;
        boolean estourou = false;

        for (int exec = 0; exec < TOTAL_EXEC; exec++) {
            long inicio = System.nanoTime();
            try {
                for (Estudante alvo : alvos) {
                    switch (tipo) {
                        case 0:
                            Buscas.linearIterativa(vetor, alvo);
                            break;
                        case 1:
                            Buscas.linearRecursiva(vetor, alvo);
                            break;
                        case 2:
                            Buscas.linearDuasPontas(vetor, alvo);
                            break;
                        case 3:
                            Buscas.binariaIterativa(vetor, alvo);
                            break;
                        case 4:
                            Buscas.binariaRecursiva(vetor, alvo);
                            break;
                    }
                }
            } catch(StackOverflowError e) {
                estourou = true;
                break;
            }
            long fim = System.nanoTime();

            if (exec >= WARMUP) {
                somaTempos += (fim - inicio);
            }
        }
        if (estourou) {
            System.out.printf("  %-22s | %-11s | StackOverflowError (pilha estourou)%n",
                    nomeAlg, cenario);
        } else {
            double mediaMs = (somaTempos / (double) EXEC_VALIDAS / AMOSTRAS_BUSCA)
                    / 1_000_000.0;
            System.out.printf("  %-22s | %-11s | media: %10.6f ms%n",
                    nomeAlg, cenario, mediaMs);
        }
    }
    static Estudante[] escolherAlvos(Estudante[] vetor, int qtd) {
        int passo = vetor.length / qtd;
        Estudante[] alvos = new Estudante[qtd];
        for (int i = 0; i < qtd; i++) {
            alvos[i] = vetor[i * passo];
        }
        return alvos;
    }
    static void cabecalho(String titulo) {
        System.out.println("\n── " + titulo
                + " ─────────────────────────────────────");
        System.out.printf("  %-22s | %-9s | %s%n",
                "Algoritmo", "Cenario", "Tempo medio");
        System.out.println("  " + "-".repeat(55));
    }
}