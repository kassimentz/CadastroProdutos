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


/**
 *
 * @author kassi
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

    /**
     * Cria a Jtable
     */
    private void criarJTable() {
        tabela = new JTable(modelo);
        //ativa a ordenacao por colunas na tabela
        tabela.setAutoCreateRowSorter(true);
        //busca os dados do banco
        buscar();
    }

    /**
     * Cria a janela principal
     */
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

    /**
     * Realiza a busca dos produtos no banco de dados 
     * e atualiza a tabela.
     */
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
