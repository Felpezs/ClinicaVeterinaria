/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Exame {

    public Exame(int id, String nome, int idConsulta) {
        this.id = id;
        this.nome = nome;
        this.idConsulta = idConsulta;
    }
    
    private int id;
    private String nome;
    private int idConsulta;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setDescExame(String nome) {
        this.nome = nome;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    @Override
    public String toString() {
        return "Exame{" + "id=" + id + ", nome=" + nome + ", idConsulta=" + idConsulta + '}';
    }
}
