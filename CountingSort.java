public class CountingSort {

    // Counting Sort para Estudante[], usando o campo 'nota' (0..10) como chave.
    // Complexidade: O(n + k), onde k = 11 (intervalo das notas: 0 a 10).
    // Algoritmo estável — preserva a ordem relativa de estudantes com a mesma nota.
    public static void sort(Estudante[] A) {
        if (A == null || A.length <= 1) {
            return;
        }

        final int K = 11; // notas variam de 0 a 10

        // Passo 1: contar ocorrências de cada nota
        int[] C = new int[K];
        for (Estudante e : A) {
            C[e.getNota()]++;
        }

        // Passo 2: soma de prefixos — C[i] passa a indicar quantos elementos
        // têm nota <= i, ou seja, a posição final do último elemento com nota i.
        for (int i = 1; i < K; i++) {
            C[i] += C[i - 1];
        }

        // Passo 3: construir o array de saída percorrendo A de trás para frente
        // (garante estabilidade).
        // O critério de ordenação é nota DECRESCENTE, por isso usamos (K-1 - nota)
        // como chave efetiva: nota 10 -> chave 0, nota 0 -> chave 10.
        // Para simplificar, ordenamos primeiro por nota crescente e depois invertemos,
        // mantendo a estabilidade exigida pelo compareTo de Estudante.
        Estudante[] B = new Estudante[A.length];

        // Recalcula C para a chave invertida (nota decrescente)
        int[] C2 = new int[K];
        for (Estudante e : A) {
            int chave = (K - 1) - e.getNota(); // inverte: nota 10 -> 0, nota 0 -> 10
            C2[chave]++;
        }
        for (int i = 1; i < K; i++) {
            C2[i] += C2[i - 1];
        }

        // Percorre de trás para frente para manter estabilidade
        for (int j = A.length - 1; j >= 0; j--) {
            int chave = (K - 1) - A[j].getNota();
            B[C2[chave] - 1] = A[j];
            C2[chave]--;
        }

        // Copia B de volta para A
        System.arraycopy(B, 0, A, 0, A.length);
    }
}
