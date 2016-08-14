/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dao.ProdutoDaoImpl;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import model.ProdutoTableModel;
import model.Produto;
import java.util.List;
import javax.swing.event.TableModelEvent;

/**
 *
 * @author kassiane
 */
public class JFCadastroProdutos extends JFrame{
    
    private JPanel painelFundo;
    private JPanel painelBotoes;
    private static JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btnNovo;
    private static ProdutoTableModel modelo;
    
    public JFCadastroProdutos(){
        criarJTable();
        criarJanela();
    }

    private void criarJTable() {
        tabela = new JTable(modelo);
        tabela.setAutoCreateRowSorter(true);
        buscar();
    }

    private void criarJanela() {
        
        btnNovo = new JButton("Novo Produto");
        painelBotoes = new JPanel();
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);
        painelBotoes.add(btnNovo);
        painelFundo.add(BorderLayout.SOUTH, painelBotoes);
        
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setTitle("Cadastro de Produto - Produtos Cadastrados");
        setVisible(true);
        
        btnNovo.addActionListener(new BtnNovoListener());
    }

    public static void buscar() {
        ProdutoDaoImpl dao = new ProdutoDaoImpl();
        modelo = new ProdutoTableModel(dao.getProdutos());
        tabela.setModel(modelo);
    }

    
    private class BtnNovoListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            abrirCadastro();
        }
    }
    
    private void abrirCadastro(){
        JDInserirProduto jfNovo = new JDInserirProduto(this, true);
        jfNovo.setVisible(true);
    }
    
}
