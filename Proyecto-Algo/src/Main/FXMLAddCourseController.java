/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Node;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLAddCourseController implements Initializable {

    private CircularDoublyLinkedList course = util.Utility.getCourses();
    private DoublyLinkedList carrer = util.Utility.getCareers();
    private Career temp;

    @FXML
    private TextField txfID;
    @FXML
    private TextField txfName;
    @FXML
    private Button btnAddCourse;
    @FXML
    private ComboBox<String> CareerComboBox;
    ObservableList<String> oL_ComboBox = FXCollections.observableArrayList();
    @FXML
    private TextField txfCredits;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        util.Utility.numericOnly(this.txfCredits);
        Node aux;
        try {
            aux = carrer.getNode(1);
            int x = 0;
            while (aux != null) {
                Career temp2 = (Career) aux.data;
                String y = temp2.getDescription();
                oL_ComboBox.add(x, y);
                aux = aux.next;
                x = x + 1;
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        CareerComboBox.setItems(oL_ComboBox);
        // TODO
    }

    @FXML
    private void btnAddCourse(ActionEvent event) throws ListException, FileNotFoundException, IOException {

        if (!course.contains1(this.txfID.getText().toUpperCase())) {
            int temp1 = Integer.parseInt(this.txfCredits.getText());
            Node aux;
            try {
                aux = carrer.getNode(1);

                while (aux != null) {
                    if (util.Utility.equals(aux.data, this.CareerComboBox.getValue())) {
                        temp = (Career) aux.data;

                    }
                    aux = aux.next;

                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            course.add(new Course(this.txfID.getText().toUpperCase(), this.txfName.getText(), temp1, temp, 0));
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("Se agregó el curso");
            alert.showAndWait();
            this.txfName.setText("");
            this.txfID.setText("");
            this.txfCredits.setText("");
            temp = null;

            
            //Contador
            int i = 0;
            util.Utility.setCoursesCounter(i++);
      
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("Ya se agregó ese curso");
            alert.showAndWait();
        }

    }
}
