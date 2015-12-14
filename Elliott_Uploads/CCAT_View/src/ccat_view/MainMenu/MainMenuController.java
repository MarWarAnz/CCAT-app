/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccat_view.MainMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import ccat_model.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * FXML Controller class
 *
 * @author Elliott
 */
public class MainMenuController implements Initializable {
    
    @FXML
    private MenuItem exit;
    
    private FileLoader template;
    
    @FXML
    private ScrollPane partAScroller;
    @FXML
    private ScrollPane partBScroller;
    @FXML
    private ScrollPane partCScroller;

    public MainMenuController(){
        
    }

    @FXML
    private void onLogout(ActionEvent event) {
        System.out.println("Logging out...");
    }

    @FXML
    private void onExit(ActionEvent event) {
        System.exit(1);
    }

    @FXML
    private void onAdminTasks(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("VerifyAdmin.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("CCAT");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onAbout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("About.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("CCAT");
        stage.setScene(scene);
        stage.show();
    }
    
    @FXML
    private void populateTabs(ScrollPane pane){
        
        Map content; //= template.getContent();
        if (pane == partAScroller){
            content = template.getContent().get("Part A: MetaData")
            
        }
    }
            
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.template = new FileLoader(new FileReader("questions.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
