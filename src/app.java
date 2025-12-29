import br.com.alura.screenmatch.modelos.Filme;

import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);

        Filme meuFilme = new Filme();
        meuFilme.setNome("Homem-Aranha: Através do Aranhaverso");
        meuFilme.setAnoDeLancamento(2023);
        meuFilme.setDuracaoEmMinutos(140);

        meuFilme.exibeFihaTecnica();
        meuFilme.avalia(8);
        meuFilme.avalia(9);
        meuFilme.avalia(10);
        System.out.println("Avaliação do filme: "+ meuFilme.getTotalDeAvaliacao());
        System.out.println("Media do filme: "+ meuFilme.pegaMedia());
        /*
        //meuFilme.somaDasAvaliacoes = 10;
        //meuFilme.totalDeAvaliacao = 1;
        //System.out.println(meuFilme.pegaMedia());
         */
    }
}
