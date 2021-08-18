import java.util.List;

/**
 *
 * @author PC
 */
public class Main {
    public static void main(String[] args){
        Cliente c1 = new Cliente(1,"Felipe", "Ruaaa", "9181", "cep", "email");

        Animal a1 = new Animal(1,"Doguinho",2,0);
        Animal a2 = new Animal(2,"Gatinho",10,1);
        
        c1.addAnimal(a1);
        c1.addAnimal(a2);
        
        System.out.println(c1);
        
        List<Animal> listaExterna = c1.getAnimais();
        Animal a3 = new Animal(3, "", 5, 1);
        listaExterna.add(a3);
        
        System.out.println(c1);
    }
}
