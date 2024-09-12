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
        // Loop principal que percorre o número de épocas (iterações completas sobre o conjunto de treinamento)
        for (int epoca = 0; epoca < epocas; epoca++) {
            
            // Percorre cada amostra do conjunto de treinamento
            for (int i = 0; i < entradasTreino.length; i++) {
                
                // 1. Forward pass (propagação direta) para calcular a saída da rede neural
                
                // Calcula as saídas da camada oculta processando as entradas
                double[] saidasOcultas = camadaOculta.processarEntradas(entradasTreino[i]);
                
                // Adiciona o bias (1.0) às saídas da camada oculta antes de passá-las à camada de saída
                double[] saidasOcultasComBias = new double[saidasOcultas.length + 1];
                saidasOcultasComBias[0] = 1.0; // Bias
                
                // Copia as saídas da camada oculta para o novo vetor que inclui o bias
                System.arraycopy(saidasOcultas, 0, saidasOcultasComBias, 1, saidasOcultas.length);
                
                // Calcula as saídas finais da rede, processando as saídas ocultas com bias pela camada de saída
                double[] saidasRede = camadaSaida.processarEntradas(saidasOcultasComBias);

                // 2. Backpropagation (retropropagação do erro) para ajuste dos pesos

                // Vetor de erros na camada de saída, calcula a diferença entre a saída desejada e a saída calculada
                double[] errosSaida = new double[saidasDesejadas[i].length];
                for (int j = 0; j < errosSaida.length; j++) {
                    // Calcula o erro da camada de saída (erro = saída desejada - saída calculada)
                    errosSaida[j] = saidasDesejadas[i][j] - saidasRede[j];
                    
                    // Calcula o delta para o neurônio de saída (derivada do erro)
                    double deltaSaida = errosSaida[j] * saidasRede[j] * (1 - saidasRede[j]);
                    
                    // Atualiza os pesos de cada neurônio da camada de saída
                    for (int k = 0; k < camadaSaida.getNeuronios()[j].getPesos().length; k++) {
                        // Ajuste do peso pela regra delta (taxaAprendizagem * deltaSaida * saída da camada oculta correspondente)
                        double deltaPeso = taxaAprendizagem * deltaSaida * saidasOcultasComBias[k];
                        camadaSaida.getNeuronios()[j].getPesos()[k] += deltaPeso; // Atualização do peso
                    }
                }

                // 3. Atualizar os pesos da camada oculta

                // Para cada neurônio da camada oculta, calcula o erro e ajusta os pesos
                for (int j = 0; j < camadaOculta.getNeuronios().length; j++) {
                    double erroOculto = 0;
                    
                    // Calcula o erro do neurônio oculto com base nos erros da camada de saída
                    for (int k = 0; k < errosSaida.length; k++) {
                        // O erro da camada oculta é a soma dos erros da camada de saída, ponderados pelos pesos
                        erroOculto += errosSaida[k] * camadaSaida.getNeuronios()[k].getPesos()[j + 1]; // Ignorando o bias
                    }
                    
                    // Calcula o delta para o neurônio oculto (derivada do erro)
                    double deltaOculto = erroOculto * saidasOcultas[j] * (1 - saidasOcultas[j]);
                    
                    // Atualiza os pesos de cada neurônio da camada oculta
                    for (int k = 0; k < entradasTreino[i].length; k++) {
                        // Ajuste do peso pela regra delta (taxaAprendizagem * deltaOculto * entrada correspondente)
                        double deltaPeso = taxaAprendizagem * deltaOculto * entradasTreino[i][k];
                        camadaOculta.getNeuronios()[j].getPesos()[k] += deltaPeso; // Atualização do peso
                    }
                }
            }

            // A cada 100 épocas, imprime os pesos das camadas oculta e de saída
            if ((epoca + 1) % 100 == 0) {
                System.out.println("Época " + (epoca + 1));
                System.out.println("Pesos da camada oculta:");
                imprimirPesos(camadaOculta);
                System.out.println("Pesos da camada de saída:");
                imprimirPesos(camadaSaida);
                System.out.println("--------------------------------------------");
            }
        }

        // No final do treinamento, imprime os pesos finais
        System.out.println("Pesos finais da camada oculta:");
        imprimirPesos(camadaOculta);
        System.out.println("Pesos finais da camada de saída:");
        imprimirPesos(camadaSaida);
        
        return new double[]{}; // Pode ser alterado para retornar algo útil, como os erros finais
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



