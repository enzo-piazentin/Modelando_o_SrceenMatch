package controlandoOAcessoEAEscritaNosDadosDaAplicacao;

public class produto {
    private String nomeProduto;
    private double precoProduto;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(double precoProduto) {
        this.precoProduto = precoProduto;
    }

    public void aplicarDesconto(double desconto){
        this.precoProduto = precoProduto * desconto;
    }
}
