package Fradesco_Pack04;
import java.util.Map;

public class TransacoesBanco {



    public void verificarSaldo(Conta conta) {
        System.out.println("Saldo: " + conta.getSaldo());
    }

    public void depositar(Conta conta, int valor) {
        conta.depositar(valor);
        System.out.println("Depósito realizado com sucesso.");
    }

    public void sacar(Conta conta, int valor) {
        if (conta.sacar(valor)) {
        	
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public void transferir(Conta contaOrigem, Conta contaDestino, int valor) {
        if (contaOrigem.transferir(contaDestino, valor)) {
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }

}
