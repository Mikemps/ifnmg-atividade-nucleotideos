public class CalculoNucleotideos {

    /**
     * Lê uma sequência de DNA e calcula o número de nucleotídeos (A, C, G, T) e erros.
     * @param sequenciaDna A string contendo a sequência de DNA.
     * @return Um array de inteiros de 5 posições: [A, C, G, T, Erros].
     * Retorna null se o número de erros for superior a 10% do tamanho da sequência.
     * @throws IllegalArgumentException se a sequência for vazia.
     */
    public int[] calculoDeNucleotideos(String sequenciaDna) {
        // Requisito f: Lançar exceção se a sequência for vazia [cite: 26]
        if (sequenciaDna == null || sequenciaDna.isEmpty()) {
            throw new IllegalArgumentException("A sequência de DNA não pode ser vazia.");
        }

        // O array de retorno terá 5 posições: [A, C, G, T, Erros] [cite: 12]
        int[] contagem = new int[5]; // Posições 0-A, 1-C, 2-G, 3-T, 4-Erros

        // Para facilitar a contagem, convertemos a string para maiúsculas.
        String dnaUpperCase = sequenciaDna.toUpperCase();

        for (char nucleotideo : dnaUpperCase.toCharArray()) {
            switch (nucleotideo) {
                case 'A':
                    contagem[0]++;
                    break;
                case 'C':
                    contagem[1]++;
                    break;
                case 'G':
                    contagem[2]++;
                    break;
                case 'T':
                    contagem[3]++;
                    break;
                default:
                    // Qualquer outro caractere é um erro [cite: 13]
                    contagem[4]++;
                    break;
            }
        }

        // Requisito e: Verificar se a sequência é inválida (> 10% de erros) [cite: 23]
        double taxaDeErro = (double) contagem[4] / sequenciaDna.length();
        if (taxaDeErro > 0.10) {
            return null; // [cite: 24]
        }

        return contagem;
    }
}