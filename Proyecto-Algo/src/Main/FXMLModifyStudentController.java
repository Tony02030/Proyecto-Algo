/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
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
    private DoublyLinkedList career = util.Utility.getCareers();
    @FXML
    private TextField txfID;
    @FXML
    private TextField txfStudentID;
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
    private ComboBox<String> ComboBox;
    ObservableList<String> oL_ComboBox = FXCollections.observableArrayList();
    @FXML
    private Text txtFirstname;
    @FXML
    private Text txtStudentID;
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
    private Text txtComboBox;
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
        numericOnly(txfID);
        
        Node aux;
        try {
            aux = career.getNode(1);
            int x = 0;
            while (aux != null) {
                Career temp = (Career) aux.data;
                String y = temp.getDescription();
                oL_ComboBox.add(x, y);
                aux = aux.next;
                x = x + 1;
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ComboBox.setItems(oL_ComboBox);
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
    private void btnSearchS(ActionEvent event) {
        int temp = Integer.parseInt(txfID.getText());
        try {
            if (student.contains1(temp)) {
                btnSearchS.setVisible(false);
                txtStudentID.setVisible(true);
                txtLastname.setVisible(true);
                txtFirstname.setVisible(true);
                txtBirthday.setVisible(true);
                txtPhoneNumber.setVisible(true);
                txtEmail.setVisible(true);
                txtAdress.setVisible(true);
                ComboBox.setVisible(true);
                btnModifyS.setVisible(true);
                
                txfID.setVisible(true);
                txfStudentID.setVisible(true);
                txfLastname.setVisible(true);
                txfName.setVisible(true);
                txfPhoneNumber.setVisible(true);
                txfEmail.setVisible(true);
                txfAdress.setVisible(true);
                
               
            } else {
                txtMessage.setText("La cédula insertada no corresponde con ningún estudiante registrado");
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private void btnModifyS(ActionEvent event) {
        //Obtiene el ID de la carrera que el usuario eligió para insertarla en el constructor de Student
        try {
            int id = 0;
            Node auxID;
            
            auxID = career.getNode(1);
            
            while (auxID != null) {
                if (util.Utility.equals(auxID.data, ComboBox.getValue())) {
                    Career temp = (Career) auxID.data;
                    id = temp.getId();
                }
                auxID = auxID.next;
                
            }//while

            //Conversión de una variable DatePicker a date para poder insertarla en el parámetro de Student
            ZoneId defaultZoneId = ZoneId.systemDefault();
            
            LocalDate lD = dPBirthday.getValue();
            
            Date date = Date.from(lD.atStartOfDay(defaultZoneId).toInstant());
            
            Node aux = student.getNode(1);
            
            while (aux != null) {
                if (util.Utility.equals(aux.data, this.txfID.getText())) {
                    Student temp = (Student) aux.data;
                    temp.setId(id);
                    temp.setStudentID(txfStudentID.getText());
                    temp.setLastname(txfLastname.getText());
                    temp.setFirstname(txfName.getText());
                    temp.setBirthday(date);
                    temp.setPhoneNumber(txfPhoneNumber.getText());
                    temp.setEmail(txfEmail.getText());
                    temp.setAddress(txfAdress.getText());
                    aux.data = temp;
                }
                aux = aux.next;
            }
            
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ventana de dialogo");
        alert.setHeaderText("Información");
        alert.setContentText("Estudiante modificado correctamente");
        alert.showAndWait();
        
        txtStudentID.setVisible(false);
        txtLastname.setVisible(false);
        txtFirstname.setVisible(false);
        txtBirthday.setVisible(false);
        txtPhoneNumber.setVisible(false);
        txtEmail.setVisible(false);
        txtAdress.setVisible(false);
        ComboBox.setVisible(false);
        btnModifyS.setVisible(false);
        btnSearchS.setVisible(true);
        
        txfID.setText("");
        txfStudentID.setText("");
        txfLastname.setText("");
        txfName.setText("");
        txfPhoneNumber.setText("");
        txfEmail.setText("");
        txfAdress.setText("");
    }
    
}
