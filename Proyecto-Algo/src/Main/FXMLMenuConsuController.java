/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularDoublyLinkedList;
import domain.Enrollment;
import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLMenuConsuController implements Initializable {
    
    private SingleLinkedList student = util.Utility.getStudents();
    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    @FXML
    private TextField txtFStudent;
    @FXML
    private Button btnIngreso;
    @FXML
    private BorderPane bp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        util.Utility.numericOnly(txtFStudent);
    }
    
    @FXML
    private void btnIngreso(ActionEvent event) throws ListException {
        if (student.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de Diálogo");
            alert.setHeaderText("Información");
            alert.setContentText("La lista de estudiantes esta vacía");
            
            alert.showAndWait();
        } else if (this.enrollment.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de Diálogo");
            alert.setHeaderText("Información");
            alert.setContentText("La lista de matriculados esta vacia");
            
            alert.showAndWait();
//           
        } else if (!this.enrollment.contains1(Integer.parseInt(this.txtFStudent.getText()))) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de Diálogo");
            alert.setHeaderText("Información");
            alert.setContentText("El estudiante no está matriculado");
            
            alert.showAndWait();
        } else {
            this.bp.setVisible(true);
            try {
                Node aux = enrollment.getNode(1);
                
                while (aux != enrollment.getNodeLast()) {
                    
                    Enrollment temp = (Enrollment) aux.data;
                    if (util.Utility.equals(this.txtFStudent.getText(), String.valueOf(temp.getId()))) {
                        util.Utility.setConsulta(temp.getStudentID());
                    }
                    
                    aux = aux.next;
                    
                }
                Enrollment temp = (Enrollment) aux.data;
                if (util.Utility.equals(this.txtFStudent.getText(), String.valueOf(temp.getId()))) {
                    util.Utility.setConsulta(temp.getStudentID());
                }
                
            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadPage("FXMLMenuConsulta");
            
        }
        
    }
    
    private void loadPage(String page) {
        Parent root = null;
        try {
            
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bp.setCenter(root);
        
    }
    
}
