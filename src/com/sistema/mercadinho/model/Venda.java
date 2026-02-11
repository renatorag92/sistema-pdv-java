package com.sistema.mercadinho.model;

import com.sistema.mercadinho.dao.ProdutoDAO;
import com.sistema.mercadinho.pagamento.MeioDePagamento;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author rag
 */
public class Venda implements Serializable {

    private Cliente cliente;
    private ArrayList<ItemVenda> itemVendaProdutos;
    private int id;
    private static int contador = 1;
    private double valorTotal;
    private MeioDePagamento formaPagamento;
    private LocalDateTime horaDaVenda;

    public Venda() {
        this.itemVendaProdutos = new ArrayList<>();
        this.horaDaVenda = LocalDateTime.now();
        this.valorTotal = 0.0;
    }

    public ArrayList<ItemVenda> getItemVendaProdutos() {
        return itemVendaProdutos;
    }

    public void setItens(ArrayList<ItemVenda> itens) {
        this.itemVendaProdutos = itens;
        // Recalcula o total ao setar a lista
        this.valorTotal = calcularTotalVenda();
    }

    public Venda(ArrayList<ItemVenda> itemVendaProdutos, double valorTotal, MeioDePagamento formaPagamento) {
        this.itemVendaProdutos = itemVendaProdutos;
        this.id = contador;
        this.id = contador;
        contador++;
        this.valorTotal = valorTotal;
        this.formaPagamento = formaPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public MeioDePagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(MeioDePagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public LocalDateTime getHoraDaVenda() {
        return horaDaVenda;
    }

    public void setHoraDaVenda(LocalDateTime horaDaVenda) {
        this.horaDaVenda = horaDaVenda;
    }

    public void adicionarItem(ItemVenda item) {
        itemVendaProdutos.add(item);
        valorTotal += item.calcularPrecoItem();

    }

    public void removerItem(ItemVenda item) {
        itemVendaProdutos.remove(item);
        valorTotal -= item.calcularPrecoItem();
    }

    public boolean finalizarVenda(ProdutoDAO estoque) {
        boolean pagamentoOk = formaPagamento.processarPagamento(valorTotal);

        if (pagamentoOk) {

            for (ItemVenda item : itemVendaProdutos) {
                Produto produto = item.getProduto();
                int vendidos = item.getQuantidade();
                int novoEstoque = produto.getEstoque() - vendidos;
                produto.setEstoque(novoEstoque);
                estoque.atualizar(produto);
            }
        }
        return true;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public void removerItem(int linhaSelecionada) {
        ItemVenda itemEncontrado = itemVendaProdutos.get(linhaSelecionada);
        removerItem(itemEncontrado);
    }

    public double calcularTotalVenda() {
        double total = 0.0;
        // Percorre a lista de itens da classe venda
        for (ItemVenda item : this.getItemVendaProdutos()) {
            total += item.calcularPrecoItem();

        }
        return total;

    }

}
