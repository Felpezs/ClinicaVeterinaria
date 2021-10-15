package View;

import Controller.Controller;
import Model.Consulta;
import Model.VeterinarioDAO;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author PC
 */
public class ConsultaTableModel extends GenericTableModel{
    
    public ConsultaTableModel(List vDados){
        super(vDados, new String[]{"Data","Período","Veterinário"});
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
                return VeterinarioDAO.getInstance().retrieveById(consulta.getIdVet()).getNome();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        Consulta consulta = (Consulta) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                //tratar string antes de passsar para o setter para evitar exception
                System.out.println("Estou setando a data");
                consulta.setData((String)aValue);
                System.out.println(consulta.getData());
                break;
            case 1:
                consulta.setHora((String)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");            
        }
        
        Controller.updateInstance(consulta);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex,int columnIndex){
        if(columnIndex == 2){
            return false;
        }
        return true;
    }
}
