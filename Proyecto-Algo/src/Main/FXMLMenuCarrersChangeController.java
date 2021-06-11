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
public class FXMLMenuCarrersChangeController implements Initializable {

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
    private TextField txtFieldSearch;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnCambiar;
    @FXML
    private TextField txtFieldChangeName;
    @FXML
    private Text txtError;
    @FXML
    private Text txtTitle1;
    @FXML
    private Text txtTitle2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        util.Utility.letterOnly(txtFieldSearch);
    }

    @FXML
    private void btnBuscar(ActionEvent event) throws ListException {
        Node aux;
        try {
            aux = career.getNode(1);
            int x = 0;
            while (aux != null) {
                Career temp = (Career) aux.data;
                if (temp.getDescription().equals(this.txtFieldSearch.getText())) {
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
            //Buscamos en la lista si contiene la carrera buscada por el usuario
            if (career.contains1(this.txtFieldSearch.getText())) {

                careerName = this.txtFieldSearch.getText();
                this.txtTitle1.setVisible(false);
                this.txtFieldSearch.setVisible(false);
                this.btnBuscar.setVisible(false);

                this.txtFieldSearch.setText("");
                this.txtTitle2.setVisible(true);
                this.txtFieldChangeName.setVisible(true);
                this.btnCambiar.setVisible(true);
                txtError.setVisible(false);

            } else {
                this.txtError.setVisible(true);
            }
        }

    }

    @FXML
    private void btnCambiar(ActionEvent event) {

        //Se le cambia el nombre a la carrera elegida
        try {
            Node aux = career.getNode(1);

            int count = 0;
            while (aux != null) {
                if (util.Utility.equals(aux.data, careerName)) {
                    Career newCareerName = (Career) aux.data;
                    newCareerName.setDescription(txtFieldChangeName.getText());
                    aux.data = newCareerName;
                    count++;
                }
                aux = aux.next;
            }
            if (count == 1) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ventana de Diálogo");
                alert.setHeaderText("Información");
                alert.setContentText("Se cambió el nombre de la carrera");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ventana de Diálogo");
                alert.setHeaderText("Información");
                alert.setContentText("No se pudo cambiar el nombre de la carrera");
                alert.showAndWait();
            }
            this.txtTitle1.setVisible(true);
            this.txtFieldSearch.setVisible(true);
            this.btnBuscar.setVisible(true);
            this.txtFieldSearch.setText("");
            this.txtTitle2.setVisible(false);
            this.txtFieldChangeName.setVisible(false);
            this.txtFieldChangeName.setText("");
            this.btnCambiar.setVisible(false);

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
