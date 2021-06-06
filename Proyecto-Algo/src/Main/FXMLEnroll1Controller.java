/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Course;
import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
import domain.Student;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLEnroll1Controller implements Initializable {

    private SingleLinkedList student = util.Utility.getStudents();
    @FXML
    private TableView<List<String>> tV_Student;
    @FXML
    private TableColumn<List<String>, String> tC_ID;
    @FXML
    private TableColumn<List<String>, String> tC_Name;
    @FXML
    private TableColumn<List<String>, String> tC_Lastname;
    @FXML
    private TableColumn<List<String>, String> tC_Career;
    @FXML
    private ComboBox<String> ComboBox_StudentID;
    @FXML
    private Button btn_StartEnrollment;
    @FXML
    private BorderPane bp;

    ObservableList<String> students = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display();
    }

    public void display() {
        this.tC_ID.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0));
            }
        });
        this.tC_Name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1));
            }
        });
        this.tC_Lastname.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2));
            }
        });
        this.tC_Career.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3));
            }
        });
        this.tV_Student.setItems(getData());

        try {
            Node aux = student.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux != null) {

                Student temp = (Student) aux.data;
                if (!util.Utility.exist(temp.getIdEnrollment())) {
                    students.add(String.valueOf(temp.getId()));
                }

                aux = aux.next;

            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ComboBox_StudentID.setItems(students);
    }

    public ObservableList<List<String>> getData() {
        final ObservableList<List<String>> data = FXCollections.observableArrayList();
        Node aux;

        try {
            aux = student.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux != null) {
                List<String> array = new ArrayList<>();
                Student temp = (Student) aux.data;
                if (!util.Utility.exist(temp.getIdEnrollment())) {
                    array.add(String.valueOf(temp.getId()));
                    array.add(temp.getFirstname());
                    array.add(temp.getLastname());
                    array.add(temp.getCareerID().getDescription());
                    data.add(array);
                }

                aux = aux.next;

            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @FXML
    private void btn_StartEnrollment(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ventana de Confirmación");
        alert.setHeaderText("AVISO");
        alert.setContentText("¿Desea matricular a este estudiante?");
        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            Node aux;

            try {
                aux = student.getNode(1);
                //Node last = courses.getNode(courses.size());
                while (aux != null) {

                    Student temp = (Student) aux.data;
                    if (util.Utility.equals(this.ComboBox_StudentID.getValue(), String.valueOf(temp.getId()))) {
                        util.Utility.setTemporal(temp);
                    }

                    aux = aux.next;

                }

            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLEnroll2.fxml"));
//
//                    Parent root = loader.load();
//
//                    FXMLMenuController controlador = loader.getController();
//
//                    Scene scene = new Scene(root);
//                    Stage stage = new Stage();
//                    stage.initModality(Modality.APPLICATION_MODAL);
//                    stage.setScene(scene);
//                    stage.showAndWait();
                Stage stage = new Stage();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLEnroll2.fxml"));
                Scene scene = new Scene(root);
                stage = new Stage(StageStyle.DECORATED);
                stage.setScene(scene);
                stage.show();

            } catch (IOException ex) {
                Logger.getLogger(FXMLMenuAdmiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            display();
        }

    }

    private void loadPage(String page) {
        Parent root = null;
        try {

            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.bp.setCenter(root);

    }

}
