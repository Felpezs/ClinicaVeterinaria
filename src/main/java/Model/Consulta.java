package Model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
    
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
        return dateFormat.format(data.getTime());
    }

    public void setData(String data) throws ParseException {
        Date date = dateFormat.parse(data);
        this.data.setTime(date);
    }

    public String getHora() {
        return hourFormat.format(data.getTime());
    }
    
    public void setHora(String hora) throws ParseException {
        Date hour = hourFormat.parse(hora);
        this.data.setTime(hour);
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
