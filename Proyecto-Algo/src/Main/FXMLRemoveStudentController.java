/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.DeEnrollment;
import domain.DoublyLinkedList;
import domain.Enrollment;
import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
import domain.Student;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLRemoveStudentController implements Initializable {

    private SingleLinkedList student = util.Utility.getStudents();
    private CircularLinkedList security = util.Utility.getSecurity();
    private DoublyLinkedList career = util.Utility.getCareers();
    private CircularDoublyLinkedList course = util.Utility.getCourses();
    private SingleLinkedList schedule = util.Utility.getSchedules();
    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    private CircularDoublyLinkedList deEnrollment = util.Utility.getDeEnrollment();

    private Student stud;
    @FXML
    private TextField txfID;
    @FXML
    private Text txtMessage;
    @FXML
    private Button btnRemove;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        numericOnly(txfID);
    }

    public static void numericOnly(final TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

    @FXML
    private void btnRemove(ActionEvent event) throws ListException {
        //Busca el estudiante en la lista
        if (txfID.getText() == "") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de Diálogo");
            alert.setHeaderText("Información");
            alert.setContentText("No puede dejar espacios en blanco");
            alert.showAndWait();
        }
        if (!this.student.isEmpty()) {
            Node aux1;
            try {
                aux1 = student.getNode(1);

                while (aux1 != null) {
                    Student c = (Student) aux1.data;
                    if (c.getId() == (Integer.parseInt(this.txfID.getText()))) {
                        stud = c;
                    }
                    aux1 = aux1.next;

                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        int count = 0;
        if (!this.deEnrollment.isEmpty()) {

            Node aux = deEnrollment.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux != deEnrollment.getNodeLast()) {

                DeEnrollment temp = (DeEnrollment) aux.data;
                if (stud.getId() == temp.getId()) {
                    count++;
                }

                aux = aux.next;

            }
            DeEnrollment temp = (DeEnrollment) aux.data;
            if (stud.getId() == temp.getId()) {
                count++;
            }
        }
        if (!this.enrollment.isEmpty()) {

            Node aux = enrollment.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux != enrollment.getNodeLast()) {

                Enrollment temp = (Enrollment) aux.data;
                if (stud.getId() == temp.getId()) {
                    count++;
                }

                aux = aux.next;

            }
            Enrollment temp = (Enrollment) aux.data;
            if (stud.getId() == temp.getId()) {
                count++;
            }
        }
        if (count > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de Diálogo");
            alert.setHeaderText("Información");
            alert.setContentText("No se puede modificar el curso");
            alert.showAndWait();

        } else {
            //Removemos el estudiante buscado de la lista
            int temp = Integer.parseInt(txfID.getText());
            if (student.contains(temp)) {
                student.remove(temp);
                txtMessage.setText("Estudiante removido de la lista correctamente");
            } else {
                txtMessage.setText("El estudiante no se encuentra en la lista");
            }
        }

    }

}
