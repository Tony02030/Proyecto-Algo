/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.CircularLinkedList;
import domain.Course;
import domain.DeEnrollment;
import domain.DoublyLinkedList;
import domain.Enrollment;
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

        //Escribe los estudiantes en el archivo txt
        FileOutputStream fosStudent = new FileOutputStream("StudentsReport.txt");
        ObjectOutputStream oosStudent = new ObjectOutputStream(fosStudent);

        Node auxStudent = student.getNode(1);

        while (auxStudent != null) {
            Student stud = (Student) auxStudent.data;
            oosStudent.writeObject(stud);
            auxStudent = auxStudent.next;
        }

        // Leer Archivo Estudiante
        FileInputStream fisStudent = new FileInputStream("StudentsReport.txt");
        ObjectInputStream oisStudent = new ObjectInputStream(fisStudent);

//            while (oisStudent.readLine() != null) {
//                Student students = (Student) oisStudent.readObject();
//                //student = (SingleLinkedList) ois.readObject();
//            }

        // Archivo Seguridad
        FileOutputStream fosSecurity = new FileOutputStream("SecurityReport.txt");
        ObjectOutputStream oosSecurity = new ObjectOutputStream(fosSecurity);

        Node auxSecurity = security.getNode(1);

        while (auxSecurity != security.getLast()) {  //getNodeLast()
            Security security = (Security) auxSecurity.data;
            oosSecurity.writeObject(security);
            auxSecurity = auxSecurity.next;
        }

//        Security temp = (Security) auxSecurity.data;
//        if (util.Utility.exist(temp.getIdEnroll())) {
//            students.add(String.valueOf(temp.getId()));
//        }

        //Lee Archivo seguridad

//            
    //Archivo Carreras
    
     FileOutputStream fosCareer = new FileOutputStream("CareersReport.txt");
        ObjectOutputStream oosCareer = new ObjectOutputStream(fosCareer);

        Node auxCareer = student.getNode(1);

        while (auxCareer != null) {
            Career career = (Career) auxCareer.data;
            oosCareer.writeObject(career);
            auxCareer = auxCareer.next;
        }
    
    //Leer Archivo de Carrera
    
    
    
    // Escribir Archivo Curso
    
     FileOutputStream fosCourse = new FileOutputStream("CourseReport.txt");
        ObjectOutputStream oosCourse = new ObjectOutputStream(fosCourse);

        Node auxCourse = course.getNode(1);

        while (auxCourse != course.getNodeLast()) {  //getNodeLast()
            Course course = (Course) auxCourse.data;
            oosCourse.writeObject(course);
            auxCourse = auxCourse.next;
        }

//        Course temp = (Course) auxCourse.data;
//        if (util.Utility.exist(temp.getIdEnroll())) {
//            students.add(String.valueOf(temp.getId()));
//        }
    
       //Leer Archivo de Cursos
       
       
       //Escribir Archivo de Horarios
       
        FileOutputStream fosSchedule = new FileOutputStream("SchedulesReport.txt");
        ObjectOutputStream oosSchedule = new ObjectOutputStream(fosSchedule);

        Node auxSchedule = schedule.getNode(1);

        while (auxSchedule != null) {
            Career career = (Career) auxCareer.data; //Aquí irían los getSchedule?
            oosSchedule.writeObject(career);
            auxSchedule = auxSchedule.next;
        }
        
        //Leer Archivo de Horarios

       
       
       //Escribir Archivo Enrollment
       
       FileOutputStream fosEnrollment = new FileOutputStream("EnrollmentReport.txt");
        ObjectOutputStream oosEnrollment = new ObjectOutputStream(fosEnrollment);

        Node auxEnrollment = enrollment.getNode(1);

        while (auxEnrollment != enrollment.getLast()) {  //getNodeLast()
            Enrollment enrollment = (Enrollment) auxEnrollment.data;
            oosEnrollment.writeObject(enrollment);
            auxEnrollment = auxEnrollment.next;
        }

//        Security temp = (Security) auxSecurity.data;
//        if (util.Utility.exist(temp.getIdEnroll())) {
//            students.add(String.valueOf(temp.getId()));
//        }

        //Leer Archivo Enrollment
        
       
       //Escribir Archivo deEnrollment
       
        FileOutputStream fosDeEnrollment = new FileOutputStream("deEnrollmentReport.txt");
        ObjectOutputStream oosDeEnrollment = new ObjectOutputStream(fosDeEnrollment);

        Node auxDeEnrollment = deEnrollment.getNode(1);

        while (auxDeEnrollment != deEnrollment.getLast()) {  //getNodeLast()
            DeEnrollment deEnrollment = (DeEnrollment) auxDeEnrollment.data;
            oosDeEnrollment.writeObject(deEnrollment);
            auxDeEnrollment = auxDeEnrollment.next;
        }

//        DeEnrollment temp = (DeEnrollment) auxSecurity.data;
//        if (util.Utility.exist(temp.getIdEnroll())) {
//            students.add(String.valueOf(temp.getId()));
//        }
       

      //Leer Archivo deEnrollment
    }

}
