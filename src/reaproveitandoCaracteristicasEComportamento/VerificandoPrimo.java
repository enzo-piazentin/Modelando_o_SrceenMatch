package reaproveitandoCaracteristicasEComportamento;

public class VerificandoPrimo extends  NumerosPrimos{

    public void verificarPrimos(int numero){
        if (verificarPrimalidade(numero)){
            System.out.println(numero + " é um número primo.");
        } else {
            System.out.println(numero + " não é um número primo.");
        }
    }
}
