

/**
 *
 * @author PC
 */
public class Veterinario {
    
    public Veterinario(String nome, String endereco, String telefone, int id_consulta, int id_vet) {
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.id_consulta = id_consulta;
        this.id_vet = id_vet;
    }
    
    private int id_vet;
    private String nome;
    private String endereco;
    private String telefone;
    private int id_consulta;

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public int getId_consulta() {
        return id_consulta;
    }

    public void setId_consulta(int id_consulta) {
        this.id_consulta = id_consulta;
    }

    public int getId_vet() {
        return id_vet;
    }

    public void setId_vet(int id_vet) {
        this.id_vet = id_vet;
    }
}
