import java.util.List;

/**
 *
 * @author PC
 */
public class Main {
    public static void main(String[] args){
        
        ClienteDAO.getInstance().create("Felipe", "Rua dos maluco", "9181", "felps@gmail.com", "986660191");
        Cliente c1 = ClienteDAO.getInstance().retrieveById(1);
        
        System.out.println(c1);
    }
}
