package Fradesco_Pack04;

import java.util.Map;

public interface BancoDeDadosLink  {
	Map<String, Conta> carregarContas();
    void salvarContas(Map<String, Conta> contas);
}
