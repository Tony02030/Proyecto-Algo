/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Node;
import domain.SingleLinkedList;
import domain.Student;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
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
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLAddStudentController implements Initializable {

    private SingleLinkedList Student = util.Utility.getStudents();
    private DoublyLinkedList dLL = util.Utility.getCareers();
    private Career temp;
    private Student st;

    @FXML
    private TextField txtAsunto;
    @FXML
    private TextArea txtMensaje;
    @FXML
    private Text txtMessage1;
    @FXML
    private Text txtMessage2;
    @FXML
    private Text txtMessage3;
    @FXML
    private Text txtMessage4;
    @FXML
    private Text txtMessage5;
    @FXML
    private Text txtMessage6;
    @FXML
    private Text txtMessage7;
    @FXML
    private Text txtMessage8;
    @FXML
    private Text txtMessage9;

    @FXML
    private Button bEnviar;
    @FXML
    private TextField txfID;
    @FXML
    private TextField txfStudentID;
    @FXML
    private TextField txfLastname;
    @FXML
    private TextField txfName;
    @FXML
    private TextField txfPhoneNumber;
    @FXML
    private TextField txfEmail;
    @FXML
    private TextField txfAdress;
    @FXML
    private Button btnAddS;
    @FXML
    private DatePicker dPBirthday;
    @FXML
    private ComboBox<String> ComboBox;
    ObservableList<String> oL_ComboBox = FXCollections.observableArrayList();
    @FXML
    private TextField txfRecibe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        util.Utility.numericOnly(txfID);
        util.Utility.letterOnly(txfName);
        util.Utility.letterOnly(txfLastname);
        util.Utility.numericOnly(txfPhoneNumber);

        Node aux;
        try {
            aux = dLL.getNode(1);
            int x = 0;
            while (aux != null) {
                Career temp = (Career) aux.data;
                String y = temp.getDescription();
                oL_ComboBox.add(x, y);
                aux = aux.next;
                x = x + 1;
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ComboBox.setItems(oL_ComboBox);
    }

    @FXML
    private void btnAddS(ActionEvent event) throws ListException {
        if (!Student.contains1(Integer.parseInt(txfID.getText()))) {
            int id = 0;
            ZoneId defaultZoneId = ZoneId.systemDefault();

            LocalDate lD = dPBirthday.getValue();

            Date date = Date.from(lD.atStartOfDay(defaultZoneId).toInstant());
            Node aux;
            try {
                aux = dLL.getNode(1);

                while (aux != null) {
                    if (util.Utility.equals(aux.data, ComboBox.getValue())) {
                        temp = (Career) aux.data;

                    }
                    aux = aux.next;

                }
            } catch (ListException ex) {
                Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
            }
            Student.add(st = new Student(Integer.parseInt(txfID.getText()), txfStudentID.getText(), txfLastname.getText(), txfName.getText(), date, txfPhoneNumber.getText(), txfEmail.getText(), txfAdress.getText(), temp, 0));

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("Estudiante agregado correctamente");
            alert.showAndWait();

            txfID.setText("");
            txfStudentID.setText("");
            txfLastname.setText("");
            txfName.setText("");
            txfPhoneNumber.setText("");
            txfEmail.setText("");
            txfAdress.setText("");
            txtMessage1.setVisible(false);
            txtMessage2.setVisible(false);
            txtMessage3.setVisible(false);
            txtMessage4.setVisible(false);
            txtMessage5.setVisible(false);
            txtMessage6.setVisible(false);
            txtMessage7.setVisible(false);
            txtMessage8.setVisible(false);
            txtMessage9.setVisible(false);
            dPBirthday.setVisible(false);
            txfID.setVisible(false);
            txfStudentID.setVisible(false);
            txfLastname.setVisible(false);
            txfName.setVisible(false);
            txfPhoneNumber.setVisible(false);
            txfAdress.setVisible(false);
            txfEmail.setVisible(false);
            ComboBox.setVisible(false);
            btnAddS.setVisible(false);
            txfRecibe.setVisible(true);
            bEnviar.setVisible(true);
            txtMensaje.setVisible(true);
            txtAsunto.setVisible(true);

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("Ya existe ese estudiante");
            alert.showAndWait();
        }

    }

    @FXML
    private void bEnviar(ActionEvent event) {
        Properties propiedad = new Properties();
        propiedad.setProperty("mail smtp host", "smtp gmail com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail smtp port", "587");
        propiedad.setProperty("mail.smtp.auth", "true");

        Session sesion = Session.getDefaultInstance(propiedad);

        String correoEnvia = "jpizarrog1@gmail.com";
        String contraseña = "Jafeth2808*";
        String destinatario = txfRecibe.getText();
        String asunto = txtAsunto.getText();
        String mensaje = txtMensaje.getText();

        Message mail = new MimeMessage(sesion);

        try {
            mail.setFrom(new InternetAddress(correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

            mail.setSubject(asunto);
            MimeBodyPart message1 = new MimeBodyPart();
            DataSource source = new FileDataSource("C:\\Users\\User\\OneDrive\\Escritorio\\Algoritmos y Estructuras de Datos\\LogoUCR.jfif");
            message1.setDataHandler(new DataHandler(source));
            MimeBodyPart message2 = new MimeBodyPart();
            message2.setText(mensaje());
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(message1);
            multipart.addBodyPart(message2);
            mail.setContent(multipart);
            Transport transporte = sesion.getTransport("smtp");
            transporte.connect(correoEnvia, contraseña);
            transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
            transporte.close();
            

        } catch (AddressException ex) {
            Logger.getLogger(FXMLAddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(FXMLAddStudentController.class.getName()).log(Level.SEVERE, null, ex);
        }

        txfRecibe.setVisible(false);
        bEnviar.setVisible(false);
        txtMensaje.setVisible(false);
        txtAsunto.setVisible(false);
    }

    public String asunto() {
        String result = "Aceptación en la Universidad de Costa Rica";
        return result;

    }

    public String mensaje() {
        String result = "Bienvenido " + st.getFirstname() + " " + st.getLastname();
        result += "Estos fueron los datos que se ingresaron en la base de datos";
        result += "Cédula: " + txfID.getText();
        result += "Carnet: " + txfStudentID.getText();
        result += "Apelidos " + txfLastname.getText();
        result += "Nombre " + txfName.getText();
        result += "Fecha de Nacimiento " + st.getDateBirth();
        result += "Número de teléfono: " + txfPhoneNumber.getText();
        result += "Email: " + txfEmail.getText();
        result += "Dirección: " + txfAdress.getText();
        result += "Carrera elegida: " + temp.getDescription();

        return result;

    }
}
