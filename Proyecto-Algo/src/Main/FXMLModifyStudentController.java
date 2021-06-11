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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLModifyStudentController implements Initializable {

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
    private TextField txfLastname;
    @FXML
    private TextField txfName;
    @FXML
    private TextField txfPhoneNumber;
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfAdress;
    @FXML
    private DatePicker dPBirthday;

    @FXML
    private Text txtFirstname;
    @FXML
    private Text txtPhoneNumber;
    @FXML
    private Text txtAdress;
    @FXML
    private Text txtEmail;
    @FXML
    private Text txtLastname;
    @FXML
    private Text txtBirthday;

    @FXML
    private Button btnModifyS;
    @FXML
    private Button btnSearchS;
    @FXML
    private Text txtMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        util.Utility.numericOnly(txfID);
        util.Utility.letterOnly(txfName);
        util.Utility.letterOnly(txfLastname);
        util.Utility.numericOnly(txfPhoneNumber);

    }

    @FXML
    private void btnSearchS(ActionEvent event) throws ListException {

        //Busca el estudiante en la lista
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
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Información");
            alert.setContentText("No se puede modificar el curso");
            alert.showAndWait();

        } else {
            int temp = Integer.parseInt(txfID.getText());
            try {
                if (student.contains1(temp)) {
                    txtMessage.setText("");

                    txtLastname.setVisible(true);
                    txtFirstname.setVisible(true);
                    txtBirthday.setVisible(true);
                    txtPhoneNumber.setVisible(true);
                    txtEmail.setVisible(true);
                    txtAdress.setVisible(true);

                    btnModifyS.setVisible(true);

                    txfID.setVisible(true);

                    txfLastname.setVisible(true);
                    txfName.setVisible(true);
                    dPBirthday.setVisible(true);
                    txfPhoneNumber.setVisible(true);
                    txfEmail.setVisible(true);
                    txfAdress.setVisible(true);

                } else {
                    txtMessage.setText("La cédula insertada no corresponde con ningún estudiante registrado");

                    txtLastname.setVisible(false);
                    txtFirstname.setVisible(false);
                    txtBirthday.setVisible(false);
                    txtPhoneNumber.setVisible(false);
                    txtEmail.setVisible(false);
                    txtAdress.setVisible(false);

                    btnModifyS.setVisible(false);

                    txfID.setText("");

                    txfLastname.setText("");
                    txfName.setText("");
                    dPBirthday.setVisible(false);
                    txfPhoneNumber.setText("");
                    txfEmail.setText("");
                    txfAdress.setText("");

                    txfLastname.setVisible(false);
                    txfName.setVisible(false);
                    txfPhoneNumber.setVisible(false);
                    txfEmail.setVisible(false);
                    txfAdress.setVisible(false);
                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void btnModifyS(ActionEvent event) {

        //Se realiza el cambio de datos correspondiente
        try {

            //Conversión de una variable DatePicker a date para poder insertarla en el parámetro de Student
            ZoneId defaultZoneId = ZoneId.systemDefault();

            LocalDate lD = dPBirthday.getValue();

            Date date = Date.from(lD.atStartOfDay(defaultZoneId).toInstant());

            Node aux = student.getNode(1);
            int ID = Integer.parseInt(txfID.getText());
            while (aux != null) {

                if (util.Utility.equals(aux.data, ID)) {
                    Student temp = (Student) aux.data;
                    temp.setLastname(txfLastname.getText());
                    temp.setFirstname(txfName.getText());
                    temp.setBirthday(date);
                    temp.setPhoneNumber(txfPhoneNumber.getText());
                    temp.setEmail(txfEmail.getText());
                    temp.setAddress(txfAdress.getText());

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ventana de Diálogo");
                    alert.setHeaderText("Información");
                    alert.setContentText("Estudiante modificado correctamente");
                    alert.showAndWait();
                }
                aux = aux.next;
            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }

        txtLastname.setVisible(false);
        txtFirstname.setVisible(false);
        txtBirthday.setVisible(false);
        txtPhoneNumber.setVisible(false);
        txtEmail.setVisible(false);
        txtAdress.setVisible(false);
        btnModifyS.setVisible(false);

        txfID.setText("");
        txfLastname.setText("");
        txfName.setText("");
        dPBirthday.setVisible(false);
        txfPhoneNumber.setText("");
        txfEmail.setText("");
        txfAdress.setText("");

        txfLastname.setVisible(false);
        txfName.setVisible(false);
        txfPhoneNumber.setVisible(false);
        txfEmail.setVisible(false);
        txfAdress.setVisible(false);
    }

}
