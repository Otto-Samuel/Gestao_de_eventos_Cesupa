package br.com.codandosimples;

import br.com.codandosimples.dao.CesupaDAO;
import br.com.codandosimples.model.Cesupa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;

public class EventosCesupaGUI {
    private CesupaDAO dao;
    private JFrame frame;
    private JTextArea textArea;

    public EventosCesupaGUI() {
        dao = new CesupaDAO();
        frame = new JFrame("Eventos Cesupa");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        textArea = new JTextArea();
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        JPanel buttonPanel = new JPanel();

        JButton addButton = new JButton("Adicionar evento");
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarEvento();
            }
        });

        JButton listButton = new JButton("Mostrar todos os eventos");
        listButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostrarEventos();
            }
        });

        JButton updateButton = new JButton("Atualizar evento (por ID)");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AtualizarEventoGUI atualizarEventoGUI = new AtualizarEventoGUI();
                atualizarEventoGUI.exibir();
            }
        });

        JButton deleteButton = new JButton("Excluir evento (por ID)");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExcluirEventoGUI excluirEventoGUI = new ExcluirEventoGUI();
                excluirEventoGUI.exibir();
            }
        });

        JButton exitButton = new JButton("Sair do programa");
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        buttonPanel.add(addButton);
        buttonPanel.add(listButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void adicionarEvento() {
        Cesupa cesupa = new Cesupa();

        // Solicitar informações do evento ao usuário
        String nome = JOptionPane.showInputDialog("Nome do evento:");
        String descricao = JOptionPane.showInputDialog("Descrição do evento:");
        String dataStr = JOptionPane.showInputDialog("Data do evento (yyyy-MM-dd):");
        LocalDate data = LocalDate.parse(dataStr);
        int npessoas = Integer.parseInt(JOptionPane.showInputDialog("Número de pessoas que vão estar:"));

        cesupa.setNome(nome);
        cesupa.setDescricao(descricao);
        cesupa.setData(data);
        cesupa.setNpessoas(npessoas);

        Cesupa eventoInserido = dao.save(cesupa);
        textArea.setText("Foi inserido o evento com o ID: " + eventoInserido.getId() + "\n");
    }

    private void mostrarEventos() {
        List<Cesupa> eventos = dao.findAll();
        StringBuilder eventosText = new StringBuilder();

        for (Cesupa evento : eventos) {
            eventosText.append("ID: ").append(evento.getId()).append("\n");
            eventosText.append("Nome do evento: ").append(evento.getNome()).append("\n");
            eventosText.append("Descrição do evento: ").append(evento.getDescricao()).append("\n");
            eventosText.append("Data do evento: ").append(evento.getData()).append("\n");
            eventosText.append("N° de pessoas que vão estar: ").append(evento.getNpessoas()).append("\n");
            eventosText.append("====================================").append("\n");
        }

        textArea.setText(eventosText.toString());
    }

    public void exibir() {
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        EventosCesupaGUI gui = new EventosCesupaGUI();
        SwingUtilities.invokeLater(() -> gui.exibir());
    }
}
