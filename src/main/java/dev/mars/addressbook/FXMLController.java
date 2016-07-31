package dev.mars.addressbook;

import dev.mars.addressbook.model.Contact;
import dev.mars.addressbook.util.ContactGender;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//import javafx.scene.control.Dialogs;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
    private Text infoDisplayArea;
    @FXML
    private ComboBox<String> firstComboBox;
    @FXML
    private ComboBox<String> secondComboBox;
    @FXML
    private Button compareDays;
    
    
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
      public void showContact(Contact ctct){
    
    }
      
     public void setMainApp(MainApp mainAp){
         this.mainApp = mainAp ;
         PersonDetail.setItems(mainApp.getAllContacts()); 
         firstComboBox.setItems(mainApp.getAllNames());
         secondComboBox.setItems(mainApp.getAllNames());
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
        
        // get contacts from the currently loaded contacts
        List<Contact> contacts = mainApp.getAllContacts();
        
        // parse through the loaded contacts and find the total number of males
        int numberOfMales = 0 ;
        for(Contact ctc : contacts){
            if(ctc.getContactGenger().equals(ContactGender.MALE))
                numberOfMales ++;
        }
        
        infoDisplayArea.setText("Number of males are " + numberOfMales);
    }

    @FXML
    private void showOldestPerson(ActionEvent event) {
        // The oldest person is the one whose dob is the earliest 
        List<Contact> contacts = mainApp.getAllContacts();
        
        Contact oldest  ; 
        // assumes that the oldest contact is the first 
        oldest = contacts.get(0);
        
        // parse through all the contacts to find the oldest contact 
        for(Contact ctc : contacts){
            
            // update oldest contact if it is not the first contact 
            if((oldest.getContactDobInCalendar()).compareTo(ctc.getContactDobInCalendar()) > 0 ){
                oldest = ctc ;
            }
        }
        
        infoDisplayArea.setText("The  Oldest   Contact   is  " + oldest.getContactName() + "  Born   on   the   " + oldest.getContactDob());
    }

    @FXML
    private void handleAbout(ActionEvent event) {
          //      Dialogs.showInformationDialog(mainApp.getPrimaryStage(), "Author: Tcheutchoua Steve", "AddressApp", "About");

    }

    @FXML
    private void handleDaysComparison(ActionEvent event) {
        int firstbox =  firstComboBox.getSelectionModel().getSelectedIndex() ;
        int secondbox = secondComboBox.getSelectionModel().getSelectedIndex() ; 
        long dayDifference = 0 ;
        if(( firstbox >= 0 ) && (secondbox >= 0)){
            System.out.println("Item has been selected");
            long endTime = mainApp.getAllContacts().get(firstbox).getContactDobInCalendar().getTimeInMillis();
            long startTime = mainApp.getAllContacts().get(secondbox).getContactDobInCalendar().getTimeInMillis();
            dayDifference = TimeUnit.MILLISECONDS.toDays((startTime) - (endTime) );
            
            
            infoDisplayArea.setText("The age difference is  " + dayDifference + " days ");
        }
        else {
            infoDisplayArea.setText("Please choose names from the two dropdow menu beside ");
            infoDisplayArea.setFill(Color.RED);
        }
        
        
    }
}
