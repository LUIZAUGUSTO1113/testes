package br.edu.exemplo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProdutoTest {

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto("Teclado");
    }

    // Requisito 1
    @Test
    void produtoDeveIniciarComEstoqueZero() {
        assertEquals(0, produto.getQuantidade());
    }

    // Requisito 2
    @Test
    void deveAdicionarProdutoAoEstoque() {
        produto.adicionarEstoque(10);

        assertEquals(10, produto.getQuantidade());
    }

    // Requisito 3
    @Test
    void deveAcumularQuantidadeEmEstoque() {
        produto.adicionarEstoque(10);
        produto.adicionarEstoque(5);

        assertEquals(15, produto.getQuantidade());
    }

    // Requisito 4
    @Test
    void deveRetirarProdutoDoEstoque() {
        produto.adicionarEstoque(10);
        produto.retirarEstoque(3);

        assertEquals(7, produto.getQuantidade());
    }

    // Requisito 5
    @Test
    void naoDeveRetirarQuantidadeMaiorQueEstoque() {
        produto.adicionarEstoque(10);

        assertThrows(IllegalArgumentException.class, () -> produto.retirarEstoque(15));

        assertEquals(10, produto.getQuantidade());
    }

    // Requisito 6
    @Test
    void naoDeveAdicionarQuantidadeNegativa() {
        assertThrows(IllegalArgumentException.class, () -> produto.adicionarEstoque(-10));

        assertEquals(0, produto.getQuantidade());
    }

    @Test
    void naoDeveAdicionarQuantidadeZero() {
        assertThrows(IllegalArgumentException.class, () -> produto.adicionarEstoque(0));

        assertEquals(0, produto.getQuantidade());
    }
}
