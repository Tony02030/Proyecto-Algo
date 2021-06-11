/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.Course;
import domain.DoublyLinkedList;
import domain.Enrollment;
import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
import domain.Student;
import domain.TimeTable;
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
 * @author 31670
 */
public class FXMLRemoveCourseController implements Initializable {

    private CircularDoublyLinkedList course = util.Utility.getCourses();
    private SingleLinkedList student = util.Utility.getStudents();
    private CircularLinkedList security = util.Utility.getSecurity();
    private DoublyLinkedList career = util.Utility.getCareers();

    private SingleLinkedList schedule = util.Utility.getSchedules();
    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    private CircularDoublyLinkedList deEnrollment = util.Utility.getDeEnrollment();

    private Course curso;
    @FXML
    private TextField tfBorrarCurso;
    @FXML
    private Button btnBorrarCurso;

    @FXML
    private Text txIngreseCurso;
    @FXML
    private Text txMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
    // TODO

    @FXML
    private void btnBorrarCurso(ActionEvent event) throws ListException {
        int count = 0;
        if (!course.isEmpty()) {
            Node aux2;

            aux2 = course.getNode(1);

            while (aux2 != course.getNodeLast()) {
                Course temp = (Course) aux2.data;
                if (temp.getName().equals(this.tfBorrarCurso.getText())) {
                    curso = temp;
                }
                aux2=aux2.next;

            }
            Course temp = (Course) aux2.data;
            if (temp.getName().equals(this.tfBorrarCurso.getText())) {
                curso = temp;
            }
        }

        if (!schedule.isEmpty()) {

            try {

                Node aux = schedule.getNode(1);

                while (aux != null) {

                    TimeTable temp = (TimeTable) aux.data;
                    if (curso.getName().equals(temp.getCourseID().getName())) {
                        count++;

                    }
                    aux=aux.next;

                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!this.enrollment.isEmpty()) {

            Node aux = enrollment.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux != enrollment.getNodeLast()) {

                Enrollment temp = (Enrollment) aux.data;
                if (curso.getName().equals(temp.getCourseID().getName())) {
                    count++;
                }

                aux = aux.next;

            }
            Enrollment temp = (Enrollment) aux.data;
            if (curso.getName().equals(temp.getCourseID().getName())) {
                count++;
            }
        }

        if (count > 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de Diálogo");
            alert.setHeaderText("Información");
            alert.setContentText("No se puede modificar la carrera");
            alert.showAndWait();

        } else {
            try {

                if (course.contains1(tfBorrarCurso.getText())) {
                    course.remove(tfBorrarCurso.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ventana de dialogo");
                    alert.setHeaderText("Informacion");
                    alert.setContentText("Se eliminó la carrera");
                    alert.showAndWait();
                    this.tfBorrarCurso.setText("");
                    this.txMessage.setVisible(false);
                } else {
                    this.txMessage.setVisible(true);

                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCarrersDeleteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
