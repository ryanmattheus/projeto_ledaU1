public class Selectionsort {
    
    // Versão do slide — troca direta com o mínimo encontrado
    public static void sort(Estudante[] A) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (A[j].compareTo(A[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            swap(A, i, minIdx);
        }
    }
    // Versão estável — em vez de trocar diretamente,
    // desloca os elementos maiores para a direita e insere
    // o mínimo na posição correta (sem "saltar" iguais).
    public static void sortEstavel(Estudante[] A) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            // Encontra o índice do mínimo no trecho [i..n-1]
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (A[j].compareTo(A[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            // Guarda o mínimo e desloca os elementos para a direita
            // até a posição onde o mínimo estava, preservando a ordem
            Estudante min = A[minIdx];
            while (minIdx > i) {
                A[minIdx] = A[minIdx - 1];
                minIdx--;
            }
            A[i] = min;
        }
    }

    private static void swap(Estudante[] A, int i, int j) {
        Estudante tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
