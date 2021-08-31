
/**
 *
 * @author PC
 */
public class Especie {

    public Especie(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    private String nome;
    private int id;

    public String getNom_esp() {
        return nome;
    }
    
    public int getId(){
        return id;
    }
}
