package View;

import Controller.Controller;
import Model.Animal;
import Model.AnimalDAO;
import Model.ClienteDAO;
import Model.Consulta;
import Model.TratamentoDAO;
import Model.VeterinarioDAO;
import java.util.List;

/**
 *
 * @author PC
 */
public class ConsultasTableModel extends GenericTableModel{
    
    public ConsultasTableModel(List vDados){
        super(vDados, new String[]{"Data","Horário","Cliente","Animal","Veterinário","Finalizado","Tratamento"});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return String.class;
            case 5:
                return Boolean.class;
            case 6:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Consulta consulta = (Consulta) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return consulta.getData();
            case 1:
                return consulta.getHora();
            case 2:
                Animal animal = AnimalDAO.getInstance().retrieveById(consulta.getIdAnimal());
                return ClienteDAO.getInstance().retrieveById(animal.getIdCliente()).getNome();      
            case 3:
                return AnimalDAO.getInstance().retrieveById(consulta.getIdAnimal()).getNome();
            case 4:
                return VeterinarioDAO.getInstance().retrieveById(consulta.getIdVet()).getNome();
            case 5:
                return consulta.isTerminou();
            case 6:
                return TratamentoDAO.getInstance().retrieveById(consulta.getIdTratamento()).getNome();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public boolean isCellEditable(int rowIndex,int columnIndex){
        return false;
    }
}
