/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saude.model;

/**
 *
 * @author gabri
 */
public class MaterialHospitalar {
    private int idMaterialHospitalar;
    private String nomeMaterial;
    private int Qtde;
    private Categoria categoria;


    public MaterialHospitalar(int idMaterialHospitalar, String nomeMaterial, int Qtde, Categoria categoria) {
        this.idMaterialHospitalar = idMaterialHospitalar;
        this.nomeMaterial = nomeMaterial;
        this.Qtde = Qtde;
        this.categoria = categoria;
        
    }

    
    
    
    public MaterialHospitalar() {
        this.idMaterialHospitalar = 0;
        this.nomeMaterial="";
        this.Qtde=0;
        this.categoria = new Categoria();
        
    }

    public int getIdMaterialHospitalar() {
        return idMaterialHospitalar;
    }

    public void setIdMaterialHospitalar(int idMaterialHospitalar) {
        this.idMaterialHospitalar = idMaterialHospitalar;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public int getQtde() {
        return Qtde;
    }

    public void setQtde(int Qtde) {
        this.Qtde = Qtde;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

   
    
    
}
