/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
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
    @FXML
    private MenuItem pdfEstudiantes;
    @FXML
    private MenuItem pdfCursos;
    @FXML
    private MenuItem pdfEstuMatriculados;
    @FXML
    private MenuItem pdfRetiros;

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
        Course course = (Course) auxCourse.data;
        oosCourse.writeObject(course);

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
        Enrollment enrollment = (Enrollment) auxEnrollment.data;
        oosEnrollment.writeObject(enrollment);

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
        DeEnrollment deEnrollment = (DeEnrollment) auxDeEnrollment.data;
        oosDeEnrollment.writeObject(deEnrollment);

//        DeEnrollment temp = (DeEnrollment) auxSecurity.data;
//        if (util.Utility.exist(temp.getIdEnroll())) {
//            students.add(String.valueOf(temp.getId()));
//        }
        //Leer Archivo deEnrollment
    }

    @FXML
    private void pdfEstudiantes(ActionEvent event) throws FileNotFoundException, DocumentException, ListException, BadElementException, IOException {
        if (student.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista de estudiantes esta vacia");
            alert.showAndWait();

        } else {
            generaEstudiante();
        }
    }

    @FXML
    private void pdfCursos(ActionEvent event) throws DocumentException, ListException, BadElementException, IOException {
        if (course.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista de cursos esta vacia");
            alert.showAndWait();

        } else {
            generaCurso();
        }

    }

    @FXML
    private void pdfEstuMatriculados(ActionEvent event) throws DocumentException, ListException, BadElementException, IOException {
        if (enrollment.isEmpty()) {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("La lista de matriculados esta vacia");
            alert.showAndWait();

        } else {
            generaMatricula();
        }

    }

    @FXML
    private void pdfRetiros(ActionEvent event) {
    }

    public void generaEstudiante() throws FileNotFoundException, DocumentException, ListException, BadElementException, IOException {

        FileOutputStream archivo = new FileOutputStream("ListaEstudiantes.pdf");
        Document docu = new Document();

        PdfWriter.getInstance(docu, archivo);
        docu.open();

        Paragraph title = new Paragraph("Lista de estudiantes", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK));

        title.setAlignment(Paragraph.ALIGN_CENTER);

        Image img = Image.getInstance("C:\\Users\\User\\OneDrive\\Escritorio\\Algoritmos y Estructuras de Datos\\logoBueno.png");
        img.setAlignment(Image.ALIGN_CENTER);
        img.setBorder(Image.BOX);
        img.setBorderWidth(0);
        img.setBorderColor(BaseColor.WHITE);
        img.scaleToFit(1100, 100); // tamaño 
        docu.add(img);
        docu.add(title);
        PdfPTable table = new PdfPTable(4);
        table.addCell("ID");
        table.addCell("Carnet");
        table.addCell("Apellidos");
        table.addCell("Nombre");

        Node aux = this.student.getNode(1);

        while (aux != null) {
            Student temp = (Student) aux.data;
            table.addCell(String.valueOf(temp.getId()));
            table.addCell(temp.getStudentID());
            table.addCell(temp.getLastname());
            table.addCell(temp.getFirstname());
            aux = aux.next;
        }
        docu.add(table);

        docu.close();

    }

    public void generaCurso() throws FileNotFoundException, DocumentException, ListException, BadElementException, IOException {

        FileOutputStream archivo = new FileOutputStream("ListaCursos.pdf");
        Document docu = new Document(PageSize.A4, 10, 10, 10, 10);

        PdfWriter.getInstance(docu, archivo);
        docu.open();

        Paragraph title = new Paragraph("Lista de Cursos", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK));
        title.setAlignment(Paragraph.ALIGN_CENTER);

        Image img = Image.getInstance("C:\\Users\\User\\OneDrive\\Escritorio\\Algoritmos y Estructuras de Datos\\logoBueno.png");
        img.setAlignment(Image.ALIGN_CENTER);
        img.setBorder(Image.BOX);
        img.setBorderWidth(0);
        img.setBorderColor(BaseColor.WHITE);
        img.scaleToFit(1100, 100); // tamaño 
        docu.add(img);

        docu.add(new Paragraph("\n\n"));

        docu.add(title);
        PdfPTable table = new PdfPTable(4);
        table.addCell("ID del Curso");
        table.addCell("Nombre del curso");
        table.addCell("Creditos");
        table.addCell("Carrera");

        Node aux = this.course.getNode(1);
        while (aux != this.course.getNodeLast()) {
            Course temp = (Course) aux.data;
            table.addCell(String.valueOf(temp.getId()));
            table.addCell(temp.getName());
            table.addCell(String.valueOf(temp.getCredits()));
            table.addCell(temp.getCareerID().getDescription());
            aux = aux.next;
        }
        Course temp = (Course) aux.data;
        table.addCell(String.valueOf(temp.getId()));
        table.addCell(temp.getName());
        table.addCell(String.valueOf(temp.getCredits()));
        table.addCell(temp.getCareerID().getDescription());
        docu.add(table);
        docu.add(table);

        docu.close();

    }

    public void generaMatricula() throws FileNotFoundException, DocumentException, ListException, BadElementException, IOException {

        FileOutputStream archivo = new FileOutputStream("ListaMatriculados.pdf");
        Document docu = new Document(PageSize.A4, 10, 10, 10, 10);

        PdfWriter.getInstance(docu, archivo);
        docu.open();

        Paragraph title = new Paragraph("Lista de Matriculados", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK));
        title.setAlignment(Paragraph.ALIGN_CENTER);

        Image img = Image.getInstance("C:\\Users\\User\\OneDrive\\Escritorio\\Algoritmos y Estructuras de Datos\\logoBueno.png");
        img.setAlignment(Image.ALIGN_CENTER);
        img.setBorder(Image.BOX);
        img.setBorderWidth(0);
        img.setBorderColor(BaseColor.WHITE);
        img.scaleToFit(1100, 100); // tamaño 
        docu.add(img);

        docu.add(new Paragraph("\n\n"));

        docu.add(title);
        Node enr1 = enrollment.getNode(1);;
        Node aux = this.student.getNode(1);
        while (aux != null) {
            Student temp = (Student) aux.data;
            if (enrollment.contains1(temp.getId())) {
                Paragraph estudiante = new Paragraph(temp.getFirstname() + " " + temp.getLastname() + "\n\n", FontFactory.getFont("arial", 18, Font.NORMAL, BaseColor.BLACK));
                estudiante.setAlignment(Paragraph.ALIGN_CENTER);

                PdfPTable table1 = new PdfPTable(3);
                table1.addCell("Cursos");
                table1.addCell("Horario");
                table1.addCell("Carrera");
                while (enr1 != enrollment.getNodeLast()) {
                    Enrollment tem = (Enrollment) enr1.data;
                    if (util.Utility.equals(tem.getStudentID().getId(), temp.getId()) && tem.getIdEnroll() == 0) {

                        table1.addCell(tem.getCourseID().getName());
                        table1.addCell(tem.getSchedule());
                        table1.addCell(tem.getStudentID().getCareerID().getDescription());

                    }
                    enr1 = enr1.next;
                }
                Enrollment tem = (Enrollment) enr1.data;
                if (util.Utility.equals(tem.getStudentID().getId(), temp.getId()) && tem.getIdEnroll() == 0) {

                    table1.addCell(tem.getCourseID().getName());
                    table1.addCell(tem.getSchedule());
                    table1.addCell(tem.getStudentID().getCareerID().getDescription());

                }
                docu.add(table1);
                docu.add(new Paragraph("\n\n"));

            }
            enr1 = enrollment.getNode(1);

            aux = aux.next;
        }

        docu.close();

    }

    public void generaRetiroMatricula() throws FileNotFoundException, DocumentException, ListException, BadElementException, IOException {

        FileOutputStream archivo = new FileOutputStream("ListaRetiro.pdf");
        Document docu = new Document(PageSize.A4, 10, 10, 10, 10);

        PdfWriter.getInstance(docu, archivo);
        docu.open();

        Paragraph title = new Paragraph("Lista de estudiantes con cursos matriculados", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK));
        title.setAlignment(Paragraph.ALIGN_CENTER);

        Image img = Image.getInstance("C:\\Users\\User\\OneDrive\\Escritorio\\Algoritmos y Estructuras de Datos\\logoBueno.png");
        img.setAlignment(Image.ALIGN_CENTER);
        img.setBorder(Image.BOX);
        img.setBorderWidth(0);
        img.setBorderColor(BaseColor.WHITE);
        img.scaleToFit(1100, 100); // tamaño 
        docu.add(img);

        docu.add(new Paragraph("\n\n"));

        docu.add(title);
        Node enr1 = deEnrollment.getNode(1);;
        Node aux = this.student.getNode(1);
        while (aux != null) {
            Student temp = (Student) aux.data;
            if (deEnrollment.contains1(temp.getId())) {
                Paragraph estudiante = new Paragraph(temp.getFirstname() + " " + temp.getLastname() + "\n\n", FontFactory.getFont("arial", 18, Font.NORMAL, BaseColor.BLACK));
                estudiante.setAlignment(Paragraph.ALIGN_CENTER);

                PdfPTable table1 = new PdfPTable(3);
                table1.addCell("Cursos");
                table1.addCell("Horario");
                table1.addCell("Carrera");
                while (enr1 != deEnrollment.getNodeLast()) {
                    DeEnrollment tem = (DeEnrollment) enr1.data;
                    if (util.Utility.equals(tem.getStudentID().getId(), temp.getId())) {

                        table1.addCell(tem.getCourseID().getName());
                        table1.addCell(tem.getSchedule());
                        table1.addCell(tem.getStudentID().getCareerID().getDescription());

                    }
                    enr1 = enr1.next;
                }
                DeEnrollment tem = (DeEnrollment) enr1.data;
                if (util.Utility.equals(tem.getStudentID().getId(), temp.getId())) {

                    table1.addCell(tem.getCourseID().getName());
                    table1.addCell(tem.getSchedule());
                    table1.addCell(tem.getStudentID().getCareerID().getDescription());

                }
                docu.add(table1);
                docu.add(new Paragraph("\n\n"));

            }
            enr1 = deEnrollment.getNode(1);

            aux = aux.next;
        }

        docu.close();

    }

}
