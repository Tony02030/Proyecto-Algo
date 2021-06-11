/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.Enrollment;
import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
import domain.Student;
import domain.TimeTable;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLEnroll2Controller implements Initializable {

    private SingleLinkedList schedules = util.Utility.getSchedules();
    private Student student = util.Utility.getTemporal();
    private SingleLinkedList students = util.Utility.getStudents();
    private CircularDoublyLinkedList enrollment = util.Utility.getEnrollment();

    private CircularDoublyLinkedList courses = util.Utility.getCourses();
    private Enrollment enroll;

    @FXML
    private TableView<List<String>> tV_EnrollCourse;
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
    ObservableList<String> courses1 = FXCollections.observableArrayList();

    private Date dateToDay = Calendar.getInstance().getTime();
    private Course temp;
    @FXML
    private Button btn_EndEnrollment;
    @FXML
    private Text txtMessage;
    @FXML
    private Text txtMessage2;
    @FXML
    private BorderPane bp;
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

    //Muestra los cursos y horarios disponibles para matricular
    public void display() {
        courses1.clear();

        addTextLimiter(txf_Schedule, 17);
        tV_EnrollCourse.setVisible(true);
        txtMessage.setVisible(true);
        ComboBox_Course.setVisible(true);
        txtMessage2.setVisible(true);
        txf_Schedule.setVisible(true);
        btn_EnrollCourse.setVisible(true);
        btn_EndEnrollment.setVisible(true);
        this.tC_Course.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(0));
            }
        });
        this.tC_Schedule1.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(1));
            }
        });
        this.tC_Schedule2.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(2));
            }
        });
        this.tC_Career.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<List<String>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<List<String>, String> data) {
                return new ReadOnlyStringWrapper(data.getValue().get(3));
            }
        });
        this.tV_EnrollCourse.setItems(getData());

        try {
            Node aux = schedules.getNode(1);
            //Node last = courses.getNode(courses.size());
            while (aux != null) {

                TimeTable temp = (TimeTable) aux.data;
                if (!util.Utility.exist(temp.getIdEnrollment()) && util.Utility.equals(temp.getCourseID().getCareerID().getDescription(), student.getCareerID().getDescription())) {
                    courses1.add(temp.getCourseID().getName());
                }

                aux = aux.next;

            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.ComboBox_Course.setItems(courses1);

    }

    //Inserta los horarios en el ObservableList
    public ObservableList<List<String>> getData() {
        final ObservableList<List<String>> data = FXCollections.observableArrayList();
        Node aux;

        try {
            aux = schedules.getNode(1);

            while (aux != null) {
                List<String> array = new ArrayList<>();
                TimeTable temp = (TimeTable) aux.data;
                if (util.Utility.equals(temp.getCourseID().getCareerID().getDescription(), student.getCareerID().getDescription()) && !util.Utility.exist(temp.getIdEnrollment())) {
                    array.add(temp.getCourseID().getName());
                    array.add(temp.getSchedule1());
                    array.add(temp.getSchedule2());
                    array.add(temp.getCourseID().getCareerID().getDescription());
                    data.add(array);

                }

                aux = aux.next;

            }

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

    //Matricula el curso y hace las verificaciones correspondientes
    @FXML
    private void btn_EnrollCourse(ActionEvent event) throws FileNotFoundException, IOException {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Ventana de Confirmación");
        alert.setHeaderText("AVISO");
        alert.setContentText("¿Desea matricular este curso?");
        ButtonType buttonTypeYes = new ButtonType("Sí");
        ButtonType buttonTypeNo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeYes) {
            if (this.txf_Schedule.getText().length() < 17 || this.ComboBox_Course.getValue() == "") {
                Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                alert1.setTitle("Ventana de Diálogo");
                alert1.setHeaderText("Información");
                alert1.setContentText("El horario que ingresó no es válido");
                alert1.showAndWait();
                display();
            } else {
                int count = 0;
                int count1 = 0;
                int count2 = 0;
                try {
                    Node aux = schedules.getNode(1);

                    while (aux != null) {

                        TimeTable tem = (TimeTable) aux.data;
                        if (util.Utility.equals(this.ComboBox_Course.getValue(), tem.getCourseID().getName())) {
                            if (!tem.getSchedule1().equals(this.txf_Schedule.getText()) && !tem.getSchedule2().equals(this.txf_Schedule.getText())) {
                                count2++;
                            }
                        }

                        aux = aux.next;

                    }

                } catch (ListException ex) {
                    Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
                }

                String temp1 = this.txf_Schedule.getText();
                String temp3 = temp1.toUpperCase();

                char[] v = temp3.toCharArray();
                char[] c = new char[v.length - 4];

                int x = 0;
                for (int i = 4; i < v.length; i++) {
                    c[x] = v[i];
                    x++;

                }

                String tem = String.valueOf(c);

                String hor = tem.charAt(0) + "" + tem.charAt(1) + "";
                String hor01 = tem.charAt(8) + "" + tem.charAt(9) + "";

                //Dias de la semana
                char day = temp3.charAt(0);
                char day01 = temp3.charAt(2);

                //Horas
                int o = Integer.parseInt(hor);
                int p = Integer.parseInt(hor01);

                if (!enrollment.isEmpty()) {
                    try {
                        Node aux = enrollment.getNode(1);

                        while (aux != enrollment.getNodeLast()) {

                            Enrollment tempor = (Enrollment) aux.data;
                            if (this.student.getId() == tempor.getId() && util.Utility.equals(tempor.getSchedule(), this.txf_Schedule.getText())) {
                                count1++;

                            }

                            aux = aux.next;
                        }
                        Enrollment tempor = (Enrollment) aux.data;
                        if (this.student.getId() == tempor.getId() && util.Utility.equals(tempor.getSchedule(), this.txf_Schedule.getText())) {
                            count1++;

                        }

                    } catch (ListException ex) {
                        Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (!enrollment.isEmpty()) {
                    try {
                        Node aux = enrollment.getNode(1);

                        while (aux != enrollment.getNodeLast()) {
                            Enrollment tempo1 = (Enrollment) aux.data;
                            String tem1 = tempo1.getSchedule();
                            String tem3 = tem1.toUpperCase();

                            char[] l = tem3.toCharArray();
                            char[] t = new char[v.length - 4];

                            x = 0;
                            for (int i = 4; i < l.length; i++) {
                                t[x] = l[i];
                                x++;

                            }
                            x = 0;

                            String te = String.valueOf(t);

                            String hor3 = te.charAt(0) + "" + te.charAt(1) + "";
                            String hor03 = te.charAt(8) + "" + te.charAt(9) + "";

                            //Dias de la semana
                            char day3 = tem3.charAt(0);
                            char day03 = tem3.charAt(2);

                            //Horas
                            int oo = Integer.parseInt(hor3);
                            int pp = Integer.parseInt(hor03);

                            if (tempo1.getStudentID().getId() == student.getId()) {
                                if (((oo >= o && oo <= p) || (pp >= o && pp <= p))) {
                                    if (day == day3 || day01 == day03) {
                                        count++;

                                    }
                                }
                            }

                            aux = aux.next;
                        }

                    } catch (ListException ex) {
                        Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                if (count1 > 0) {
                    Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
                    alert2.setTitle("Ventana de Diálogo");
                    alert2.setHeaderText("Información");
                    alert2.setContentText("El horario ya fue ingresado");
                    alert2.showAndWait();

                    display();

                } else if (count > 0) {
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("Ventana de Diálogo");
                    alert3.setHeaderText("Información");
                    alert3.setContentText("El horario que ingresó no es válido");
                    alert3.showAndWait();

                    display();

                } else if (count2 > 0) {
                    Alert alert3 = new Alert(Alert.AlertType.INFORMATION);
                    alert3.setTitle("Ventana de Diálogo");
                    alert3.setHeaderText("Información");
                    alert3.setContentText("Debe ingresar el mismo horario del curso que eligió");
                    alert3.showAndWait();

                    display();

                }
                if (count == 0 && count1 == 0 && count2 == 0) {
                    int contador = 1;
                    if (!enrollment.isEmpty()) {
                        try {
                            Node aux = enrollment.getNode(1);

                            while (aux != enrollment.getNodeLast()) {

                                Enrollment tempor = (Enrollment) aux.data;
                                if (this.student.getId() == tempor.getId() && tempor.getIdentifier() == 1) {
                                    contador = 0;

                                }

                                aux = aux.next;
                            }
                            Enrollment tempor = (Enrollment) aux.data;
                            if (this.student.getId() == tempor.getId() && tempor.getIdentifier() == 1) {
                                contador = 0;

                            }

                        } catch (ListException ex) {
                            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    try {
                        Node aux = schedules.getNode(1);

                        while (aux != null) {

                            TimeTable te = (TimeTable) aux.data;
                            if (util.Utility.equals(this.ComboBox_Course.getValue(), te.getCourseID().getName())) {
                                te.setIdEnrollment(1);
                                temp = te.getCourseID();
                            }

                            aux = aux.next;

                        }

                    } catch (ListException ex) {
                        Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    this.enrollment.add(new Enrollment(student.getId(), this.dateToDay, student, temp, this.txf_Schedule.getText(), 0, contador));
                    display();
                    this.ComboBox_Course.setValue("");
                    this.txf_Schedule.setText("");
                    //Contador
                    int i = 1;
                    util.Utility.setEnrollmentCounter(util.Utility.getEnrollmentCounter() + i);
                }
                count = 0;
                count1 = 0;
                count2 = 0;

            }

        } else {
            display();
        }

    }

    //Finaliza el proceso de Matrícula
    @FXML
    private void btn_EndEnrollment(ActionEvent event) throws ListException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Ventana de Confirmación");
        alert.setHeaderText("AVISO");
        alert.setContentText("¿Está seguro de terminar el proceso de matrícula?");
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

            util.Utility.setTemporal(null);
            this.student.setIdEnrollment(1);
            tV_EnrollCourse.setVisible(false);
            txtMessage.setVisible(false);
            ComboBox_Course.setVisible(false);
            txtMessage2.setVisible(false);
            txf_Schedule.setVisible(false);
            btn_EnrollCourse.setVisible(false);
            btn_EndEnrollment.setVisible(false);
            txtMessage1.setVisible(false);
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
            loadPage("FXMLEnroll1");
        }

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

    private String asunto() {
        String result = "Matricula del estudiante";
        return result;
    }

    private String mensaje() throws ListException {
        String result = "\n";
        result += "Nombre: " + this.student.getFirstname() + " " + this.student.getLastname() + "\n";
        result += "Carnet: " + this.student.getStudentID() + "\n";
        result += "Cursos Matriculados:\n";
        int count = 0;
        Node aux = this.enrollment.getNode(1);
        while (aux != this.enrollment.getNodeLast()) {
            Enrollment tem = (Enrollment) aux.data;
            if (util.Utility.equals(this.student.getId(), tem.getId())) {
                result += "Curso: " + tem.getCourseID().getName() + " Horario: " + tem.getSchedule() + " Carrera: " + tem.getCourseID().getCareerID().getDescription() + "\n";
                count = count + tem.getCourseID().getCredits();
            }

            aux = aux.next;
        }
        Enrollment tem = (Enrollment) aux.data;
        if (util.Utility.equals(this.student.getId(), tem.getId())) {
            result += "Curso: " + tem.getCourseID().getName() + " Horario: " + tem.getSchedule() + " Carrera: " + tem.getCourseID().getCareerID().getDescription() + "\n";
            count = count + tem.getCourseID().getCredits();
        }
        result += "Carga Académica: " + count;
        return result;

    }
}
