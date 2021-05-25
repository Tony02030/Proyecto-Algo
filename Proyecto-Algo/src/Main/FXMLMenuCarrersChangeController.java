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
public class FXMLMenuCarrersChangeController implements Initializable {

    private DoublyLinkedList carrer = util.Utility.getCareers();
    private String temp1;
    @FXML
    private Text txtInfo;
    @FXML
    private TextField txtFieldSearch;
    @FXML
    private Button btnBuscar;
    @FXML
    private Text txtInfo1;
    private TextField txtFieldChange;
    @FXML
    private Button btnCambiar;
    @FXML
    private TextField txtFieldChangeName;
    private Text txtInfo2;
    private TextField txtFieldChangeId;
    @FXML
    private Text txtInfo3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void btnBuscar(ActionEvent event) {

        try {
            if (carrer.contains1(this.txtFieldSearch.getText())) {
                temp1=this.txtFieldSearch.getText();
                this.btnBuscar.setVisible(false);
                this.txtFieldSearch.setVisible(false);
                this.txtInfo.setVisible(false);
                this.btnCambiar.setVisible(true);
                this.txtFieldChangeName.setVisible(true);
                this.txtInfo1.setVisible(true);
                this.txtFieldSearch.setText("");
                this.txtInfo3.setVisible(false);
            }
        } catch (ListException ex) {
            this.txtInfo3.setVisible(true);
        }
    }

    @FXML
    private void btnCambiar(ActionEvent event) {

        try {
            Node aux = carrer.getNode(1);
            
            int count=0;
            while (aux != null) {
                if (util.Utility.equals(aux.data, temp1)) {
                    Career temp = (Career) aux.data;
                    temp.setDescription(txtFieldChangeName.getText());
                    aux.data = temp;
                    count++;
                } 
                aux = aux.next;
            }
            if(count==1){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ventana de dialogo");
                    alert.setHeaderText("Informacion");
                    alert.setContentText("Se cambió la carrera");
                    alert.showAndWait();
            }else{
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ventana de dialogo");
                    alert.setHeaderText("Informacion");
                    alert.setContentText("No se pudó cambiar la carrera");
                    alert.showAndWait(); 
            }
            this.btnBuscar.setVisible(true);
            this.txtFieldSearch.setVisible(true);
            this.txtInfo.setVisible(true);
            this.btnCambiar.setVisible(false);
            this.txtFieldChangeName.setVisible(false);
            this.txtInfo1.setVisible(false);
            this.txtFieldChangeName.setText("");
            

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
