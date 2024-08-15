/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.polinator;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leoza
 */
public class OptionsController implements Initializable {
    
    public Round round;
    
    @FXML
    private Label labelOptions;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonPrevScene;
    @FXML
    private TextArea textAreaOptions;
    @FXML
    private Label labelCentral;
       
    @FXML
    private HBox hpaneTop;
    @FXML
    private VBox vpaneLeft;
    @FXML
    private HBox hpaneBottom;
    @FXML
    private VBox Central;
    @FXML
    private VBox vapenRight;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.round = App.getRound();
        
        setQuestionsText();
        
        hpaneBottom.setStyle("-fx-background-color: #FAEDCE;"); 
        hpaneTop.setStyle("-fx-background-color: #FAEDCE;");  // Esto aplica un color de fondo azul claro
        vpaneLeft.setStyle("-fx-background-color: #FAEDCE;");
        vapenRight.setStyle("-fx-background-color: #FAEDCE;");
        Central.setStyle("-fx-background-color:#FEFAE0 ;");
            
    }
    
    
    public void setQuestionsText() {
              
        String text = round.getStringQuestions();
        textAreaOptions.setText(text);
              
    }
     
    @FXML
    public void AbrirVentana(ActionEvent event) {
       try {
            App.setRoot("round");
        } 
        catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el fxml");
            a.show();
        }
    }


    @FXML
    private void prevScene(ActionEvent event) {
         try {
            App.setRoot("lobby");
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo abrir el fxml");
            a.show();
        }
        
        
    }
    
    
    @FXML
    private void uploadQuestions(ActionEvent event) {
      
        try {
           FXMLLoader fxml = App.loadFXML("Usertxt");
           Scene sc = new Scene(fxml.load(), 850, 600);
           Stage st = new Stage();

           st.setScene(sc);
           st.show();
       } catch (IOException ex) {
           Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el fxml");
           a.show();
       }
        Button b = (Button) event.getSource();
        Stage s = (Stage) b.getScene().getWindow();
        s.close();
     
    }
     
}
