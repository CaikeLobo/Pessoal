package Fradesco_Pack04;

import java.io.*;
import java.util.*;


// Implementação das operações do banco
class Banco implements OperacoesBanco {
    private LoginLogout loginLogout;
    private TransacoesBanco transacoesBanco;
    private BancoDeDadosLink bancoDeDados;

    public Banco(BancoDeDadosLink bancoDeDados) {
    	this.bancoDeDados = bancoDeDados;
        loginLogout = new LoginLogout(bancoDeDados.carregarContas());
        transacoesBanco = new TransacoesBanco();
    }

    // Salva as contas no arquivo texto
    private void salvarContas() {
    	bancoDeDados.salvarContas(loginLogout.getContas());
    }


    // Implementação dos métodos da interface OperacoesBanco
    @Override
    public void verificarSaldo(Conta conta) {
        System.out.println("Saldo: " + conta.getSaldo());
    }

    @Override
    public void depositar(Conta conta, int valor) {// Repetir isso com o sacar e transferir, talvez o verificar tbm
    	transacoesBanco.depositar(conta, valor);;
        salvarContas();
        System.out.println("Depósito realizado com sucesso.");
    }

    @Override
    public void sacar(Conta conta, int valor) {
        if (conta.sacar(valor)) {
            salvarContas();
            System.out.println("Saque realizado com sucesso.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    @Override
    public void transferir(Conta contaOrigem, Conta contaDestino, int valor) {
        if (contaOrigem.transferir(contaDestino, valor)) {
            salvarContas();
            System.out.println("Transferência realizada com sucesso.");
        } else {
            System.out.println("Saldo insuficiente para transferência.");
        }
    }

    // Métodos delegados para a classe LoginLogout
    public void criarConta(String nome, String cpf, String numeroDaConta, String senha) {
        loginLogout.criarConta(nome, cpf, numeroDaConta, senha);
        salvarContas(); // Atualizar contas após criação
    }

    public void login(String numeroDaConta, String senha) {
        loginLogout.login(numeroDaConta, senha);
    }

    public void logout() {
        loginLogout.logout();
    }

    public Conta getContaLogada() {
        return loginLogout.getContaLogada();
    }

    public Conta getContaByNumero(String numero) {
        return loginLogout.getContaByNumero(numero);
    }
    
    public void SalvarMemento() {
    	loginLogout.SalvarMemento();   	
    }
    
    public void CarregarMemento() {	
    	bancoDeDados.salvarContas(loginLogout.getMemento());
    	//loginLogout = new LoginLogout(bancoDeDados.carregarContas()); // Teoricamente Funcional???? Vai saber
    }
}
