package Model;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author PC
 */
public class Cliente {

    public Cliente(int id, String nome, String endereco, String cep, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.cep = cep;
        this.email = email;
        this.animais = new ArrayList<Animal>();
    }
    
    private List<Animal> animais;
    private int id;
    private String nome;
    private String endereco;
    private String telefone;
    private String cep;
    private String email;

    public int getId() {
        return id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public String getEndereco() {
        return endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public String getEmail() {
        return email;
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

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setEmail(String email) {
        if(!email.equals("")){
            this.email = email;
        }
    }
    
    /*
    public boolean addAnimal(Animal animal){
        if(!animal.getNome().isBlank()){
            animais.add(animal);
            return true;
        }
        return false;
    }
    */
    
    public List<Animal> getAnimais(){
        List<Animal> copia = new ArrayList<Animal>(this.animais);
        return copia;
    }
    
    @Override
    public String toString() {
        String desc = "Cliente{" + "nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone + ", cep=" + cep + ", email=" + email + '}';
        String strAnimais = animais.toString();
        
        return desc + "\n" + strAnimais;
    }
    
}

