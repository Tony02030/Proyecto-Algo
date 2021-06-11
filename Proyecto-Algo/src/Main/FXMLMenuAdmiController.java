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
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    @FXML
    private AnchorPane ap;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Imagen del Logo de la UCR
        Image imageUCR = new Image(getClass().getResourceAsStream("logo.png"));
        ImageView imageViewUCR = new ImageView(imageUCR);

        imageViewUCR.setFitWidth(290);
        imageViewUCR.setFitHeight(140);

        imageViewUCR.setX(290);
        imageViewUCR.setY(70);

        //Imagen de Seguridad
        Image imageSecurity = new Image(getClass().getResourceAsStream("MenuSesion.jpg"));
        ImageView imageViewSecurity = new ImageView(imageSecurity);

        imageViewSecurity.setFitWidth(280);
        imageViewSecurity.setFitHeight(135);

        imageViewSecurity.setX(297);
        imageViewSecurity.setY(445);

        ap.getChildren().add(imageViewUCR);
        ap.getChildren().add(imageViewSecurity);

    }

    @FXML
    private void btnIngreso(MouseEvent event) throws ClassNotFoundException, FileNotFoundException, IOException, ListException {

        if (this.security.contains1(this.textFieldUser.getText(), this.textFieldPassword.getText())) {
            txtMessage.setVisible(false);

//            //Leer archivo de Counters
//            FileInputStream fis = new FileInputStream("Counters.txt");
//            InputStreamReader isr = new InputStreamReader(fis);
//            BufferedReader br = new BufferedReader(isr);
//            String b = br.readLine();
//            if (b != null) {
//                util.Utility.setStudentCounter(Integer.parseInt(b));
//                util.Utility.setSecurityCounter(Integer.parseInt(br.readLine()));
//                util.Utility.setCareersCounter(Integer.parseInt(br.readLine()));
//                util.Utility.setCoursesCounter(Integer.parseInt(br.readLine()));
//                util.Utility.setSchedulesCounter(Integer.parseInt(br.readLine()));
//                util.Utility.setEnrollmentCounter(Integer.parseInt(br.readLine()));
//                util.Utility.setDeEnrollmentCounter(Integer.parseInt(br.readLine()));
//                PrintWriter writer = new PrintWriter("Counters.txt");
//                writer.print("");
//
//                //Leer Archivo de Seguridad
//                if (util.Utility.getCareersCounter() != 0) {
//                    FileInputStream fisCareer = new FileInputStream("CareersReport.txt");
//                    ObjectInputStream oisCareer = new ObjectInputStream(fisCareer);
//                    for (int i = 0; i < util.Utility.getCareersCounter(); i++) {
//                        Career careerR = (Career) oisCareer.readObject();
//                        career.add(careerR);
//                    }
//                }
//
//                //Leer Archivo de Estudiante
//                if (util.Utility.getStudentCounter() != 0) {
//                    FileInputStream fisStudent = new FileInputStream("StudentsReport.txt");
//                    ObjectInputStream oisStudent = new ObjectInputStream(fisStudent);
//
//                    for (int i = 0; i < util.Utility.getStudentCounter(); i++) {
//
//                        Student studentR = (Student) oisStudent.readObject();
//                        student.add(studentR);
//
//                    }
//                }
//
//                //Leer Archivo de Cursos
//                if (util.Utility.getCoursesCounter() != 0) {
//                    FileInputStream fisCourse = new FileInputStream("CourseReport.txt");
//                    ObjectInputStream oisCourse = new ObjectInputStream(fisCourse);
//
//                    for (int i = 0; i < util.Utility.getCoursesCounter(); i++) {
//                        Course courseR = (Course) oisCourse.readObject();
//                        course.add(courseR);
//                    }
//                }
//
//                //Leer Archivo de Horarios
//                if (util.Utility.getSchedulesCounter() != 0) {
//                    FileInputStream fisSchedule = new FileInputStream("SchedulesReport.txt");
//                    ObjectInputStream oisSchedule = new ObjectInputStream(fisSchedule);
//
//                    for (int i = 0; i < util.Utility.getSchedulesCounter(); i++) {
//                        TimeTable timeTableR = (TimeTable) oisSchedule.readObject();
//                        schedule.add(timeTableR);
//                    }
//                }
//
//                //Leer Archivo Enrollment
//                if (util.Utility.getEnrollmentCounter() != 0) {
//                    FileInputStream fisEnrollment = new FileInputStream("EnrollmentReport.txt");
//                    ObjectInputStream oisEnrollment = new ObjectInputStream(fisEnrollment);
//
//                    for (int i = 0; i < util.Utility.getEnrollmentCounter(); i++) {
//                        Enrollment enrollmentR = (Enrollment) oisEnrollment.readObject();
//                        enrollment.add(enrollmentR);
//                    }
//                }
//
//                //Leer Archivo deEnrollment
//                if (util.Utility.getDeEnrollmentCounter() != 0) {
//                    FileInputStream fisDeEnrollment = new FileInputStream("deEnrollmentReport.txt");
//                    ObjectInputStream oisDeEnrollment = new ObjectInputStream(fisDeEnrollment);
//
//                    for (int i = 0; i < util.Utility.getDeEnrollmentCounter(); i++) {
//                        DeEnrollment DeEnrollmentR = (DeEnrollment) oisDeEnrollment.readObject();
//                        deEnrollment.add(DeEnrollmentR);
//                    }
//                }
//
//            }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLMenu.fxml"));

            Parent parent = (Parent) loader.load();

            FXMLMenuController controlador = loader.getController();
            loader.setController(controlador);

            Scene scene = new Scene(parent, 878, 680);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();

        } else {
            this.txtMessage.setVisible(true);
        }

    }

}
