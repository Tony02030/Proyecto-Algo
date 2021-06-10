/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Node;
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
public class FXMLMenuCarrersChangeController implements Initializable {

    private DoublyLinkedList carrer = util.Utility.getCareers();
    private String careerName;
    @FXML
    private TextField txtFieldSearch;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnCambiar;
    @FXML
    private TextField txtFieldChangeName;
    @FXML
    private Text txtError;
    @FXML
    private Text txtTitle1;
    @FXML
    private Text txtTitle2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        util.Utility.letterOnly(txtFieldSearch);
    }

    @FXML
    private void btnBuscar(ActionEvent event) throws ListException {

        //Buscamos en la lista si contiene la carrera buscada por el usuario
        if (carrer.contains1(this.txtFieldSearch.getText())) {

            careerName = this.txtFieldSearch.getText();
            this.txtTitle1.setVisible(false);
            this.txtFieldSearch.setVisible(false);
            this.btnBuscar.setVisible(false);

            this.txtFieldSearch.setText("");
            this.txtTitle2.setVisible(true);
            this.txtFieldChangeName.setVisible(true);
            this.btnCambiar.setVisible(true);
            txtError.setVisible(false);

        } else {
            this.txtError.setVisible(true);
        }

    }

    @FXML
    private void btnCambiar(ActionEvent event) {

        //Se le cambia el nombre a la carrera elegida
        try {
            Node aux = carrer.getNode(1);

            int count = 0;
            while (aux != null) {
                if (util.Utility.equals(aux.data, careerName)) {
                    Career newCareerName = (Career) aux.data;
                    newCareerName.setDescription(txtFieldChangeName.getText());
                    aux.data = newCareerName;
                    count++;
                }
                aux = aux.next;
            }
            if (count == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ventana de Diálogo");
                alert.setHeaderText("Información");
                alert.setContentText("Se cambió el nombre de la carrera");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ventana de Diálogo");
                alert.setHeaderText("Información");
                alert.setContentText("No se pudo cambiar el nombre de la carrera");
                alert.showAndWait();
            }
            this.txtTitle1.setVisible(true);
            this.txtFieldSearch.setVisible(true);
            this.btnBuscar.setVisible(true);
            this.txtFieldSearch.setText("");
            this.txtTitle2.setVisible(false);
            this.txtFieldChangeName.setVisible(false);
            this.txtFieldChangeName.setText("");
            this.btnCambiar.setVisible(false);

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
