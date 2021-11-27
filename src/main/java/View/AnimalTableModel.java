package View;

import Controller.Controller;
import Model.Animal;
import Model.Especie;
import Model.EspecieDAO;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author PC
 */
public class AnimalTableModel extends GenericTableModel{
    
    public AnimalTableModel(List vDados){
        super(vDados, new String[]{"Nome","Ano de Nascimento","Sexo","Especie"});
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex){
            case 0:
                return String.class;
            case 1:
                return Integer.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        Animal animal = (Animal) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                return animal.getNome();
            case 1:
                return animal.getAnoNasc();
            case 2:
                return animal.getSexo();
            case 3:
                Especie especie = EspecieDAO.getInstance().retrieveById(animal.getIdEspecie());
                return especie != null ? especie.getNom_esp() : "";
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        Animal animal = (Animal) vDados.get(rowIndex);
        
        switch(columnIndex){
            case 0:
                animal.setNome((String)aValue);
                break;
            case 1:
                animal.setAnoNasc((int)aValue);
                break;
            case 2:
                boolean validSex = animal.setSexo((String)aValue);
                if(!validSex){
                    JOptionPane.showMessageDialog(new JFrame(), "Um cliente deve ser selecionado para fazer o cadastro de um animal", "Dialog",JOptionPane.ERROR_MESSAGE);
                }
                break;
            case 3:
                Especie especie = EspecieDAO.getInstance().retrieveByName((String)aValue);
                
                if(especie == null){
                    especie = EspecieDAO.getInstance().create((String)aValue);
                    Controller.updateInstance(especie);
                }
                animal.setIdEspecie(especie.getId());
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        Controller.updateInstance(animal); 
    }
    
    @Override
    public boolean isCellEditable(int rowIndex,int columnIndex){
        return true;
    }
}
