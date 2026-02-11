/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.mercadinho.pagamento;

/**
 *
 * @author rag
 */
public class Debito extends PagamentoDigital {

    @Override
    public boolean processarPagamento(double valorDaVenda) {
        return false;
    }

    @Override
    public String toString() {
        return "Débito";
    }

}
