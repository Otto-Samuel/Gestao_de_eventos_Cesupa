package br.com.codandosimples;

import br.com.codandosimples.dao.CesupaDAO;
import br.com.codandosimples.model.Cesupa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Optional;

public class AtualizarEventoGUI {
    private CesupaDAO dao;
    private JFrame frame;

    public AtualizarEventoGUI() {
        dao = new CesupaDAO();
        frame = new JFrame("Atualizar Evento");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 250);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("ID do Evento:");
        JTextField idField = new JTextField();
        JLabel nameLabel = new JLabel("Novo Nome:");
        JTextField nameField = new JTextField();
        JLabel descriptionLabel = new JLabel("Nova Descrição:");
        JTextField descriptionField = new JTextField();
        JLabel dateLabel = new JLabel("Nova Data (yyyy-MM-dd):");
        JTextField dateField = new JTextField();
        JLabel npessoasLabel = new JLabel("Novo Número de Pessoas:");
        JTextField npessoasField = new JTextField();

        JButton updateButton = new JButton("Atualizar");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long eventIdToUpdate = Long.parseLong(idField.getText());
                Optional<Cesupa> eventoOptional = dao.findById(eventIdToUpdate);

                if (eventoOptional.isPresent()) {
                    Cesupa eventoParaAtualizar = eventoOptional.get();
                    eventoParaAtualizar.setNome(nameField.getText());
                    eventoParaAtualizar.setDescricao(descriptionField.getText());
                    eventoParaAtualizar.setData(LocalDate.parse(dateField.getText()));
                    eventoParaAtualizar.setNpessoas(Integer.parseInt(npessoasField.getText()));

                    dao.update(eventoParaAtualizar);
                    JOptionPane.showMessageDialog(frame, "Evento atualizado com sucesso!");
                    frame.dispose();
                } else {
                    JOptionPane.showMessageDialog(frame, "Evento com ID " + eventIdToUpdate + " não encontrado.");
                }
            }
        });

        panel.add(idLabel);
        panel.add(idField);
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(descriptionLabel);
        panel.add(descriptionField);
        panel.add(dateLabel);
        panel.add(dateField);
        panel.add(npessoasLabel);
        panel.add(npessoasField);

        frame.add(panel, BorderLayout.CENTER);
        frame.add(updateButton, BorderLayout.SOUTH);
    }

    public void exibir() {
        frame.setVisible(true);
    }
}
