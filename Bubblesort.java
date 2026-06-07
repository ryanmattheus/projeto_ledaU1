public class Bubblesort {
    // Versão do slide — sempre executa os dois loops completos
    public static void sort(Estudante[] A) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (A[j].compareTo(A[j + 1]) > 0) {
                    swap(A, j, j + 1);
                }
            }
        }
    }
    // Versão otimizada — interrompe se não houve trocas na passagem
    public static void sortOtimizado(Estudante[] A) {
        int n = A.length;
        for (int i = 0; i < n - 1; i++) {
            boolean houveTroca = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (A[j].compareTo(A[j + 1]) > 0) {
                    swap(A, j, j + 1);
                    houveTroca = true;
                }
            }
            if (!houveTroca) break; // vetor já ordenado, para cedo
        }
    }
    private static void swap(Estudante[] A, int i, int j) {
        Estudante tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
}
