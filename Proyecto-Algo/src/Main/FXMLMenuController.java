/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularDoublyLinkedList;
import domain.DoublyLinkedList;
import domain.SingleLinkedList;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLMenuController implements Initializable {

    @FXML
    private BorderPane bp;
    @FXML
    private MenuItem agregaCurso;
    @FXML
    private MenuItem eliminaCurso;
    @FXML
    private MenuItem muestraCursos;
    @FXML
    private MenuItem agregaEstudiante;
    @FXML
    private MenuItem modificaEstudiante;
    @FXML
    private MenuItem eliminaEstudiante;
    @FXML
    private MenuItem muestraEstudiantes;
    @FXML
    private MenuItem modificaCurso;
    @FXML
    private MenuItem matriculaEstudiante;
    @FXML
    private MenuItem retiroCurso;
    @FXML
    private MenuItem muestraEstudiantesM;
    @FXML
    private MenuItem agregaHorario;
    @FXML
    private MenuItem muestraHorarios;
    @FXML
    private MenuItem agregaAdministrador;

    private SingleLinkedList student = util.Utility.getStudents();
    private SingleLinkedList schedule = util.Utility.getSchedules();
    private CircularDoublyLinkedList course = util.Utility.getCourses();
    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    private DoublyLinkedList career = util.Utility.getCareers();
    @FXML
    private MenuItem agregaCarrera;
    @FXML
    private MenuItem modificaCarrera;
    @FXML
    private MenuItem eliminaCarrera;
    @FXML
    private MenuItem muestraCarreras;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
    private void agregaCurso(ActionEvent event) {

    }

    @FXML
    private void modificaCurso(ActionEvent event) {
    }

    @FXML
    private void eliminaCurso(ActionEvent event) {
    }

    @FXML
    private void muestraCursos(ActionEvent event) {
    }

    @FXML
    private void agregaEstudiante(ActionEvent event) {
        this.loadPage("FXMLAddAndReportS");
    }

    @FXML
    private void modificaEstudiante(ActionEvent event) {
    }

    @FXML
    private void eliminaEstudiante(ActionEvent event) {
    }

    @FXML
    private void muestraEstudiante(ActionEvent event) {
    }

    @FXML
    private void matriculaEstudiante(ActionEvent event) {
    }

    @FXML
    private void retiroCurso(ActionEvent event) {
    }

    @FXML
    private void muestraEstudianteM(ActionEvent event) {
    }

    @FXML
    private void agregaHorario(ActionEvent event) {
    }

    @FXML
    private void muestraHorarios(ActionEvent event) {
    }

    @FXML
    private void agregaAdministrador(ActionEvent event) {
    }

    @FXML
    private void agregaCarrera(ActionEvent event) {
        loadPage("FXMLMenuCarrersAdd");
    }

    @FXML
    private void modificaCarrera(ActionEvent event) {
        if (career.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista esta vacia");

            alert.showAndWait();
        } else {
            loadPage("FXMLMenuCarrersChange");

        }
        
    }

    @FXML
    private void eliminaCarrera(ActionEvent event) {
        if (career.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista esta vacia");

            alert.showAndWait();
        } else {
            loadPage("FXMLMenuCarrersDelete");

        }
    }

    @FXML
    private void muestraCarreras(ActionEvent event) {
        if (career.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista esta vacia");

            alert.showAndWait();
        } else {
            loadPage("FXMLMenuCareersDisplay");

        }
        
    }

}
