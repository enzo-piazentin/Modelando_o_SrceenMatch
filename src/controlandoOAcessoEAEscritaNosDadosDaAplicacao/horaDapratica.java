package controlandoOAcessoEAEscritaNosDadosDaAplicacao;

public class horaDapratica {
    public static void main(String[] args) {
        String pular ="""
                --------------------
                """;
        System.out.println("Exercício 1");
        Conta contaBancaria = new Conta();

        contaBancaria.setNumeroConta(123);
        contaBancaria.setSaldo(1000);
        contaBancaria.titular = "Enzo";

        System.out.println("Número da conta: " + contaBancaria.getNumeroConta());
        System.out.println("Saldo: " + contaBancaria.getSaldo());
        System.out.println("Titular: "+ contaBancaria.titular);

        contaBancaria.setSaldo(1500);
        System.out.println("Novo Saldo: "+ contaBancaria.getSaldo());

        System.out.println(pular);
        System.out.println("Exercício 2");

        IdadePessoa pessoa = new IdadePessoa();

        pessoa.setNome("Enzo Ribeiro");
        pessoa.setIdade(19);

        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Idade: " + pessoa.getIdade());
        pessoa.verificaIdade();

        pessoa.setNome("Mariana Perrella");
        pessoa.setIdade(16);
        System.out.println("Nome: " + pessoa.getNome());
        System.out.println("Idade: " + pessoa.getIdade());
        pessoa.verificaIdade();


    }
}
