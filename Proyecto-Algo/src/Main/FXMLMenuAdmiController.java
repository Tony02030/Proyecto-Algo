/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularLinkedList;
import domain.ListException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLMenuAdmiController implements Initializable {

    private FXMLMenuSesionController mn;
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

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMenuSesion.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("Proyecto");
        primaryStage.setTitle("Inicio de Sesion");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void btnIngreso(MouseEvent event) {
        try {

            if (this.security.contains1(this.textFieldUser.getText(), this.textFieldPassword.getText())) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu.fxml"));

                    Parent root = loader.load();

                    FXMLMenuController controlador = loader.getController();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();

//                    //Escribe el usuario y contraseña en el archivo txt
//                    FileOutputStream fos = new FileOutputStream("SecurityReport.txt");
//                    ObjectOutputStream oos;
//                    oos = new ObjectOutputStream(fos);
//                    oos.writeObject(security);
//
//                    //Contador
//                    int i = 0;
//                    util.Utility.setSecurityCounter(i++);

                } catch (IOException ex) {
                    Logger.getLogger(FXMLMenuAdmiController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                this.txtMessage.setText("No se pudo ingresar");
            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuAdmiController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        

    }

}
