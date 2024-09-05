package Moon;

public class Camada {
    private Neuronio[] neuronios;

    public Camada(int numNeuronios, int numEntradasPorNeuronio) {
        neuronios = new Neuronio[numNeuronios];
        for (int i = 0; i < numNeuronios; i++) {
            neuronios[i] = new Neuronio(numEntradasPorNeuronio);
        }
    }

    public double[] processarEntradas(double[] entradas) {
        double[] saidas = new double[neuronios.length];
        for (int i = 0; i < neuronios.length; i++) {
            saidas[i] = neuronios[i].ativacao(entradas);
        }
        return saidas;
    }

    public Neuronio[] getNeuronios() {
        return neuronios;
    }
}
