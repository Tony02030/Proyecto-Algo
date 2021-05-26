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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLAddStudentController implements Initializable {

    private SingleLinkedList Student = util.Utility.getStudents();
    private DoublyLinkedList dLL = util.Utility.getCareers();
    private Career temp;
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
    private Button btnAddS;
    @FXML
    private DatePicker dPBirthday;
    @FXML
    private ComboBox<String> ComboBox;
    ObservableList<String> oL_ComboBox = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        numericOnly(txfID);

        Node aux;
        try {
            aux = dLL.getNode(1);
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
    private void btnAddS(ActionEvent event) {
        int id = 0;
        ZoneId defaultZoneId = ZoneId.systemDefault();

        LocalDate lD = dPBirthday.getValue();

        Date date = Date.from(lD.atStartOfDay(defaultZoneId).toInstant());
        Node aux;
        try {
            aux = dLL.getNode(1);

            while (aux != null) {
                if (util.Utility.equals(aux.data, ComboBox.getValue())) {
                    temp = (Career) aux.data;
                    
                }
                aux = aux.next;

            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Student.add(new Student(Integer.parseInt(txfID.getText()), txfStudentID.getText(), txfLastname.getText(), txfName.getText(), date, txfPhoneNumber.getText(), txfEmail.getText(), txfAdress.getText(), temp));

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ventana de dialogo");
        alert.setHeaderText("Informacion");
        alert.setContentText("Estudiante agregado correctamente");
        alert.showAndWait();

        txfID.setText("");
        txfStudentID.setText("");
        txfLastname.setText("");
        txfName.setText("");
        txfPhoneNumber.setText("");
        txfEmail.setText("");
        txfAdress.setText("");

    }

}
