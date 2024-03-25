package Fradesco_Pack04;

interface OperacoesBanco {
    void verificarSaldo(Conta conta);
    void depositar(Conta conta, int valor);
    void sacar(Conta conta, int valor);
    void transferir(Conta contaOrigem, Conta contaDestino, int valor);
    void logout();
}

