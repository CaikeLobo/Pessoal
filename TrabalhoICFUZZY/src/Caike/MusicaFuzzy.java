package Caike;

import net.sourceforge.jFuzzyLogic.FIS;


public class MusicaFuzzy {
    public static void main(String[] args) {
        // Caminho do arquivo FCL (lógica fuzzy)
        String arquivoFCL = "C:\\\\Users\\\\caike\\\\Downloads\\\\musica.fcl";
        FIS fis = FIS.load(arquivoFCL, true);

        if (fis == null) {
            System.err.println("Erro ao carregar o arquivo: " + arquivoFCL);
            return;
        }

        // Definir valores das entradas
        fis.setVariable("complexidadePartitura", 4); // Exemplo: 7 de 10
        fis.setVariable("rapidezExecucao", 6);       // Exemplo: 8 de 10

        // Processar
        fis.evaluate();
        

        // Obter o valor da saída
        double dificuldade = fis.getVariable("dificuldade").getValue();

        // Exibir resultado
        System.out.println("Dificuldade calculada: " + dificuldade);
    }
}
