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
    private TextField txfSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Text txtError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        util.Utility.letterOnly(this.txfSearch);
    }

    @FXML
    private void btnSearch(ActionEvent event) {
        
        //Busca en la lista la carrera que se quiere eliminar
        try {
            if (carrer.contains1(this.txfSearch.getText())) {
                carrer.remove(this.txfSearch.getText());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ventana de Diálogo");
                alert.setHeaderText("Información");
                alert.setContentText("Carrera Eliminada Correctamente");
                alert.showAndWait();
                this.txfSearch.setText("");
                this.txtError.setVisible(false);
            } else {
                this.txtError.setVisible(true);

            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersDeleteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
