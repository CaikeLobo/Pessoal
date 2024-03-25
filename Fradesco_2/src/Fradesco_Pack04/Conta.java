package Fradesco_Pack04;

import java.io.*;
import java.util.*;



// Classe para representar uma conta
class Conta {
    private String numeroDaConta;
    private String senha;
    private int saldo;
    private String cpf;
    private String nome;

    public Conta(String numeroDaConta, String senha, int saldo, String cpf, String nome) {
        this.numeroDaConta = numeroDaConta;
        this.senha = senha;
        this.saldo = saldo;
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getNumeroDaConta() {
        return numeroDaConta;
    }

    public String getSenha() {
        return senha;
    }

    public int getSaldo() {
        return saldo;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public void depositar(int valor) {
        saldo += valor;
    }

    public boolean sacar(int valor) {
        if (valor <= saldo) {
            saldo -= valor;
            return true;
        }
        return false;
    }

    public boolean transferir(Conta destino, int valor) {
        if (valor <= saldo) {
            saldo -= valor;
            destino.depositar(valor);
            return true;
        }
        return false;
    }
}
 
