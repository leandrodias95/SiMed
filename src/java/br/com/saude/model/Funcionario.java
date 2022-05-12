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
public class Funcionario {
    
    private int idfuncionario;
    private String nomeFuncionario;
    private String cpf;
    private String turno;
    private String imagemFuncionario;
    private Cargo cargo;

    public Funcionario(int idfuncionario, String nomeFuncionario, String cpf, String turno, String imagemFuncionario, Cargo cargo) {
        this.idfuncionario = idfuncionario;
        this.nomeFuncionario = nomeFuncionario;
        this.cpf = cpf;
        this.turno = turno;
        this.imagemFuncionario = imagemFuncionario;
        this.cargo = cargo;
    }

    

    public Funcionario() {
        idfuncionario =0;
        nomeFuncionario ="";
        cpf="";
        turno = "";
        cargo = new Cargo();
    }

    public int getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(int idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getImagemFuncionario() {
        return imagemFuncionario;
    }

    public void setImagemFuncionario(String imagemFuncionario) {
        this.imagemFuncionario = imagemFuncionario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    
       
    
}
