/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.mercadinho.dao;

import com.sistema.mercadinho.model.Cliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rag
 */
public class ClienteDAO {

    private final String ARQUIVO = "clientes.ser";

    private static HashMap<String, Cliente> dadosClientes = new HashMap<>();

    public ClienteDAO() {
        if (dadosClientes.isEmpty()) {
            carregarDados();
        }
    }
    
    public List<Cliente> filtrarPorCpf(ClienteDAO cpfBuscado) {
        List<Cliente> resultado = new ArrayList<>();
        
        for (Cliente cliente : dadosClientes.values()) {
            if (cliente.getCPF().equals(cpfBuscado)) {
                resultado.add(cliente);
            }
        }
        return resultado;
    }

    public HashMap<String, Cliente> getDadosClientes() {
        return dadosClientes;
    }

    public void cadastrar(Cliente cliente) {
        dadosClientes.put(cliente.getCPF(), cliente);
        salvarDados();
    }

    public Cliente consultar(String cpf) {
        return dadosClientes.get(cpf);
    }

    public void remover(String cpf) {
        dadosClientes.remove(cpf);
        salvarDados();
    }

    public void atualizar(Cliente cliente) {
        dadosClientes.put(cliente.getCPF(), cliente);
        salvarDados();
    }

    // Método útil para fazer consultas
    public ArrayList<Cliente> listarTodos() {
        return new ArrayList<>(dadosClientes.values());
    }

    private void salvarDados() {
        try {
            FileOutputStream fileOut = new FileOutputStream(ARQUIVO);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dadosClientes);
            out.close();
            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void carregarDados() {
        try {
            File f = new File(ARQUIVO);

            if (f.exists()) {
                FileInputStream fileIn = new FileInputStream(ARQUIVO);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                dadosClientes = (HashMap<String, Cliente>) in.readObject();
                in.close();
                fileIn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            dadosClientes = new HashMap<>();
        }

    }

}
