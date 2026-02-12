import br.com.alura.screenmatch.calculo.CalculadoraDeTempo;
import br.com.alura.screenmatch.modelos.Filme;
import br.com.alura.screenmatch.modelos.Serie;

import java.util.Scanner;

public class app {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);

        Filme meuFilme = new Filme();
        meuFilme.setNome("Homem-Aranha: Através do Aranhaverso");
        meuFilme.setAnoDeLancamento(2023);
        meuFilme.setDuracaoEmMinutos(140);
        System.out.println("Duração do filme: "+ meuFilme.getDuracaoEmMinutos());

        meuFilme.exibeFihaTecnica();
        meuFilme.avalia(8);
        meuFilme.avalia(9);
        meuFilme.avalia(10);
        System.out.println("Avaliação do filme: "+ meuFilme.getTotalDeAvaliacao());
        System.out.println("Media do filme: "+ meuFilme.pegaMedia());


        Serie serie = new Serie();

        serie.setNome("Rick and Morty");
        serie.setAnoDeLancamento(2013);
        serie.setTemporadas(6);
        serie.setEpisodiosPorTemporada(10);
        serie.setMinutosPorEpisodio(25);
        serie.exibeFihaTecnica();
        System.out.println("Duração da série: "+ serie.getDuracaoEmMinutos());

        CalculadoraDeTempo calculadora = new CalculadoraDeTempo();

        calculadora.inclui(meuFilme);
        System.out.println(calculadora.getTempoTotal());

        System.out.println("-------------------");

        Filme outroFilme = new Filme();
        outroFilme.setNome("Cidade de Deus");
        outroFilme.setAnoDeLancamento(2002);
        outroFilme.setDuracaoEmMinutos(130);
        calculadora.inclui(outroFilme);
        calculadora.inclui(serie);
        System.out.println("Duração do filme: "+ outroFilme.getDuracaoEmMinutos());
    }
}
