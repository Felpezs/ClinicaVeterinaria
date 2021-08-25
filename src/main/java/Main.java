import java.util.List;

/**
 *
 * @author PC
 */
public class Main {
    public static void main(String[] args){
        
        Cliente c1 = ClienteDAO.getInstance().retrieveById(1);
        Cliente c2 = ClienteDAO.getInstance().retrieveById(2);
        
        List<Animal> c1_animals = AnimalDAO.getInstance().retrieveByIdCliente(c1.getId());
        List<Animal> c2_animals = AnimalDAO.getInstance().retrieveByIdCliente(c2.getId());
        System.out.println(c1_animals);
        System.out.println(c2_animals);
    }
}