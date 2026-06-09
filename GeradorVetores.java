import java.util.Arrays;
import java.util.Random;

public class GeradorVetores {

    private static final String[] NOMES = {
            "Ane", "Bruno", "Caroline", "Diana", "Everton", "Fernanda",
            "Gustavo", "Helena", "Igor", "Julia", "Klaus", "Larissa",
            "Maria", "Natalia", "Otavio", "Paula", "Ryan", "Sandra",
            "Thiago", "Ursula", "Victor", "Willy", "Xavier", "Yasmin", "Zeca"
    };

    private static final Random RANDOM = new Random(42); // seed fixa p/ reprodutibilidade

    // Gera vetor aleatório de Estudantes
    public static Estudante[] gerarAleatorio(int tamanho, long L) {
        Estudante[] vetor = new Estudante[tamanho];
        for (int i = 0; i < tamanho; i++) {
            int matricula = 10000 + i;
            String nome = NOMES[RANDOM.nextInt(NOMES.length)]
                    + " " + NOMES[RANDOM.nextInt(NOMES.length)];
            int nota = RANDOM.nextInt(11); // 0 a 10
            vetor[i] = new Estudante(matricula, nome, nota);
        }
        return vetor;
    }

    // Gera vetor já ordenado (usa Arrays.sort do Java como referência)
    public static Estudante[] gerarOrdenado(int tamanho) {
        Estudante[] vetor = gerarAleatorio(tamanho, 42L);
        Arrays.sort(vetor); // ordenação de referência
        return vetor;
    }

    // Gera vetor em ordem inversa
    public static Estudante[] gerarInvertido(int tamanho) {
        Estudante[] vetor = gerarOrdenado(tamanho);
        // Inverte in-place
        for (int i = 0, j = vetor.length - 1; i < j; i++, j--) {
            Estudante tmp = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = tmp;
        }
        return vetor;
    }

    // Copia um vetor (para não reutilizar saída já ordenada)
    public static Estudante[] copiar(Estudante[] original) {
        return Arrays.copyOf(original, original.length);
    }

    // Gera vetor aleatório de int[] (para experimento extra com QuickSort)
    public static int[] gerarInteiroAleatorio(int tamanho) {
        int[] vetor = new int[tamanho];
        for (int i = 0; i < tamanho; i++) {
            vetor[i] = RANDOM.nextInt(1_000_000);
        }
        return vetor;
    }

    public static int[] gerarInteiroOrdenado(int tamanho) {
        int[] vetor = gerarInteiroAleatorio(tamanho);
        Arrays.sort(vetor);
        return vetor;
    }

    public static int[] gerarInteiroInvertido(int tamanho) {
        int[] vetor = gerarInteiroOrdenado(tamanho);
        for (int i = 0, j = vetor.length - 1; i < j; i++, j--) {
            int tmp = vetor[i];
            vetor[i] = vetor[j];
            vetor[j] = tmp;
        }
        return vetor;
    }

    public static int[] copiarInteiro(int[] original) {
        return Arrays.copyOf(original, original.length);
    }
}
