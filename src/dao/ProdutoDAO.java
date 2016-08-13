/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Produto;

/**
 *
 * @author kassiane
 */
public interface ProdutoDAO {
    
    public int insert(Produto produto);
    public int delete(int id);
    public int update(Produto produto);
    public Produto findById(int id);
    public List<Produto> getProdutos();
    
}
