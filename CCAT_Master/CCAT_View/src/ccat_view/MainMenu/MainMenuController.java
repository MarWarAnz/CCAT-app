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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
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
    @FXML
    private VBox partAScroller;
    @FXML
    private VBox partBScroller;
    @FXML
    private VBox partCScroller;
    @FXML
    private Tab partA;
    @FXML
    private Tab partB;
    @FXML
    private Tab partC;
    
    private FileLoader template;
    private TabPane tabPane;
    private List<Tab> tabs;
    private List<VBox> scrollers;
    private Map<ToggleGroup, String> answers;

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
        stage.getIcons().add(new Image("/medicalIcon.png"));
        stage.show();
    }

    @FXML
    private void onAbout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("About.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setTitle("CCAT");
        stage.setScene(scene);
        stage.getIcons().add(new Image("/medicalIcon.png"));
        stage.show();
    }
    
    
    //TODO: make partB tab also scrollable
    
    @FXML
    private void populateTabs(){
        
        Map<String, Map<String, List<String>>> content = template.getContent();
        answers = new HashMap<>();
        //template.traverseMap();
        template.getHeaders();
        int i = 0;
        for (String header : content.keySet()){
            
            //tabPane.getSelectionModel().select(tabs.get(i));
            
            for (String subheader : content.get(header).keySet()){
                
                FlowPane sectionBox = new FlowPane(); 
                sectionBox.setStyle("-fx-background-color: #336699");
                Label subHeaderLabel = new Label(subheader);
                subHeaderLabel.setTextFill(Color.web("#FFFFFF"));
                subHeaderLabel.setFont(Font.font("Verdana", 15));
                sectionBox.getChildren().add(subHeaderLabel);
                scrollers.get(i).getChildren().add(sectionBox);
                
                List<String> list = content.get(header).get(subheader);
                
                
                for (String question : list) {
                    Label label = new Label(question);
                    label.setPrefWidth(450.0);
                    ToggleGroup group = new ToggleGroup();
                    RadioButton yes = new RadioButton("yes");
                    RadioButton no = new RadioButton("no");
                    RadioButton na = new RadioButton("n/a");
                    yes.setPrefWidth(40.0);
                    no.setPrefWidth(40.0);
                    na.setPrefWidth(40.0);
                    yes.setToggleGroup(group);
                    no.setToggleGroup(group);
                    na.setToggleGroup(group);
                    TextArea area = new TextArea();
                    area.setPrefSize(0.0, 0.0);
                    area.setVisible(false);
                    
                    no.setOnAction((ActionEvent event) -> {
                        //area.setMaxSize(600.0, 10.0);
                        //area.resize(600.0, 10.0);
                        area.setPrefSize(600.0, 10.0);
                        area.setVisible(true);
                    });
                    na.setOnAction((ActionEvent event) -> {
                        //area.setMaxSize(600.0, 10.0);
                        //area.resize(600.0, 10.0);
                        area.setPrefSize(600.0, 10.0);
                        area.setVisible(true);
                    });
                    yes.setOnAction((ActionEvent event) -> {
                        //area.setMaxSize(0.0, 0.0);
                        //area.resize(0.0, 0.0);
                        area.setVisible(false);
                    });
                    
                    answers.put(group, question);
                    //TODO:  add ToggleGroup to a list so input can be accessed later
                    FlowPane flow = new FlowPane();
                    flow.setVgap(10.0);
                    flow.setHgap(10.0);
                    flow.setPrefWrapLength(600.0);
                    flow.getChildren().addAll(label, yes, no, na, area);
                    flow.setStyle("-fx-border-style: solid;"
                                    + "-fx-border-width: 0.25;"
                                    + "-fx-border-color: black");
         
//                    FlowPane flow2 = new FlowPane();
//                    flow2.setVgap(10.0);
//                    flow2.setHgap(10.0);
//                    flow2.setMaxSize(0.0, 0.0);
//                    flow2.getChildren().add(area);

                    scrollers.get(i).getChildren().addAll(flow);
                }
            }
            i++;
        }
    }
    
    @FXML
    public void saveFile(){
        
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
        tabs = new ArrayList<>();
        scrollers.add(partAScroller);
        scrollers.add(partBScroller);
        scrollers.add(partCScroller);
        tabs.add(partA);
        tabs.add(partB);
        tabs.add(partC);
        template.loadTemplate();
//        for (VBox box : scrollers){
//            box.setStyle("-fx-border-style: solid;"
//                + "-fx-border-width: 1;"
//                + "-fx-border-color: black");
//        }
        
        populateTabs();
        //tabPane.getSelectionModel().select(partA);
    }

}
