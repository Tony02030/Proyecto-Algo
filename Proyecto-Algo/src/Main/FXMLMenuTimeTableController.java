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
            last = courses.getNode(courses.size());
            int x = 0;
            while (aux != last) {
                Course temp = (Course) aux.data;
                String y = temp.getName();
                course.add(x, y);
                aux = aux.next;
                x = x + 1;
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
            Node last = courses.getNode(courses.size());
            
            while (aux != last) {
                Course temp = (Course) aux.data;
                List<String> array = new ArrayList<>();
                array.add(String.valueOf(temp.getId()));
                array.add(temp.getName());
                array.add(temp.getCareerID().getDescription());
                data.add(array);
                aux = aux.next;
                
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
        int count = 0;
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
        if (count == 1) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("Este horario ya ha sido ingresado");
            alert.showAndWait();
            this.txtFieldSchedule1.setText("");
            this.txtFieldSchedule2.setText("");
        }
        if (count == 0) {
            try {
                Node aux = courses.getNode(1);
                Node last = courses.getNode(courses.size());
                
                while (aux != last) {
                    if (util.Utility.equals(aux.data, this.comboBox.getValue())) {
                        temp = (Course) aux.data;
                        
                    }
                    aux = aux.next;
                }
                
            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            String temp1 = this.txtFieldSchedule1.getText();
            String temp2 = this.txtFieldSchedule2.getText();
            temp1 = temp1.trim();
            temp2 = temp2.trim();
            
            char[] v = temp1.toCharArray();
            char[] b = temp2.toCharArray();
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
            String hor02 = temm.charAt(0) + "" + temm.charAt(1) + "";
            //Dias de la semana
            char day = temp1.charAt(0);
            char day01 = temp1.charAt(2);
            char day2 = temp2.charAt(0);
            char day02 = temp2.charAt(2);

            //Horas
            int o = Integer.parseInt(hor);
            int p = Integer.parseInt(hor01);
            int n = Integer.parseInt(hor2);
            int q = Integer.parseInt(hor02);
            
            if (day != day01 && day2 != day02 && o < p && n < q && o >= 07 && o <= 21 && n >= 07 && n <= 21 && p <= 21 && q <= 21) {
                this.schedule.add(new TimeTable(temp, this.txtFPeriod.getText(), this.txtFieldSchedule1.getText(), this.txtFieldSchedule2.getText()));
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ventana de dialogo");
                alert.setHeaderText("Informacion");
                alert.setContentText("Se agregaron los horarios al curso");
                alert.showAndWait();
                this.txtFPeriod.setText("");
                this.txtFieldSchedule1.setText("");
                this.txtFieldSchedule2.setText("");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ventana de dialogo");
                alert.setHeaderText("Informacion");
                alert.setContentText("Los horarios no son coherentes");
                alert.showAndWait();
                this.txtFPeriod.setText("");
                this.txtFieldSchedule1.setText("");
                this.txtFieldSchedule2.setText("");
            }
            
        }
    }
    
}
