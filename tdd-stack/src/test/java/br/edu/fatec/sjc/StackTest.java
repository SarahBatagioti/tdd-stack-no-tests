package br.edu.fatec.sjc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StackTest {

    private CustomStack<Double> cut;
    @Mock
    private CalculableStrategy<Double> calculableStrategy;

    @BeforeEach
    void setUp() {
        cut = new CustomStack<>(3, calculableStrategy);
    }

    @Test
    void deveIniciarPilhaVazia() {
        assertTrue(cut.isEmpty());
        assertEquals(0, cut.size());
    }

    @Test
    void deveEmpilharComValorCalculadoEConsultarTopo() throws StackFullException {
        when(calculableStrategy.calculateValue(2.0)).thenReturn(4.0);

        cut.push(2.0);

        assertFalse(cut.isEmpty());
        assertEquals(1, cut.size());
        assertEquals(4.0, cut.top());
    }

    @Test
    void deveDesempilharEmOrdemLifo() throws StackFullException, StackEmptyException {
        when(calculableStrategy.calculateValue(1.0)).thenReturn(10.0);
        when(calculableStrategy.calculateValue(2.0)).thenReturn(20.0);

        cut.push(1.0);
        cut.push(2.0);

        assertEquals(20.0, cut.pop());
        assertEquals(1, cut.size());
        assertEquals(10.0, cut.top());
    }

    @Test
    void deveLancarExcecaoAoEmpilharComPilhaCheia() throws StackFullException {
        when(calculableStrategy.calculateValue(1.0)).thenReturn(1.0);
        when(calculableStrategy.calculateValue(2.0)).thenReturn(2.0);
        when(calculableStrategy.calculateValue(3.0)).thenReturn(3.0);

        cut.push(1.0);
        cut.push(2.0);
        cut.push(3.0);

        assertThrows(StackFullException.class, () -> cut.push(4.0));
        assertEquals(3, cut.size());
        verify(calculableStrategy, never()).calculateValue(4.0);
    }

    @Test
    void deveLancarExcecaoAoDesempilharPilhaVazia() {
        assertThrows(StackEmptyException.class, () -> cut.pop());
    }

    @Test
    void devePropagarErroDaEstrategiaSemAlterarEstadoDaPilha() {
        when(calculableStrategy.calculateValue(5.0)).thenThrow(new NullPointerException());

        assertThrows(NullPointerException.class, () -> cut.push(5.0));
        assertTrue(cut.isEmpty());
        assertEquals(0, cut.size());
        verify(calculableStrategy).calculateValue(5.0);
    }
}
