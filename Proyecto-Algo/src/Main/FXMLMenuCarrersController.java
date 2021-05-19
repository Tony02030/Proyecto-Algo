/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLMenuCarrersController implements Initializable {

    @FXML
    private TableColumn<Career,Integer > colId;
    @FXML
    private TableColumn<Career, String> colDescription;
    @FXML
    private Text textCarrer;
    @FXML
    private Text textId;
    @FXML
    private Button btnAdd;
    @FXML
    private Text textBuscaId;
    @FXML
    private Button btnBusca;
    ObservableList<Career> career = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnAdd(ActionEvent event) {
    }

    @FXML
    private void btnBusca(ActionEvent event) {
    }
    
}
