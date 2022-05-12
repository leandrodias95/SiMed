/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saude.dao;

import br.com.saude.model.Categoria;
import br.com.saude.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO implements GenericDAO{
    
    private Connection conexao;
    
    public CategoriaDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Categoria oCategoria = (Categoria) objeto;
        Boolean retorno=false;
        if(oCategoria.getIdCategoria()==0){
            retorno = this.inserir(oCategoria);
        }else{
            retorno = this.alterar(oCategoria);
        }
        return retorno;
    }

    @Override
   public Boolean inserir(Object objeto) {
        Categoria oCategoria = (Categoria) objeto;
        PreparedStatement stmt = null;
        String sql ="insert into categoria (nomeCategoria) values (?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oCategoria.getNomeCategoria());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
            System.out.println("Problemas as cadastrar Categoria erro!"+ex.getMessage());
            ex.printStackTrace();
            conexao.rollback();
        }catch(SQLException e){
            System.out.println("Erro "+e.getMessage());
            e.printStackTrace();
            }
        return false;
        }
   }

    @Override
    public Boolean alterar(Object objeto) {
        Categoria oCategoria = (Categoria) objeto;
        PreparedStatement stmt = null;
        String sql = "update categoria set nomeCategoria=? where idCategoria=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oCategoria.getNomeCategoria());
            stmt.setInt(2, oCategoria.getIdCategoria());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas as alterar Categoria! Erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch(SQLException e){
                System.out.println("Erro: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
       int idCategoria = numero;
       PreparedStatement stmt = null;
       String sql ="delete from categoria where idCategoria=?";
       try{
           
           stmt=conexao.prepareStatement(sql);
           stmt.setInt(1, idCategoria);
           stmt.execute();
           conexao.commit();
           return true;
       }catch (Exception ex){
           System.out.println("Problemas as excluir Categoria Erro: "+ex.getMessage());
           try{
               conexao.rollback();
           }catch(SQLException e){
               System.out.println("erro rolback"+e.getMessage());
               e.printStackTrace();
           }
           return false;
       }
    }

    @Override
    public Object carregar(int numero) {
        int idCategoria = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Categoria oCategoria =null;
        String sql ="select * from categoria where idCategoria=?";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idCategoria);
            rs = stmt.executeQuery();
            while(rs.next()){
                oCategoria = new Categoria();
                oCategoria.setIdCategoria(rs.getInt("idCategoria"));
                oCategoria.setNomeCategoria(rs.getString("nomeCategoria"));
            }
            return oCategoria;
        }catch (Exception ex){
            System.out.println("Problemas ao carregar Categoria! Erro: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs= null;
        String sql = "select * from Categoria order by idCategoria";
        try{
            stmt= conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Categoria oCategoria = new Categoria();
                oCategoria.setIdCategoria(rs.getInt("idCategoria"));
                oCategoria.setNomeCategoria(rs.getString("nomeCategoria"));
                resultado.add(oCategoria);
            }
        }catch(SQLException ex){
            System.out.println("Problemas ao listar Categoria! Erro: "+ex.getMessage());
        }
       return resultado;         
    }
    
}
