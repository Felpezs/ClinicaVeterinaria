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

    public Exame(String desc_exame, int id_consulta) {
        this.desc_exame = desc_exame;
        this.id_consulta = id_consulta;
    }
    
    private int id_exame;
    private String desc_exame;
    private int id_consulta;

    public String getDesc_exame() {
        return desc_exame;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setDesc_exame(String desc_exame) {
        this.desc_exame = desc_exame;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }
    
    
}
