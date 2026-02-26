package reaproveitandoCaracteristicasEComportamento;

public class GerandoPrimo extends  NumerosPrimos{

    public int gerarProximoPrimo(int ultimoPrimoConhecido){
        int proximoNumero = ultimoPrimoConhecido+ 1;
        while (!verificarPrimalidade(proximoNumero)){
            proximoNumero++;
        }
        return proximoNumero;
    }
}
