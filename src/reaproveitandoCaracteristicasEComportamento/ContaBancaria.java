package reaproveitandoCaracteristicasEComportamento;

public class ContaBancaria {
    private double saldo;

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }


    public void depositar(double valor){
        saldo += valor;
        System.out.println("Depósito: " + valor + " | Saldo atual: " + saldo);
    }

    public void sacar(double sacar){
        saldo -= sacar;
        if (sacar > saldo) {
            System.out.println("Saldo insuficiente para realizar o saque.");
            return;
        }
        else {
            saldo -= sacar;
            System.out.println("Saque: " + sacar + " | Saldo atual: " + saldo);
        }
    }

    public void consultarSaldo(){
        System.out.println("Saldo: " + saldo);
    }
}
