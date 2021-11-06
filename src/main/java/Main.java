import Model.Cliente;
import Model.ClienteDAO;
import Model.Consulta;
import Model.ConsultaDAO;


/**
 *
 * @author PC
 */
public class Main {
    public static void main(String[] args){
    /*
        Calendar c = Calendar.getInstance();
        
        Animal a = AnimalDAO.getInstance().retrieveById(1);
        Veterinario vet = VeterinarioDAO.getInstance().retrieveById(1);
        Tratamento trat = TratamentoDAO.getInstance().retrieveById(5);
        String comentarios = "Otite canina";
        ConsultaDAO.getInstance().create(c, comentarios, a, vet, trat, true); 
        
        Consulta consulta = ConsultaDAO.getInstance().retrieveById(1);

        List<Veterinario> veterinarios = VeterinarioDAO.getInstance().retrieveAll();
    */  
        //System.out.println(e1);
    /*
        Animal a1 = AnimalDAO.getInstance().retrieveById(1);
        
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        
        String dataFim="31/12/2021";  
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date data2 = dateFormat.parse(dataFim);
        c2.setTime(data2);
        Tratamento t1 = TratamentoDAO.getInstance().create(a1, "sutura", c1, c2, true);
    */
    
    //List<Consulta> consultas = ConsultaDAO.getInstance().retrieveAll();
         Consulta c = ConsultaDAO.getInstance().retrieveById(1);
       
        // c.setData("25/01/2002");
         //System.out.println(c.getData());
         
        Cliente cliente = ClienteDAO.getInstance().create("Marcos", "Rua dos laranjais", "080808", "Marcos@borges.unicamp.br", "123456789");
        
        System.out.println(cliente);
         
    }
}