package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class Consulta {

    public Consulta(int id, Calendar data, String comentarios, int idAnimal, int idVet, int idTratamento, boolean terminou) {
        this.id = id;
        this.data = data;
        this.comentarios = comentarios;
        this.idAnimal = idAnimal;
        this.idVet = idVet;
        this.idTratamento = idTratamento;
        this.terminou = terminou;
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    SimpleDateFormat datFormat = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
    private int id;
    private Calendar data;
    private String comentarios;
    private int idAnimal;
    private int idVet;
    private int idTratamento;
    private boolean terminou;    

    public int getId() {
        return id;
    }
    
    public String getData() {
        return datFormat.format(data.getTime());
    }

    public void setData(String data){
        try{
            this.data.setTime(sdf.parse(data + " " + this.getHora()));
        }
        catch(ParseException e){
            System.err.println("Exception: " + e.getMessage());
        }
    }

    public String getHora() {
        return hourFormat.format(data.getTime());
    }
    
    public void setHora(String hora){
        try{
            this.data.setTime(sdf.parse(this.getData() + " " + hora));
        }
        catch(ParseException e){
            System.err.println("Exception: " + e.getMessage());
        }
        
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(int idAnimal) {
        this.idAnimal = idAnimal;
    }

    public int getIdVet() {
        return idVet;
    }

    public void setIdVet(int idVet) {
        this.idVet = idVet;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }

    @Override
    public String toString() {
        return "Consulta{" + "id=" + id + ", comentarios=" + comentarios + ", idAnimal=" + idAnimal + ", idVet=" + idVet + ", idTratamento=" + idTratamento + ", terminou=" + terminou + '}';
    }
}
