package br.edu.exemplo;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {

    private static final double LIMITE_DESCONTO = 500.00;
    private static final double PERCENTUAL_DESCONTO = 0.10;

    private final List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        if (produto.getPreco() < 0) {
            throw new IllegalArgumentException("Preço do produto não pode ser negativo.");
        }
        produtos.add(produto);
    }

    public boolean remover(Produto produto) {
        return produtos.remove(produto);
    }

    public double getSubtotal() {
        double subtotal = 0;
        for (Produto produto : produtos) {
            subtotal += produto.getPreco();
        }
        return subtotal;
    }

    public double getDesconto() {
        double subtotal = getSubtotal();
        return subtotal >= LIMITE_DESCONTO ? subtotal * PERCENTUAL_DESCONTO : 0;
    }

    public double getValorTotal() {
        return getSubtotal() - getDesconto();
    }
}
