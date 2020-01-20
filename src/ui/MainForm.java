package ui;

import business.ContactBusiness;
import entity.ContactEntity;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainForm extends JFrame {
    private JPanel rootPanel;
    private JButton btnNovoContato;
    private JButton btnRemover;
    private JTable tableContatos;
    private JLabel lblContador;

    private ContactBusiness mContactBusiness;
    private String mNome = "";
    private String mTelefone = "";

    public MainForm() {
        setContentPane(rootPanel);
        setSize(500, 250);
        setVisible(true);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width / 2 - getSize().width / 2, dim.height / 2 - getSize().height / 2);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mContactBusiness = new ContactBusiness();

        setListeners();
        carregarContatos();
    }

    private void carregarContatos() {
        List<ContactEntity> contatos = mContactBusiness.obterLista();

        String[] colummNames = {"Nome", "Telefone"};
        DefaultTableModel model = new DefaultTableModel(new Object[0][0], colummNames);

        for (ContactEntity i : contatos) {
            Object[] o = new Object[2];
            o[0] = i.getNome();
            o[1] = i.getTelefone();

            model.addRow(o);
        }

        tableContatos.clearSelection();

        tableContatos.setModel(model);

        lblContador.setText(mContactBusiness.obterQuantidadeContato());
    }

    private void setListeners() {
        btnNovoContato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                new ContactForm();
                dispose();
            }
        });

        tableContatos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    mNome = tableContatos.getValueAt(tableContatos.getSelectedRow(), 0).toString();
                    mTelefone = tableContatos.getValueAt(tableContatos.getSelectedRow(), 1).toString();
                }
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                try {
                    mContactBusiness.deletar(mNome, mTelefone);

                    carregarContatos();

                    mNome = "";
                    mTelefone = "";


                } catch (Exception e) {

                    JOptionPane.showMessageDialog(new JFrame(), e.getMessage());
                }
            }
        });
    }
}
