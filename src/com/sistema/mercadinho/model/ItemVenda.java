package com.sistema.mercadinho.model;

import java.io.Serializable;

/**
 *
 * @author rag
 */
public class ItemVenda implements Serializable {

    private Produto produto;
    private int quantidade;

    public ItemVenda(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public double calcularPrecoItem() {
        return produto.getValorUnitario() * quantidade;

    }
}
