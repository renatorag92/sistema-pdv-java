package com.sistema.mercadinho.pagamento;

import java.io.Serializable;

/**
 *
 * @author rag
 */
public abstract class MeioDePagamento implements Serializable {

    public abstract boolean processarPagamento(double valorDaVenda);

}
