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
public class Cliente extends Pessoa implements Serializable {

    private String endereco;
    private String telefone;

    public Cliente(String nome, String CPF, String endereco, String telefone) {
        super(nome, CPF);
        this.endereco = endereco;
        this.telefone = telefone;
    }
    
    public Cliente() {
        
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

}
