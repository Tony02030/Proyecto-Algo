/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.ListException;
import domain.Node;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLMenuTimeTableController implements Initializable {

    private CircularDoublyLinkedList courses = util.Utility.getCourses();
    ;
    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField txtFieldSchedule1;
    @FXML
    private TextField txtFieldSchedule2;
    @FXML
    private Button btnAdd;
    ObservableList<String> course = FXCollections.observableArrayList();
    ObservableList<Course> course1 = FXCollections.observableArrayList();
    
    @FXML
    private TableView<Course> tableCourse;
    @FXML
    private TableColumn<Course, String> colCourse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Node aux;
        try {
            aux = courses.getNode(1);
            int x=0;
            while (aux != null) {
                Course temp=(Course)aux.data;
                course.add(x, temp.getName());
                course1.add(x,temp);
                aux=aux.next;
                x=x+1;
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        colCourse.setCellValueFactory(new PropertyValueFactory("name"));
        comboBox.setItems(course);
        tableCourse.setItems(course1);
    
    }    

    @FXML
    private void btnAdd(ActionEvent event) {
    }
    
}
