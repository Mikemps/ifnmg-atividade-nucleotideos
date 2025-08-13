/* 
* projeto: Cálculo de Nucleotídeos - Testes Unitários
* @author: emillysilva & micaelpereira
* Esta classe implementa o método calculoDeNucleotideos, que
* recebe como parâmetro uma sequência de DNA (string) e retorna um array de inteiros
*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculoNucleotideosTest {

    private CalculoNucleotideos calculadora;

    @BeforeEach 
    void setUp() {
        calculadora = new CalculoNucleotideos();
    }

    @Test
    @DisplayName("Verifica contagem para uma sequência de DNA válida e sem erros") 
    void deveContarNucleotideosCorretamenteParaSequenciaValida() {
        String dna = "AAAGTCTGAC";
        int[] esperado = {4, 2, 2, 2, 0};
        int[] resultado = calculadora.calculoDeNucleotideos(dna);
        assertArrayEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Verifica contagem para sequência com um caractere inválido")
    void deveContarNucleotideosESinalizarUmErro() {
        String dna = "AACTGTCGBA";
        int[] esperado = {3, 2, 2, 2, 1};
        int[] resultado = calculadora.calculoDeNucleotideos(dna);
        assertArrayEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Verifica se retorna null quando erros excedem 10% do total") 
    void deveRetornarNullSeErrosSuperamDezPorcento() {
        String dna = "ABC TEM FALHA"; 
        int[] resultado = calculadora.calculoDeNucleotideos(dna);
        assertNull(resultado, "O método deveria retornar null para sequências com mais de 10% de erros."); 
    }

    @Test
    @DisplayName("Verifica o limite de 10%: não deve retornar null com exatamente 10% de erros") 
    void deveProcessarCorretamenteComExatosDezPorcentoDeErros() {
        String dna = "AGCTAGCTAX";
        int[] esperado = {3, 2, 2, 2, 1};
        int[] resultado = calculadora.calculoDeNucleotideos(dna);
        assertArrayEquals(esperado, resultado);
    }

    @Test
    @DisplayName("Verifica se lança exceção para sequência vazia") 
    void deveLancarExcecaoParaSequenciaVazia() {
        String dna = "";
        assertThrows(IllegalArgumentException.class, () -> {
            calculadora.calculoDeNucleotideos(dna);
        });
    }
}