/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Produto;

/**
 *
 * @author kassi
 */
public class ProdutoDaoImpl implements ProdutoDAO {

    private static final String DRIVER_NAME = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/produtos";
    private static final String ID = "root";
    private static final String PASS = "root";
    private static final String DB_NAME = "produtos";
    private static final String DATABASE = "CREATE DATABASE IF NOT EXISTS "+DB_NAME;
    
    
    private static final String TB_PRODUTO = "CREATE TABLE IF NOT EXISTS produto (id mediumint(9) NOT NULL PRIMARY KEY AUTO_INCREMENT, nome varchar(200), preco double, imagem blob)";

    private static final String DELETE = "DELETE FROM produto WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM produto ORDER BY id";
    private static final String FIND_BY_ID = "SELECT * FROM produto WHERE id=?";
    private static final String INSERT = "INSERT INTO produto(nome, preco, imagem) VALUES(?, ?, ?)";
    private static final String UPDATE = "UPDATE produto SET nome=?, preco=?, imagem=? WHERE id=?";
    

    /**
     * Deletar um produto buscando por seu ID
     * @param id
     * @return o status da operacao
     */
    public int delete(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    /**
     * Retorna a lista de produtos existentes no banco
     * @return lista de produtos
     */
    public List<Produto> getProdutos() {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Produto> list = new ArrayList<>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setImagem(rs.getBytes("imagem"));

                list.add(produto);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }

        return list;
    }

    /**
     * Busca um produto pelo seu id
     * @param id
     * @return produto encontrado
     */
    public Produto findById(int id) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produto produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setImagem(rs.getBytes("imagem"));

                return produto;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    /**
     * insere um produto na base de dados
     * @param produto
     * @return o status da operacao
     */
    public int insert(Produto produto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setBytes(3, produto.getImagem());

            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                produto.setId(rs.getInt(1));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    /**
     * Atualiza um produto na base de dados
     * @param produto
     * @return o status da operacao
     */
    public int update(Produto produto) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setBytes(3, produto.getImagem());
            stmt.setInt(4, produto.getId());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }
    
    /**
     * cria o banco de dados e a tabela se ainda nao existirem
     */
    public void iniciaDB(){
        createDatabase();
        createTable();
    }
    
    /**
     * cria o banco de dados
     */
    private void createDatabase(){
        
        Connection conn = null;
        
        try {
            Class.forName(DRIVER_NAME);
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/",ID, PASS);
            Statement statement = conn.createStatement();
            statement.executeUpdate(DATABASE);
            statement.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * cria a tabela
     */
    private void createTable(){
        Connection conn = null;
        try {
            Class.forName(DRIVER_NAME);
            conn = getConnection();
            Statement statement = conn.createStatement();
            statement.execute(TB_PRODUTO);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    }

    /**
     * realiza a conexao do banco de dados
     * @return 
     */
    private Connection getConnection() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(DB_URL, ID, PASS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * fecha a conexao do banco de dados
     * @param con 
     */
    private static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * fecha o statement
     * @param stmt 
     */
    private static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
