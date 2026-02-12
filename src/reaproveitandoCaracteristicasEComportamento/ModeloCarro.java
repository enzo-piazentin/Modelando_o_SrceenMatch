package reaproveitandoCaracteristicasEComportamento;

public class ModeloCarro extends Carro{
    private String marca, cor, tipoCombustivel;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public void mostrarInformacoesAdd(){
        System.out.println("Marca do carro: "+ marca);
        System.out.println("Cor do carro: " + cor);
        System.out.println("Tipo de combustível: " + tipoCombustivel);
    }
}
