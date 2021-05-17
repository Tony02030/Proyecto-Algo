/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularLinkedList;
import domain.ListException;
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
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLMenuAdmiController implements Initializable {

    private FXMLMenuSesionController mn = new FXMLMenuSesionController();
    @FXML
    private Button btnIngreso;
    @FXML
    private TextField textFieldUser;
    @FXML
    private PasswordField textFieldPassword;
     
    private CircularLinkedList security = util.Utility.getSecurity();
    @FXML
    private Text txtMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mn.getBp1().setCenter(root);
       
    }
    private void btnIngreso(ActionEvent event) {
//        try {
//            if(this.security.contains1(this.textFieldUser.getText(), this.textFieldPassword.getText())){
////                loadPage("FXMLMenu");
//                this.txtMessage.setText("Si funciona");
//
//            }
//             this.txtMessage.setText("No funciona");
//        } catch (ListException ex) {
//            Logger.getLogger(FXMLMenuAdmiController.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    @FXML
    private void btnIngreso(MouseEvent event) {
        try {
            
            if(this.security.contains1(this.textFieldUser.getText(), this.textFieldPassword.getText())){
               loadPage("FXMLMenu");
                this.txtMessage.setVisible(true);
                

            }else{
              this.txtMessage.setText("No se pudo ingresar");  
            }
             
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuAdmiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
