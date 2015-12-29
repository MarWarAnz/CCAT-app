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

import javafx.stage.Stage;
import ccat_model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;

import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import javafx.scene.layout.HBox;
import javafx.scene.layout.HBoxBuilder;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

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
    private VBox partAScroller;
    @FXML
    private VBox partBScroller;
    @FXML
    private VBox partCScroller;
    
    private List<VBox> scrollers;
    
    @FXML
    private Map<String, HBox> accessor;

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
        stage.setTitle("CCAT - Login");
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
    
    
    //TODO: make partB tab also scrollable
    
    @FXML
    private void populateTabs(){
        
        Map<String, Map<String, List<String>>> content = template.getContent();
        //template.traverseMap();
        template.getHeaders();
        int i = 0;
        for (String header : content.keySet()){
            
            for (String subheader : content.get(header).keySet()){
                
                HBox sectionBox = new HBox();   
                sectionBox.setStyle("-fx-background-color: #336699");
                Label subHeaderLabel = new Label(subheader);
                subHeaderLabel.setTextFill(Color.web("#FFFFFF"));
                subHeaderLabel.setFont(Font.font("Verdana", 15));
                sectionBox.getChildren().add(subHeaderLabel);
                scrollers.get(i).getChildren().add(sectionBox);
                List<String> list = content.get(header).get(subheader);
                
                for (String list1 : list) {
                    Label label = new Label(list1);
                    label.setPrefWidth(305.0);
                    ToggleGroup group = new ToggleGroup();
                    RadioButton yes = new RadioButton("yes");
                    RadioButton no = new RadioButton("no");
                    RadioButton na = new RadioButton("n/a");
                    yes.setPrefWidth(55.0);
                    no.setPrefWidth(55.0);
                    na.setPrefWidth(55.0);
                    yes.setToggleGroup(group);
                    no.setToggleGroup(group);
                    na.setToggleGroup(group);
                    //TODO:  add ToggleGroup to a list so input can be accessed later
                    HBox box;
                    box = HBoxBuilder.create()
                            .spacing(60.0)
                            .padding(new Insets(5, 5, 5, 5))
                            .children(label, yes, no, na)
                            .build();
                    box.borderProperty();
                    scrollers.get(i).getChildren().add(box);
                }
            }
            i++;
        }
    }
    
    //TODO: add onClickListeners to radioButton groups to update score in real time
    //      as well as check for errors in case someone tries to submit an incomplete 
    //      form
    
    

    //TODO: add tabs dynamically to make the populateTabs function run more smoothly 
    //      and be more loosely coupled
    
    //TODO: error logging and reporting option
            
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
        scrollers = new ArrayList<>();
        scrollers.add(partAScroller);
        scrollers.add(partBScroller);
        scrollers.add(partCScroller);
        template.loadTemplate();
        populateTabs();
    }

}
