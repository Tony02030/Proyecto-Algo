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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import domain.Security;
import domain.SingleLinkedList;
import domain.Student;
import domain.TimeTable;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 *
 * @author User
 */
public class FXMLMenuSesionController implements Initializable {

    private SingleLinkedList student = util.Utility.getStudents();
    private CircularLinkedList security = util.Utility.getSecurity();
    private DoublyLinkedList career = util.Utility.getCareers();
    private CircularDoublyLinkedList course = util.Utility.getCourses();
    private SingleLinkedList schedule = util.Utility.getSchedules();
    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    private CircularDoublyLinkedList deEnrollment = util.Utility.getDeEnrollment();
    private Label label;
    @FXML
    private MenuItem openAdmin;
    @FXML
    private MenuItem openStudent;
    @FXML
    private BorderPane bp;
    private BorderPane bp1;
    @FXML
    private MenuItem Exit;

    public BorderPane getBp1() {
        return bp1;
    }

    private void loadPage(String page) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        bp.setCenter(root);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            cargaArchivos();
        } catch (IOException ex) {
            Logger.getLogger(FXMLMenuSesionController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FXMLMenuSesionController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cargaArchivos() throws FileNotFoundException, IOException, ClassNotFoundException {
        //Leer archivo de Counters
        FileInputStream fis = new FileInputStream("Counters.txt");
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);
        String b = br.readLine();
        if (b != null) {
            util.Utility.setStudentCounter(Integer.parseInt(b));
            util.Utility.setSecurityCounter(Integer.parseInt(br.readLine()));
            util.Utility.setCareersCounter(Integer.parseInt(br.readLine()));
            util.Utility.setCoursesCounter(Integer.parseInt(br.readLine()));
            util.Utility.setSchedulesCounter(Integer.parseInt(br.readLine()));
            util.Utility.setEnrollmentCounter(Integer.parseInt(br.readLine()));
            util.Utility.setDeEnrollmentCounter(Integer.parseInt(br.readLine()));
            PrintWriter writer = new PrintWriter("Counters.txt");
            writer.print("");

            //Leer Archivo de Seguridad
            if (util.Utility.getCareersCounter() != 0) {
                FileInputStream fisCareer = new FileInputStream("CareersReport.txt");
                ObjectInputStream oisCareer = new ObjectInputStream(fisCareer);
                for (int i = 0; i < util.Utility.getCareersCounter(); i++) {
                    Career careerR = (Career) oisCareer.readObject();
                    career.add(careerR);
                }
            }
            PrintWriter writer1 = new PrintWriter("CareersReport.txt");
            writer1.print("");

            //Leer Archivo de Estudiante
            if (util.Utility.getStudentCounter() != 0) {
                FileInputStream fisStudent = new FileInputStream("StudentsReport.txt");
                ObjectInputStream oisStudent = new ObjectInputStream(fisStudent);

                for (int i = 0; i < util.Utility.getStudentCounter(); i++) {

                    Student studentR = (Student) oisStudent.readObject();
                    student.add(studentR);

                }
            }
            PrintWriter writer2 = new PrintWriter("StudentsReport.txt");
            writer2.print("");

            //Leer Archivo de Cursos
            if (util.Utility.getCoursesCounter() != 0) {
                FileInputStream fisCourse = new FileInputStream("CourseReport.txt");
                ObjectInputStream oisCourse = new ObjectInputStream(fisCourse);

                for (int i = 0; i < util.Utility.getCoursesCounter(); i++) {
                    Course courseR = (Course) oisCourse.readObject();
                    course.add(courseR);
                }
            }
            PrintWriter writer3 = new PrintWriter("CourseReport.txt");
            writer3.print("");

            //Leer Archivo de Horarios
            if (util.Utility.getSchedulesCounter() != 0) {
                FileInputStream fisSchedule = new FileInputStream("SchedulesReport.txt");
                ObjectInputStream oisSchedule = new ObjectInputStream(fisSchedule);

                for (int i = 0; i < util.Utility.getSchedulesCounter(); i++) {
                    TimeTable timeTableR = (TimeTable) oisSchedule.readObject();
                    schedule.add(timeTableR);
                }
            }
            PrintWriter writer4 = new PrintWriter("SchedulesReport.txt");
            writer4.print("");

            //Leer Archivo Enrollment
            if (util.Utility.getEnrollmentCounter() != 0) {
                FileInputStream fisEnrollment = new FileInputStream("EnrollmentReport.txt");
                ObjectInputStream oisEnrollment = new ObjectInputStream(fisEnrollment);

                for (int i = 0; i < util.Utility.getEnrollmentCounter(); i++) {
                    Enrollment enrollmentR = (Enrollment) oisEnrollment.readObject();
                    enrollment.add(enrollmentR);
                }
            }
            PrintWriter writer5 = new PrintWriter("EnrollmentReport.txt");
            writer5.print("");

            //Leer Archivo deEnrollment
            if (util.Utility.getDeEnrollmentCounter() != 0) {
                FileInputStream fisDeEnrollment = new FileInputStream("deEnrollmentReport.txt");
                ObjectInputStream oisDeEnrollment = new ObjectInputStream(fisDeEnrollment);

                for (int i = 0; i < util.Utility.getDeEnrollmentCounter(); i++) {
                    DeEnrollment DeEnrollmentR = (DeEnrollment) oisDeEnrollment.readObject();
                    deEnrollment.add(DeEnrollmentR);
                }
            }
            PrintWriter writer6 = new PrintWriter("deEnrollmentReport.txt");
            writer6.print("");

        }
    }

    @FXML
    private void openAdmin(ActionEvent event) {
        loadPage("FXMLMenuAdmi");
    }

    @FXML
    private void openStudent(ActionEvent event) {
        loadPage("FXMLMenuConsu");
    }

    @FXML
    private void Exit(ActionEvent event) throws FileNotFoundException, IOException, ListException, ClassNotFoundException {
        //Archivo Carreras
        //Archivo de contadores
        FileOutputStream fos = new FileOutputStream("Counters.txt", true);

        PrintStream ps = new PrintStream(fos);

        int studentC = util.Utility.getStudentCounter();
        int securityC = util.Utility.getSecurityCounter();
        int careerC = util.Utility.getCareersCounter();
        int coursesC = util.Utility.getCoursesCounter();
        int schedulesC = util.Utility.getSchedulesCounter();
        int enrollmentC = util.Utility.getEnrollmentCounter();
        int deEnrollmentC = util.Utility.getDeEnrollmentCounter();

        ps.println(String.valueOf(studentC));
        ps.println(String.valueOf(securityC));
        ps.println(String.valueOf(careerC));
        ps.println(String.valueOf(coursesC));
        ps.println(String.valueOf(schedulesC));
        ps.println(String.valueOf(enrollmentC));
        ps.println(String.valueOf(deEnrollmentC));

        if (!career.isEmpty()) {
            FileOutputStream fosCareer = new FileOutputStream("CareersReport.txt");
            ObjectOutputStream oosCareer = new ObjectOutputStream(fosCareer);

            Node auxCareer = career.getNode(1);

            while (auxCareer != null) {
                Career careerW = (Career) auxCareer.data;
                oosCareer.writeObject(careerW);
                auxCareer = auxCareer.next;
            }
        }
        //Archivo de Estudiante
        if (!student.isEmpty()) {
            FileOutputStream fosStudent = new FileOutputStream("StudentsReport.txt");
            ObjectOutputStream oosStudent = new ObjectOutputStream(fosStudent);

            Node auxStudent = student.getNode(1);

            while (auxStudent != null) {
                Student studentW = (Student) auxStudent.data;
                oosStudent.writeObject(studentW);
                auxStudent = auxStudent.next;
            }
        }
        if (!course.isEmpty()) {
            FileOutputStream fosCourse = new FileOutputStream("CourseReport.txt");
            ObjectOutputStream oosCourse = new ObjectOutputStream(fosCourse);

            Node auxCourse = course.getNode(1);

            while (auxCourse != course.getNodeLast()) {
                Course courseW = (Course) auxCourse.data;
                oosCourse.writeObject(courseW);
                auxCourse = auxCourse.next;
            }
            Course courseW = (Course) auxCourse.data;
            oosCourse.writeObject(courseW);
        }
        if (!schedule.isEmpty()) {
            FileOutputStream fosSchedule = new FileOutputStream("SchedulesReport.txt");
            ObjectOutputStream oosSchedule = new ObjectOutputStream(fosSchedule);

            Node auxSchedule = schedule.getNode(1);

            while (auxSchedule != null) {
                TimeTable timeTableW = (TimeTable) auxSchedule.data;
                oosSchedule.writeObject(timeTableW);
                auxSchedule = auxSchedule.next;
            }
        }
        if (!enrollment.isEmpty()) {
            FileOutputStream fosEnrollment = new FileOutputStream("EnrollmentReport.txt");
            ObjectOutputStream oosEnrollment = new ObjectOutputStream(fosEnrollment);

            Node auxEnrollment = enrollment.getNode(1);

            while (auxEnrollment != enrollment.getNodeLast()) {
                Enrollment enrollmentW = (Enrollment) auxEnrollment.data;
                oosEnrollment.writeObject(enrollmentW);
                auxEnrollment = auxEnrollment.next;
            }
            Enrollment enrollmentW = (Enrollment) auxEnrollment.data;
            oosEnrollment.writeObject(enrollmentW);
        }
        if (!deEnrollment.isEmpty()) {
            FileOutputStream fosDeEnrollment = new FileOutputStream("deEnrollmentReport.txt");
            ObjectOutputStream oosDeEnrollment = new ObjectOutputStream(fosDeEnrollment);

            Node auxDeEnrollment = deEnrollment.getNode(1);

            while (auxDeEnrollment != deEnrollment.getNodeLast()) {
                DeEnrollment deEnrollmentW = (DeEnrollment) auxDeEnrollment.data;
                oosDeEnrollment.writeObject(deEnrollmentW);

                auxDeEnrollment = auxDeEnrollment.next;
            }
            DeEnrollment deEnrollmentW = (DeEnrollment) auxDeEnrollment.data;
            oosDeEnrollment.writeObject(deEnrollmentW);
        }

        //Archivo Seguridad
        FileOutputStream fosSecurity = new FileOutputStream("SecurityReport.txt");
        ObjectOutputStream oosSecurity = new ObjectOutputStream(fosSecurity);

        Node auxSecurity = security.getNode(1);

        while (auxSecurity != security.getNode(security.size())) {  //getNodeLast()

            Security securityW = (Security) auxSecurity.data;
            oosSecurity.writeObject(securityW);
            auxSecurity = auxSecurity.next;
        }
        Security securityW = (Security) auxSecurity.data;
        oosSecurity.writeObject(securityW);

        //Leer Archivo seguridad
        FileInputStream fisSecurity = new FileInputStream("SecurityReport.txt");
        ObjectInputStream oisSecurity = new ObjectInputStream(fisSecurity);

        for (int i = 0; i < util.Utility.getSecurityCounter(); i++) {
            Security securityR = (Security) oisSecurity.readObject();
            this.security.add(securityR);

        }

        System.exit(0);
    }

}
