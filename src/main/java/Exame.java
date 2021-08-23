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

    public Exame(String descExame, int idConsulta) {
        this.descExame = descExame;
        this.idConsulta = idConsulta;
    }
    
    private String descExame;
    private int idConsulta;

    public String getDescExame() {
        return descExame;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setDescExame(String descExame) {
        this.descExame = descExame;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }
}
