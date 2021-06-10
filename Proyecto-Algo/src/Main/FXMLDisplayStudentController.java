/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
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
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLDisplayStudentController implements Initializable {

    SingleLinkedList student = util.Utility.getStudents();
    @FXML
    private TableView<List<String>> tV_ShowStudents;
    ObservableList<Student> oL_tVStudent = FXCollections.observableArrayList();
    @FXML
    private TableColumn<List<String>, String> tC_ID;
    @FXML
    private TableColumn<List<String>, String> tC_StudentID;
    @FXML
    private TableColumn<List<String>, String> tC_Lastname;
    @FXML
    private TableColumn<List<String>, String> tC_Firstname;
    @FXML
    private TableColumn<List<String>, String> tC_Birthday;
    @FXML
    private TableColumn<List<String>, String> tC_PhoneN;
    @FXML
    private TableColumn<List<String>, String> tC_Email;
    @FXML
    private TableColumn<List<String>, String> tC_Adress;
    @FXML
    private TableColumn<List<String>, String> tC_Career;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       //Le asigna los datos a las Table Column
        this.tC_ID.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0));
            }
        });
         this.tC_StudentID.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1));
            }
        });
          this.tC_Lastname.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2));
            }
        });
           this.tC_Firstname.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3));
            }
        });
            this.tC_Birthday.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(4));
            }
        });
             this.tC_PhoneN.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(5));
            }
        });
             this.tC_Email.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(6));
            }
        });
              this.tC_Adress.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(7));
            }
        });
               this.tC_Career.setCellValueFactory(new Callback<CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(8));
            }
        });
           this.tV_ShowStudents.setItems(getData());

    }

    //ObservableList para ingresar los datos a la TableView
    public ObservableList<List<String>> getData() {
        final ObservableList<List<String>> data = FXCollections.observableArrayList();
        Node aux;
        try {
            aux = student.getNode(1);

            while (aux != null) {
                Student temp = (Student) aux.data;
                List<String> array = new ArrayList<>();
                array.add(String.valueOf(temp.getId()));
                array.add(temp.getStudentID());
                array.add(temp.getLastname());
                array.add(temp.getFirstname());
                array.add(temp.getDateBirth());
                array.add(temp.getPhoneNumber());
                array.add(temp.getEmail());
                array.add(temp.getAddress());
                array.add(temp.getCareerID().getDescription());
                data.add(array);
                aux = aux.next;

            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

}
