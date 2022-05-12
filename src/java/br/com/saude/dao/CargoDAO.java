
package br.com.saude.dao;

import br.com.saude.model.Cargo;
import br.com.saude.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CargoDAO implements GenericDAO{

    private Connection conexao;
    
    public CargoDAO() throws Exception{
        conexao = SingleConnection.getConnection();
    }
    @Override
    public Boolean cadastrar(Object objeto) {
        Cargo oCargo = (Cargo) objeto;
        Boolean retorno = false;
        if(oCargo.getIdcargo()== 0){
            retorno = this.inserir(oCargo);
        }else{
            retorno = this.alterar(oCargo);
        }
        return retorno;
    }

    @Override
    public Boolean inserir(Object objeto) {
        Cargo oCargo = (Cargo) objeto;
        PreparedStatement stmt = null;
        String sql ="insert into cargo (nomecargo) values (?)";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oCargo.getNomecargo());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
            System.out.println("Problemas as cadastrar cargo erro!"+ex.getMessage());
            ex.printStackTrace();
            conexao.rollback();
        }catch(SQLException e){
            System.out.println("ERRO"+e.getMessage());
            e.printStackTrace();
        }
        return false;
    }}

    @Override
    public Boolean alterar(Object objeto) {
        Cargo oCargo = (Cargo) objeto;
        PreparedStatement stmt = null;
        String sql = "update cargo set nomecargo=? where idcargo=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oCargo.getNomecargo());
            stmt.setInt(2, oCargo.getIdcargo());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas as alterar cargo Erro!"+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch(SQLException e){
                System.out.println("ERRO:"+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
    public Boolean excluir(int numero) {
       int idcargo = numero;
       PreparedStatement stmt = null;
       String sql ="delete from cargo where idcargo=?";
       try{
           stmt=conexao.prepareStatement(sql);
           stmt.setInt(1, idcargo);
           stmt.execute();
           conexao.commit();
           return true;
       }catch (Exception ex){
           System.out.println("Problemas as excluir o cargo !ERRO"
           +ex.getMessage());
           try{
               conexao.rollback();
           }catch(SQLException e){
               System.out.println("ERRO rolback"+e.getMessage());
               e.printStackTrace();
           }
           return false;
       }
    }

    @Override
    public Object carregar(int numero) {
        int idcargo = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Cargo oCargo =null;
        String sql ="select * from cargo where idcargo=?";
        
        try{
            stmt=conexao.prepareStatement(sql);
            stmt.setInt(1, idcargo);
            rs = stmt.executeQuery();
            while(rs.next()){
                oCargo = new Cargo();
                oCargo.setIdcargo(rs.getInt("idcargo"));
                oCargo.setNomecargo(rs.getString("nomecargo"));
            }
            return oCargo;
        }catch (Exception ex){
            System.out.println("Problemas as carregar Cargo Erro"+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from cargo order by idcargo";
        try{
            stmt = conexao.prepareStatement(sql);
            rs=stmt.executeQuery();
            while (rs.next()){
                Cargo oCargo = new Cargo();
                oCargo.setIdcargo(rs.getInt("idcargo"));
                oCargo.setNomecargo(rs.getString("nomecargo"));
                resultado.add(oCargo);
            }
        }catch (SQLException ex){
            System.out.println("Problemas ao listar Cargo ERRP!"
                    +ex.getMessage());
        }
        return resultado;
    }
    
}
