/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.DoublyLinkedList;
import domain.ListException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLMenuCarrersAddController implements Initializable {

    private DoublyLinkedList career = util.Utility.getCareers();
    @FXML
    private Button btnAdd;

    @FXML
    private TextField textFieldCareer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        util.Utility.letterOnly(this.textFieldCareer);
    }

    @FXML
    private void btnAdd(ActionEvent event) throws ListException, FileNotFoundException, IOException {
        //Agregar carrera a la lista

        int z = 0;
        if ("".equals(this.textFieldCareer.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de Diálogo");
            alert.setHeaderText("Información");
            alert.setContentText("No puede dejar el espacio en blanco");
            alert.showAndWait();
            z++;

        }
        if (!career.contains1(this.textFieldCareer.getText()) && z != 1) {
            career.add(new Career(this.textFieldCareer.getText()));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de Diálogo");
            alert.setHeaderText("Información");
            alert.setContentText("Se agregó la carrera correctamente");
            alert.showAndWait();
            this.textFieldCareer.setText("");

            //Contador
            int i = 0;
            util.Utility.setCareersCounter(i++);

        }
        if (career.contains1(this.textFieldCareer.getText())) { //(career.contains1(this.textFieldCareer.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de Diálogo");
            alert.setHeaderText("Información");
            alert.setContentText("Ya existe esta carrera en la Lista");
            alert.showAndWait();
        }

    }

}
