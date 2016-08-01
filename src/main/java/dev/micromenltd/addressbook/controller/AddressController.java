package dev.micromenltd.addressbook.controller;

import com.sun.javafx.scene.control.skin.TableViewSkinBase;
import dev.micromenltd.addressbook.MainApp;
import dev.micromenltd.addressbook.model.Contact;
import dev.micromenltd.addressbook.util.ContactGender;
import dev.micromenltd.addressbook.util.ContactParser;
import dev.micromenltd.addressbook.util.GenderFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

public class AddressController implements Initializable {
    
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
    private TableView<Contact> personDetail;
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
    
    
    
      public void showContact(Contact ctct){
    
    }
      
     public void setMainApp(MainApp mainAp ){
         this.mainApp = mainAp ;
         
        personDetail.setItems(mainApp.getAllContacts()); 
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
        personDetail.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        
        // Listen for selection changes
        personDetail.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contact>(){

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
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "CSV files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            try {
                ObservableList<Contact> contacts = FXCollections.observableArrayList(ContactParser.parseCSV(file));
                //refrestTable(mainApp, (ObservableList<Contact>) contacts);
                mainApp.updateTable(contacts);
            } catch (IOException ex) {
                Logger.getLogger(AddressController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (GenderFormatException ex) {
                Logger.getLogger(AddressController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void handleExit(ActionEvent event) {
        // Close application
        System.exit(0);
    }

    @FXML
    private void showNumberOfMales(ActionEvent event) {
        
        // get contacts from the currently loaded contacts
        List<Contact> contacts = mainApp.getAllContacts();
        
        if(contacts.size() != 0){
            // parse through the loaded contacts and find the total number of males
            int numberOfMales = 0 ;
            for(Contact ctc : contacts){
                if(ctc.getContactGenger().equals(ContactGender.MALE))
                    numberOfMales ++;
            }

            infoDisplayArea.setText("Number of males are " + numberOfMales);
        } else{
           noContactMessage("Please Load some data before proceeding");
        }
    }

    @FXML
    private void showOldestPerson(ActionEvent event) {
        
        if(mainApp.getAllContacts().size() >= 0){
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
        else 
        {
            noContactMessage("Please Load some data before proceeding");
        }
    }

    @FXML
    private void handleAbout(ActionEvent event) {
        //Dialogs.showInformationDialog(mainApp.getPrimaryStage(), "Author: Gil", "AddressApp", "About");
        Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("About");
	alert.setHeaderText("About Address App");
	alert.setContentText("Author: Gil \nApplication Created to perform some simple tasks on a csv file");
	alert.showAndWait();

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
            //infoDisplayArea.setText("Please choose names from the two dropdow menu beside ");
            //infoDisplayArea.setFill(Color.RED);
            noContactMessage("Please choose names from the two dropdow menu ");
        }
    }
    
    // Generalized notification message for application
    private void noContactMessage(String message){
        Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("No Data Found");
            alert.setContentText(message);
            alert.showAndWait();
    }
    
    /*public void refrestTable(MainApp mainApp , ObservableList<Contact> newList )
        
        infoDisplayArea.setText("all Contact size is  " + mainApp.getAllContacts().size());
         personDetail.getItems().clear();
         personDetail.getProperties().put(TableViewSkinBase.RECREATE, Boolean.TRUE);
         personDetail.setItems(newList);
         firstComboBox.getItems().clear();
         firstComboBox.getItems().addAll(getAllNames(newList));
         secondComboBox.getItems().clear();
         secondComboBox.getItems().addAll(getAllNames(newList));
         
         infoDisplayArea.setText("all Contact size is  " + mainApp.getAllContacts().size());
         
    }
    
    public ObservableList<String> getAllNames(ObservableList<Contact> contacts){
        ObservableList<String> names = null;
        for(Contact ctc : contacts){
            names.add(ctc.getContactName());
        }
        return names;
    }
*/
}
