package modeloOScreenMatch.horaDaPratica;

import java.util.Scanner;

public class horaDaPratica {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        String tiraPulo;
        Pessoa olaPessoa = new Pessoa();
        String pular = """
                --------------------
                """;


        System.out.println("Questão 1");
        System.out.println(olaPessoa.ola);

        System.out.println(pular);
        System.out.println("Questão 2");
        Calculadora calculadora = new Calculadora();


        System.out.println("Quem num vc quer?");
        int num = leitura.nextInt();
        calculadora.dobra(num);
        System.out.println("O dobro do número escolhido é: " + calculadora.retornaDobra());

        System.out.println(pular);
        System.out.println("Questão 3");
        Musica relatorio = new Musica();

        tiraPulo = leitura.nextLine();
        System.out.println("Qual album vc gosta: ");
        relatorio.nomeDoAlbum = leitura.nextLine().trim();

        System.out.println("Qual é o nome do Artista: ");
        relatorio.artista = leitura.nextLine().trim();

        System.out.println("Ano de lançamento desse album: ");
        relatorio.anoDeLancamento = leitura.nextInt();
        relatorio.pegaMedia(9);
        relatorio.pegaMedia(10);
        relatorio.pegaMedia(8.5);

        relatorio.infoAlbumArtistaEAno();
        System.out.println(String.format( "A media desse album é: %.2f", relatorio.daMedia()));

        System.out.println(pular);
        System.out.println("Questão 4");
        Carro Veiculo = new Carro();


        System.out.println("Qual é o seu veiculo: ");
        Veiculo.modelo = leitura.nextLine().trim();

        System.out.println("Qual cor é o seu veiculo: ");
        Veiculo.cor = leitura.nextLine().trim();

        System.out.println("Qual ano é o seu veiculo: ");
        Veiculo.anoDoCarro = leitura.nextInt();

        Veiculo.fichatecnica();

        System.out.println(pular);
        System.out.println("Questão 5");
        aluno cadatroAluno = new aluno();

        System.out.println("Nome: ");
        cadatroAluno.aluno = leitura.nextLine().trim();

        System.out.println("Idade: ");
        cadatroAluno.idade = leitura.nextInt();

        System.out.println("RM: ");
        cadatroAluno.rm = leitura.nextInt();
        tiraPulo = leitura.nextLine();

        System.out.println("Sala: ");
        cadatroAluno.sala = leitura.nextLine().trim();

        System.out.println("------------Ficha Técnica------------");
        cadatroAluno.fichatecnica();

    }
}
