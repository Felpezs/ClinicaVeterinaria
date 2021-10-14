package View;

import Controller.Controller;
import Model.Cliente;
import Model.Especie;
import java.util.List;

/**
 *
 * @author PC
 */
public class EspecieTableModel extends GenericTableModel{
    
    public EspecieTableModel(List vDados){
        super(vDados, new String[]{"Nome"});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Especie especie = (Especie) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return especie.getNom_esp();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        Especie especie = (Especie) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                especie.setNom_esp((String)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        Controller.updateInstance(especie);
    }
    
    @Override
    public boolean isCellEditable(int rowIndex,int columnIndex){
        return true;
    }
}

