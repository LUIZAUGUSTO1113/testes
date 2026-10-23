package br.edu.exemplo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CarrinhoTest {

    private static final double DELTA = 0.001;

    private Carrinho carrinho;

    @BeforeEach
    void setUp() {
        carrinho = new Carrinho();
    }

    // Requisito 1
    @Test
    void carrinhoVazioDeveTerTotalZero() {
        assertEquals(0.0, carrinho.getValorTotal(), DELTA);
    }

    // Requisito 2
    @Test
    void deveAdicionarUmProduto() {
        carrinho.adicionar(new Produto("Teclado", 100.00));

        assertEquals(100.00, carrinho.getValorTotal(), DELTA);
    }

    // Requisito 3
    @Test
    void deveSomarVariosProdutos() {
        carrinho.adicionar(new Produto("Teclado", 100.00));
        carrinho.adicionar(new Produto("Mouse", 50.00));

        assertEquals(150.00, carrinho.getValorTotal(), DELTA);
    }

    // Requisito 4
    @Test
    void deveRemoverProduto() {
        Produto teclado = new Produto("Teclado", 100.00);
        Produto mouse = new Produto("Mouse", 50.00);
        carrinho.adicionar(teclado);
        carrinho.adicionar(mouse);

        carrinho.remover(mouse);

        assertEquals(100.00, carrinho.getValorTotal(), DELTA);
    }

    @Test
    void removerProdutoInexistenteNaoDeveAlterarTotal() {
        carrinho.adicionar(new Produto("Teclado", 100.00));

        assertFalse(carrinho.remover(new Produto("Mouse", 50.00)));
        assertEquals(100.00, carrinho.getValorTotal(), DELTA);
    }

    // Requisito 5
    @Test
    void deveLancarExcecaoParaPrecoNegativo() {
        assertThrows(IllegalArgumentException.class,
                () -> carrinho.adicionar(new Produto("Teclado", -100.00)));
    }

    @Test
    void precoNegativoNaoDeveAlterarTotal() {
        assertThrows(IllegalArgumentException.class,
                () -> carrinho.adicionar(new Produto("Teclado", -100.00)));

        assertEquals(0.0, carrinho.getValorTotal(), DELTA);
    }

    @Test
    void deveLancarExcecaoParaProdutoNulo() {
        assertThrows(IllegalArgumentException.class, () -> carrinho.adicionar(null));
    }

    // Requisito 6
    @Test
    void deveAplicarDescontoDe10PorCentoAcimaDoLimite() {
        carrinho.adicionar(new Produto("Monitor", 600.00));

        assertEquals(540.00, carrinho.getValorTotal(), DELTA);
    }

    @Test
    void naoDeveAplicarDescontoEm499e99() {
        carrinho.adicionar(new Produto("Item", 499.99));

        assertEquals(499.99, carrinho.getValorTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoEmExatamente500() {
        carrinho.adicionar(new Produto("Item", 500.00));

        assertEquals(450.00, carrinho.getValorTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoEm500e01() {
        carrinho.adicionar(new Produto("Item", 500.01));

        assertEquals(450.009, carrinho.getValorTotal(), DELTA);
    }

    @Test
    void deveAplicarDescontoSobreSomaDeVariosProdutos() {
        carrinho.adicionar(new Produto("Teclado", 300.00));
        carrinho.adicionar(new Produto("Monitor", 300.00));

        assertEquals(540.00, carrinho.getValorTotal(), DELTA);
    }
}
