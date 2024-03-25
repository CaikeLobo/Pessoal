package Fradesco_Pack04;

import java.io.*;
import java.util.*;

// Classe para lidar com login, logout e operações relacionadas
class LoginLogout {
    private Map<String, Conta> contas;
    private Conta contaLogada;
    private Map<String, Conta> contasAlt;

    public LoginLogout(Map<String, Conta> contas) {
        this.contas = contas;
    }
    
    public Map<String, Conta> getContas() {
        return contas;
    }

    // Método para criar uma nova conta
    public void criarConta(String nome, String cpf, String numeroDaConta, String senha) {
        if (!contas.containsKey(numeroDaConta)) {
            Conta conta = new Conta(numeroDaConta, senha, 0, cpf, nome);
            contas.put(numeroDaConta, conta);
            System.out.println("Conta criada com sucesso.");
        } else {
            System.out.println("Número de conta já existente.");
        }
    }

    // Método para realizar login
    public void login(String numeroDaConta, String senha) {
        if (contas.containsKey(numeroDaConta)) {
            Conta conta = contas.get(numeroDaConta);
            if (conta.getSenha().equals(senha)) {
                contaLogada = conta;
                System.out.println("Login realizado com sucesso.");
            } else {
                System.out.println("Senha incorreta.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    // Método para realizar logout
    public void logout() {
        contaLogada = null;
        System.out.println("Logout realizado com sucesso.");
    }

    // Método para obter a conta logada
    public Conta getContaLogada() {
        return contaLogada;
    }

    // Método para obter conta pelo número
    public Conta getContaByNumero(String numero) {
        return contas.get(numero);
    }
    
    public void SalvarMemento() {
    	contasAlt = new HashMap<>(contas);
    	
    	 System.out.println(contasAlt);
    }
    
    public Map<String, Conta> getMemento() {
    	//contas.clear(); // Teoricamente Funcional, Nào testado
    	System.out.println(contasAlt);
        return contasAlt;
    }
    
    
}