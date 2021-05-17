/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private MenuItem Cursos;
    @FXML
    private MenuItem Horarios;
    @FXML
    private MenuItem eMatriculados;
    @FXML
    private MenuItem matricularE;
    @FXML
    private MenuItem Rmatricula;
    @FXML
    private MenuItem RretiroCursos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     private void loadPage(String page){
        Parent root = null;
        try {
            
            root = FXMLLoader.load(getClass().getResource(page+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bp.setCenter(root);
        
    }
    
}
