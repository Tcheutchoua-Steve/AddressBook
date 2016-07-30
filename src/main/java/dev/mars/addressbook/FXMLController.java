package dev.mars.addressbook;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;

public class FXMLController implements Initializable {
    
    private Label label;
    @FXML
    private MenuItem Open;
    @FXML
    private MenuItem exit;
    @FXML
    private MenuItem numberOfMales;
    @FXML
    private MenuItem oldestPerson;
    @FXML
    private MenuItem about;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleOpen(ActionEvent event) {
    }

    @FXML
    private void handleExit(ActionEvent event) {
    }

    @FXML
    private void showNumberOfMales(ActionEvent event) {
    }

    @FXML
    private void showOldestPerson(ActionEvent event) {
    }

    @FXML
    private void handleAbout(ActionEvent event) {
    }
}
