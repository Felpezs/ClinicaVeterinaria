package View;

import Controller.Controller;
import Model.Tratamento;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author PC
 */
public class TratamentoTableModel extends GenericTableModel{
    
    public TratamentoTableModel(List vDados){
        super(vDados, new String[]{"Nome","Data de Início","Concluído"});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return String.class;
            case 1:
                return Calendar.class;
            case 2:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Tratamento tratamento = (Tratamento) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return tratamento.getNome();
            case 1:
                return tratamento.getDat_ini();
            case 2:
                return tratamento.isTerminou();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        Tratamento tratamento = (Tratamento) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                tratamento.setNome((String)aValue);
                break;
            case 1:
                tratamento.setDat_ini((Calendar)aValue);
                break;
            case 2:
                tratamento.setTerminou((Boolean)aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        Controller.updateInstance(tratamento); 
    }
    
    @Override
    public boolean isCellEditable(int rowIndex,int columnIndex){
        return true;
    }
}
