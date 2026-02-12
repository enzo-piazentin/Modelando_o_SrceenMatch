package reaproveitandoCaracteristicasEComportamento;

import java.util.Scanner;

public class TesteDeCarro {
    public static void main(String[] args){
        Scanner leitura = new Scanner(System.in);
        ModeloCarro modeloCarro = new ModeloCarro();
        Carro carro = new Carro();

        System.out.println("Qual é a marca do seu carro?");
        String marca =leitura.nextLine().trim();
        modeloCarro.setMarca(marca);

        System.out.println("Qual é o modelo do seu carro?");
        String modelo = leitura.nextLine().trim();
        modeloCarro.setModelo(modelo);

        System.out.println("Qual é a cor do seu carro?");
        String cor = leitura.nextLine().trim();
        modeloCarro.setCor(cor);

        System.out.println("Qual é o tipo de combustível do seu carro?");
        String tipoCombustivel = leitura.nextLine().trim();
        modeloCarro.setTipoCombustivel(tipoCombustivel);

        System.out.println("Digite o preço do carro no ano 1:");
        double precoA1 = leitura.nextInt();

        System.out.println("Digite o preço do carro no ano 2:");
        double precoAno2 = leitura.nextInt();

        System.out.println("Digite o preço do carro no ano 3:");
        double precoAno3 = leitura.nextInt();

        carro.definirPrecos(precoA1, precoAno2, precoAno3);
        carro.calcularMaiorPreco();
        carro.calcularMenorPreco();

        modeloCarro.mostrarInformacoesAdd();
        carro.exibirFichaTecnica();

    }
}
