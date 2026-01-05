package controlandoOAcessoEAEscritaNosDadosDaAplicacao;

public class livro {
    private String livro, autor;

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void exibirFichaTecinica(){
        System.out.println("Nome do Livro: " + livro);
        System.out.println("Autor do livro: "+ autor);
    }
}
