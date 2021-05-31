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
import java.net.URL;
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
public class FXMLAddCurseController implements Initializable {

    private CircularDoublyLinkedList course = util.Utility.getCourses();
     private DoublyLinkedList carrer = util.Utility.getCareers();
     private Career temp;

    @FXML
    private Button btnAgregarCurso;
    private TextField tfAgregarCurso1;
    private TextField tfAgregarCurso;
    @FXML
    private ComboBox<String> CboxAgregarCurso;
    ObservableList<String> oL_ComboBox = FXCollections.observableArrayList();
    @FXML
    private TextField txtFID;
    @FXML
    private TextField txtFfAgregarCurso;
    @FXML
    private TextField txtFfCréditos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numericOnly(this.txtFfCréditos);
          Node aux;
        try {
            aux = carrer.getNode(1);
            int x = 0;
            while (aux != null) {
                Career temp2 = (Career) aux.data;
                String y = temp2.getDescription();
                oL_ComboBox.add(x, y);
                aux = aux.next;
                x = x + 1;
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        CboxAgregarCurso.setItems(oL_ComboBox);
        // TODO
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

    @FXML
    private void btnAgregarCurso(ActionEvent event) {
        
         int temp1 = Integer.parseInt(this.txtFfCréditos.getText());
         Node aux;
          try {
             aux = carrer.getNode(1);

            while (aux != null) {
                if (util.Utility.equals(aux.data, this.CboxAgregarCurso.getValue())) {
                    temp = (Career) aux.data;
                    
                }
                aux = aux.next;

            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        course.add(new Course(this.txtFID.getText(),this.txtFfAgregarCurso.getText(),temp1,temp));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("Se agregó la carrera");
            alert.showAndWait();
           this.txtFfAgregarCurso.setText("");
           this.txtFID.setText("");
           this.txtFfCréditos.setText("");
           
            
    }
    }
    

