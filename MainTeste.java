public class MainTeste {

    static final int[] TAMANHOS = { 1_000 };
    static final int TOTAL_EXEC = 25;
    static final int WARMUP = 5;
    static final int EXEC_VALIDAS = TOTAL_EXEC - WARMUP; // 20

    public static void main(String[] args) {

        System.out.println("=================================================");
        System.out.println("  EXPERIMENTOS DE ORDENACAO");
        System.out.println("  " + EXEC_VALIDAS + " execucoes validas (+"
                + WARMUP + " warm-up descartadas)");
        System.out.println("=================================================");

        for (int tamanho : TAMANHOS) {

            System.out.println("\n\n#################################################");
            System.out.println("  TAMANHO: " + tamanho + " elementos");
            System.out.println("#################################################");

            System.out.println("  Gerando vetores...");
            Estudante[] baseAleatorio = GeradorVetores.gerarAleatorio(tamanho, 7L);
            Estudante[] baseOrdenado = GeradorVetores.gerarOrdenado(tamanho);
            Estudante[] baseInvertido = GeradorVetores.gerarInvertido(tamanho);

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

        System.out.println("\n\n=================================================");
        System.out.println("  FIM DOS EXPERIMENTOS");
        System.out.println("=================================================");
    }

    // ── Método central de medição ──────────────────────────────────────────
    static void medir(String nomeAlg, String cenario,
                      Estudante[] base, int tipo) {

        System.out.println("\n  >> " + nomeAlg.trim() + " | " + cenario.trim());
        System.out.println("     Exec  | Tempo (ns)              | Tempo (ms)");
        System.out.println("     " + "-".repeat(56));

        long somaTempos = 0;
        int execValida = 1;
        boolean estourou = false;

        for (int exec = 0; exec < TOTAL_EXEC; exec++) {
            Estudante[] copia = GeradorVetores.copiar(base);

            long inicio = System.nanoTime();
            try {
                switch (tipo) {
                    case 0: BubbleSort.sort(copia); break;
                    case 1: BubbleSort.sortOtimizado(copia); break;
                    case 2: SelectionSort.sort(copia); break;
                    case 3: SelectionSort.sortEstavel(copia); break;
                    case 4: InsertionSort.sort(copia); break;
                    case 5: MergeSort.sort(copia); break;
                    case 6: MergeSort.sortJavaTimSort(copia); break;
                    case 7: QuickSort.sort(copia); break;
                    case 8: QuickSort.sortComShuffle(copia); break;
                }
            } catch (StackOverflowError e) {
                estourou = true;
                break;
            }
            long fim = System.nanoTime();
            long tempoNs = fim - inicio;
            double tempoMs = tempoNs / 1_000_000.0;

            if (exec < WARMUP) {
                System.out.printf("     W%-3d  | %23d ns | %10.3f ms  (warm-up)%n",
                        exec + 1, tempoNs, tempoMs);
            } else {
                somaTempos += tempoNs;
                System.out.printf("     #%-3d  | %23d ns | %10.3f ms%n",
                        execValida, tempoNs, tempoMs);
                execValida++;
            }
        }

        if (estourou) {
            System.out.println("     >> CRASH: StackOverflowError (Estouro de Pilha recursiva)");
        } else {
            double mediaMs = (somaTempos / (double) EXEC_VALIDAS) / 1_000_000.0;
            System.out.println("     " + "-".repeat(56));
            System.out.printf("     Soma  (%d exec. validas)  : %d ns  =  %.3f ms%n",
                    EXEC_VALIDAS, somaTempos, somaTempos / 1_000_000.0);
            System.out.printf("     MEDIA (%d exec. validas)  :              %10.3f ms%n",
                    EXEC_VALIDAS, mediaMs);
        }
    }

    // Método para medir o experimento com int[]
    static void medirPrimitivo(String nomeAlg, String cenario, int[] base, int tipo) {
        System.out.println("\n  >> " + nomeAlg.trim() + " | " + cenario.trim());
        System.out.println("     Exec  | Tempo (ns)              | Tempo (ms)");
        System.out.println("     " + "-".repeat(56));

        long somaTempos = 0;
        int execValida = 1;
        boolean estourou = false;

        for (int exec = 0; exec < TOTAL_EXEC; exec++) {
            int[] copia = GeradorVetores.copiarInteiro(base);

            long inicio = System.nanoTime();
            try {
                switch (tipo) {
                    case 0: QuickSort.sortPrimitivo(copia); break;
                    case 1: QuickSort.sortJavaPrimitivo(copia); break;
                }
            } catch (StackOverflowError e) {
                estourou = true;
                break;
            }
            long fim = System.nanoTime();
            long tempoNs = fim - inicio;
            double tempoMs = tempoNs / 1_000_000.0;

            if (exec < WARMUP) {
                System.out.printf("     W%-3d  | %23d ns | %10.3f ms  (warm-up)%n",
                        exec + 1, tempoNs, tempoMs);
            } else {
                somaTempos += tempoNs;
                System.out.printf("     #%-3d  | %23d ns | %10.3f ms%n",
                        execValida, tempoNs, tempoMs);
                execValida++;
            }
        }

        if (estourou) {
            System.out.println("     >> CRASH: StackOverflowError (Estouro de Pilha recursiva)");
        } else {
            double mediaMs = (somaTempos / (double) EXEC_VALIDAS) / 1_000_000.0;
            System.out.println("     " + "-".repeat(56));
            System.out.printf("     Soma  (%d exec. validas)  : %d ns  =  %.3f ms%n",
                    EXEC_VALIDAS, somaTempos, somaTempos / 1_000_000.0);
            System.out.printf("     MEDIA (%d exec. validas)  :              %10.3f ms%n",
                    EXEC_VALIDAS, mediaMs);
        }
    }

    static void cabecalho(String titulo) {
        System.out.println("\n\n══ " + titulo
                + " ═══════════════════════════════════════");
    }
}