/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLEnroll2Controller implements Initializable {

    @FXML
    private TableView<?> tV_EnrollCourse;
    @FXML
    private TableColumn<List<String>, String> tC_Course;
    @FXML
    private TableColumn<List<String>, String> tC_Schedule1;
    @FXML
    private TableColumn<List<String>, String> tC_Schedule2;
    @FXML
    private TableColumn<List<String>, String> tC_Career;
    @FXML
    private ComboBox<String> ComboBox_Course;
    @FXML
    private TextField txf_Schedule;
    @FXML
    private Button btn_EnrollCourse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void btn_EnrollCourse(ActionEvent event) {
        
        //Utilizar una DialogPane para confirmar que se matriculó el curso correctamente
        
        
        //Si se matricula correctamente...
        
        txf_Schedule.setText("");
    }

}
