package Moon;

public class Main5 {
    public static void main(String[] args) {
        MLP mlp = new MLP();
        
        // Conjunto de treinamento (primeira entrada é o bias)
        double[][] entradas = {
            {1, 1, 0, 1, 0}, // Gripe
            {1, 0, 1, 0, 1}, // Dengue
            {1, 0, 0, 1, 0}, // Gripe
            {1, 0, 0, 0, 1}  // Dengue
        };

        // Saídas desejadas: {Gripe, Dengue}
        double[][] saidasDesejadas = {
            {1, 0}, // Gripe
            {0, 1}, // Dengue
            {1, 0}, // Gripe
            {0, 1}  // Dengue
        };

        mlp.treinar(entradas, saidasDesejadas, 1000); // Treinando por 10000 épocas

        // Teste
        double[] teste1 = {1, 1, 0, 1, 0}; // Gripe
        double[] resultado = mlp.calcularSaida(teste1);
        System.out.println("Resultado para teste1 (Gripe): " + resultado[0] + ", " + resultado[1]);
        
        double[] teste2 = {1, 0, 1, 0, 1}; // Gripe
        double[] resultado2 = mlp.calcularSaida(teste2);
        System.out.println("Resultado para teste2 (Dengue): " + resultado2[0] + ", " + resultado2[1]);
    }
}

