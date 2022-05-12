/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saude.dao;

import br.com.saude.model.UnidadeHospitalar;
import br.com.saude.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 */
public class UnidadeDAO implements GenericDAO {
    
    private Connection conexao;
    
    public UnidadeDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        UnidadeHospitalar oUnidadeHospitalar = (UnidadeHospitalar) objeto;
        boolean retorno = false;
        if(oUnidadeHospitalar.getIdunidadeHospitalar() == 0){
            retorno = inserir(oUnidadeHospitalar);
        }else{
            retorno = alterar(oUnidadeHospitalar);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
      UnidadeHospitalar oUnidadeHospitalar = (UnidadeHospitalar) objeto;
      PreparedStatement stmt = null;
      String sql = "insert into UnidadeHospitalar(nomeUnidade, cnpj) values(?,?)";
      
      try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oUnidadeHospitalar.getNomeUnidade());
          stmt.setString(2, oUnidadeHospitalar.getCnpj());
          stmt.execute();
          conexao.commit();
          return true;
          
      }catch(Exception e){
          try{
              System.out.println("Problemas ao cadastrar Unidade Hospitalar"+e.getMessage());
              e.printStackTrace();
              conexao.commit();
          }catch(SQLException ex){
              System.out.println("Problemas ao executar rollback"+ex.getMessage());
              ex.printStackTrace();
          }
          return false;
      }
    }

    @Override
    public Boolean alterar(Object objeto) {
      UnidadeHospitalar oUnidadeHospitalar = (UnidadeHospitalar) objeto;
      PreparedStatement stmt = null;
      String sql = "update unidadeHospitalar set nomeUnidade=?, cnpj=? where idunidadeHospitalar=?";
      try{
          stmt = conexao.prepareStatement(sql);
          stmt.setString(1, oUnidadeHospitalar.getNomeUnidade());
          stmt.setString(2, oUnidadeHospitalar.getCnpj());
          stmt.setInt(3, oUnidadeHospitalar.getIdunidadeHospitalar());
          stmt.execute();
          conexao.commit();
          return true;
          }catch(Exception e){
          try{
              System.out.println("Problemas ao alterar Unidade Hospitalar"+e.getMessage());
              e.printStackTrace();
              conexao.commit();
          }catch(SQLException ex){
              System.out.println("Problemas ao executar rollback"+ex.getMessage());
              ex.printStackTrace();
          }
          return false;
      }
    }

    @Override
         public Boolean excluir(int numero) {
        int idunidadeHospitalar = numero;
        PreparedStatement stmt = null;
        String sql = "delete from unidadeHospitalar where idunidadeHospitalar = ?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idunidadeHospitalar);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception e){
            try {
                System.out.println("Problemas as excluir unidade hospitalar ERRO" +e.getMessage());
                e.printStackTrace();
                conexao.rollback();
            }catch (SQLException ex ){
                System.out.println("Problemas ao executar rollback" + ex.getMessage());
                ex.printStackTrace();
            }
            return false;
        }
    }    


    @Override
      public Object carregar(int numero) {
        int idunidadeHospitalar = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UnidadeHospitalar oUnidadeHospitalar = null;
        String sql ="select * from unidadeHospitalar where idunidadeHospitalar=? ";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idunidadeHospitalar);
            rs = stmt.executeQuery();
            while (rs.next()){
                 oUnidadeHospitalar= new UnidadeHospitalar();
                 oUnidadeHospitalar.setIdunidadeHospitalar(rs.getInt("idunidadeHospitalar"));
                 oUnidadeHospitalar.setNomeUnidade(rs.getString("nomeUnidade"));
                 oUnidadeHospitalar.setCnpj(rs.getString("cnpj"));
               
            }
            return oUnidadeHospitalar;
        }catch (Exception ex){
            System.out.println("Problemas as carregar unidade hospitalar! erro: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
    List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from unidadeHospitalar order by nomeUnidade";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                UnidadeHospitalar oUnidadeHospitalar = new UnidadeHospitalar();
                oUnidadeHospitalar.setIdunidadeHospitalar(rs.getInt("idunidadeHospitalar"));
                oUnidadeHospitalar.setNomeUnidade(rs.getString("nomeUnidade"));
                oUnidadeHospitalar.setCnpj(rs.getString("cnpj"));
                resultado.add(oUnidadeHospitalar);
                
            }
        }catch (SQLException ex){
            System.out.println("Problemas as listar material erro: " +ex.getMessage());
        }
        return resultado;
    }
}
    
