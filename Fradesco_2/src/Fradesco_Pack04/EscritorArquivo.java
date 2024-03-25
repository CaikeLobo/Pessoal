package Fradesco_Pack04;

import java.io.*;
import java.util.*;

class EscritorArquivo implements BancoDeDadosLink {
    // Carrega as contas do arquivo texto
    public Map<String, Conta> carregarContas() {
        Map<String, Conta> contas = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\\\Users\\\\caike\\\\Downloads\\\\contas.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String cpf = parts[0];
                String nome = parts[1];
                String numeroDaConta = parts[2];
                String senha = parts[3];
                int saldo = Integer.parseInt(parts[4]);
                Conta conta = new Conta(numeroDaConta, senha, saldo, cpf, nome);
                contas.put(numeroDaConta, conta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contas;
    }

    // Salva as contas no arquivo texto
    public void salvarContas(Map<String, Conta> contas) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\\\Users\\\\caike\\\\Downloads\\\\contas.txt"))) {
            for (Conta conta : contas.values()) {
                bw.write(conta.getCpf() + "," + conta.getNome() + "," + conta.getNumeroDaConta() + "," + conta.getSenha() + "," + conta.getSaldo());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
