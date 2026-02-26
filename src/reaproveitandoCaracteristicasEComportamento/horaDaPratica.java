package reaproveitandoCaracteristicasEComportamento;

public class horaDaPratica {
    public static void main(String[] args) {
        Cachorro cachorro = new Cachorro();
        Gato gato = new Gato();

        gato.emitirSom();
        cachorro.emitirSom();

        System.out.println("--------------------------------------");
        System.out.println(" ");

        ContaCorrente contaCorrente = new ContaCorrente();
        contaCorrente.depositar(1000);
        contaCorrente.consultarSaldo();

        contaCorrente.sacar(500);
        contaCorrente.consultarSaldo();

        contaCorrente.depositar(2000);
        contaCorrente.consultarSaldo();

        contaCorrente.cobrarTarifaMensal(contaCorrente.getSaldo());



        System.out.println("--------------------------------------");
        System.out.println(" ");

        VerificandoPrimo verificador = new VerificandoPrimo();
        verificador.verificarPrimos(17);

        GerandoPrimo gerador = new GerandoPrimo();
        int proximoNumero = gerador.gerarProximoPrimo(17);
        System.out.println("O próximo número primo após 17 é: " + proximoNumero);

        NumerosPrimos numerosPrimos = new NumerosPrimos();
        numerosPrimos.listarPrimos(30);
    }
}
