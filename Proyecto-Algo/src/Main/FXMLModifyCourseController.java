/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
import domain.TimeTable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author 31670
 */
public class FXMLModifyCourseController implements Initializable {

    private CircularDoublyLinkedList course = util.Utility.getCourses();
    private DoublyLinkedList career = util.Utility.getCareers();
    private Course curso;

    
    
    
    @FXML
    private Text txtMessage2;
    @FXML
    private Text txtMessage3;
    @FXML
    private Text txtMessage4;
    
    @FXML
    private TextField txfNewName;
    @FXML
    private TextField txfNewCredits;
    @FXML
    private ComboBox<String> ComboBox;
    ObservableList<String> oL_ComboBox = FXCollections.observableArrayList();
    @FXML
    private Button btnCambiarCurso;
    @FXML
    private Button btnBuscarCurso;
    @FXML
    private Text txtMessage;
     private SingleLinkedList schedule = util.Utility.getSchedules();
    @FXML
    private TextField tfName;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
       
        // TODO
         Node aux;
        try {
            aux = career.getNode(1);
            int x = 0;
            while (aux != null) {
                Career temp = (Career) aux.data;
                String y = temp.getDescription();
                oL_ComboBox.add(x, y);
                aux = aux.next;
                x = x + 1;
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ComboBox.setItems(oL_ComboBox);
    

    }

    @FXML
    private void btnBuscarCurso(ActionEvent event) {
        
        int i=0;
        
         try{
         
            Node aux = course.getNode(1);
            
                    while(aux!=course.getNodeLast()){
                    Course temp=(Course) aux.data;
                    if (util.Utility.equals(temp.getName(), this.tfName)) {
                    curso=temp;
                   
                 
                     }
                    aux=aux.next;
                    }
                     Course temp=(Course) aux.data;
                    if (util.Utility.equals(temp.getName(), this.tfName)) {
                    curso=temp;
                   
                 
                     }
                    }catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
         if(!schedule.isEmpty()){
         
         try {
             
           Node aux = schedule.getNode(1);

            while (aux != null) { 
                
                TimeTable temp = (TimeTable) aux.data;
               if(curso.getName().equals(temp.getCourseID().getName())){
               i++;
               
               }
               

            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
         if(i>0){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ventana de dialogo");
                    alert.setHeaderText("Información");
                    alert.setContentText("No se puede modificar el curso");
                    alert.showAndWait();
         
         
         }else{
         
         try {
            if (course.contains1(tfName.getText()) ){
                curso=null;
                txtMessage.setText("");
                
                txtMessage2.setVisible(true);
                txtMessage3.setVisible(true);
                txtMessage4.setVisible(true);
               
                txfNewName.setVisible(true);
                txfNewCredits.setVisible(true);
                ComboBox.setVisible(true);
                btnCambiarCurso.setVisible(true);

            } else {
                this.txtMessage.setText("El curso no está en la lista");
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLModifyCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         
         
         }
        
//        try {
//            if (course.contains2(tfCursoID.getText()) ){
//                txtMessage.setText("");
//                txtMessage1.setVisible(true);
//                txtMessage2.setVisible(true);
//                txtMessage3.setVisible(true);
//                txtMessage4.setVisible(true);
//                txfNewID.setVisible(true);
//                txfNewName.setVisible(true);
//                txfNewCredits.setVisible(true);
//                ComboBox.setVisible(true);
//
//            } else {
//                this.txtMessage.setText("El curso no está en la lista");
//            }
//        } catch (ListException ex) {
//            Logger.getLogger(FXMLModifyCourseController.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }
    
    @FXML
    private void btnCambiarCurso(ActionEvent event) throws ListException {
        
        try{
         
            Node aux = course.getNode(1);
            
                    while(aux!=course.getNodeLast()){
                    Course temp=(Course) aux.data;
                    if (util.Utility.equals(temp.getName(), this.tfName)) {
                    curso=temp;
                   
                    
//                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Ventana de dialogo");
//                    alert.setHeaderText("Información");
//                    alert.setContentText("Curso modificado correctamente");
//                    alert.showAndWait();
                    
                     }
                    aux=aux.next;
                    }
                    
                    }catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
                    
                    }  
                    
                    curso.setName(txfNewName.getText());
                   curso.setCredits(Integer.parseInt(txfNewCredits.getText()));
                    
               
                txtMessage2.setVisible(false);
                txtMessage3.setVisible(false);
                txtMessage4.setVisible(false);
        
                
                txfNewName.setText("");
                txfNewCredits.setText("");
                ComboBox.setVisible(false);
                btnCambiarCurso.setText("");
                
               
                txfNewName.setVisible(false);
                txfNewCredits.setVisible(false);
                btnCambiarCurso.setVisible(false);
                        
                
                
                
               
        
    }
    public static void numericOnly(final TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

//        try {
//            Node aux = course.getNode(1);
//            int x = Integer.parseInt(this.tfNuevoID.getText());
//
//            while (aux != null) {
//                if (!util.Utility.equals(aux.data, x)) {
//                    aux.data = new Course("","",x, Integer.parseInt(tfCambiarNombre.getText()));
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Ventana de dialogo");
//                    alert.setHeaderText("Informacion");
//                    alert.setContentText("Se cambió la carrera");
//                    alert.showAndWait();
//                } else {
//                    aux.data = new Course(x, Integer.parseInt(tfCambiarNombre.getText());
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Ventana de dialogo");
//                    alert.setHeaderText("Informacion");
//                    alert.setContentText("Se cambió la carrera");
//                    alert.showAndWait();
//
//                }
//                aux = aux.next;
//            }
//            this.btnBuscarCurso.setVisible(true);
//            this.tfCursoID.setVisible(true);
//            this.txCursoID.setVisible(true);
//            this.btnCambiarCurso1.setVisible(false);
//            this.tfCambiarNombre.setVisible(false);
//            this.txNuevoID.setVisible(false);
//            this.txCambiarNombre.setVisible(false);
//            this.tfNuevoID.setVisible(false);
//            this.tfCambiarNombre.setText("");
//            this.tfNuevoID.setText("");
//
//        } catch (ListException ex) {
//            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
}
