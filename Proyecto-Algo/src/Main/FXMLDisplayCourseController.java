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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author 31670
 */
public class FXMLDisplayCourseController implements Initializable {

    private CircularDoublyLinkedList course = util.Utility.getCourses();
    ObservableList<Course> courses = FXCollections.observableArrayList();

    @FXML
    private TableView<List<String>> tableCurse;
    @FXML
    private TableColumn<List<String>, String> IDcolumn;
    @FXML
    private TableColumn<List<String>, String> NombreColumn;
    @FXML
    private TableColumn<List<String>, String> CreditsColumn;
    @FXML
    private TableColumn<List<String>, String> CarreraColumn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//          Node aux;
////        try {
//            aux = course.getNode(1);
//            int x=0;
//            while (aux != null) {
//                Course temp=(Course)aux.data;
//                courses.add(x, temp);
//                aux=aux.next;
//                x=x+1;
//            }
//        } catch (ListException ex) {
//            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        colCarrers.setCellValueFactory(new PropertyValueFactory("description"));
//        tableCourses.setItems(courses);

        this.IDcolumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0));
            }
        });
        this.NombreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1));
            }
        });
        this.CreditsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2));
            }
        });
        this.CarreraColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3));
            }
        });
        this.tableCurse.setItems(getData());
    }
    // TODO

    public ObservableList<List<String>> getData() {
        final ObservableList<List<String>> data = FXCollections.observableArrayList();
        Node aux;
        try {
            aux = course.getNode(1);

            while (aux != course.getNodeLast()) {
                Course temp = (Course) aux.data;
                List<String> array = new ArrayList<>();

                array.add(temp.getId());
                array.add(temp.getName());
                array.add((String.valueOf(temp.getCredits())));
                array.add(temp.getCareerID().getDescription());
                data.add(array);
                aux = aux.next;

            }
            Course temp = (Course) aux.data;
            List<String> array = new ArrayList<>();

            array.add(temp.getId());
            array.add(temp.getName());
            array.add((String.valueOf(temp.getCredits())));
            array.add(temp.getCareerID().getDescription());
            data.add(array);
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

}
