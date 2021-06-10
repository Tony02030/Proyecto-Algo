/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.DoublyLinkedList;
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
    private void btnSearchS(ActionEvent event) {
        
        //Busca el estudiante en la lista
        
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
