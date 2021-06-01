/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
import domain.Student;
import domain.TimeTable;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLMenuTimeTableController implements Initializable {

    private CircularDoublyLinkedList courses = util.Utility.getCourses();
    private DoublyLinkedList career = util.Utility.getCareers();
    private SingleLinkedList schedule = util.Utility.getSchedules();
    private Course temp;
    private TimeTable sch;

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TextField txtFieldSchedule1;
    @FXML
    private TextField txtFieldSchedule2;
    @FXML
    private Button btnAdd;
    ObservableList<String> course = FXCollections.observableArrayList();

    @FXML
    private TableView<List<String>> tableCourse;
    @FXML
    private TableColumn<List<String>, String> colCourse;
    @FXML
    private TableColumn<List<String>, String> colCareer;
    @FXML
    private TextField txtFPeriod;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        this.colCourse.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
//                return new ReadOnlyStringWrapper(data.getValue().get(0));
//            }
//        });
//        this.colCareer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
//            @Override
//            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
//                return new ReadOnlyStringWrapper(data.getValue().get(1));
//            }
//        });
//        addTextLimiter(this.txtFieldSchedule1, 17);
//        addTextLimiter(this.txtFieldSchedule2, 17);
//        this.tableCourse.setItems(getData());
//        Node aux;
//        Node last;
//        try {
//            aux = courses.getNode(1);
//            //last = courses.getNode(courses.size()+1);
//            int x = 0;
//            while (aux != courses.getNodeLast()) {
//                Course temp = (Course) aux.data;
//                if (!util.Utility.exist(temp.getIdentifier())) {
//                    String y = temp.getName();
//                    course.add(x, y);
//                    x = x + 1;
//                }
//                aux = aux.next;
//
//            }
//            Course temp = (Course) aux.data;
//            if (!util.Utility.exist(temp.getIdentifier())) {
//                String y = temp.getName();
//                course.add(x, y);
//                x = x + 1;
//            }
//        } catch (ListException ex) {
//            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        comboBox.setItems(course);
        display();

    }

    public void display() {
        course.clear();
        this.colCourse.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0));
            }
        });
        this.colCareer.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1));
            }
        });
        addTextLimiter(this.txtFieldSchedule1, 17);
        addTextLimiter(this.txtFieldSchedule2, 17);
        this.tableCourse.setItems(getData());
        Node aux;
        Node last;
        try {
            aux = courses.getNode(1);
            //last = courses.getNode(courses.size()+1);
            
            while (aux != courses.getNodeLast()) {
                Course temp = (Course) aux.data;
                if (!util.Utility.exist(temp.getIdentifier())) {
                    String y = temp.getName();
                    course.add(y);
                    
                }
                aux = aux.next;

            }
            Course temp = (Course) aux.data;
            if (!util.Utility.exist(temp.getIdentifier())) {
                String y = temp.getName();
                course.add(y);

            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        comboBox.setItems(course);

    }

    public ObservableList<List<String>> getData() {
        final ObservableList<List<String>> data = FXCollections.observableArrayList();
        Node aux;

        try {
            aux = courses.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux != courses.getNodeLast()) {
                List<String> array = new ArrayList<>();
                Course temp = (Course) aux.data;
                if (!util.Utility.exist(temp.getIdentifier())) {
                    array.add(temp.getName());
                    array.add(temp.getCareerID().getDescription());
                    data.add(array);
                }

                aux = aux.next;

            }
            List<String> array = new ArrayList<>();
            Course temp = (Course) aux.data;
            if (!util.Utility.exist(temp.getIdentifier())) {
                array.add(temp.getName());
                array.add(temp.getCareerID().getDescription());
                data.add(array);
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
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

    @FXML
    private void btnAdd(ActionEvent event) {
        try {
            Node aux = courses.getNode(1);

            while (aux != courses.getNodeLast()) {
                if (util.Utility.equals(aux.data, this.comboBox.getValue())) {
                    temp = (Course) aux.data;

                }
                aux = aux.next;
            }
            if (util.Utility.equals(aux.data, this.comboBox.getValue())) {
                temp = (Course) aux.data;

            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        String temp1 = this.txtFieldSchedule1.getText();
        String temp2 = this.txtFieldSchedule2.getText();
        String temp3 = temp1.toUpperCase();
        String temp4 = temp2.toUpperCase();
        if (this.txtFieldSchedule1.getText().length() < 17 || this.txtFieldSchedule2.getText().length() < 17 || comboBox.getValue() == "") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("Los horarios que ingresó no son validos");
            alert.showAndWait();
            display();
        }

        char[] v = temp3.toCharArray();
        char[] b = temp4.toCharArray();
        char[] c = new char[v.length - 4];
        char[] d = new char[b.length - 4];

        int x = 0;
        for (int i = 4; i < v.length; i++) {
            c[x] = v[i];
            x++;

        }
        x = 0;
        for (int i = 4; i < b.length; i++) {
            d[x] = b[i];
            x++;

        }
        String tem = String.valueOf(c);
        String temm = String.valueOf(d);
        String hor = tem.charAt(0) + "" + tem.charAt(1) + "";
        String hor01 = tem.charAt(8) + "" + tem.charAt(9) + "";
        String hor2 = temm.charAt(0) + "" + temm.charAt(1) + "";
        String hor02 = temm.charAt(8) + "" + temm.charAt(9) + "";
        //Dias de la semana
        char day = temp3.charAt(0);
        char day01 = temp3.charAt(2);
        char day2 = temp4.charAt(0);
        char day02 = temp4.charAt(2);

        //Horas
        int o = Integer.parseInt(hor);
        int p = Integer.parseInt(hor01);
        int n = Integer.parseInt(hor2);
        int q = Integer.parseInt(hor02);
        if (!schedule.isEmpty()) {
            try {
                Node aux = schedule.getNode(1);

                while (aux != null) {

                    if (util.Utility.equals(aux.data, this.txtFieldSchedule1) && util.Utility.equals(aux.data, this.txtFieldSchedule2)) {
                        count++;

                    }

                    aux = aux.next;
                }

            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!schedule.isEmpty()) {
            try {
                Node aux = schedule.getNode(1);

                while (aux != null) {
                    TimeTable tempo1 = (TimeTable) aux.data;
                    String tem1 = tempo1.getSchedule1();
                    String tem2 = tempo1.getSchedule2();
                    String tem3 = temp1.toUpperCase();
                    String tem4 = temp2.toUpperCase();

                    char[] l = tem3.toCharArray();
                    char[] ñ = tem4.toCharArray();
                    char[] t = new char[v.length - 4];
                    char[] y = new char[b.length - 4];

                    x = 0;
                    for (int i = 4; i < l.length; i++) {
                        t[x] = l[i];
                        x++;

                    }
                    x = 0;
                    for (int i = 4; i < ñ.length; i++) {
                        y[x] = ñ[i];
                        x++;

                    }
                    String te = String.valueOf(t);
                    String ten = String.valueOf(y);
                    String hor3 = te.charAt(0) + "" + te.charAt(1) + "";
                    String hor03 = te.charAt(8) + "" + te.charAt(9) + "";
                    String hor4 = ten.charAt(0) + "" + ten.charAt(1) + "";
                    String hor04 = ten.charAt(8) + "" + ten.charAt(9) + "";
                    //Dias de la semana
                    char day3 = tem3.charAt(0);
                    char day03 = tem3.charAt(2);
                    char day4 = tem4.charAt(0);
                    char day04 = tem4.charAt(2);

                    //Horas
                    int oo = Integer.parseInt(hor3);
                    int pp = Integer.parseInt(hor03);
                    int nn = Integer.parseInt(hor4);
                    int qq = Integer.parseInt(hor04);

                    sch = (TimeTable) aux.data;
                    if (sch.getCourseID().getCareerID().getDescription().equals(temp.getCareerID().getDescription())) {
                        if ((oo >= o && oo <= p) || (pp == o && pp <= p) || (n == nn && nn <= q) || (qq == o && qq <= q)) {
                            if (day == day3 || day2 == day4 || day01 == day03 || day02 == day04) {
                                count++;

                            }
                        }
                    }

                    aux = aux.next;
                }

            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        if (count == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("Los horarios que ingresó no son validos");
            alert.showAndWait();
            display();

        }
        if (count == 0) {

//Este if verifica que no se puedan hacer horarios antes de la 7am, despues de las 9pm, los domingos,que la hora que termine sea menor a la hora que empieza y que no se ingrese otras letras que no sean las abreviaturas de los dias de la semana
            if (day != day01 && day2 != day02 && o < p && n < q && o >= 07 && o <= 21 && n >= 07 && n <= 21 && p <= 21 && q <= 21 && day != 'D' && day01 != 'D' && day2 != 'D' && day02 != 'D' && ("LMKJVS").contains(day + "") && ("LMKJVS").contains(day01 + "") && ("LMKJVS").contains(day2 + "") && ("LMKJVS").contains(day02 + "")) {
                temp.setIdentifier(1);
                this.schedule.add(new TimeTable(temp, this.txtFPeriod.getText(), this.txtFieldSchedule1.getText(), this.txtFieldSchedule2.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ventana de dialogo");
                alert.setHeaderText("Informacion");
                alert.setContentText("Se agregaron los horarios al curso");
                alert.showAndWait();
                display();
                this.txtFieldSchedule1.setText("");
                this.txtFieldSchedule2.setText("");
                this.txtFPeriod.setText("");
                this.comboBox.setValue("");

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ventana de dialogo");
                alert.setHeaderText("Informacion");
                alert.setContentText("Los horarios que ingresó no son validos");
                alert.showAndWait();
                display();

            }

        }
    }

}
