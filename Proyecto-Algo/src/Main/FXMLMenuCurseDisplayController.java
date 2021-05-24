/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import domain.Career;
import domain.CircularDoublyLinkedList;
import domain.Course;
import domain.ListException;
import domain.Node;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author 31670
 */
public class FXMLMenuCurseDisplayController implements Initializable {
    private CircularDoublyLinkedList course = util.Utility.getCourses();
    @FXML
    private TableColumn<Career, String> colCarrers;
     ObservableList<Career> carrers = FXCollections.observableArrayList();

    @FXML
    private TableView<?> tableCurse;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          Node aux;
//        try {
//            aux = course.getNode(1);
//            int x=0;
//            while (aux != null) {
//                Course temp=(Course)aux.data;
//                courses.add(x, temp);
//                aux=aux.next;
//                x=x+1;
//            }
//        } catch (ListException ex) {
//            Logger.getLogger(FXMLMenuCareersDisplayController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        colCarrers.setCellValueFactory(new PropertyValueFactory("description"));
//        tableCourses.setItems(courses);
        

            
    }    
        // TODO
    }    
    

