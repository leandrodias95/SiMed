/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saude.model;


public class UnidadeHospitalar {
    
    private int idunidadeHospitalar;
    private String nomeUnidade;
    private String cnpj;
   

    public UnidadeHospitalar(int idunidadeHospitalar, String nomeUnidade, String cnpj) {
        this.idunidadeHospitalar = idunidadeHospitalar;
        this.nomeUnidade= nomeUnidade;
        this.cnpj = cnpj;
        
    }

    public UnidadeHospitalar() {
        this.idunidadeHospitalar = 0;
        this.nomeUnidade= "";
        this.cnpj="";
        
        
    }   

    public int getIdunidadeHospitalar() {
        return idunidadeHospitalar;
    }

    public void setIdunidadeHospitalar(int idunidadeHospitalar) {
        this.idunidadeHospitalar = idunidadeHospitalar;
    }

    public String getNomeUnidade() {
        return nomeUnidade;
    }

    public void setNomeUnidade(String nomeUnidade) {
        this.nomeUnidade = nomeUnidade;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
}
