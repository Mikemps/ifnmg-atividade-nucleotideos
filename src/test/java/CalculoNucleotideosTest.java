import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculoNucleotideosTest {

    private CalculoNucleotideos calculadora;

    @BeforeEach // Anotação para inicializador de método de teste [cite: 15]
    void setUp() {
        calculadora = new CalculoNucleotideos();
    }

    @Test
    @DisplayName("Verifica contagem para uma sequência de DNA válida e sem erros") // [cite: 18]
    void deveContarNucleotideosCorretamenteParaSequenciaValida() {
        // Exemplo do PDF: "AAAGTCTGAC" → [4, 2, 2, 2, 0] [cite: 21]
        String dna = "AAAGTCTGAC";
        int[] esperado = {4, 2, 2, 2, 0};
        int[] resultado = calculadora.calculoDeNucleotideos(dna);
        assertArrayEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Verifica contagem para sequência com um caractere inválido") // [cite: 18]
    void deveContarNucleotideosESinalizarUmErro() {
        // Exemplo do PDF: "AACTGTCGBA" → [3, 2, 2, 2, 1] [cite: 22]
        String dna = "AACTGTCGBA";
        int[] esperado = {3, 2, 2, 2, 1};
        int[] resultado = calculadora.calculoDeNucleotideos(dna);
        assertArrayEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Verifica se retorna null quando erros excedem 10% do total") // [cite: 18, 19]
    void deveRetornarNullSeErrosSuperamDezPorcento() {
        // Sequência com 13 caracteres e 2 erros (15.3% > 10%) [cite: 23]
        String dna = "ABC TEM FALHA"; // Espaços também são erros [cite: 25]
        int[] resultado = calculadora.calculoDeNucleotideos(dna);
        assertNull(resultado, "O método deveria retornar null para sequências com mais de 10% de erros."); // [cite: 24]
    }

    @Test
    @DisplayName("Verifica o limite de 10%: não deve retornar null com exatamente 10% de erros") // [cite: 18]
    void deveProcessarCorretamenteComExatosDezPorcentoDeErros() {
        // Sequência com 10 caracteres e 1 erro (exatamente 10%)
        String dna = "AGCTAGCTAX";
        int[] esperado = {3, 2, 2, 2, 1}; // O erro 'X' é contado, mas não invalida o resultado [cite: 12, 13]
        int[] resultado = calculadora.calculoDeNucleotideos(dna);
        assertArrayEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Verifica se lança exceção para sequência vazia") // [cite: 18, 27]
    void deveLancarExcecaoParaSequenciaVazia() {
        String dna = "";
        // Verifica se uma exceção do tipo IllegalArgumentException é lançada [cite: 26]
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calculoDeNucleotideos(dna);
        });
    }
}