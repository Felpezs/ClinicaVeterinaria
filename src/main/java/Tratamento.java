import java.util.Calendar;

/**
 *
 * @author PC
 */
public class Tratamento {
    
    public Tratamento(String nome, Calendar dat_ini, Calendar dat_fin, int idAnimal, boolean terminou) {
        this.nome = nome;
        this.dat_ini = dat_ini;
        this.dat_fin = dat_fin;
        this.idAnimal = idAnimal;
        this.terminou = terminou;
    }
        
    private String nome;
    private Calendar dat_ini;
    private Calendar dat_fin;
    private int idAnimal;
    private boolean terminou;

    public Calendar getDat_ini() {
        return dat_ini;
    }

    public Calendar getDat_fin() {
        return dat_fin;
    }

    public void setDat_ini(Calendar dat_ini) {
        this.dat_ini = dat_ini;
    }

    public void setDat_fin(Calendar dat_fin) {
        this.dat_fin = dat_fin;
    }

    public String getNome() {
        return nome;
    }

    public int getIdAnimal() {
        return idAnimal;
    }

    public boolean isTerminou() {
        return terminou;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTerminou(boolean terminou) {
        this.terminou = terminou;
    }
    
}
