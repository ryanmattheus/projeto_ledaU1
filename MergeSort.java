import java.util.Arrays;

public class MergeSort {

    // Versão Clássica O(n log n)
    public static void sort(Estudante[] A) {
        if (A == null || A.length <= 1) {
            return;
        }
        Estudante[] aux = new Estudante[A.length];
        mergeSortRecursivo(A, aux, 0, A.length - 1);
    }

    private static void mergeSortRecursivo(Estudante[] A, Estudante[] aux, int esq, int dir) {
        if (esq < dir) {
            int meio = esq + (dir - esq) / 2;
            mergeSortRecursivo(A, aux, esq, meio);
            mergeSortRecursivo(A, aux, meio + 1, dir);
            merge(A, aux, esq, meio, dir);
        }
    }

    private static void merge(Estudante[] A, Estudante[] aux, int esq, int meio, int dir) {
        for (int i = esq; i <= dir; i++) {
            aux[i] = A[i];
        }

        int i = esq;
        int j = meio + 1;
        int k = esq;

        while (i <= meio && j <= dir) {
            if (aux[i].compareTo(aux[j]) <= 0) {
                A[k] = aux[i];
                i++;
            } else {
                A[k] = aux[j];
                j++;
            }
            k++;
        }

        while (i <= meio) {
            A[k] = aux[i];
            k++;
            i++;
        }
    }

    // Versão TimSort do Java para Objetos
    public static void sortJavaTimSort(Estudante[] A) {
        Arrays.sort(A);
    }
}