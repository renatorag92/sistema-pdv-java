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
public abstract class Pessoa implements Serializable {

    private String nome;
    private String CPF;

    public Pessoa(String nome, String CPF) {
        this.nome = nome;
        this.CPF = CPF;
    }
    
    public Pessoa() {
        
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

}
