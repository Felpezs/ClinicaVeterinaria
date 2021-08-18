import java.util.Calendar;

/**
 *
 * @author PC
 */
public class Consulta {

    public Consulta(Calendar dat_con, String historico, int idTratamento) {
        this.dat_con = dat_con;
        this.historico = historico;
        this.idTratamento = idTratamento;
    }
    
    private Calendar dat_con;
    private String historico;
    private int idTratamento;
    private int id_animal;
    private int id_vet;
    private boolean terminou;

    public Calendar getDat_con() {
        return dat_con;
    }

    public String getHistorico() {
        return historico;
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setDat_con(Calendar dat_con) {
        this.dat_con = dat_con;
    }

    public void setHistorico(String historico) {
        this.historico = historico;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }
}
