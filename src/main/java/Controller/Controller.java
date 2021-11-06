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
import java.util.List;
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
            ((GenericTableModel)table.getModel()).addListOfItems(AnimalDAO.getInstance().retrieveBySimilarName(input, clienteSelecionado.getId()));
        }
        else if(table.getModel() instanceof EspecieTableModel)
            ((GenericTableModel)table.getModel()).addListOfItems(EspecieDAO.getInstance().retrieveBySimilarName(input));
    }
    
    public static void switchPanels(JPanel panel){
        visiblePanel.setVisible(false);
        visiblePanel = panel;
        visiblePanel.setVisible(true);
    }
}
