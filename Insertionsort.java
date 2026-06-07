public class InsertionSort {
    // Versão do slide
    public static void sort(Estudante[] A) {
        int n = A.length;
        for (int j = 1; j < n; j++) {
            Estudante chave = A[j];
            int i = j - 1;
            // Desloca elementos maiores que a chave uma posição à direita
            while (i >= 0 && A[i].compareTo(chave) > 0) {
                A[i + 1] = A[i];
                i--;
            }
            A[i + 1] = chave;
        }
    }
}
