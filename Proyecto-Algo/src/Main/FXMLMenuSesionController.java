/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularLinkedList;
import java.io.IOException;
import java.net.URL;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import domain.Security;

/**
 *
 * @author User
 */
public class FXMLMenuSesionController implements Initializable {
    
    private Label label;
    @FXML
    private MenuItem openAdmin;
    @FXML
    private MenuItem openStudent;
    @FXML
    private BorderPane bp;
    @FXML
    private BorderPane bp1;

    public BorderPane getBp1() {
        return bp1;
    }
   
    
    
   
    
    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);
       
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    

    @FXML
    private void openAdmin(ActionEvent event) {
        loadPage("FXMLMenuAdmi");
    }

    @FXML
    private void openStudent(ActionEvent event) {
         loadPage("FXMLMenuConsu");
    }

    
    
}
