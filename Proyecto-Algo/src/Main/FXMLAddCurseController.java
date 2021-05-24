/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularDoublyLinkedList;
import domain.DoublyLinkedList;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author 31670
 */
public class FXMLAddCurseController implements Initializable {

    private CircularDoublyLinkedList course = util.Utility.getCourses();

    @FXML
    private Button btnAgregarCurso;
    @FXML
    private TextField tfAgregarCurso1;
    @FXML
    private TextField tfAgregarCurso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numericOnly(tfAgregarCurso1);
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
         int temp = Integer.parseInt(this.tfAgregarCurso1.getText());
        course.add(new Courses(temp,this.tfAgregarCurso.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("Se agregó la carrera");
            alert.showAndWait();
            this.tfAgregarCurso.setText("");
            this.tfAgregarCurso1.setText("");
    }
    }
    

