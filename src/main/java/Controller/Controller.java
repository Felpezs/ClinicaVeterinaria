package Controller;

import Model.Animal;
import Model.AnimalDAO;
import Model.Cliente;
import Model.ClienteDAO;
import Model.Consulta;
import Model.ConsultaDAO;
import Model.Especie;
import Model.EspecieDAO;
import Model.Exame;
import Model.ExameDAO;
import Model.Tratamento;
import Model.TratamentoDAO;
import Model.Veterinario;
import Model.VeterinarioDAO;
import javax.swing.JTable;
import View.GenericTableModel;

/**
 *
 * @author PC
 */
public class Controller {
    public static void setTableModel(JTable table, GenericTableModel tableModel){
        table.setModel(tableModel);
    }
    
    public static void updateInstance(Object instance){
        if(instance.getClass().equals(Cliente.class))
            ClienteDAO.getInstance().update((Cliente)instance);
        
        else if(instance.getClass().equals(Animal.class))
            AnimalDAO.getInstance().update((Animal)instance);
        
        else if(instance.getClass().equals(Veterinario.class))
            VeterinarioDAO.getInstance().update((Veterinario)instance);
        
        else if(instance.getClass().equals(Consulta.class))
            ConsultaDAO.getInstance().update((Consulta)instance);
        
        else if(instance.getClass().equals(Exame.class))
            ExameDAO.getInstance().update((Exame)instance);
        
        else if(instance.getClass().equals(Tratamento.class))
            TratamentoDAO.getInstance().update((Tratamento)instance);
        
        else if(instance.getClass().equals(Especie.class))
            EspecieDAO.getInstance().update((Especie)instance);
    }
}
