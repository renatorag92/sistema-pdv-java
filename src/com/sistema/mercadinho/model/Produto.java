package com.sistema.mercadinho.model;

import java.io.Serializable;

/**
 *
 * @author Renato A. Grilo
 */
public class Produto implements Serializable {

    private String codigo;
    private String descricao;
    private double valorUnitario;
    private int numeroUnidades;

    public Produto(String codigo, String descricao, double valorUnitario, int numeroUnidades) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.valorUnitario = valorUnitario;
        this.numeroUnidades = numeroUnidades;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getEstoque() {
        return numeroUnidades;
    }

    public void setEstoque(int numeroUnidades) {
        this.numeroUnidades = numeroUnidades;
    }

    public boolean realizarVenda(int quantidadeVendida) {
        if (quantidadeVendida > numeroUnidades) {
            return false;
        } else {
            this.numeroUnidades -= quantidadeVendida;
            return true;
        }
    }

}
