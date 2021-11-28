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
import View.AnimalTableModel;
import View.ClienteTableModel;
import View.EspecieTableModel;
import javax.swing.JTable;
import View.GenericTableModel;
import View.VeterinarioTableModel;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author PC
 */
public class Controller {
    private static Cliente clienteSelecionado = null;
    private static Animal animalSelecionado = null;
    private static Veterinario veterinarioSelecionado = null;
    private static Tratamento tratamentoSelecionado = null;
    private static Consulta consultaSelecionado = null;
    private static Especie especieSelecionado = null;
    private static JTextField clienteSelecionadoTextField = null;
    private static JTextField animalSelecionadoTextField = null;
    private static JTextField especieSelecionadoTextField = null;
    private static JTextField veterinarioSelecionadoTextField = null;
    private static JTextField tratamentoSelecionadoTextField = null;
    private static JTextArea consultaSelecionadoTextField = null;
    private static JTextArea consultaSelecionadoTextArea = null;
    private static JTextArea exameTextArea = null;
    private static JPanel visiblePanel = null;
    private static JPanel clickedNavItem = null;
    private static Color navItemColor = null;
    private static Color clickedColor = new Color(77, 86, 168);
    private static Color defaultColor = new Color(38, 38, 84);
    
    public static void setTableModel(JTable table, GenericTableModel tableModel){
        table.setModel(tableModel);
    }
    
    public static void setFields(JTextField clienteField, JTextField animalField, JTextField especieField, JTextField veterinarioField, JTextField tratamentoField, JTextArea consultaField, JTextArea consultaField2, JTextArea exameField){
        clienteSelecionadoTextField = clienteField;
        animalSelecionadoTextField = animalField;
        especieSelecionadoTextField = especieField;
        veterinarioSelecionadoTextField = veterinarioField;
        tratamentoSelecionadoTextField = tratamentoField;
        consultaSelecionadoTextField = consultaField;
        consultaSelecionadoTextArea = consultaField2;
        exameTextArea = exameField;
    }
    
    public static void setVisiblePanel(JPanel animalCliente){
        visiblePanel = animalCliente;
    }
    
    public static void setClickedItem(JPanel itemAnimal){
        clickedNavItem = itemAnimal;
    }
    
    public static void setClicked(JPanel panel){
        clickedNavItem.setBackground(defaultColor);
        clickedNavItem = panel;
        clickedNavItem.setBackground(clickedColor);
        navItemColor = clickedColor;
    }
    
    public static void hoverEffect(JPanel panel){
        navItemColor = panel.getBackground();
        if(!panel.getBackground().equals(clickedColor)){
            panel.setBackground(new Color(55, 55, 123));
        }
    }
    
    public static Color exitedHover(){
        return navItemColor;
    }
    
    public static Cliente getClienteSelecionado(){
        return clienteSelecionado;
    }
    
    public static Animal getAnimalSelecionado(){
        return animalSelecionado;
    }
    
    public static Tratamento getTratamentoSelecionado(){
        return tratamentoSelecionado;
    }
    
    public static void setSelected(Object selected){
       if(selected instanceof Cliente){
           clienteSelecionado = (Cliente)selected;
           clienteSelecionadoTextField.setText(clienteSelecionado.getNome());
           animalSelecionadoTextField.setText("");
           especieSelecionadoTextField.setText("");
           consultaSelecionadoTextField.setText("");
           consultaSelecionadoTextArea.setText("");
       }
       else if(selected instanceof Animal){
           animalSelecionado = (Animal)selected;
           animalSelecionadoTextField.setText(animalSelecionado.getNome());
           especieSelecionadoTextField.setText(EspecieDAO.getInstance().retrieveById(animalSelecionado.getIdEspecie()).getNom_esp());
           tratamentoSelecionadoTextField.setText("");
           consultaSelecionadoTextField.setText("");
           consultaSelecionadoTextArea.setText("");
           List<Consulta> consulta = ConsultaDAO.getInstance().retrieveByIdAnimal(animalSelecionado.getId());
           if(consulta.isEmpty()){
               consultaSelecionadoTextField.setText("Sem consultas registradas");
               consultaSelecionadoTextArea.setText("Sem consultas registradas");
           }
           else{
               for(Consulta c : consulta){
                   String status = c.isTerminou() ? "(Concluída) - " : "(Pendente) - ";
                   consultaSelecionadoTextField.append(status + c.getComentarios() + "\n");
               }
           }
       }
       else if(selected instanceof Veterinario){
           veterinarioSelecionado = (Veterinario)selected;
           veterinarioSelecionadoTextField.setText(veterinarioSelecionado.getNome());
       }
       else if(selected instanceof Tratamento){
           tratamentoSelecionado = (Tratamento)selected;
           tratamentoSelecionadoTextField.setText(tratamentoSelecionado.getNome());
           exameTextArea.setText("");
           consultaSelecionadoTextArea.setText("");
       }
       
       else if(selected instanceof Consulta){
           consultaSelecionado = (Consulta)selected;
           String status = consultaSelecionado.isTerminou() ? "(Concluída) - " : "(Pendente) - ";
           consultaSelecionadoTextArea.setText(status + consultaSelecionado.getComentarios());
           
           exameTextArea.setText("");
           List<Exame> exame = ExameDAO.getInstance().retrieveByIdConsulta(consultaSelecionado.getId());
           
           if(exame.isEmpty()){
               exameTextArea.setText("Sem exames registrados para a consulta selecionada!");
           }
           else{
               for(Exame e : exame){
                   exameTextArea.append(e.getNome());
               }
           }
       }
       else if(selected instanceof Especie){
           especieSelecionado = (Especie)selected;
       }
    }
    
    public static void updateInstance(Object instance){
        if(instance.getClass().equals(Cliente.class))
            ClienteDAO.getInstance().update((Cliente)instance);
        
        else if(instance.getClass().equals(Animal.class))
            AnimalDAO.getInstance().update((Animal)instance);
        
        else if(instance.getClass().equals(Veterinario.class))
            VeterinarioDAO.getInstance().update((Veterinario)instance);
        
        else if(instance.getClass().equals(Consulta.class)){
            ConsultaDAO.getInstance().update((Consulta)instance);
        }
             
        else if(instance.getClass().equals(Exame.class))
            ExameDAO.getInstance().update((Exame)instance);
        
        else if(instance.getClass().equals(Tratamento.class))
            TratamentoDAO.getInstance().update((Tratamento)instance);
        
        else if(instance.getClass().equals(Especie.class))
            EspecieDAO.getInstance().update((Especie)instance);
    }
    
    public static void setBySearch(JTable table, String input){
        if(table.getModel() instanceof ClienteTableModel)
            ((GenericTableModel)table.getModel()).addListOfItems(ClienteDAO.getInstance().retrieveBySimilarName(input));
        else if(table.getModel() instanceof VeterinarioTableModel)
            ((GenericTableModel)table.getModel()).addListOfItems(VeterinarioDAO.getInstance().retrieveBySimilarName(input));
        else if(table.getModel() instanceof AnimalTableModel){
            if(clienteSelecionado != null)
                ((GenericTableModel)table.getModel()).addListOfItems(AnimalDAO.getInstance().retrieveBySimilarName(input, clienteSelecionado.getId()));
        }
        else if(table.getModel() instanceof EspecieTableModel)
            ((GenericTableModel)table.getModel()).addListOfItems(EspecieDAO.getInstance().retrieveBySimilarName(input));
    }
    
    public static void insertLine(JTable table){
        if(table.getModel() instanceof ClienteTableModel){
            Cliente cliente = ClienteDAO.getInstance().create("", "", "", "", "");
            ((GenericTableModel)table.getModel()).addItem(cliente);
        }
        else if(table.getModel() instanceof VeterinarioTableModel){
            Veterinario veterinario = VeterinarioDAO.getInstance().create("", "", "");
            ((GenericTableModel)table.getModel()).addItem(veterinario);
        }
        
        else if(table.getModel() instanceof AnimalTableModel){      
            if(Controller.getClienteSelecionado()!=null){
                Especie especie = EspecieDAO.getInstance().retrieveById(1);
                Animal animal = AnimalDAO.getInstance().create("", 0, "macho", especie, clienteSelecionado); 
                ((GenericTableModel)table.getModel()).addItem(animal);
            }
            else
                JOptionPane.showMessageDialog(new JFrame(), "Um cliente deve ser selecionado para fazer o cadastro de um animal", "Dialog",JOptionPane.ERROR_MESSAGE);   
        }
        
        else if(table.getModel() instanceof Tratamento){
            if(Controller.getAnimalSelecionado() != null){
                Calendar c1 = Calendar.getInstance();
                Tratamento tratamento = TratamentoDAO.getInstance().create(Controller.getAnimalSelecionado(), "", c1, c1, false);
                ((GenericTableModel)table.getModel()).addItem(tratamento);
            }
            else
                JOptionPane.showMessageDialog(new JFrame(), "Um animal deve ser selecionado para fazer o cadastro de um tratamento", "Dialog",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void cleanSearch(JTextField textField){
        textField.setText("");
    }
    
    public static void deleteRowCliente(JTable tableCliente, JTable tableAnimal){
        Cliente cliente = (Cliente)((GenericTableModel)tableCliente.getModel()).getItem(tableCliente.getSelectedRow());
        
        List<Animal> animais = AnimalDAO.getInstance().retrieveByIdCliente(cliente.getId());
        for(Animal animal : animais){
            AnimalDAO.getInstance().delete(animal);
            ((GenericTableModel)tableAnimal.getModel()).removeItem(0);
        }
        
        ClienteDAO.getInstance().delete(cliente);
        ((GenericTableModel)tableCliente.getModel()).removeItem(tableCliente.getSelectedRow());
        
        animalSelecionadoTextField.setText("");
        especieSelecionadoTextField.setText("");
        consultaSelecionadoTextField.setText("");
        consultaSelecionadoTextArea.setText("");
        clienteSelecionadoTextField.setText("");
        animalSelecionado = null;
        clienteSelecionado = null;
    }
    /*
    public static void deleteRow(JTable table){
        Object obj = ((GenericTableModel)table.getModel()).getItem(table.getSelectedRow());
        ((GenericTableModel)table.getModel()).removeItem(table.getSelectedRow());
        
        if(table.getModel() instanceof VeterinarioTableModel){
            Veterinario veterinario = (Veterinario) obj;
            VeterinarioDAO.getInstance().delete(veterinario);
        }
        else if(table.getModel() instanceof)
    }
*/
    
    public static void switchPanels(JPanel panel){
        visiblePanel.setVisible(false);
        visiblePanel = panel;
        visiblePanel.setVisible(true);
    }
}
