package br.edu.exemplo;

public class Produto {

    private String nome;
    private int quantidade;

    public Produto(String nome) {
        this.nome = nome;
        this.quantidade = 0;
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade a adicionar deve ser maior que zero.");
        }
        this.quantidade += quantidade;
    }

    public void retirarEstoque(int quantidade) {
        if (quantidade > this.quantidade) {
            throw new IllegalArgumentException("Quantidade insuficiente em estoque.");
        }
        this.quantidade -= quantidade;
    }
}
