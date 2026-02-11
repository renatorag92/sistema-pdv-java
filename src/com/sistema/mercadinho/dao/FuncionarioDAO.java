/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.mercadinho.dao;

import com.sistema.mercadinho.model.Funcionario;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author rag
 */
public class FuncionarioDAO {

    private final String ARQUIVO = "funcionarios.ser";

    private static HashMap<String, Funcionario> dadosFuncionarios = new HashMap<>();

    public FuncionarioDAO() {
        if (dadosFuncionarios.isEmpty()) {
            carregarDados();
        }
    }

    public HashMap<String, Funcionario> getDadosFuncionarios() {
        return dadosFuncionarios;
    }

    public void cadastrar(Funcionario funcionario) {
        dadosFuncionarios.put(funcionario.getCPF(), funcionario);
        salvarDados();
    }

    public Funcionario consultar(String cpf) {
        return dadosFuncionarios.get(cpf);
    }

    public void remover(String cpf) {
        dadosFuncionarios.remove(cpf);
        salvarDados();
    }

    public void atualizar(Funcionario funcionario) {
        dadosFuncionarios.put(funcionario.getCPF(), funcionario);
        salvarDados();
    }
    
    public ArrayList<Funcionario> listarTodos(){
        return new ArrayList<>(dadosFuncionarios.values());
    }

    // Verificaçao de login
    public Funcionario fazerLogin(String cpf, String senha) {
        Funcionario funcionario = dadosFuncionarios.get(cpf);

        if (funcionario != null && funcionario.getSenha().equals(senha)) {
            return funcionario;
        } else {
            return null;
        }
    }

    public void criarAdminPadrao() {
        Funcionario admin = new Funcionario();
        admin.setNome("Administrador");
        admin.setCPF("000.000.000-00");
        admin.setSenha("1234");
        admin.setCargo("Gerente");

        dadosFuncionarios.put(admin.getCPF(), admin);
        salvarDados();
    }

    private void salvarDados() {
        try {
            FileOutputStream fileOut = new FileOutputStream(ARQUIVO);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(dadosFuncionarios);
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
                dadosFuncionarios = (HashMap<String, Funcionario>) in.readObject();
                in.close();
                fileIn.close();
            } else {
                // Se o arquivo não existir(Primeira abertura)
                // Criar Admin padrão
                criarAdminPadrao();
            }
        } catch (Exception e) {
            e.printStackTrace();
            criarAdminPadrao();
        }
    }

}
