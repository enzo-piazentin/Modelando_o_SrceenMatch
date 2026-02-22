package reaproveitandoCaracteristicasEComportamento;

public class ContaCorrente extends ContaBancaria {

    public void cobrarTarifaMensal(double valor){
        double tarifa = 12.90;
        valor -= tarifa;
        System.out.println("Tarifa mensal cobrada: " + tarifa + " | Saldo atual: " + valor);
    }

}