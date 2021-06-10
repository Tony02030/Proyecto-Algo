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
import domain.SingleLinkedList;
import domain.Student;
import domain.TimeTable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLMenuAdmiController implements Initializable {

    private SingleLinkedList student = util.Utility.getStudents();
    private CircularLinkedList security = util.Utility.getSecurity();
    private DoublyLinkedList career = util.Utility.getCareers();
    private CircularDoublyLinkedList course = util.Utility.getCourses();
    private SingleLinkedList schedule = util.Utility.getSchedules();
    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    private CircularDoublyLinkedList deEnrollment = util.Utility.getDeEnrollment();
    private FXMLMenuSesionController mn;
    @FXML
    private Button btnIngreso;
    @FXML
    private TextField textFieldUser;
    @FXML
    private PasswordField textFieldPassword;

    @FXML
    private Text txtMessage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }

        mn.getBp1().setCenter(root);

    }

    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLMenuSesion.fxml"));

        Scene scene = new Scene(root);
        primaryStage.setTitle("Proyecto");
        primaryStage.setTitle("Inicio de Sesión");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private void btnIngreso(MouseEvent event) throws ClassNotFoundException {
        try {

            if (this.security.contains1(this.textFieldUser.getText(), this.textFieldPassword.getText())) {
                try {
                    FileInputStream fisCareer = new FileInputStream("CareersReport.txt");
                    ObjectInputStream oisCareer = new ObjectInputStream(fisCareer);
                    for (int i = 0; i < util.Utility.getCareersCounter(); i++) {
                        Career careerR = (Career) oisCareer.readObject();
                        career.add(careerR);
                    }
                    FileInputStream fisStudent = new FileInputStream("StudentsReport.txt");
                    ObjectInputStream oisStudent = new ObjectInputStream(fisStudent);

                    for (int i = 0; i < util.Utility.getStudentCounter(); i++) {

                        Student studentR = (Student) oisStudent.readObject();
                        student.add(studentR);

                    }
                    FileInputStream fisCourse = new FileInputStream("CourseReport.txt");
                    ObjectInputStream oisCourse = new ObjectInputStream(fisCourse);

                    for (int i = 0; i < util.Utility.getCoursesCounter(); i++) {
                        Course courseR = (Course) oisCourse.readObject();
                        course.add(courseR);
                    }

                    //Escribir Archivo de Horarios
                    //Leer Archivo de Horarios
                    FileInputStream fisSchedule = new FileInputStream("SchedulesReport.txt");
                    ObjectInputStream oisSchedule = new ObjectInputStream(fisSchedule);

                    for (int i = 0; i < util.Utility.getSchedulesCounter(); i++) {
                        TimeTable timeTableR = (TimeTable) oisSchedule.readObject();
                        schedule.add(timeTableR);
                    }

                    //Escribir Archivo Enrollment
                    //Leer Archivo Enrollment
                    FileInputStream fisEnrollment = new FileInputStream("EnrollmentReport.txt");
                    ObjectInputStream oisEnrollment = new ObjectInputStream(fisEnrollment);

                    for (int i = 0; i < util.Utility.getEnrollmentCounter(); i++) {
                        Enrollment enrollmentR = (Enrollment) oisEnrollment.readObject();
                        enrollment.add(enrollmentR);
                    }

                    //Escribir Archivo deEnrollment
                    //Leer Archivo deEnrollment
                    FileInputStream fisDeEnrollment = new FileInputStream("deEnrollmentReport.txt");
                    ObjectInputStream oisDeEnrollment = new ObjectInputStream(fisDeEnrollment);

                    for (int i = 0; i < util.Utility.getDeEnrollmentCounter(); i++) {
                        DeEnrollment DeEnrollmentR = (DeEnrollment) oisDeEnrollment.readObject();
                        enrollment.add(DeEnrollmentR);
                    }
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu.fxml"));

                    Parent root = loader.load();

                    FXMLMenuController controlador = loader.getController();

                    Scene scene = new Scene(root);
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setScene(scene);
                    stage.showAndWait();

                } catch (IOException ex) {
                    Logger.getLogger(FXMLMenuAdmiController.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                this.txtMessage.setText("No se pudo ingresar");
            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuAdmiController.class.getName()).log(Level.SEVERE, null, ex);
        }
//        

    }

}
