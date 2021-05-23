/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.ListException;
import domain.SingleLinkedList;
import domain.Student;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLAddAndReportSController implements Initializable {

    SingleLinkedList sLL = new SingleLinkedList();
    private Student s;
    @FXML
    private TextField txfID;
    @FXML
    private TextField txfStudentID;
    @FXML
    private TextField txfLastname;
    @FXML
    private TextField txfName;
    @FXML
    private DatePicker dPBirthday;
    @FXML
    private TextField txfPhoneNumber;
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfAdress;
    @FXML
    private TextField txfCareerID;
    @FXML
    private Button btnAddS;
    @FXML
    private Button btnShowReportS;
    @FXML
    private TableColumn<Student, Integer> tC_ID;
    @FXML
    private TableColumn<Student, String> tC_StudentID;
    @FXML
    private TableColumn<Student, String> tC_Lastname;
    @FXML
    private TableColumn<Student, String> tC_FirstName;
    @FXML
    private TableColumn<Student, Date> tC_Birthday;
    @FXML
    private TableColumn<Student, String> tC_PhoneNumber;
    @FXML
    private TableColumn<Student, String> tC_Email;
    @FXML
    private TableColumn<Student, String> tC_Adress;
    @FXML
    private TableColumn<Student, Integer> tC_CarrerID;
    @FXML
    private TableView<Student> tV_ShowS;
    ObservableList<Student> oL_Student = FXCollections.observableArrayList();
    @FXML
    private Text txtName;
    @FXML
    private Text txtID;
    @FXML
    private Text txtStudentID;
    @FXML
    private Text txtPhoneN;
    @FXML
    private Text txtAdress;
    @FXML
    private Text txtEmail;
    @FXML
    private Text txtLastname;
    @FXML
    private Text txtBirthday;
    @FXML
    private Text txtCarrerID;
    @FXML
    private Button btnModifyS;
    @FXML
    private Button btnRemoveS;
    @FXML
    private Button btnRemove;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btnAddS(ActionEvent event) {
        tV_ShowS.setVisible(false);
        ZoneId defaultZoneId = ZoneId.systemDefault();

        LocalDate lD = dPBirthday.getValue();

        Date date = Date.from(lD.atStartOfDay(defaultZoneId).toInstant());

        sLL.add(s = new Student(Integer.parseInt(txfID.getText()), txfStudentID.getText(), txfLastname.getText(), txfName.getText(), date, txfPhoneNumber.getText(), txfEmail.getText(), txfAdress.getText(), Integer.parseInt(txfCareerID.getText())));
        oL_Student.add(s);
//        txfID.setText("");
//        txfStudentID.setText("");
//        txfLastname.setText("");
//        txfName.setText("");

//        txfPhoneNumber.setText("");
//        txfEmail.setText("");
//        txfAdress.setText("");
//        txfCareerID.setText("");
    }
    
    @FXML
    private void btnModifyS(ActionEvent event) {
        txtID.setText("");
        txtLastname.setText("");
        txtName.setText("");
        txtBirthday.setText("");
        txtPhoneN.setText("");
        txtEmail.setText("");
        txtAdress.setText("");
        txtCarrerID.setText("");
         
        txfID.setVisible(false);
        txfLastname.setVisible(false);
        txfName.setVisible(false);
        dPBirthday.setVisible(false);
        txfPhoneNumber.setVisible(false);
        txfEmail.setVisible(false);
        txfAdress.setVisible(false);
        txfCareerID.setVisible(false);
        
    }

    @FXML
    private void btnRemoveS(ActionEvent event) {
        txtID.setText("");
        txtLastname.setText("");
        txtName.setText("");
        txtBirthday.setText("");
        txtPhoneN.setText("");
        txtEmail.setText("");
        txtAdress.setText("");
        txtCarrerID.setText("");
         
        txfID.setVisible(false);
        txfLastname.setVisible(false);
        txfName.setVisible(false);
        dPBirthday.setVisible(false);
        txfPhoneNumber.setVisible(false);
        txfEmail.setVisible(false);
        txfAdress.setVisible(false);
        txfCareerID.setVisible(false);
        
        btnRemove.setVisible(true);
    }
    
     @FXML
    private void btnRemove(ActionEvent event) throws ListException {
        sLL.remove(txfStudentID.getText());
        //if si lo encuentra txt "se ha removido correctamente
        //else el estudiante no se encuentra registrado
    }

    @FXML
    private void btnShowReportS(ActionEvent event) {
        tV_ShowS.setVisible(true);
        tC_ID.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tC_StudentID.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
        tC_Lastname.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
        tC_FirstName.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
        tC_Birthday.setCellValueFactory(new PropertyValueFactory<Student, Date>("birthday"));
        tC_PhoneNumber.setCellValueFactory(new PropertyValueFactory<Student, String>("phoneNumber"));
        tC_Email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        tC_Adress.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));
        tC_CarrerID.setCellValueFactory(new PropertyValueFactory<Student, Integer>("careerID"));
        tV_ShowS.setItems(oL_Student);
    }

}
