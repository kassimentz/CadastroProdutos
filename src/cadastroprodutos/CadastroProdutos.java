/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cadastroprodutos;

import dao.ProdutoDaoImpl;
import telas.JFCadastroProdutos;

/**
 *
 * @author kassi
 */
public class CadastroProdutos {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ProdutoDaoImpl dao = new ProdutoDaoImpl();
        dao.iniciaDB();
        JFCadastroProdutos cadastro = new JFCadastroProdutos();
        cadastro.setVisible(true);
    }
    
}
