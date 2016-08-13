/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import dao.ProdutoDaoImpl;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Produto;
import model.ProdutoTableModel;

/**
 *
 * @author kassiane
 */
public class JFNovoProduto extends JFrame{
    
    private ProdutoTableModel modelo = new ProdutoTableModel();
    private JPanel painelFundo;
    private JButton btnConfirmar;
    private JButton btnCancelar;
    private JLabel lbNome;
    private JLabel lblPreco;
    private JLabel lblImagem;
    private JTextField txtNome;
    private JTextField txtPreco;
    private JTextField iptImagem;
    
    public JFNovoProduto(ProdutoTableModel md){
        criaJanela();
        modelo = md;
    }

    private void criaJanela() {
        btnConfirmar = new JButton("Confirmar");
        btnCancelar = new JButton("Cancelar");
        lbNome = new JLabel("Nome do produto:");
        lblPreco = new JLabel("Preço:");
        lblImagem = new JLabel("Imagem:");
        txtNome = new JTextField(100);
        txtPreco = new JTextField();
        iptImagem = new JTextField();
        
        painelFundo = new JPanel();
        painelFundo.setLayout(new GridLayout(4, 2, 2, 4));
        painelFundo.add(lbNome);
        painelFundo.add(txtNome);
        painelFundo.add(lblPreco);
        painelFundo.add(txtPreco);
        painelFundo.add(lblImagem);
        painelFundo.add(iptImagem);
        painelFundo.add(btnConfirmar);
        painelFundo.add(btnCancelar);
        
        getContentPane().add(painelFundo);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setVisible(true);
        btnConfirmar.addActionListener(new BtnConfirmarListener());
        btnCancelar.addActionListener(new BtnCancelarListener());

    }

    private class BtnConfirmarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Produto prod = new Produto();
            prod.setNome(txtNome.getText());
            prod.setPreco(Double.valueOf(txtPreco.getText()));
            
            ProdutoDaoImpl dao = new ProdutoDaoImpl();
            dao.insert(prod);
            JFCadastroProdutos.buscar();
            setVisible(false);
            
        }
    }

    private class BtnCancelarListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
}
