/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import dao.ProdutoDaoImpl;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author kassiane
 */
public class ProdutoTableModel extends AbstractTableModel {

    private List<Produto> listaProduto = new ArrayList<>();
    private String[] colunas = new String[]{"Id","Imagem", "Nome", "Preço"};

    public ProdutoTableModel() {
    }

    public ProdutoTableModel(List<Produto> listaProduto) {
        this.listaProduto = listaProduto;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public int getRowCount() {
        if (!listaProduto.isEmpty()) {
            return listaProduto.size();
        } else {
            return 0;
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return String.class;
    }

    /**
     * Retorna o campo referente à coluna selecionada. O Switch verifica qual a
     * coluna retornar
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Produto produto = listaProduto.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return produto.getId();
            case 1:
                return produto.getImagem();
            case 2:
                return produto.getNome();
            case 3:
                return produto.getPreco();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    /**
     * Seta valor na linha e coluna selecionada. O Switch verifica qual coluna
     * setar
     *
     * @param aValue
     * @param rowIndex
     * @param columnIndex
     */
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Produto produto = listaProduto.get(rowIndex);

        switch (columnIndex) {

            case 2:
                produto.setNome(aValue.toString());
                break;
            case 3:
                produto.setPreco(Double.valueOf(aValue.toString()));
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);
        ProdutoDaoImpl dao = new ProdutoDaoImpl();
        dao.update(produto);
    }

    public void setValueAtRow(Produto prod, int rowIndex) {
        Produto produto = listaProduto.get(rowIndex);

        produto.setNome(prod.getNome());
        produto.setPreco(prod.getPreco());

        fireTableCellUpdated(rowIndex, 1);
        fireTableCellUpdated(rowIndex, 2);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if(columnIndex == 0){
            return false;
        }else{
            return true;
        }
    }

    public Produto getProduto(int rowIndex) {
        return listaProduto.get(rowIndex);
    }

    /**
     * Adiciona um produto na JTable
     *
     * @param produto
     */
    public void addProduto(Produto produto) {
        listaProduto.add(produto);
        int lastIndex = getRowCount() - 1;

        fireTableRowsInserted(lastIndex, lastIndex);
    }

    /**
     * Adiciona uma lista de produtos ao final dos registros
     *
     * @param listaProduto
     */
    public void addListProduto(List<Produto> listaProduto) {
        int position = getRowCount() - 1;
        int oldSize = getRowCount();
        this.listaProduto.addAll(listaProduto);

        fireTableRowsInserted(oldSize, position);
    }

    /**
     * Remove todos os registros
     */
    public void clear() {
        listaProduto.clear();
        fireTableDataChanged();
    }

    public boolean isEmpty() {
        return listaProduto.isEmpty();
    }

    private void updateProduto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
