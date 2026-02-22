package reaproveitandoCaracteristicasEComportamento;

public class horaDaPratica {
    public static void main(String[] args) {
        ContaCorrente contaCorrente = new ContaCorrente();

        contaCorrente.depositar(1000);
        contaCorrente.consultarSaldo();

        contaCorrente.sacar(500);
        contaCorrente.consultarSaldo();

        contaCorrente.depositar(2000);
        contaCorrente.consultarSaldo();

        contaCorrente.cobrarTarifaMensal(contaCorrente.getSaldo());
    }
}
