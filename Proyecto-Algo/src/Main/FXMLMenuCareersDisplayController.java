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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLMenuCareersDisplayController implements Initializable {
    

    private DoublyLinkedList carrer = util.Utility.getCareers();
    @FXML
    private TableView<Career> tableCarrers;
    ObservableList<Career> carrers = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Career, String> colCarrers;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Se ponen las carreras agregadas en la lista a la tabla
            Node aux;
        try {
            aux = carrer.getNode(1);
            int x=0;
            while (aux != null) {
                Career temp=(Career)aux.data;
                carrers.add(x, temp);
                aux=aux.next;
                x=x+1;
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colCarrers.setCellValueFactory(new PropertyValueFactory("description"));
        tableCarrers.setItems(carrers);
        

            
    }    
    
}
