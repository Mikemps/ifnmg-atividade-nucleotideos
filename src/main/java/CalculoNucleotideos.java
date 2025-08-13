/*
 * projeto: Cálculo de Nucleotídeos - Testes Unitários
 * @author: emillysilva & micaelpereira
 * Esta classe implementa o método calculoDeNucleotideos, que recebe como
 * parâmetro uma sequência de DNA (string) e retorna um array de inteiros 
 * contendo a contagem de cada nucleotídeo
 */ 

public class CalculoNucleotideos {

    public int[] calculoDeNucleotideos(String sequenciaDna) {
        if (sequenciaDna == null || sequenciaDna.isEmpty()) {
            throw new IllegalArgumentException("A sequência de DNA não pode ser vazia.");
        }

        int[] contagem = new int[5]; 

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
                    contagem[4]++;
                    break;
            }
        }

        double taxaDeErro = (double) contagem[4] / sequenciaDna.length();
        if (taxaDeErro > 0.10) {
            return null;
        }

        return contagem;
    }
}