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
import domain.CircularDoublyLinkedList;
import domain.Enrollment;
import domain.ListException;
import domain.Node;
import domain.Student;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class FXMLMenuConsultaController implements Initializable {

    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    private Student student = util.Utility.getConsulta();
    @FXML
    private Button showCourses;
    @FXML
    private Button generaPdf;
    @FXML
    private BorderPane bp;

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
    private void showCourses(ActionEvent event) {
        loadPage("FXMLDisplayEnrolledCourses");
    }

    @FXML
    private void generaPdf(ActionEvent event) throws DocumentException, ListException, BadElementException, IOException {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Ventana de dialogo");
        alert.setHeaderText("Informacion");
        alert.setContentText("Se generó el reporte");
        alert.showAndWait();
        generaMatricula();

    }

    public void generaMatricula() throws FileNotFoundException, DocumentException, ListException, BadElementException, IOException {

        FileOutputStream archivo = new FileOutputStream("HorarioEstudiante" + this.student.getId() + ".pdf");
        Document docu = new Document(PageSize.A4, 10, 10, 10, 10);

        PdfWriter.getInstance(docu, archivo);
        docu.open();

        Paragraph title = new Paragraph(this.student.getFirstname() + " " + this.student.getLastname()+"\n", FontFactory.getFont("arial", 22, Font.BOLD, BaseColor.BLACK));
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
        PdfPTable table1 = new PdfPTable(3);
        table1.addCell("Cursos");
        table1.addCell("Horario");
        table1.addCell("Carrera");

        Node aux = enrollment.getNode(1);;

        while (aux != enrollment.getNodeLast()) {
            Enrollment temp = (Enrollment) aux.data;
            if (util.Utility.equals(temp.getId(), this.student.getId())) {
                table1.addCell(temp.getCourseID().getName());
                table1.addCell(temp.getSchedule());
                table1.addCell(temp.getCourseID().getCareerID().getDescription());
            }
            aux = aux.next;
        }
        Enrollment temp = (Enrollment) aux.data;
        if (util.Utility.equals(temp.getId(), this.student.getId())) {
            table1.addCell(temp.getCourseID().getName());
            table1.addCell(temp.getSchedule());
            table1.addCell(temp.getCourseID().getCareerID().getDescription());
        }
        docu.add(table1);

        docu.close();

    }

}
