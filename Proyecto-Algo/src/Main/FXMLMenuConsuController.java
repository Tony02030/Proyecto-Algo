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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnIngreso(ActionEvent event) throws ListException {
        if (student.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista de estudiantes esta vacia");

            alert.showAndWait();
        } else if (this.enrollment.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista de matriculados esta vacia");

            alert.showAndWait();
//           
        } else if (!this.enrollment.contains1(this.txtFStudent.getText())) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("El estudiante no esta matriculado");

            alert.showAndWait();
        } else {
            try {
                Node aux = enrollment.getNode(1);
                //Node last = courses.getNode(courses.size());
                while (aux != enrollment.getNodeLast()) {

                    Enrollment temp = (Enrollment) aux.data;
                    if (util.Utility.equals(this.txtFStudent.getText(), String.valueOf(temp.getId())) && !util.Utility.exist(temp.getIdEnroll())) {
                        util.Utility.setConsulta(temp.getStudentID());
                    }

                    aux = aux.next;

                }
                Enrollment temp = (Enrollment) aux.data;
                if (util.Utility.equals(this.txtFStudent.getText(), String.valueOf(temp.getId())) && !util.Utility.exist(temp.getIdEnroll())) {
                    util.Utility.setConsulta(temp.getStudentID());
                }

            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenuConsulta.fxml"));

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
        }

    }

}
