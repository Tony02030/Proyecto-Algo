/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.ListException;
import domain.SingleLinkedList;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Fabio
 */
public class FXMLModifyStudentController implements Initializable {

    SingleLinkedList Student = util.Utility.getStudents();
    @FXML
    private Button btnModify;
    @FXML
    private TextField txfID;
    @FXML
    private Text txtMessage1;
    @FXML
    private Text txtMessage2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        numericOnly(txfID);
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
    private void btnModify(ActionEvent event) throws ListException {

        if (Student.contains1(txfID.getText())) {
            
        } else {

            txtMessage2.setVisible(true);
        }
    }

}
