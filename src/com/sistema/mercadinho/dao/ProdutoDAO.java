package com.sistema.mercadinho.dao;

import com.sistema.mercadinho.model.Produto;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author rag
 */
public class ProdutoDAO {
    
    private Produto p;

    private static HashMap<String, Produto> estoque = new HashMap<>();

    public HashMap<String, Produto> getEstoque() {
        return estoque;
    }

    public void cadastrar(Produto produto) {
        estoque.put(produto.getCodigo(), produto);
        salvarDados();
    }

    public Produto consultar(String codigo) {
        return estoque.get(codigo);

    }

    public void remover(String codigo) {
        estoque.remove(codigo);
        salvarDados();

    }

    public void atualizar(Produto produto) {
        estoque.put(produto.getCodigo(), produto);
        salvarDados();

    }

    public ArrayList<Produto> listar() {
        return new ArrayList<>(estoque.values());
    }

    public void salvarDados() {
        try {
            FileOutputStream fileOut = new FileOutputStream("estoque.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(estoque); // Salva o teu HashMap inteiro
            out.close();
            fileOut.close();
            System.out.println("Dados do estoque salvos com sucesso!");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public void carregarDados() {
        try {
            FileInputStream fileIn = new FileInputStream("estoque.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            estoque = (HashMap<String, Produto>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Dados do estoque carregados!");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nenhum dado salvo encontrado. Começando estoque vazio.");
            estoque = new HashMap<>();
        }
    }

}
