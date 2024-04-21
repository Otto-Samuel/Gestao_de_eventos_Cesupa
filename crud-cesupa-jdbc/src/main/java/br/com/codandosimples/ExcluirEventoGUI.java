package br.com.codandosimples;

import br.com.codandosimples.dao.CesupaDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExcluirEventoGUI {
    private CesupaDAO dao;
    private JFrame frame;

    public ExcluirEventoGUI() {
        dao = new CesupaDAO();
        frame = new JFrame("Excluir Evento");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(300, 100);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));

        JLabel idLabel = new JLabel("ID do Evento:");
        JTextField idField = new JTextField();

        JButton deleteButton = new JButton("Excluir");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long eventIdToDelete = Long.parseLong(idField.getText());
                if (dao.findById(eventIdToDelete).isPresent()) {
                    dao.delete(eventIdToDelete);
                    JOptionPane.showMessageDialog(frame, "Evento com ID " + eventIdToDelete + " foi excluído com sucesso!");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Evento com ID " + eventIdToDelete + " não encontrado.");
                }
            }
        });

        panel.add(idLabel);
        panel.add(idField);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(deleteButton, BorderLayout.SOUTH);
    }

    public void exibir() {
        frame.setVisible(true);
    }
}
