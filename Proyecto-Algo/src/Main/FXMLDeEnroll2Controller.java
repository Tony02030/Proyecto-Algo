/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.DeEnrollment;
import domain.Enrollment;
import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
import domain.Student;
import domain.TimeTable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLDeEnroll2Controller implements Initializable {

    private SingleLinkedList schedules = util.Utility.getSchedules();
    private Student student = util.Utility.getTemporal();
    private SingleLinkedList students = util.Utility.getStudents();
    private CircularDoublyLinkedList deEnrollment = util.Utility.getDeEnrollment();
    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    private CircularDoublyLinkedList courses = util.Utility.getCourses();
    private Enrollment enroll;

    @FXML
    private TableColumn<List<String>, String> tC_Course;

    @FXML
    private TableColumn<List<String>, String> tC_Career;
    @FXML
    private ComboBox<String> ComboBox_Course;
    private TextField txf_Schedule;
    private Button btn_EnrollCourse;
    ObservableList<String> courses1 = FXCollections.observableArrayList();

    private Date dateToDay = Calendar.getInstance().getTime();
    private Course temp;
    private Button btn_EndEnrollment;
    @FXML
    private Text txtMessage;
    @FXML
    private Text txtMessage2;
    @FXML
    private BorderPane bp;
    @FXML
    private TableView<List<String>> tV_DeEnrollCourse;
    @FXML
    private TableColumn<List<String>, String> tC_Schedule;
    @FXML
    private Button btn_EndDeEnrollment;
    @FXML
    private Button btn_DeEnrollCourse;
    @FXML
    private TextArea textAMotivo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display();

    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

    public void display() {
        courses1.clear();
        this.ComboBox_Course.setItems(courses1);

        tV_DeEnrollCourse.setVisible(true);
        txtMessage.setVisible(true);
        ComboBox_Course.setVisible(true);
        txtMessage2.setVisible(true);
        txf_Schedule.setVisible(true);
        btn_EnrollCourse.setVisible(true);
        btn_EndEnrollment.setVisible(true);
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
                return new ReadOnlyStringWrapper(data.getValue().get(3));
            }
        });
        this.tV_DeEnrollCourse.setItems(getData());

        try {
            Node aux = enrollment.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux != enrollment.getNodeLast()) {

                Enrollment temp = (Enrollment) aux.data;
                if (!util.Utility.exist(temp.getIdEnroll())) {
                    courses1.add(String.valueOf(temp.getCourseID().getName()));
                }

                aux = aux.next;

            }
            Enrollment temp = (Enrollment) aux.data;
            if (!util.Utility.exist(temp.getIdEnroll())) {
                courses1.add(String.valueOf(temp.getCourseID().getName()));
            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        for (int i = 0; i < getData().size(); i++) {
//            courses1.add(getData().get(i).get(0));
//        }

    }

    public ObservableList<List<String>> getData() {
        final ObservableList<List<String>> data = FXCollections.observableArrayList();
        Node aux;

        try {
            aux = enrollment.getNode(1);

            while (aux != enrollment.getNodeLast()) {
                List<String> array = new ArrayList<>();
                Enrollment temp = (Enrollment) aux.data;
                //!util.Utility.exist(temp.getIdEnrollment()
                if (util.Utility.equals(temp.getCourseID().getCareerID().getDescription(), student.getCareerID().getDescription())) {
                    array.add(temp.getCourseID().getName());
                    array.add(temp.getSchedule());
                    array.add(temp.getCourseID().getCareerID().getDescription());
                    data.add(array);

                }

                aux = aux.next;

            }
            Enrollment temp = (Enrollment) aux.data;
            if (!util.Utility.exist(temp.getIdEnroll())) {
                courses1.add(String.valueOf(temp.getCourseID().getName()));
            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
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

    @FXML
    private void btn_EndDeEnrollment(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ventana de Confirmación");
        alert.setHeaderText("AVISO");
        alert.setContentText("¿Está seguro de terminar el proceso de retiro de cursos?");
        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            tV_DeEnrollCourse.setVisible(false);
            txtMessage.setVisible(false);
            ComboBox_Course.setVisible(false);
            txtMessage2.setVisible(false);
            textAMotivo.setVisible(false);
            btn_EnrollCourse.setVisible(false);
            btn_EndEnrollment.setVisible(false);
            try {
                Node aux = schedules.getNode(1);

                while (aux != null) {

                    TimeTable tem = (TimeTable) aux.data;
                    tem.setIdEnrollment(0);

                    aux = aux.next;

                }

            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadPage("FXMLEnroll1");
        }
    }

    @FXML
    private void btn_DeEnrollCourse(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Ventana de Confirmación");
        alert.setHeaderText("AVISO");
        alert.setContentText("¿Desea retirar este curso?");
        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            try {
                Node aux = enrollment.getNode(1);

                while (aux != null) {

                    Enrollment tem = (Enrollment) aux.data;
                    if (util.Utility.equals(this.ComboBox_Course.getValue(), tem.getCourseID().getName())) {
                        temp = tem.getCourseID();
                        enroll = tem;
                    }

                    aux = aux.next;

                }
                Enrollment tem = (Enrollment) aux.data;
                if (util.Utility.equals(this.ComboBox_Course.getValue(), tem.getCourseID().getName())) {
                    temp = tem.getCourseID();
                    enroll=tem;
                }

            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }

            enroll.setIdEnroll(1);
            this.enrollment.add(new DeEnrollment(student.getId(), this.dateToDay, student, temp, enroll.getSchedule(), textAMotivo.getText()));
            display();
            this.ComboBox_Course.setValue("");
            this.textAMotivo.setText("");

            //Contador
            int i = 0;
            util.Utility.setDeEnrollmentCounter(i++);
        } else {
            display();
        }

    }
}
