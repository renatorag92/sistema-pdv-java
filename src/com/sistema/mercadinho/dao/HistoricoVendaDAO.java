package com.sistema.mercadinho.dao;

import com.sistema.mercadinho.model.Venda;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author rag
 */
public class HistoricoVendaDAO {

    private static HashMap<Integer, Venda> historico = new HashMap<>();

    public static HashMap<Integer, Venda> getHistorico() {
        return historico;
    }

    public ArrayList<Venda> listar() {
        return new ArrayList<>(historico.values());

    }
    
    public List<Venda> filtrarPorData(LocalDate dataBuscada) {
        List<Venda> resultado = new ArrayList<>();
        
        for (Venda venda : historico.values()) {
            if (venda.getHoraDaVenda().toLocalDate().equals(dataBuscada)) {
                resultado.add(venda);
            }
        }
        return resultado;
    }
    
    public HistoricoVendaDAO() {
        if (historico.isEmpty()) {
            carregarDados();
        }
    }

    public void adicionar(Venda venda) {
        while (historico.containsKey(venda.getId())) {
            int novoId = venda.getId() + 1;
            venda.setId(novoId);
        }           
        historico.put(venda.getId(), venda);
        salvarDados();
    }
    
    public Venda consultar(int id) {
        return historico.get(id);
    }
    
    public void remover(int id) {
        historico.remove(id);
        salvarDados();

    }
    
    public void atualizar(Venda venda) {
        historico.put(venda.getId(), venda);
        salvarDados();
    }

    public void salvarDados() {
        try {
            FileOutputStream fileOut = new FileOutputStream("vendas.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(historico);
            fileOut.close();
            System.out.println("Dados da venda salvos com sucesso!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void carregarDados() {
        try {
            FileInputStream fileIn = new FileInputStream("vendas.ser");
            ObjectInput in = new ObjectInputStream(fileIn);
            historico = (HashMap<Integer, Venda>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Dados das vendas carregados com sucesso!");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Nenhum dado salvo encontrado. Começando histórico vazio.");            
            historico  = new HashMap<>();
        }
    }
}
