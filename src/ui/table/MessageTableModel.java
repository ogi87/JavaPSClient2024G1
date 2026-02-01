/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.table;

import domain.Message;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.text.SimpleDateFormat;

public class MessageTableModel extends AbstractTableModel {
    private List<Message> messages;
    private String[] columnNames = {"Od", "Kome", "Poruka", "Vreme"};
    private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public MessageTableModel(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public int getRowCount() {
        return messages.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Message m = messages.get(rowIndex);
        switch (columnIndex) {
            case 0: return m.getSender().getFirstName();
            case 1: return (m.getReceiver() == null) ? "SVI" : m.getReceiver().getFirstName();
            case 2: 
                String text = m.getText();
                // Logika za prikazivanje samo prvih 20 karaktera
                if (text.length() > 20) {
                    return text.substring(0, 20) + "...";
                }
                return text;
            case 3: return sdf.format(m.getTimestamp());
            default: return "";
        }
    }

    // Pomoćna metoda da izvučemo celu poruku kada korisnik klikne na red
    public Message getMessageAt(int row) {
        return messages.get(row);
    }
}
