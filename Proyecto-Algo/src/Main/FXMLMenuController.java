/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Node;
import domain.Security;
import domain.SingleLinkedList;
import domain.Student;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
import javax.swing.JOptionPane;

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
    private CircularLinkedList security = util.Utility.getSecurity();
    private DoublyLinkedList career = util.Utility.getCareers();
    private CircularDoublyLinkedList course = util.Utility.getCourses();
    private SingleLinkedList schedule = util.Utility.getSchedules();
    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    private CircularDoublyLinkedList deEnrollment = util.Utility.getDeEnrollment();

    @FXML
    private MenuItem agregaCarrera;
    @FXML
    private MenuItem modificaCarrera;
    @FXML
    private MenuItem eliminaCarrera;
    @FXML
    private MenuItem muestraCarreras;
    @FXML
    private MenuItem Exit;

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
        if (this.career.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("No hay carreras agregadas");
            alert.showAndWait();
        } else {
            loadPage("FXMLAddCourse");
        }

    }

    @FXML
    private void modificaCurso(ActionEvent event) {
        if (course.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista esta vacia");

            alert.showAndWait();
        } else {
            this.loadPage("FXMLModifyCourse");
        }

    }

    @FXML
    private void eliminaCurso(ActionEvent event) {
        if (course.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista esta vacia");

            alert.showAndWait();
        } else {
            this.loadPage("FXMLRemoveCourse");
        }

    }

    @FXML
    private void muestraCursos(ActionEvent event) {
        loadPage("FXMLDisplayCourse");
    }

    @FXML
    private void agregaEstudiante(ActionEvent event) {
        this.loadPage("FXMLAddStudent");
    }

    @FXML
    private void modificaEstudiante(ActionEvent event) {
        if (student.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista esta vacia");

            alert.showAndWait();
        } else {
            this.loadPage("FXMLModifyStudent");
        }

    }

    @FXML
    private void eliminaEstudiante(ActionEvent event) {
        if (student.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista esta vacia");

            alert.showAndWait();
        } else {
            this.loadPage("FXMLRemoveStudent");
        }

    }

    @FXML
    private void muestraEstudiante(ActionEvent event) {
        if (student.isEmpty() && career.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("No hay estudiantes ni carreras agregadas");

            alert.showAndWait();
        }
        if (student.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("No hay estudiantes agregados");

            alert.showAndWait();
        }
        if (!this.student.isEmpty() && !this.career.isEmpty()) {
            this.loadPage("FXMLDisplayStudent");
        }

    }

    @FXML
    private void matriculaEstudiante(ActionEvent event) {
        if (this.student.isEmpty() && this.schedule.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("No hay estudiantes ni horarios agregados");
            alert.showAndWait();
        } else if (this.schedule.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("No hay horarios agregados");
            alert.showAndWait();
        } else {
            loadPage("FXMLEnroll1");
        }

    }

    @FXML
    private void retiroCurso(ActionEvent event) {
    }

    @FXML
    private void muestraEstudianteM(ActionEvent event) {

    }

    @FXML
    private void agregaHorario(ActionEvent event) {
        if (course.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("No hay cursos agregados");

            alert.showAndWait();
        } else {
            this.loadPage("FXMLMenuTimeTable");
        }

    }

    @FXML
    private void muestraHorarios(ActionEvent event) {
        if (course.isEmpty() && this.schedule.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("No hay cursos ni horarios agregados");

            alert.showAndWait();
        }
        if (this.schedule.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("No hay horarios agregados");

            alert.showAndWait();
        }
        if (!course.isEmpty() && !this.schedule.isEmpty()) {
            this.loadPage("FXMLMenuTimeTableDisplay");
        }

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
            alert.setContentText("No hay carreras agragadas");

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

    @FXML
    private void Exit(ActionEvent event) throws IOException, ClassNotFoundException, ListException {
        System.exit(0);

        try {

            //Escribe los estudiantes en el archivo txt
            FileOutputStream fosStudent = new FileOutputStream("StudentsReport.txt");
            ObjectOutputStream oosStudent  = new ObjectOutputStream(fosStudent);
  
            Node auxStudent = student.getNode(1);

            while (auxStudent != null) {
                Student stud = (Student) auxStudent.data;
                oosStudent.writeObject(stud);
                auxStudent = auxStudent.next;
            }

            //Archivo Estudiante
            FileInputStream fisStudent = new FileInputStream("StudentsReport.txt");
            ObjectInputStream oisStudent = new ObjectInputStream(fisStudent);

            //Archivo Seguridad
            FileInputStream fisSecurity = new FileInputStream("SecurityReport.txt");
            ObjectInputStream oisSecurtity = new ObjectInputStream(fisSecurity);

            while (oisStudent.readLine() != null) {
                Student students = (Student) oisStudent.readObject();
                //student = (SingleLinkedList) ois.readObject();
            }

            while (oisSecurtity.readLine() != null) {
                Security security = (Security) oisSecurtity.readObject();
            }

        } catch (FileNotFoundException fnfe) {
            JOptionPane.showMessageDialog(null, "Problemas con el archivo: " + fnfe);
        }//End catch

    }

}
