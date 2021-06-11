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
 * @author User
 */
public class FXMLMenuCarrersDeleteController implements Initializable {

    private SingleLinkedList student = util.Utility.getStudents();
    private CircularLinkedList security = util.Utility.getSecurity();
    private DoublyLinkedList career = util.Utility.getCareers();
    private CircularDoublyLinkedList course = util.Utility.getCourses();
    private SingleLinkedList schedule = util.Utility.getSchedules();
    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    private CircularDoublyLinkedList deEnrollment = util.Utility.getDeEnrollment();
    private String careerName;
    private Career carr;
    @FXML
    private TextField txfSearch;
    @FXML
    private Button btnSearch;
    @FXML
    private Text txtError;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        util.Utility.letterOnly(this.txfSearch);
    }

    @FXML
    private void btnSearch(ActionEvent event) throws ListException {
        Node aux;
        try {
            aux = career.getNode(1);

            while (aux != null) {
                Career temp = (Career) aux.data;
                if (temp.getDescription().equals(this.txfSearch.getText())) {
                    carr = temp;
                }

            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }

        int count = 0;
        if (!this.student.isEmpty()) {
            Node aux1;
            try {
                aux1 = student.getNode(1);

                while (aux1 != null) {
                    Student c = (Student) aux1.data;
                    if (c.getCareerID().getDescription().equals(carr.getDescription())) {
                        count++;
                    }
                    aux1 = aux1.next;

                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!course.isEmpty()) {
            Node aux2;

            aux2 = course.getNode(1);

            while (aux2 != course.getNodeLast()) {
                Course temp = (Course) aux2.data;
                if (temp.getCareerID().getDescription().equals(carr.getDescription())) {
                    count++;
                }

            }
            Course temp = (Course) aux2.data;
            if (temp.getCareerID().getDescription().equals(carr.getDescription())) {
                count++;
            }
        }
        if (!this.deEnrollment.isEmpty()) {

            Node aux2 = deEnrollment.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux2 != deEnrollment.getNodeLast()) {

                DeEnrollment temp = (DeEnrollment) aux2.data;
                if (carr.getDescription().equals(temp.getCourseID().getCareerID().getDescription())) {
                    count++;
                }

                aux2 = aux2.next;

            }
            DeEnrollment temp = (DeEnrollment) aux2.data;
            if (carr.getDescription().equals(temp.getCourseID().getCareerID().getDescription())) {
                count++;
            }
        }
        if (!this.enrollment.isEmpty()) {

            Node aux2 = enrollment.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux2 != enrollment.getNodeLast()) {

                Enrollment temp = (Enrollment) aux2.data;
                if (carr.getDescription().equals(temp.getCourseID().getCareerID().getDescription())) {
                    count++;
                }

                aux2 = aux2.next;

            }
            Enrollment temp = (Enrollment) aux2.data;
            if (carr.getDescription().equals(temp.getCourseID().getCareerID().getDescription())) {
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
                if (career.contains1(this.txfSearch.getText())) {
                    career.remove(this.txfSearch.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ventana de Diálogo");
                    alert.setHeaderText("Información");
                    alert.setContentText("Carrera Eliminada Correctamente");
                    alert.showAndWait();
                    this.txfSearch.setText("");
                    this.txtError.setVisible(false);
                } else {
                    this.txtError.setVisible(true);

                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCarrersDeleteController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //Busca en la lista la carrera que se quiere eliminar
    }
}
