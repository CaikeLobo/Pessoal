package Fradesco_Pack04;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BancoDeDadosLink bancoDeDados = new EscritorArquivo(); // Inicialize o EscritorArquivo
        Banco banco = new Banco(bancoDeDados);
        
        System.out.println("              .,-:;//;:=,\r\n"
    			+ "          . :H@@@MM@M#H/.,+%;,\r\n"
    			+ "       ,/X+ +M@@M@MM%=,-%HMMM@X/,\r\n"
    			+ "     -+@MM; $M@@MH+-,;XMMMM@MMMM@+-\r\n"
    			+ "    ;@M@@M- XM@X;. -+XXXXXHHH@M@M#@/.\r\n"
    			+ "  ,%MM@@MH ,@%=             .---=-=:=,.\r\n"
    			+ "  =@#@@@MX.,                -%HX$$%%%:;\r\n"
    			+ " =-./@M@M$                   .;@MMMM@MM:\r\n"
    			+ " X@/ -$MM/                    . +MM@@@M$\r\n"
    			+ ",@M@H: :@:                    . =X#@@@@-\r\n"
    			+ ",@@@MMX, .                    /H- ;@M@M=\r\n"
    			+ ".H@@@@M@+,                    %MM+..%#$.\r\n"
    			+ " /MMMM@MMH/.                  XM@MH; =;\r\n"
    			+ "  /%+%$XHH@$=              , .H@@@@MX,\r\n"
    			+ "   .=--------.           -%H.,@@@@@MX,\r\n"
    			+ "   .%MM@@@HHHXX$$$%+- .:$MMX =M@@MM%.\r\n"
    			+ "     =XMMM@MM@MM#H;,-+HMM@M+ /MMMX=\r\n"
    			+ "       =%@M@M#@$-.=$@MM@@@M; %M%=\r\n"
    			+ "         ,:+$+-,/H#MMMMMMM@= =,\r\n"
    			+ "               =++%%%%+/:-.\r\n"
    			+ "");
        
        while (true) {
        	
            System.out.println("\n V 04 Selecione uma opção:");
            System.out.println("1. Login");
            System.out.println("2. Criar Conta");
            System.out.println("3. Sair");
            System.out.println("4. Criar Memento");
            System.out.println("5. Carregar Memento");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("Número da conta:");
                    String numeroDaConta = scanner.next();
                    System.out.println("Senha:");
                    String senha = scanner.next();
                    banco.login(numeroDaConta, senha);
                    if (banco.getContaLogada() != null) {
                        menuOperacoes(banco, scanner);
                    }
                    break;
                case 2:
                    System.out.println("Nome:");
                    scanner.nextLine();
                    String nome = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("CPF:");
                    String cpf = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Número da conta:");
                    String numeroConta = scanner.nextLine();
                    scanner.nextLine();
                    System.out.println("Senha:");
                    String senhaConta = scanner.nextLine();
                    System.out.println(nome + cpf + numeroConta +  senhaConta);
                    banco.criarConta(nome, cpf, numeroConta, senhaConta);
                    break;
                case 3:
                    System.out.println("Saindo...");
                    System.exit(0);
                case 4:
                	banco.SalvarMemento();
                	System.out.println("Memento Salvo!");
                	break;
                case 5:
                	banco.CarregarMemento();
                	System.out.println("Memento Carregado!");
                	System.out.println("Reinicie o Sistema Por Favor");
                	System.exit(0);
                	break;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }

    // Menu de operações disponíveis após login
    private static void menuOperacoes(Banco banco, Scanner scanner) {
        while (true) {
            System.out.println("\nSelecione uma operação:");
            System.out.println("1. Verificar Saldo");
            System.out.println("2. Depositar");
            System.out.println("3. Sacar");
            System.out.println("4. Transferir");
            System.out.println("5. Logout");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    banco.verificarSaldo(banco.getContaLogada());
                    break;
                case 2:
                    System.out.println("Digite o valor a ser depositado:");
                    int valorDeposito = scanner.nextInt();
                    banco.depositar(banco.getContaLogada(), valorDeposito);
                    break;
                case 3:
                    System.out.println("Digite o valor a ser sacado:");
                    int valorSaque = scanner.nextInt();
                    banco.sacar(banco.getContaLogada(), valorSaque);
                    break;
                case 4:
                    System.out.println("Digite o número da conta de destino:");// Enviar Numero da conta ao inves da conta de destino pronta, o banco que se foda para ver
                    String numeroDestino = scanner.next();
                    Conta contaDestino = banco.getContaByNumero(numeroDestino);
                    if (contaDestino != null) {
                        System.out.println("Digite o valor a ser transferido:");
                        int valorTransferencia = scanner.nextInt();
                        banco.transferir(banco.getContaLogada(), contaDestino, valorTransferencia);
                    } else {
                        System.out.println("Conta de destino não encontrada.");
                    }
                    break;
                case 5:
                    banco.logout();
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}