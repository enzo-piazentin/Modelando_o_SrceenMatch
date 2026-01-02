package controlandoOAcessoEAEscritaNosDadosDaAplicacao;

public class Conta {
    private double saldo;
    private int numeroConta;
    public String titular;

    public double getSaldo(){
        return saldo;
    }

    public void setSaldo(double saldo){
        this.saldo = saldo;
    }

    public int getNumeroConta(){
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta){
        this.numeroConta = numeroConta;
    }
}
