/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.Enrollment;
import domain.ListException;
import domain.Node;
import domain.Student;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLDisplayEnrolledCoursesController implements Initializable {

    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    @FXML
    private TableView<List<String>> tV_Enrolled;
    @FXML
    private TableColumn<List<String>, String> tC_Course;
    @FXML
    private TableColumn<List<String>, String> tC_Schedule;
    @FXML
    private TableColumn<List<String>, String> tC_Career;
    private Student student = util.Utility.getConsulta();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.tC_Course.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0));
            }
        });
        this.tC_Schedule.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1));
            }
        });
        this.tC_Career.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2));
            }
        });
        tV_Enrolled.setItems(getData());
    }

    public ObservableList<List<String>> getData() {
        final ObservableList<List<String>> data = FXCollections.observableArrayList();
        Node aux;
        try {
            aux = enrollment.getNode(1);

            while (aux != enrollment.getNodeLast()) {
                Enrollment temp = (Enrollment) aux.data;
                List<String> array = new ArrayList<>();

                if (student.getId() == temp.getId() && temp.getIdEnroll()==0) {
                    array.add(temp.getCourseID().getName());
                    array.add(temp.getSchedule());
                    array.add(temp.getCourseID().getCareerID().getDescription());

                    data.add(array);
                }

                aux = aux.next;

            }
            Enrollment temp = (Enrollment) aux.data;
            List<String> array = new ArrayList<>();
            if (student.getId() == temp.getId() && temp.getIdEnroll()==0) {
                array.add(temp.getCourseID().getName());
                array.add(temp.getSchedule());
                array.add(temp.getCourseID().getCareerID().getDescription());

                data.add(array);
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

}
