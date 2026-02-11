/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.mercadinho.model;

import java.io.Serializable;

/**
 *
 * @author rag
 */
public class Funcionario extends Pessoa implements Serializable {

    private String cargo;
    private String senha;

    public Funcionario(String nome, String CPF, String cargo, String senha) {
        super(nome, CPF);
        this.cargo = cargo;
        this.senha = senha;
    }

    public Funcionario() {
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
