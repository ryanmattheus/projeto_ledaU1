import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    // =========================================================================
    // 1. Versão do Slide (Clássica para Objetos)
    // =========================================================================
    public static void sort(Estudante[] A) {
        if (A == null || A.length <= 1) {
            return;
        }
        quickSortRecursivo(A, 0, A.length - 1);
    }

    private static void quickSortRecursivo(Estudante[] A, int esq, int dir) {
        if (esq < dir) {
            int indicePivo = partition(A, esq, dir);
            quickSortRecursivo(A, esq, indicePivo - 1);
            quickSortRecursivo(A, indicePivo + 1, dir);
        }
    }

    private static int partition(Estudante[] A, int esq, int dir) {
        Estudante pivo = A[dir];
        int i = esq - 1;

        for (int j = esq; j < dir; j++) {
            if (A[j].compareTo(pivo) <= 0) {
                i++;
                swap(A, i, j);
            }
        }
        swap(A, i + 1, dir);
        return i + 1;
    }

    private static void swap(Estudante[] A, int i, int j) {
        Estudante temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    // =========================================================================
    // 2. Versão do Slide + Shuffle (Evita o pior caso O(n²) em vetores ordenados)
    // =========================================================================
    public static void sortComShuffle(Estudante[] A) {
        if (A == null || A.length <= 1) {
            return;
        }
        shuffle(A); // Aplica o embaralhamento antes de ordenar
        quickSortRecursivo(A, 0, A.length - 1);
    }

    // Algoritmo de Fisher-Yates para o Shuffle prático
    private static void shuffle(Estudante[] A) {
        Random rand = new Random(42); // Mesma seed estável do projeto
        for (int i = A.length - 1; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            swap(A, i, j);
        }
    }

    // =========================================================================
    // 3. Experimento Extra: QuickSort Clássico para tipos primitivos (int[])
    // =========================================================================
    public static void sortPrimitivo(int[] A) {
        if (A == null || A.length <= 1) {
            return;
        }
        quickSortPrimitivoRecursivo(A, 0, A.length - 1);
    }

    private static void quickSortPrimitivoRecursivo(int[] A, int esq, int dir) {
        if (esq < dir) {
            int indicePivo = partitionPrimitivo(A, esq, dir);
            quickSortPrimitivoRecursivo(A, esq, indicePivo - 1);
            // CORREÇÃO FEITA AQUI: Estava com "e" no final
            quickSortPrimitivoRecursivo(A, indicePivo + 1, dir);
        }
    }

    private static int partitionPrimitivo(int[] A, int esq, int dir) {
        int pivo = A[dir];
        int i = esq - 1;

        for (int j = esq; j < dir; j++) {
            if (A[j] <= pivo) {
                i++;
                swapPrimitivo(A, i, j);
            }
        }
        swapPrimitivo(A, i + 1, dir);
        return i + 1;
    }

    private static void swapPrimitivo(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    // =========================================================================
    // 4. Versão Java para Objetos — Arrays.sort usa TimSort internamente para
    //    objetos, mas serve como referência de ordenação nativa da plataforma.
    // =========================================================================
    public static void sortJavaObjeto(Estudante[] A) {
        Arrays.sort(A);
    }

    // =========================================================================
    // 5. Chamada do Dual-Pivot QuickSort nativo do Java para primitivos
    // =========================================================================
    public static void sortJavaPrimitivo(int[] A) {
        Arrays.sort(A);
    }
}