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
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

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
    private void btnAddS(ActionEvent event) throws ListException, FileNotFoundException, IOException, MessagingException {
        if (txfID.getText() == "" || txfStudentID.getText() == "" || txfLastname.getText() == "" || txfName.getText() == "" || txfPhoneNumber.getText() == "" || txfEmail.getText() == "" || txfAdress.getText() == "") {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de Di??logo");
            alert.setHeaderText("Informaci??n");
            alert.setContentText("No puede dejar espacios en blanco");
            alert.showAndWait();

        }

        //Se agregan los estudiantes a la lista
        if (!Student.contains1(Integer.parseInt(txfID.getText()))) {
            int id = 0;
            ZoneId defaultZoneId = ZoneId.systemDefault();

            LocalDate lD = dPBirthday.getValue();

            Date date = Date.from(lD.atStartOfDay(defaultZoneId).toInstant());
            Node aux;

            aux = dLL.getNode(1);

            while (aux != null) {
                if (util.Utility.equals(aux.data, ComboBox.getValue())) {
                    temp = (Career) aux.data;

                }
                aux = aux.next;

            }

            Student.add(st = new Student(Integer.parseInt(txfID.getText()), txfStudentID.getText(), txfLastname.getText(), txfName.getText(), date, txfPhoneNumber.getText(), txfEmail.getText(), txfAdress.getText(), temp, 0));

            //Contador para archivos
            int i = 1;
            util.Utility.setStudentCounter(util.Utility.getStudentCounter()+i);

           // Claves para enviar correos por "gmail" al estudiante
            Properties propiedad = new Properties();
            propiedad.put("mail smtp host", "smtp gmail com");
            propiedad.put("mail smtp port", "587");
            propiedad.put("mail.smtp.auth", "true");
            propiedad.put("mail.smtp.starttls.enable", "true");
            propiedad.put("mail.smtp.user", "anthony.rs02@gmail.com");
            propiedad.put("mail.smtp.clave", "");

            Session sesion = Session.getDefaultInstance(propiedad);

            String correoEnvia = "anthony.rs02@gmail.com";
            String contrase??a = "18702NACE";
            String destinatario = txfEmail.getText();

            MimeMessage mail = new MimeMessage(sesion);

            //Creaci??n del cuerpo y asunto del correo
                mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));

                mail.setSubject(asunto());
                Multipart multipart = new MimeMultipart();

                MimeBodyPart message1 = new MimeBodyPart();
                String htmlText = "<img src=\"cid:image\">";
                message1.setContent(htmlText, "text/html");

                MimeBodyPart message2 = new MimeBodyPart();
                DataSource source = new FileDataSource("C:\\Users\\User\\OneDrive\\Escritorio\\Algoritmos y Estructuras de Datos\\logoBueno.png");
                message2.setDataHandler(new DataHandler(source));
                message2.setHeader("Content-ID", "<image>");
                
                MimeBodyPart message3 = new MimeBodyPart();
                message3.setText(mensaje());

                multipart.addBodyPart(message1);
                multipart.addBodyPart(message2);
                multipart.addBodyPart(message3);
                mail.setContent(multipart);
                Transport transporte = sesion.getTransport("smtp");
                transporte.connect("smtp.gmail.com", correoEnvia, contrase??a);
                transporte.sendMessage(mail, mail.getAllRecipients());
                transporte.close();
                
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informaci??n");
            alert.setContentText("Estudiante agregado correctamente");
            alert.showAndWait();

            txfID.setText("");
            txfStudentID.setText("");
            txfLastname.setText("");
            txfName.setText("");
            txfPhoneNumber.setText("");
            txfEmail.setText("");
            txfAdress.setText("");

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de Di??logo");
            alert.setHeaderText("Informaci??n");
            alert.setContentText("Este estudiante ya se encuentra en la Lista");
            alert.showAndWait();
        }
    }

    public String asunto() {
        //Asunto del correo enviado

        String result = "Aceptaci??n en la Universidad de Costa Rica";
        return result;

    }

    public String mensaje() {
        //Cuerpo del mensaje con la informaci??n del estudiante
        String result = "\n";
        result += "Bienvenido " + st.getFirstname() + " " + st.getLastname() + "\n";
        result += "Estos fueron los datos que se ingresaron en la base de datos:\n";
        result += "C??dula: " + txfID.getText() + "\n";
        result += "Carnet: " + txfStudentID.getText() + "\n";
        result += "Apelidos: " + txfLastname.getText() + "\n";
        result += "Nombre: " + txfName.getText() + "\n";
        result += "Fecha de Nacimiento: " + st.getDateBirth() + "\n";
        result += "N??mero de tel??fono: " + txfPhoneNumber.getText() + "\n";
        result += "Email: " + txfEmail.getText() + "\n";
        result += "Direcci??n: " + txfAdress.getText() + "\n";
        result += "Carrera elegida: " + temp.getDescription() + "\n";
        result += "Saludos cordiales.";

        return result;

    }
}
