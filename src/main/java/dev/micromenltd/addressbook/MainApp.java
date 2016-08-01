package dev.micromenltd.addressbook;

import dev.micromenltd.addressbook.controller.AddressController;
import dev.micromenltd.addressbook.model.Contact;
import dev.micromenltd.addressbook.util.ContactParser;
import dev.micromenltd.addressbook.util.GenderFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MainApp extends Application  implements Initializable{

    private Stage stage;
    
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList(); 

    private static ObservableList<String> namesOfContact = FXCollections.observableArrayList();
    private BorderPane borderPane ; 
    private Parent root ; 
    AddressController controller ; 
    public MainApp(){
    }
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage = primaryStage ;
        stage.setTitle("AddressBook");
        // Load the fxml root layout 
        
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/MainAppView.fxml"));
            borderPane = (BorderPane) loader.load();
            
            Scene scene = new Scene(borderPane);
            //scene.getStylesheets().add("/styles/Styles.css");
            
            loadParsedContacts();
           stage.setScene(scene);
        //Git controller access to the Main application class 
        controller = loader.getController();
        controller.setMainApp(this);
        stage.show();
        } catch (IOException ex) {
            System.out.println("Got an exception");
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    public Stage getPrimaryStage(){
        return this.stage;
    }
       
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    // return all the conctacts that are loaded
    public ObservableList<Contact> getAllContacts() {
        return allContacts;
    }  
    
    // return all the names of the loaded contacts
    public ObservableList<String> getAllNames(){
        namesOfContact.clear();
        for(Contact ctc : allContacts){
            namesOfContact.add(ctc.getContactName());
        }
        return namesOfContact;
    }
    
    
    // Get all contacts from the defined csv file 
    public void loadParsedContacts() throws IOException{
        ClassLoader classLoader = getClass().getClassLoader();
        File fi = new File(MainApp.class.getResource("/csv/AddressBook.csv").getFile());
        try {
            allContacts.addAll(ContactParser.parseCSV(fi));
            
        } catch (FileNotFoundException ex) {
            //System.out.println("an error occured");
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GenderFormatException ex) {
            //System.out.println("an error occured");
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {   
        try {
            loadParsedContacts();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void updateTable(List<Contact> newContacts){
        ObservableList<Contact> tempList =  FXCollections.observableArrayList(newContacts);
        if((allContacts.size() <= 0 ) && (newContacts.size() >= 0 )){
            this.allContacts.removeAll(allContacts);
            //allContacts.setItem(null);
             
            allContacts.addAll(newContacts);
            //this.allContacts = new FXCollections.observableArrayList(newContacts);
            //FXCollections.copy(this.allContacts, tempList);
            
           controller.setMainApp(this);
           
        }
        
    }
}
