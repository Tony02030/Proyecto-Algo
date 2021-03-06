/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularDoublyLinkedList;
import domain.Enrollment;
import domain.ListException;
import domain.Node;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLDeEnroll1Controller implements Initializable {

    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
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
    @FXML
    private Text txtMessage;
    @FXML
    private Text txtMessage2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display();

    }

    public void display() {
        txtMessage.setVisible(true);
        tV_Student.setVisible(true);
        txtMessage2.setVisible(true);
        ComboBox_StudentID.setVisible(true);
        btn_StartEnrollment.setVisible(true);
        //Insertar los datos a las TableColumn

        this.students.clear();
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
            Node aux = enrollment.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux != enrollment.getNodeLast()) {

                Enrollment temp = (Enrollment) aux.data;
                if (temp.getIdEnroll() == 0 && temp.getIdentifier() == 1) {
                    students.add(String.valueOf(temp.getId()));
                }

                aux = aux.next;

            }
            Enrollment temp = (Enrollment) aux.data;
            if (temp.getIdEnroll() == 0 && temp.getIdentifier() == 1) {
                students.add(String.valueOf(temp.getId()));
            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ComboBox_StudentID.setItems(students);
    }

    //ObservableList para poder insertar los datos del estudiante a la TableView
    public ObservableList<List<String>> getData() {
        final ObservableList<List<String>> data = FXCollections.observableArrayList();
        Node aux;

        try {
            aux = enrollment.getNode(1);
            while (aux != enrollment.getNodeLast()) {
                List<String> array = new ArrayList<>();

                Enrollment temp = (Enrollment) aux.data;
                if (temp.getIdEnroll() == 0 && temp.getIdentifier() == 1) {
                    array.add(String.valueOf(temp.getId()));
                    array.add(temp.getStudentID().getFirstname());
                    array.add(temp.getStudentID().getLastname());
                    array.add(temp.getStudentID().getCareerID().getDescription());
                    data.add(array);
                }

                aux = aux.next;

            }
            List<String> array = new ArrayList<>();
            Enrollment temp = (Enrollment) aux.data;
            if (temp.getIdEnroll() == 0 && temp.getIdentifier() == 1) {
                array.add(String.valueOf(temp.getId()));
                array.add(temp.getStudentID().getFirstname());
                array.add(temp.getStudentID().getLastname());
                array.add(temp.getStudentID().getCareerID().getDescription());
                data.add(array);
            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    @FXML
    private void btn_StartEnrollment(ActionEvent event) {
        //Retiro de curso
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ventana de Confirmaci??n");
        alert.setHeaderText("AVISO");
        alert.setContentText("??Desea retirar cursos de este estudiante?");
        ButtonType buttonTypeYes = new ButtonType("S??");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            Node aux;

            try {
                aux = enrollment.getNode(1);
                //Node last = courses.getNode(courses.size());
                while (aux != enrollment.getNodeLast()) {

                    Enrollment temp = (Enrollment) aux.data;
                    if (util.Utility.equals(this.ComboBox_StudentID.getValue(), String.valueOf(temp.getId())) && temp.getIdEnroll() == 0) {
                        util.Utility.setTemporal(temp.getStudentID());
                    }

                    aux = aux.next;

                }
                Enrollment temp = (Enrollment) aux.data;
                if (util.Utility.equals(this.ComboBox_StudentID.getValue(), String.valueOf(temp.getId())) && temp.getIdEnroll() == 0) {
                    util.Utility.setTemporal(temp.getStudentID());
                }

            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtMessage.setVisible(false);
            tV_Student.setVisible(false);
            txtMessage2.setVisible(false);
            ComboBox_StudentID.setVisible(false);
            btn_StartEnrollment.setVisible(false);
            loadPage("FXMLDeEnroll2");

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
