/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.DeEnrollment;
import domain.Enrollment;
import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
import domain.Student;
import domain.TimeTable;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Callback;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLDeEnroll2Controller implements Initializable {

    private SingleLinkedList schedules = util.Utility.getSchedules();
    private Student student = util.Utility.getTemporal();
    private SingleLinkedList students = util.Utility.getStudents();
    private CircularDoublyLinkedList deEnrollment = util.Utility.getDeEnrollment();
    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();
    private CircularDoublyLinkedList courses = util.Utility.getCourses();
    private Enrollment enroll;

    @FXML
    private TableColumn<List<String>, String> tC_Course;

    @FXML
    private TableColumn<List<String>, String> tC_Career;
    @FXML
    private ComboBox<String> ComboBox_Course;

    ObservableList<String> courses1 = FXCollections.observableArrayList();

    private Date dateToDay = Calendar.getInstance().getTime();
    private Course temp;

    @FXML
    private Text txtMessage;
    @FXML
    private Text txtMessage2;
    @FXML
    private BorderPane bp;
    @FXML
    private TableView<List<String>> tV_DeEnrollCourse;
    @FXML
    private TableColumn<List<String>, String> tC_Schedule;
    @FXML
    private Button btn_EndDeEnrollment;
    @FXML
    private Button btn_DeEnrollCourse;
    @FXML
    private TextArea textAMotivo;
    @FXML
    private Text txtMessage1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        display();

    }

    public static void addTextLimiter(final TextField tf, final int maxLength) {
        tf.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (tf.getText().length() > maxLength) {
                    String s = tf.getText().substring(0, maxLength);
                    tf.setText(s);
                }
            }
        });
    }

    public void display() {
        
        courses1.clear();
        this.ComboBox_Course.setItems(courses1);

        tV_DeEnrollCourse.setVisible(true);
        txtMessage.setVisible(true);
        ComboBox_Course.setVisible(true);
        txtMessage2.setVisible(true);

        this.tC_Course.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0));
            }
        });
        this.tC_Schedule.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1));
            }
        });

        this.tC_Career.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2));
            }
        });
        this.tV_DeEnrollCourse.setItems(getData());

        try {
            Node aux = enrollment.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux != enrollment.getNodeLast()) {

                Enrollment temp = (Enrollment) aux.data;
                if (util.Utility.exist(temp.getIdEnroll())) {
                    courses1.add(String.valueOf(temp.getCourseID().getName()));
                }

                aux = aux.next;

            }
            Enrollment temp = (Enrollment) aux.data;
            if (util.Utility.exist(temp.getIdEnroll())) {
                courses1.add(String.valueOf(temp.getCourseID().getName()));
            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ObservableList<List<String>> getData() {
        final ObservableList<List<String>> data = FXCollections.observableArrayList();
        Node aux;

        try {
            aux = enrollment.getNode(1);

            while (aux != enrollment.getNodeLast()) {
                List<String> array = new ArrayList<>();
                Enrollment temp = (Enrollment) aux.data;
                //!util.Utility.exist(temp.getIdEnrollment()
                if (util.Utility.equals(temp.getCourseID().getCareerID().getDescription(), student.getCareerID().getDescription())) {
                    array.add(temp.getCourseID().getName());
                    array.add(temp.getSchedule());
                    array.add(temp.getCourseID().getCareerID().getDescription());
                    data.add(array);

                }

                aux = aux.next;

            }
            List<String> array = new ArrayList<>();
            Enrollment temp = (Enrollment) aux.data;
            if (util.Utility.equals(temp.getCourseID().getCareerID().getDescription(), student.getCareerID().getDescription())) {
                array.add(temp.getCourseID().getName());
                array.add(temp.getSchedule());
                array.add(temp.getCourseID().getCareerID().getDescription());
                data.add(array);

            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
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

    //Botón para finalizar el proceso de matrícula
    @FXML
    private void btn_EndDeEnrollment(ActionEvent event) throws ListException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ventana de Confirmación");
        alert.setHeaderText("AVISO");
        alert.setContentText("¿Está seguro de terminar el proceso de retiro de cursos?");
        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
//            Properties propiedad = new Properties();
//            propiedad.put("mail smtp host", "smtp gmail com");
//            propiedad.put("mail smtp port", "587");
//            propiedad.put("mail.smtp.auth", "true");
//            propiedad.put("mail.smtp.starttls.enable", "true");
//            propiedad.put("mail.smtp.user", "anthony.rs02@gmail.com");
//            propiedad.put("mail.smtp.clave", "");
//
//            Session sesion = Session.getDefaultInstance(propiedad);
//
//            String correoEnvia = "anthony.rs02@gmail.com";
//            String contraseña = "18702NACE";
//            String destinatario = this.student.getEmail();
//
//            MimeMessage mail = new MimeMessage(sesion);
//
//            try {
////                mail.setFrom(new InternetAddress(correoEnvia));
//                mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
//
//                mail.setSubject(asunto());
//                Multipart multipart = new MimeMultipart();
//
//                MimeBodyPart message1 = new MimeBodyPart();
//                String htmlText = "<img src=\"cid:image\">";
//                message1.setContent(htmlText, "text/html");
//
//                MimeBodyPart message2 = new MimeBodyPart();
//                DataSource source = new FileDataSource("C:\\Users\\User\\OneDrive\\Escritorio\\Algoritmos y Estructuras de Datos\\logoBueno.png");
//                message2.setDataHandler(new DataHandler(source));
//                message2.setHeader("Content-ID", "<image>");
////                message1.setFileName(source.getName());
//
//                MimeBodyPart message3 = new MimeBodyPart();
//                message3.setText(mensaje());
//
//                multipart.addBodyPart(message1);
//                multipart.addBodyPart(message2);
//                multipart.addBodyPart(message3);
//                mail.setContent(multipart);
//                Transport transporte = sesion.getTransport("smtp");
//                transporte.connect("smtp.gmail.com", correoEnvia, contraseña);
//                transporte.sendMessage(mail, mail.getAllRecipients());
//                transporte.close();
//
//            } catch (AddressException ex) {
//                Logger.getLogger(FXMLAddStudentController.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (MessagingException ex) {
//                Logger.getLogger(FXMLAddStudentController.class.getName()).log(Level.SEVERE, null, ex);
//            }
            tV_DeEnrollCourse.setVisible(false);
            txtMessage.setVisible(false);
            ComboBox_Course.setVisible(false);
            txtMessage2.setVisible(false);
            textAMotivo.setVisible(false);

            try {
                Node aux = schedules.getNode(1);

                while (aux != null) {

                    TimeTable tem = (TimeTable) aux.data;
                    tem.setIdEnrollment(0);

                    aux = aux.next;

                }

            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            loadPage("FXMLDeEnroll1");
        }
    }

    //Botón para hacer el proceso de retirar curso
    @FXML
    private void btn_DeEnrollCourse(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Ventana de Confirmación");
        alert.setHeaderText("AVISO");
        alert.setContentText("¿Desea retirar este curso?");
        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            if (this.ComboBox_Course.getValue() == "" && this.textAMotivo.getText() == "") {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ventana de Diálogo");
                alert.setHeaderText("Información");
                alert.setContentText("Debe seleccionar la carrera que desea eliminar");
                alert.showAndWait();
            } else {

                //Verificaciones
                try {
                    Node aux = enrollment.getNode(1);

                    while (aux != enrollment.getNodeLast()) {

                        Enrollment tem = (Enrollment) aux.data;
                        if (util.Utility.equals(this.ComboBox_Course.getValue(), tem.getCourseID().getName())) {

                            temp = tem.getCourseID();
                            enroll = tem;
                        }

                        aux = aux.next;

                    }
                    Enrollment tem = (Enrollment) aux.data;
                    if (util.Utility.equals(this.ComboBox_Course.getValue(), tem.getCourseID().getName())) {
                        temp = tem.getCourseID();
                        enroll = tem;
                    }

                } catch (ListException ex) {
                    Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    Node aux1 = schedules.getNode(1);

                    while (aux1 != null) {

                        TimeTable te = (TimeTable) aux1.data;
                        if (util.Utility.equals(this.ComboBox_Course.getValue(), te.getCourseID().getName())) {
                            te.setIdEnrollment(1);

                        }

                        aux1 = aux1.next;

                    }

                } catch (ListException ex) {
                    Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
                }
                enroll.setIdEnroll(1);
                this.enrollment.add(new DeEnrollment(student.getId(), this.dateToDay, student, temp, enroll.getSchedule(), textAMotivo.getText()));
                display();
                this.ComboBox_Course.setValue("");
                this.textAMotivo.setText("");

                //Contador
                int i = 1;
                util.Utility.setDeEnrollmentCounter(util.Utility.getDeEnrollmentCounter() + i);

            }

        } else {
            display();
        }

    }

    private String asunto() {
        String result = "Retiro de cursos del estudiante";
        return result;
    }

    private String mensaje() throws ListException {
        String result = "\n";
        result += "Nombre: " + this.student.getFirstname() + " " + this.student.getLastname() + "\n";
        result += "Carnet: " + this.student.getStudentID() + "\n";
        result += "Cursos Retirados:\n";
        Node aux = this.deEnrollment.getNode(1);
        while (aux != this.deEnrollment.getNodeLast()) {
            DeEnrollment tem = (DeEnrollment) aux.data;
            if (util.Utility.equals(this.student.getId(), tem.getId())) {
                result += "Curso: " + tem.getCourseID().getName() + " Horario: " + tem.getSchedule() + " Carrera: " + tem.getCourseID().getCareerID().getDescription() + "Motivo del retiro: " + tem.getRemark() + "\n";

            }

            aux = aux.next;
        }
        DeEnrollment tem = (DeEnrollment) aux.data;
        if (util.Utility.equals(this.student.getId(), tem.getId())) {
            result += "Curso: " + tem.getCourseID().getName() + " Horario: " + tem.getSchedule() + " Carrera: " + tem.getCourseID().getCareerID().getDescription() + "Motivo del retiro: " + tem.getRemark() + "\n";

        }

        return result;

    }
}
