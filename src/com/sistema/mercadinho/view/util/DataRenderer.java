/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sistema.mercadinho.view.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author rag
 */
public class DataRenderer extends DefaultTableCellRenderer {

    @Override
    public void setValue(Object value) {
        if (value instanceof LocalDateTime) {
            setText(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss").format((LocalDateTime) value));
        } else {
            super.setValue(value);
        }
    }

}
