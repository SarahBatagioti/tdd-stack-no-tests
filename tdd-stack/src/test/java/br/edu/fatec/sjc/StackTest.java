package br.edu.fatec.sjc;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
public class StackTest {

    private CustomStack<Double> cut;
    private CalculableStrategy<Double> calculableStrategy;

    @BeforeEach
    void setUp() {
        calculableStrategy = mock(CalculableStrategy.class);
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
}
