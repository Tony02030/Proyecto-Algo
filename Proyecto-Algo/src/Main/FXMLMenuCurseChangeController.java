/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author 31670
 */
public class FXMLMenuCurseChangeController implements Initializable {
     private CircularDoublyLinkedList course = util.Utility.getCourses();

    private TextField tfCambiarCurso;
    @FXML
    private Button btnBuscarCurso;
    @FXML
    private Button btnCambiarCurso1;
    @FXML
    private Text txCursoNoAgregado;
    @FXML
    private Text txNuevoID;
    @FXML
    private TextField tfNuevoID;
    @FXML
    private Text txCursoID;
    @FXML
    private TextField tfCursoID;
    @FXML
    private Text txCambiarNombre;
    @FXML
    private TextField tfCambiarNombre;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        numericOnly(tfNuevoID);
        numericOnly(tfCursoID);
        // TODO
    }    

    @FXML
    private void btnBuscarCurso(ActionEvent event) {
       int x = Integer.parseInt(tfCursoID.getText());
        try {
            if (course.contains1(x)) {
                this.btnBuscarCurso.setVisible(false);
                this.tfCursoID.setVisible(false);
                this.txCursoID.setVisible(false);
                this.btnCambiarCurso1.setVisible(true);
                this.tfCambiarNombre.setVisible(true);
                this.txNuevoID.setVisible(true);
                this.txCambiarNombre.setVisible(true);
                this.tfNuevoID.setVisible(true);
                this.tfCursoID.setText("");
                this.txCursoNoAgregado.setVisible(false);
            }
        } catch (ListException ex) {
            this.txCursoNoAgregado.setVisible(true);
        }
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
    @FXML
    private void btnCambiarCurso1(ActionEvent event) {
        
        try {
            Node aux = course.getNode(1);
            int x = Integer.parseInt(this.tfNuevoID.getText());

            while (aux != null) {
                if (!util.Utility.equals(aux.data, x)) {
                    aux.data = new Course("","",x, this.tfCambiarNombre.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ventana de dialogo");
                    alert.setHeaderText("Informacion");
                    alert.setContentText("Se cambió la carrera");
                    alert.showAndWait();
                } else {
                    aux.data = new Course(x, this.tfCambiarNombre.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Ventana de dialogo");
                    alert.setHeaderText("Informacion");
                    alert.setContentText("Se cambió la carrera");
                    alert.showAndWait();

                }
                aux = aux.next;
            }
            this.btnBuscarCurso.setVisible(true);
            this.tfCursoID.setVisible(true);
            this.txCursoID.setVisible(true);
            this.btnCambiarCurso1.setVisible(false);
            this.tfCambiarNombre.setVisible(false);
            this.txNuevoID.setVisible(false);
            this.txCambiarNombre.setVisible(false);
            this.tfNuevoID.setVisible(false);
            this.tfCambiarNombre.setText("");
            this.tfNuevoID.setText("");

        } catch (ListException ex) {
            Logger.getLogger(FXMLMenuCarrersChangeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
