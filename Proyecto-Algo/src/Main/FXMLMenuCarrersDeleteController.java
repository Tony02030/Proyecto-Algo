/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.DoublyLinkedList;
import domain.ListException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
 * @author User
 */
public class FXMLMenuCarrersDeleteController implements Initializable {

    private DoublyLinkedList carrer = util.Utility.getCareers();
    @FXML
    private Text txtInfo;
    @FXML
    private TextField txtFieldSearch;
    @FXML
    private Button btnBuscar;
    @FXML
    private Text txtInfo2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
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
    private void btnBuscar(ActionEvent event) {
       
        try {
            if (carrer.contains1(this.txtFieldSearch.getText())) {
                carrer.remove(this.txtFieldSearch.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ventana de dialogo");
                alert.setHeaderText("Informacion");
                alert.setContentText("Se eliminó la carrera");
                alert.showAndWait();
                this.txtFieldSearch.setText("");
                this.txtInfo2.setVisible(false);
            } else {
                this.txtInfo2.setVisible(true);

            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersDeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
