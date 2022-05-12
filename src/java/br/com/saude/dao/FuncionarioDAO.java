/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saude.dao;

import br.com.saude.model.Cargo;
import br.com.saude.model.Funcionario;
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
public class FuncionarioDAO implements GenericDAO {
    
    private Connection conexao;
    public FuncionarioDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }

    @Override
    public Boolean cadastrar(Object objeto) {
        Funcionario oFuncionario = (Funcionario) objeto;
        boolean retorno = false;
        if(oFuncionario.getIdfuncionario() == 0){
            retorno = inserir(oFuncionario);
        }else{
            retorno = alterar(oFuncionario);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
       Funcionario oFuncionario =(Funcionario) objeto;
       PreparedStatement stmt = null;
       String sql ="insert into funcionario (nomefuncionario,cpf,turno,idcargo,imagemfuncionario) values (?,?,?,?,?)";
       try{
           stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oFuncionario.getNomeFuncionario());
            stmt.setString(2, oFuncionario.getCpf());
            stmt.setString(3, oFuncionario.getTurno());
            stmt.setInt(4, oFuncionario.getCargo().getIdcargo());
            stmt.setString(5, oFuncionario.getImagemFuncionario());
            stmt.execute();
            conexao.commit();
            return true;
       }catch (Exception ex){
           try{
               System.out.println("Problemas as cadastrar o funcionario ERRO"+ex.getMessage());
               ex.printStackTrace();
               conexao.rollback();
           }catch (SQLException e){
               System.out.println("ERRO"+e.getMessage());
               e.printStackTrace();
           }
           return false;
       }
    }

     @Override
    public Boolean alterar(Object objeto) {
        Funcionario oFuncionario = (Funcionario) objeto;
        PreparedStatement stmt = null;
        String sql ="update funcionario set nomefuncionario=?,cpf=?,turno=?,idcargo=?,imagemfuncionario=? where idfuncionario=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oFuncionario.getNomeFuncionario());
            stmt.setString(2, oFuncionario.getCpf());
            stmt.setString(3, oFuncionario.getTurno());
            stmt.setInt(4, oFuncionario.getCargo().getIdcargo());
            stmt.setString(5, oFuncionario.getImagemFuncionario());
            stmt.setInt(6, oFuncionario.getIdfuncionario());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas as alterar funcionario ERRO"+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("ERRO"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
        int idfuncionario = numero;
        PreparedStatement stmt = null;
        String sql = "delete from funcionario  where idfuncionario=?";
        try{
           
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idfuncionario);
            stmt.execute();
            conexao.commit();
            return true;
    }catch(Exception ex){
            
            try{
                System.out.println("Problemas ao excluir funcionario ERRO"+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch(SQLException e){
                System.out.println("Erro rolback"+e.getMessage());
                e.printStackTrace();
            }
            return false;
    }
    }    

    @Override
    public Object carregar(int numero) {
        int idfuncionario = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Funcionario oFuncionario = null;
        String sql ="select * from funcionario where idfuncionario=? ";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idfuncionario);
            rs = stmt.executeQuery();
            while (rs.next()){
                oFuncionario = new Funcionario();
                oFuncionario.setIdfuncionario(rs.getInt("idfuncionario"));
                oFuncionario.setNomeFuncionario(rs.getString("nomefuncionario"));
                oFuncionario.setCpf(rs.getString("cpf"));
                oFuncionario.setTurno(rs.getString("turno"));
                oFuncionario.setImagemFuncionario(rs.getString("imagemfuncionario"));
                
                CargoDAO oCargoDAO = new CargoDAO();
                oFuncionario.setCargo((Cargo) oCargoDAO.carregar(rs.getInt("idcargo")));
            }
            return oFuncionario;
        }catch (Exception ex){
            System.out.println("Problemas as carregar Funcionario ERRO"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from funcionario order by nomefuncionario";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                Funcionario oFuncionario = new Funcionario();
                oFuncionario.setIdfuncionario(rs.getInt("idfuncionario"));
                oFuncionario.setNomeFuncionario(rs.getString("nomeFuncionario"));
                oFuncionario.setCpf(rs.getString("cpf"));
                oFuncionario.setTurno(rs.getString("turno"));
                oFuncionario.setImagemFuncionario(rs.getString("imagemFuncionario"));
                
                CargoDAO oCargoDAO = null;
                
                try{
                    oCargoDAO = new CargoDAO();
                } catch (Exception ex){
                    System.out.println("ERRO buscar cargo"+ex.getMessage());
                    ex.printStackTrace();
                }
                oFuncionario.setCargo((Cargo) oCargoDAO.carregar(rs.getInt("idcargo")));
                resultado.add(oFuncionario);
            }
        }catch (SQLException ex){
            System.out.println("Problemas as listar Funcionario ERRO" 
                +ex.getMessage());
        }
        return resultado;
    }
    
}
