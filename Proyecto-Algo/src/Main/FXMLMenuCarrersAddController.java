/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.DoublyLinkedList;
import domain.ListException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLMenuCarrersAddController implements Initializable {

    private DoublyLinkedList career = util.Utility.getCareers();
    @FXML
    private Button btnAdd;

    @FXML
    private TextField textFieldCareer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        util.Utility.letterOnly(this.textFieldCareer);
    }
    

    @FXML
    private void btnAdd(ActionEvent event) throws ListException, FileNotFoundException, IOException {
        if(!career.contains1(this.textFieldCareer.getText())){
            career.add(new Career(this.textFieldCareer.getText()));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ventana de dialogo");
        alert.setHeaderText("Informacion");
        alert.setContentText("Se agregó la carrera");
        alert.showAndWait();
        this.textFieldCareer.setText("");
        
         //Escribe las carreras en el archivo txt
            FileOutputStream fos = new FileOutputStream("CareersReport.txt");
            ObjectOutputStream oos;
            oos = new ObjectOutputStream(fos);
            oos.writeObject(career);
            
             //Contador
            int i = 0;
            util.Utility.setCareersCounter(i++);
            
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ventana de dialogo");
        alert.setHeaderText("Informacion");
        alert.setContentText("Ya existe esa carrera");
        alert.showAndWait();
        }
       
       
        

    }

}
