/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ccat_view.MainMenu;

import ccat_model.Account;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Elliott
 */
public class VerifyAdminController implements Initializable {
    // TMP
    @FXML
    private Stage parentStage;
    //
    
    @FXML
    private TextField username;
    
    @FXML
    private PasswordField pass;

    @FXML
    private Button back;
    
    @FXML
    private Label errMsg;
    
    private Account account;

    @FXML
    private void onBack(ActionEvent event) {
        Stage stage = (Stage) back.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onLogin(ActionEvent event) throws FileNotFoundException {
        if (account.validate(username.getText(), pass.getText())){
            //this.parentStage
        }else{
            username.clear();
            pass.clear();
            errMsg.setText("Username or Password incorrect");
        }
    }
    
    // TMP
    public void setStage(Stage parentStage){
        this.parentStage = parentStage;
    }
    //
    
    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            account = new Account();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(VerifyAdminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
