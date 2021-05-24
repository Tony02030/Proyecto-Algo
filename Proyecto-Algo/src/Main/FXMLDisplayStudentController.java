/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
import domain.Student;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLDisplayStudentController implements Initializable {
    SingleLinkedList Student = util.Utility.getStudents();
    @FXML
    private TableView<Student> tV_ShowStudents;
    ObservableList<Student> oL_tVStudent = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Student, Integer> tC_ID;
    @FXML
    private TableColumn<Student, String> tC_StudentID;
    @FXML
    private TableColumn<Student, String> tC_Lastname;
    @FXML
    private TableColumn<Student, String> tC_Firstname;
    @FXML
    private TableColumn<Student, String> tC_Birthday;
    @FXML
    private TableColumn<Student, String> tC_PhoneN;
    @FXML
    private TableColumn<Student, String> tC_Email;
    @FXML
    private TableColumn<Student, String> tC_Adress;
    @FXML
    private TableColumn<?, ?> tC_Career;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
           Node aux;
        try {
            aux = Student.getNode(1);
            int x=0;
            while (aux != null) {
                Student temp=(Student)aux.data;
                oL_tVStudent.add(x, temp);
                aux=aux.next;
                x=x+1;
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
           tC_ID.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        tC_StudentID.setCellValueFactory(new PropertyValueFactory<Student, String>("studentID"));
        tC_Lastname.setCellValueFactory(new PropertyValueFactory<Student, String>("lastname"));
        tC_Firstname.setCellValueFactory(new PropertyValueFactory<Student, String>("firstname"));
        tC_Birthday.setCellValueFactory(new PropertyValueFactory<Student, String>("dateBirth"));
        tC_PhoneN.setCellValueFactory(new PropertyValueFactory<Student, String>("phoneNumber"));
        tC_Email.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        tC_Adress.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));
        //tC_Career.setCellValueFactory(new PropertyValueFactory<Student, Integer>("careerID"));
        tV_ShowStudents.setItems(oL_tVStudent);
}
    }    
   
     
