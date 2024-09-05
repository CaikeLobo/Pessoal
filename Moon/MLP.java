package Moon;

//Classe que representa a rede neural
public class MLP {
    private Camada camadaOculta;
    private Camada camadaSaida;
    private double taxaAprendizagem = 0.5;

    public MLP() {
        camadaOculta = new Camada(2, 5); // 2 neurônios, 5 entradas (incluindo o bias)
        camadaSaida = new Camada(2, 3);  // 2 neurônios, 3 entradas (incluindo o bias e as saídas da camada oculta)
    }

    public double[] treinar(double[][] entradasTreino, double[][] saidasDesejadas, int epocas) {
        for (int epoca = 0; epoca < epocas; epoca++) {
            for (int i = 0; i < entradasTreino.length; i++) {
                // Forward pass
                double[] saidasOcultas = camadaOculta.processarEntradas(entradasTreino[i]);
                double[] saidasOcultasComBias = new double[saidasOcultas.length + 1];
                saidasOcultasComBias[0] = 1.0; // Bias
                System.arraycopy(saidasOcultas, 0, saidasOcultasComBias, 1, saidasOcultas.length);
                double[] saidasRede = camadaSaida.processarEntradas(saidasOcultasComBias);

                // Backpropagation e ajuste de pesos
                double[] errosSaida = new double[saidasDesejadas[i].length];
                for (int j = 0; j < errosSaida.length; j++) {
                    errosSaida[j] = saidasDesejadas[i][j] - saidasRede[j];
                    double deltaSaida = errosSaida[j] * saidasRede[j] * (1 - saidasRede[j]);
                    for (int k = 0; k < camadaSaida.getNeuronios()[j].getPesos().length; k++) {
                        double deltaPeso = taxaAprendizagem * deltaSaida * saidasOcultasComBias[k];
                        camadaSaida.getNeuronios()[j].getPesos()[k] += deltaPeso;
                    }
                }

                // Atualizar pesos da camada oculta
                for (int j = 0; j < camadaOculta.getNeuronios().length; j++) {
                    double erroOculto = 0;
                    for (int k = 0; k < errosSaida.length; k++) {
                        erroOculto += errosSaida[k] * camadaSaida.getNeuronios()[k].getPesos()[j + 1]; // Sem contar o bias
                    }
                    double deltaOculto = erroOculto * saidasOcultas[j] * (1 - saidasOcultas[j]);
                    for (int k = 0; k < entradasTreino[i].length; k++) {
                        double deltaPeso = taxaAprendizagem * deltaOculto * entradasTreino[i][k];
                        camadaOculta.getNeuronios()[j].getPesos()[k] += deltaPeso;
                    }
                }
            }

            // Imprimir os pesos a cada 100 ciclos
            if ((epoca + 1) % 100 == 0) {
                System.out.println("Época " + (epoca + 1));
                System.out.println("Pesos da camada oculta:");
                imprimirPesos(camadaOculta);
                System.out.println("Pesos da camada de saída:");
                imprimirPesos(camadaSaida);
                System.out.println("--------------------------------------------");
            }
        }

        // Imprimir os pesos finais
        System.out.println("Pesos finais da camada oculta:");
        imprimirPesos(camadaOculta);
        System.out.println("Pesos finais da camada de saída:");
        imprimirPesos(camadaSaida);
        
        return new double[]{};
    }

    private void imprimirPesos(Camada camada) {
        for (int i = 0; i < camada.getNeuronios().length; i++) {
            double[] pesos = camada.getNeuronios()[i].getPesos();
            System.out.print("Neuronio " + (i + 1) + ": ");
            for (double peso : pesos) {
                System.out.print(peso + " ");
            }
            System.out.println();
        }
    }

    public double[] calcularSaida(double[] entrada) {
        double[] saidasOcultas = camadaOculta.processarEntradas(entrada);
        double[] saidasOcultasComBias = new double[saidasOcultas.length + 1];
        saidasOcultasComBias[0] = 1.0; // Bias
        System.arraycopy(saidasOcultas, 0, saidasOcultasComBias, 1, saidasOcultas.length);
        return camadaSaida.processarEntradas(saidasOcultasComBias);
    }
}



