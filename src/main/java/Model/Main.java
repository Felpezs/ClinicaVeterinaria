package Model;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


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
    
    List<Consulta> consultas = ConsultaDAO.getInstance().retrieveAll();
    
        System.out.println(consultas);
    }
}