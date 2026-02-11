package com.sistema.mercadinho.pagamento;

/**
 *
 * @author rag
 */
public class Dinheiro extends MeioDePagamento {

    private double valorEntregue;
    private double troco;

    public Dinheiro() {

    }

    public void setValorEntregue(double valorEntregue) {
        this.valorEntregue = valorEntregue;
    }

    public double getValorEntregue() {
        return valorEntregue;
    }

    public double getTroco() {
        return troco;
    }

    @Override
    public boolean processarPagamento(double valorDaVenda) {
        // Debug: ver o que está acontecendo com o troco
        System.out.println("Processando Dinheiro...");
        System.out.println("Valor da Venda: " + valorDaVenda);
        System.out.println("Valor Entregue (memória): " + this.valorEntregue);
        if (valorEntregue < valorDaVenda) {
            return false;
        }
        this.troco = this.valorEntregue - valorDaVenda;
        return true;
    }

    @Override
    public String toString() {
        return "Dinheiro";
    }

}
