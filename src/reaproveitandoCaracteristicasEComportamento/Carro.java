package reaproveitandoCaracteristicasEComportamento;

public class Carro {
    private String modelo;
    private double procoAno1, precoAno2, precoAno3;

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }


    public void definirPrecos(double precoAno1, double precoAno2, double precoAno3) {
        this.procoAno1 = precoAno1;
        this.precoAno2 = precoAno2;
        this.precoAno3 = precoAno3;
    }

    public void exibirFichaTecnica(){
        System.out.println("Modelo do carro: " + modelo);
        System.out.println("Preço no ano 1: " + procoAno1);
        System.out.println("Preço no ano 2: " + precoAno2);
        System.out.println("Preço no ano 3: " + precoAno3);
        System.out.println("Maior preço: " + calcularMaiorPreco());
        System.out.println("Menor preço: " + calcularMenorPreco());
    }

    public double calcularMaiorPreco(){
        double maiorPreco = procoAno1;

        if (precoAno2 > maiorPreco) {
            maiorPreco = precoAno2;
        }
        if (precoAno3 > maiorPreco) {
            maiorPreco = precoAno3;
        }
        return maiorPreco;
    }

    public double calcularMenorPreco(){
        double menorPreco = procoAno1;

        if (precoAno2 < menorPreco) {
            menorPreco = precoAno2;
        }
        if (precoAno3 < menorPreco) {
            menorPreco = precoAno3;
        }
        return menorPreco;
    }

}
