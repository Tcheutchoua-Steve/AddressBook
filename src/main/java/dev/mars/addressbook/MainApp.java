package dev.mars.addressbook;

import dev.mars.addressbook.model.Contact;
import dev.mars.addressbook.util.ContactParser;
import dev.mars.addressbook.util.GenderFormatException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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

    private Stage primaryStage;
    
    private BorderPane borderPane ; 
    private Parent root ; 
    
    public MainApp(){
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("AddressBook");
        // Load the fxml root layout 
        
        try {
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/fxml/MainAppView.fxml"));
            borderPane = (BorderPane) loader.load();
            
            Scene scene = new Scene(borderPane);
            //scene.getStylesheets().add("/styles/Styles.css");
           stage.setScene(scene);
        //Git controller access to the Main application class 
        FXMLController controller = loader.getController();
        controller.setMainApp(this);
        stage.show();
        } catch (IOException ex) {
            System.out.println("Got an exception");
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
      
        
        /*try {
            System.out.println("Trying to load contacts ");
            loadParsedContacts();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
    }
    
    private static ObservableList<Contact> allContacts = FXCollections.observableArrayList(); 
        
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

    public ObservableList<Contact> getAllContacts() {
        return allContacts;
    }  
    
    
    public void loadParsedContacts() throws IOException{
        ClassLoader classLoader = getClass().getClassLoader();
        File fi = new File(classLoader.getResource("src/main/resources/AddressBook.csv").getFile());
        try {
            allContacts.addAll(ContactParser.parseCSV(fi));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        } catch (GenderFormatException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Initializing main app");
                
        try {
            System.out.println("Trying to load contacts ");
            loadParsedContacts();
        } catch (IOException ex) {
            Logger.getLogger(MainApp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
