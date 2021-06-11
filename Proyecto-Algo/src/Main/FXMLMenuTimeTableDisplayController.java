package Main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


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
public class FXMLMenuTimeTableDisplayController implements Initializable {

    @FXML
    private TableColumn<List<String>, String> colCurso;
    @FXML
    private TableColumn<List<String>, String> colHorario1;
    @FXML
    private TableColumn<List<String>, String> ColHorario2;
    @FXML
    private TableColumn<List<String>, String> colPeriodo;
    @FXML
    private TableColumn<List<String>, String> colCarrera;
    @FXML
    private TableView<List<String>> tableTimeSchedule;
    private SingleLinkedList schedule = util.Utility.getSchedules();

    /**
     * Initializes the controller class.
     */
    
    //TableView para mostrar los horarios con sus horarios y periodo respectivo
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         this.colCurso.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0));
            }
        });
         this.colHorario1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1));
            }
        });
          this.ColHorario2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2));
            }
        });
           this.colPeriodo.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3));
            }
        });
            this.colCarrera.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(4));
            }
        });
        this.tableTimeSchedule.setItems(getData());
       
    }
    
    //ObservableList para añadir los horarios con sus horarios y periodo respectivo a la TableView
    public ObservableList<List<String>> getData() {
        final ObservableList<List<String>> data = FXCollections.observableArrayList();
        Node aux;
        try {
            aux = schedule.getNode(1);

            while (aux != null) {
                TimeTable temp = (TimeTable) aux.data;
                List<String> array = new ArrayList<>();
                array.add(temp.getCourseID().getName());
                array.add(temp.getSchedule1());
                array.add(temp.getSchedule2());
                array.add(temp.getPeriod());
                array.add(temp.getCourseID().getCareerID().getDescription());
                data.add(array);
                aux = aux.next;

            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }
    
}
