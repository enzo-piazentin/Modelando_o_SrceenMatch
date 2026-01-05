package controlandoOAcessoEAEscritaNosDadosDaAplicacao;

public class aluno {
    private String nome;
    private double nota;
    private int totalDeNotas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota += nota;
        totalDeNotas++;
    }

    public double retornaMedia(){
        return nota/totalDeNotas;
    }
}
