public class Estudante implements Comparable<Estudante> {

    private int matricula;
    private String nome;
    private int nota; // 0 a 10

    public Estudante(int matricula, String nome, int nota) {
        this.matricula = matricula;
        this.nome = nome;
        this.nota = nota;
    }

    // Critério: nota decrescente → nome crescente → matrícula crescente
    @Override
    public int compareTo(Estudante outro) {
        // 1. Nota decrescente
        if (this.nota != outro.nota) {
            return outro.nota - this.nota;
        }
        // 2. Nome crescente (empate de nota)
        int cmpNome = this.nome.compareTo(outro.nome);
        if (cmpNome != 0) {
            return cmpNome;
        }
        // 3. Matrícula crescente (empate de nota e nome)
        return this.matricula - outro.matricula;
    }

    public int getMatricula() { return matricula; }
    public String getNome()   { return nome; }
    public int getNota()      { return nota; }

    @Override
    public String toString() {
        return "Estudante{matricula=" + matricula
                + ", nome='" + nome + "'"
                + ", nota=" + nota + "}";
    }
}
