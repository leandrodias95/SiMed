package br.com.saude.dao;

import br.com.saude.model.Categoria;
import br.com.saude.model.MaterialHospitalar;
import br.com.saude.utils.SingleConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO implements GenericDAO {

    private Connection conexao;
    
    public MaterialDAO() throws Exception{
        
        conexao = SingleConnection.getConnection();
    }
    
    @Override
   public Boolean cadastrar(Object objeto) {
        MaterialHospitalar oMaterialHospitalar = (MaterialHospitalar) objeto;
        Boolean retorno =false;
        if(oMaterialHospitalar.getIdMaterialHospitalar() ==0){
            retorno = this.inserir(oMaterialHospitalar);
        }else{
            retorno = this.alterar(oMaterialHospitalar);
        }
        return retorno;}

    @Override
    public Boolean inserir(Object objeto) {
       MaterialHospitalar oMaterialHospitalar =(MaterialHospitalar) objeto;
       PreparedStatement stmt = null;
       String sql ="insert into MaterialHospitalar (nomeMaterial, qtde,  idCategoria) values (?,?,?)";
       try{
           stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oMaterialHospitalar.getNomeMaterial());
            stmt.setInt(2, oMaterialHospitalar.getQtde());
            
            stmt.setInt(3, oMaterialHospitalar.getCategoria().getIdCategoria());
            stmt.execute();
            conexao.commit();
            return true;
       }catch (Exception ex){
           try{
               System.out.println("Problemas as cadastrar o material! erro: "+ex.getMessage());
               ex.printStackTrace();
               conexao.rollback();
           }catch (SQLException e){
               System.out.println("Erro"+e.getMessage());
               e.printStackTrace();
           }
           return false;
       }
    }

    @Override
    public Boolean alterar(Object objeto) {
        MaterialHospitalar oMaterialHospitalar = (MaterialHospitalar) objeto;
        PreparedStatement stmt = null;
        String sql ="update MaterialHospitalar set nomeMaterial=?, qtde=?, idCategoria=? where idMaterialHospitalar=?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, oMaterialHospitalar.getNomeMaterial());
            stmt.setInt(2, oMaterialHospitalar.getQtde());
            
            stmt.setInt(3, oMaterialHospitalar.getCategoria().getIdCategoria());
            stmt.setInt(4, oMaterialHospitalar.getIdMaterialHospitalar());
            stmt.execute();
            conexao.commit();
            return true;
        }catch(Exception ex){
            try{
                System.out.println("Problemas as alterar material erro: "+ex.getMessage());
                ex.printStackTrace();
                conexao.rollback();
            }catch (SQLException e){
                System.out.println("Erro: "+e.getMessage());
                e.printStackTrace();
            }
            return false;
        }
    }

    @Override
       public Boolean excluir(int numero) {
        int idMaterialHospitalar = numero;
        PreparedStatement stmt = null;
        String sql = "delete from MaterialHospitalar where idMaterialHospitalar = ?";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMaterialHospitalar);
            stmt.execute();
            conexao.commit();
            return true;
        }catch (Exception e){
            try {
                System.out.println("Problemas as excluir despesa ERRO" +e.getMessage());
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
        int idMaterialHospitalar = numero;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        MaterialHospitalar oMaterialHospitalar = null;
        String sql ="select * from MaterialHospitalar where idMaterialHospitalar=? ";
        try{
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idMaterialHospitalar);
            rs = stmt.executeQuery();
            while (rs.next()){
                oMaterialHospitalar = new MaterialHospitalar();
                oMaterialHospitalar.setIdMaterialHospitalar(rs.getInt("idMaterialHospitalar"));
                oMaterialHospitalar.setNomeMaterial(rs.getString("nomeMaterial"));
                oMaterialHospitalar.setQtde(rs.getInt("Qtde"));
                
                
                CategoriaDAO oCategoriaDAO = new CategoriaDAO();
                oMaterialHospitalar.setCategoria((Categoria) oCategoriaDAO.carregar(rs.getInt("idCategoria")));
            }
            return oMaterialHospitalar;
        }catch (Exception ex){
            System.out.println("Problemas as carregar material! erro: "+ex.getMessage());
            return false;
        }
    }

    @Override
    public List<Object> listar() {
        List<Object> resultado = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String sql = "Select * from MaterialHospitalar order by nomeMaterial";
        try{
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                MaterialHospitalar oMaterialHospitalar = new MaterialHospitalar();
                oMaterialHospitalar.setIdMaterialHospitalar(rs.getInt("idMaterialHospitalar"));
                oMaterialHospitalar.setNomeMaterial(rs.getString("nomeMaterial"));
                oMaterialHospitalar.setQtde(rs.getInt("qtde"));
                
                
                CategoriaDAO oCategoriaDAO = null;
                
                try{
                    oCategoriaDAO = new CategoriaDAO();
                } catch (Exception ex){
                    System.out.println("Erro ao buscar categoria: "+ex.getMessage());
                    ex.printStackTrace();
                }
                oMaterialHospitalar.setCategoria((Categoria) oCategoriaDAO.carregar(rs.getInt("idCategoria")));
                resultado.add(oMaterialHospitalar);
            }
        }catch (SQLException ex){
            System.out.println("Problemas as listar material erro: " +ex.getMessage());
        }
        return resultado;
    }
    
}