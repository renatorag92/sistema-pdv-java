/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.mercadinho.view.util;

import java.text.NumberFormat;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author rag
 */
public class MoedaRenderer extends DefaultTableCellRenderer {

    @Override
    public void setValue(Object value) {
        if (value instanceof Number) {
            setText(NumberFormat.getCurrencyInstance().format(value));
        } else {
            super.setValue(value);
        }
    }
    
}
