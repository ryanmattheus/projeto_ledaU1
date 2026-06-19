
public class Buscas {

    // 1. Busca Linear Iterativa
    public static int linearIterativa(Estudante[] vetor, Estudante alvo) {
        for (int i = 0; i < vetor.length; i++) {
            if (vetor[i].compareTo(alvo) == 0) {
                return i;
            }
        }
        return -1;
    }

    // 2. Busca Linear Recursiva
    public static int linearRecursiva(Estudante[] vetor, Estudante alvo) {
        return linearRecursivaAux(vetor, alvo, 0);
    }

    private static int linearRecursivaAux(Estudante[] vetor, Estudante alvo, int idx) {
        // Caso base: índice fora do array = não encontrado
        if (idx >= vetor.length) {
            return -1;
        }
        // Caso base: elemento encontrado
        if (vetor[idx].compareTo(alvo) == 0) {
            return idx;
        }
        // Caso recursivo: avança para o próximo índice
        return linearRecursivaAux(vetor, alvo, idx + 1);
    }

    // 3. Busca Binária Iterativa
    public static int binariaIterativa(Estudante[] vetor, Estudante alvo) {
        int esq = 0;
        int dir = vetor.length - 1;

        while (esq <= dir) {
            int meio = esq + (dir - esq) / 2; // evita overflow
            int cmp = vetor[meio].compareTo(alvo);

            if (cmp == 0) {
                return meio; // encontrado
            } else if (cmp < 0) {
                // vetor[meio] < alvo = alvo está na metade DIREITA
                esq = meio + 1;
            } else {
                // vetor[meio] > alvo = alvo está na metade ESQUERDA
                dir = meio - 1;
            }
        }

        return -1; // não encontrado
    }

    // 4. Busca Binária Recursiva
    public static int binariaRecursiva(Estudante[] vetor, Estudante alvo) {
        return binariaRecursivaAux(vetor, alvo, 0, vetor.length - 1);
    }

    // Auxiliar recursivo da busca binária.
    private static int binariaRecursivaAux(Estudante[] vetor, Estudante alvo,
            int esq, int dir) {
        // Caso base: intervalo vazio = não encontrado
        if (esq > dir) {
            return -1;
        }

        int meio = esq + (dir - esq) / 2;
        int cmp = vetor[meio].compareTo(alvo);

        if (cmp == 0) {
            return meio; // encontrado
        } else if (cmp < 0) {
            return binariaRecursivaAux(vetor, alvo, meio + 1, dir); // metade direita
        } else {
            return binariaRecursivaAux(vetor, alvo, esq, meio - 1); // metade esquerda
        }
    }

     //5. Busca Linear Iterativa Duas Pontas
    
    public static int linearDuasPontas(Estudante[] vetor, Estudante alvo) {
        int esq = 0;
        int dir = vetor.length - 1;

        while (esq <= dir) {
            // Verifica ponta esquerda
            if (vetor[esq].compareTo(alvo) == 0)
                return esq;

            // Verifica ponta direita (só se for um índice diferente)
            if (vetor[dir].compareTo(alvo) == 0)
                return dir;

            esq++;
            dir--;
        }

        return -1; // não encontrado
    }
}
