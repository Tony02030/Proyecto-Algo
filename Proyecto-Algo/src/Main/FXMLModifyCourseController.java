/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Node;
import java.net.URL;
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
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author 31670
 */
public class FXMLModifyCourseController implements Initializable {

    private CircularDoublyLinkedList course = util.Utility.getCourses();
    private DoublyLinkedList career = util.Utility.getCareers();

    @FXML
    private TextField tfCursoID;
    @FXML
    private Text txtCursoNoAgregado;
    @FXML
    private Text txtMessage1;
    @FXML
    private Text txtMessage2;
    @FXML
    private Text txtMessage3;
    @FXML
    private Text txtMessage4;
    @FXML
    private TextField txfNewID;
    @FXML
    private TextField txfNewName;
    @FXML
    private TextField txfNewCredits;
    @FXML
    private ComboBox<String> ComboBox;
    ObservableList<String> oL_ComboBox = FXCollections.observableArrayList();
    @FXML
    private Button btnCambiarCurso;
    @FXML
    private Button btnBuscarCurso;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        numericOnly(tfCursoID);
        numericOnly(txfNewID);
        // TODO
         Node aux;
        try {
            aux = career.getNode(1);
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
    private void btnBuscarCurso(ActionEvent event) {
        int x = Integer.parseInt(tfCursoID.getText());

        try {
            if (course.contains1(x)) {
                txtCursoNoAgregado.setVisible(false);
                txtMessage1.setVisible(true);
                txtMessage2.setVisible(true);
                txtMessage3.setVisible(true);
                txtMessage4.setVisible(true);
                txfNewID.setVisible(true);
                txfNewName.setVisible(true);
                txfNewCredits.setVisible(true);
                ComboBox.setVisible(true);

            } else {
                this.txtCursoNoAgregado.setVisible(true);
            }
        } catch (ListException ex) {
            Logger.getLogger(FXMLModifyCourseController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    @FXML
    private void btnCambiarCurso(ActionEvent event) throws ListException {
        
        try{
            Career CareerID = null;
            Node aux = course.getNode(1);
            int ID = Integer.parseInt(tfCursoID.getText());
                    while(aux!=null){
                    
                    if (util.Utility.equals(aux.data, ID)) {
                    Course temp=(Course) aux.data;
                    temp.setId(txfNewID.getText());
                    temp.setName(txfNewName.getText());
                    temp.setCredits(Integer.parseInt(txfNewCredits.getText()));
                    temp.setCareerID(CareerID);
                    
                     Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ventana de dialogo");
                    alert.setHeaderText("Información");
                    alert.setContentText("Curso modificado correctamente");
                    alert.showAndWait();
                    
                     }
                    aux=aux.next;
                    }
                    
                    }catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
                    
                    }  
        
        
               
    
                txtMessage1.setVisible(false);
                txtMessage2.setVisible(false);
                txtMessage3.setVisible(false);
                txtMessage4.setVisible(false);
        
                txfNewID.setText("");
                txfNewName.setText("");
                txfNewCredits.setText("");
                ComboBox.setVisible(false);
                btnCambiarCurso.setText("");
                
                
                
                
                
                
                txfNewID.setVisible(false);
                txfNewName.setVisible(false);
                txfNewCredits.setVisible(false);
                btnCambiarCurso.setVisible(false);
                        
                
                
                
               
        
    }
    public static void numericOnly(final TextField field) {
        field.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(
                    ObservableValue<? extends String> observable,
                    String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    field.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }

//        try {
//            Node aux = course.getNode(1);
//            int x = Integer.parseInt(this.tfNuevoID.getText());
//
//            while (aux != null) {
//                if (!util.Utility.equals(aux.data, x)) {
//                    aux.data = new Course("","",x, Integer.parseInt(tfCambiarNombre.getText()));
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Ventana de dialogo");
//                    alert.setHeaderText("Informacion");
//                    alert.setContentText("Se cambió la carrera");
//                    alert.showAndWait();
//                } else {
//                    aux.data = new Course(x, Integer.parseInt(tfCambiarNombre.getText());
//                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Ventana de dialogo");
//                    alert.setHeaderText("Informacion");
//                    alert.setContentText("Se cambió la carrera");
//                    alert.showAndWait();
//
//                }
//                aux = aux.next;
//            }
//            this.btnBuscarCurso.setVisible(true);
//            this.tfCursoID.setVisible(true);
//            this.txCursoID.setVisible(true);
//            this.btnCambiarCurso1.setVisible(false);
//            this.tfCambiarNombre.setVisible(false);
//            this.txNuevoID.setVisible(false);
//            this.txCambiarNombre.setVisible(false);
//            this.tfNuevoID.setVisible(false);
//            this.tfCambiarNombre.setText("");
//            this.tfNuevoID.setText("");
//
//        } catch (ListException ex) {
//            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
//        }
}
