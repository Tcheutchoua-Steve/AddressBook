package dev.mars.addressbook;

import dev.mars.addressbook.model.Contact;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLController implements Initializable {
    
    private MainApp mainApp ; 
    
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
    @FXML
    private TableView<Contact> PersonDetail;
    @FXML
    private TableColumn<Contact, String> personNameColumn;
    @FXML
    private TableColumn<Contact, String> personGenderColumn;
    @FXML
    private TableColumn<Contact, String> personDobColumn;
    @FXML
    private TextArea messageArea;
    
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
      public void showContact(Contact ctct){
    
    }
      
     public void setMainApp(MainApp mainAp){
         this.mainApp = mainAp ;
         PersonDetail.setItems(mainApp.getAllContacts()); 
     }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         // Initialize the Contact table
        personNameColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("contactName"));
        personGenderColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("contactGenger"));
        personDobColumn.setCellValueFactory(new PropertyValueFactory<Contact, String>("contactDob"));
        
        // Auto resize columns
        PersonDetail.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        // Listen for selection changes
        PersonDetail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>(){

            @Override
            public void changed(ObservableValue<? extends Contact> ov, Contact t, Contact t1) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                showContact(t1);
            }
            
        });
        
       //PersonDetail.setItems(mainApp.getAllContacts());
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
