package Moon;

public class Neuronio {
    private double[] pesos;
    private double saida;

    public Neuronio(int numEntradas) {
        pesos = new double[numEntradas];
        // Inicializar os pesos com valores aleatórios
        for (int i = 0; i < numEntradas; i++) {
            pesos[i] = Math.random() - 0.5;
        }
    }

    public double ativacao(double[] entradas) {
        double soma = 0;
        for (int i = 0; i < entradas.length; i++) {
            soma += entradas[i] * pesos[i];
        }
        saida = 1 / (1 + Math.exp(-soma)); // Função sigmoide
        return saida;
    }

    public double[] getPesos() {
        return pesos;
    }

    public void atualizarPesos(double[] deltaPesos) {
        for (int i = 0; i < pesos.length; i++) {
            pesos[i] += deltaPesos[i];
        }
    }

    public double getSaida() {
        return saida;
    }
}
