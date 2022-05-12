/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.saude.model;

public class Cargo {
    
    private int idcargo;
    private String nomecargo;

    public Cargo() {
        this.idcargo = 0;
        this.nomecargo = "";
    }

    public Cargo(int idcargo, String nomecargo) {
        this.idcargo = idcargo;
        this.nomecargo = nomecargo;
    }

    public int getIdcargo() {
        return idcargo;
    }

    public void setIdcargo(int idcargo) {
        this.idcargo = idcargo;
    }

    public String getNomecargo() {
        return nomecargo;
    }

    public void setNomecargo(String nomecargo) {
        this.nomecargo = nomecargo;
    }
    
    
    
}
