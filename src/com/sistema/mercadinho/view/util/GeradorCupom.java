/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.mercadinho.view.util;

import com.sistema.mercadinho.model.ItemVenda;
import com.sistema.mercadinho.model.Venda;
import com.sistema.mercadinho.pagamento.Dinheiro;
import com.sistema.mercadinho.pagamento.MeioDePagamento;
import java.time.format.DateTimeFormatter;
import javax.swing.JEditorPane;
import javax.swing.JOptionPane;

/**
 *
 * @author rag
 */
public class GeradorCupom {
    
    public static void emitir(Venda venda) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyy HH:mm:ss");
        
        StringBuilder cupom = new StringBuilder();
        cupom.append("<html><pre>===== CUPOM FISCAL =====\n\n");
        
        if (venda.getHoraDaVenda() != null) {
            cupom.append("Data: ").append(venda.getHoraDaVenda().format(formatador)).append("\n");
        }
        
        cupom.append("ID da venda: ").append(venda.getId()).append("\n");
        
        if (venda.getCliente() != null && venda.getCliente().getCPF() != null && !venda.getCliente().getCPF().trim().isEmpty()) {
            // Mostra o nome
            cupom.append("Cliente: ").append(venda.getCliente().getNome()).append("\n");
             // Mostra o CPF
            cupom.append("CPF: ").append(venda.getCliente().getCPF()).append("\n");
        } else {
            cupom.append("CPF: Não informado\n");
        }
        
        cupom.append("------------------------------------\n");
        cupom.append(String.format("%-15s %5s %10s\n", "Produto", "Qtd", "Preço"));

        for (ItemVenda item : venda.getItemVendaProdutos()) {
            cupom.append(String.format("%-15s %5d %10s\n",
                    item.getProduto().getDescricao(),
                    item.getQuantidade(),
                    String.format("R$ %.2f", item.calcularPrecoItem())
            ));
        }
        
        cupom.append("------------------------------------\n");
        
        MeioDePagamento meioPagamento = venda.getFormaPagamento();
        
        cupom.append(String.format("TOTAL DA VENDA:   R$ %.2f\n", venda.getValorTotal()));
        
        if (meioPagamento != null) {
            // POLIMORFISMO: O método toString() vai responder conforme a classe (Dinheiro, Pix, etc)
            cupom.append("Forma de pagamento:  ").append(meioPagamento.toString()).append("\n");

            // Verifica se é dinheiro para mostrar troco
            if (meioPagamento instanceof Dinheiro) {
                Dinheiro d = (Dinheiro) meioPagamento;
                cupom.append(String.format("Valor Recebido:   R$ %.2f\n", d.getValorEntregue()));
                cupom.append(String.format("Troco:            R$ %.2f\n", d.getTroco()));
            }
        } else {
            cupom.append("Pagamento:        Desconhecido\n");
        }

        cupom.append("</pre></html>");
        exibirJanela(cupom.toString(), venda.getId());
    }
    
    private static void exibirJanela(String textoCupom, int idVenda) {        
        JEditorPane areaTexto = new JEditorPane("text/html", textoCupom);
        areaTexto.setEditable(false);
        areaTexto.setOpaque(false);
        
        Object[] botoes = {"Imprimir", "Fechar"};
        
        int opcao = JOptionPane.showOptionDialog(null,
                areaTexto,
                "Cupom Fiscal#" + idVenda,
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                botoes,
                botoes[1]);
        
        if (opcao == 0) {
            try {
                boolean completo  = areaTexto.print();
                if (completo) {
                    JOptionPane.showMessageDialog(null, "Enviado para a impressora!");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
        }
    }
    
}
