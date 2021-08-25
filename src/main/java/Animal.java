/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Animal {
    
    public Animal(int id, String nome, int anoNasc, String sexo, int idEspecie, int idCliente) {
        this.id = id;
        this.nome = nome;
        this.anoNasc = anoNasc;
        this.sexo = sexo;
        this.idEspecie = idEspecie;
        this.idCliente = idCliente;
    }
    
    private int id;
    private String nome;
    private int anoNasc;
    private int idEspecie;
    private int idCliente;
    private String sexo; //Macho, Femea

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getAnoNasc() {
        return anoNasc;
    }

    public String getSexo() {
        return sexo;
    }

    public int getIdEspecie() {
        return idEspecie;
    }

    public int getIdCliente() {
        return idCliente;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setAnoNasc(int anoNasc) {
        this.anoNasc = anoNasc;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setIdEspecie(int idEspecie) {
        this.idEspecie = idEspecie;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    @Override
    public String toString() {
        return "Animal{" + "nome=" + nome + ", ano de nascimento=" + anoNasc + ", especie=" + idEspecie +'}';
    }

    
}
