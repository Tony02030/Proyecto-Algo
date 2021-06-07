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
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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
    private void btnAddS(ActionEvent event) throws ListException, FileNotFoundException, IOException {
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

            
            Properties propiedad = new Properties();
            propiedad.put("mail smtp host", "smtp gmail com");
            propiedad.put("mail smtp port", "587");
             propiedad.put("mail.smtp.auth", "true");
            propiedad.put("mail.smtp.starttls.enable", "true");
            propiedad.put("mail.smtp.user","anthony.rs02@gmail.com");
            propiedad.put("mail.smtp.clave", "");

            Session sesion = Session.getDefaultInstance(propiedad);

            String correoEnvia = "anthony.rs02@gmail.com";
            String contraseña = "18702NACE";
            String destinatario = txfEmail.getText();

            MimeMessage mail = new MimeMessage(sesion);

            try {
//                mail.setFrom(new InternetAddress(correoEnvia));
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
//                message1.setFileName(source.getName());


                MimeBodyPart message3 = new MimeBodyPart();
                message3.setText(mensaje());
                
                multipart.addBodyPart(message1);
                multipart.addBodyPart(message2);
                multipart.addBodyPart(message3);
                mail.setContent(multipart);
                Transport transporte = sesion.getTransport("smtp");
                transporte.connect("smtp.gmail.com",correoEnvia, contraseña);
                transporte.sendMessage(mail, mail.getAllRecipients());
                transporte.close();

            } catch (AddressException ex) {
                Logger.getLogger(FXMLAddStudentController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (MessagingException ex) {
                Logger.getLogger(FXMLAddStudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
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

           

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Ventana de dialogo");
            alert.setHeaderText("Informacion");
            alert.setContentText("Ya existe ese estudiante");
            alert.showAndWait();
        }

    }

    public String asunto() {
        String result = "Aceptación en la Universidad de Costa Rica";
        return result;

    }

    public String mensaje() {
        String result="\n";
        result += "Bienvenido " + st.getFirstname() + " " + st.getLastname()+"\n";
        result += "Estos fueron los datos que se ingresaron en la base de datos\n";
        result += "Cédula: " + txfID.getText()+"\n";
        result += "Carnet: " + txfStudentID.getText()+"\n";
        result += "Apelidos " + txfLastname.getText()+"\n";
        result += "Nombre " + txfName.getText()+"\n";
        result += "Fecha de Nacimiento " + st.getDateBirth()+"\n";
        result += "Número de teléfono: " + txfPhoneNumber.getText()+"\n";
        result += "Email: " + txfEmail.getText()+"\n";
        result += "Dirección: " + txfAdress.getText()+"\n";
        result += "Carrera elegida: " + temp.getDescription()+"\n";

        return result;

    }
}
