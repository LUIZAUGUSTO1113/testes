package br.edu.exemplo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContaBancariaTest {

    private static final double DELTA = 0.001;

    private ContaBancaria conta;

    @BeforeEach
    void setUp() {
        conta = new ContaBancaria();
    }

    @Test
    void deveDepositarValorNaConta() {
        conta.depositar(100);

        assertEquals(100, conta.getSaldo(), DELTA);
    }

    @Test
    void deveAcumularDepositos() {
        conta.depositar(100);
        conta.depositar(50);

        assertEquals(150, conta.getSaldo(), DELTA);
    }

    @Test
    void deveSacarValorDaConta() {
        conta.depositar(100);

        conta.sacar(40);

        assertEquals(60, conta.getSaldo(), DELTA);
    }

    @Test
    void naoDevePermitirSaqueMaiorQueSaldo() {
        conta.depositar(100);

        assertThrows(IllegalArgumentException.class, () -> conta.sacar(150));

        assertEquals(100, conta.getSaldo(), DELTA);
    }

    @Test
    void devePermitirSaqueDeTodoOSaldo() {
        conta.depositar(100);

        conta.sacar(100);

        assertEquals(0, conta.getSaldo(), DELTA);
    }
}
