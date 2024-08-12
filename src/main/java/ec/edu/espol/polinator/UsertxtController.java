/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.polinator;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leoza
 */
public class UsertxtController implements Initializable {
    
       private File preguntasFile;
    private File respuestasFile;
    private List<GameSet> gameSets = new ArrayList<>();
    private int gameCount = 0;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO
    }    

    @FXML
    private void Crear(ActionEvent event) {
    if (preguntasFile != null && respuestasFile != null) {
               String gameName = "Game " + (GameSet.getInstance().getGames().size() + 1);
               GameSet.getInstance().addGame(preguntasFile, respuestasFile, gameName);

               // Resetear archivos para la siguiente creación de juego
               preguntasFile = null;
               respuestasFile = null;
               Abrir("primary_1");
                 Button b = (Button) event.getSource();
                 Stage s = (Stage) b.getScene().getWindow();
                 s.close();
               
           } else {
               showAlert("Porfavor sube los dos archivos completos.");
           }
    }

    @FXML
    private void Preguntas(ActionEvent event) {
        preguntasFile = openFileChooser(event);
    }

    @FXML
    private void Respuestas(ActionEvent event) {
        respuestasFile = openFileChooser(event);
    }
    
    
    private File openFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Archivos de texto (*.txt)", "*.txt"));
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        return fileChooser.showOpenDialog(stage);
    }

    
    public void Abrir(String ruta){

          try {
           FXMLLoader fxml = App.loadFXML(ruta);
           Scene sc = new Scene(fxml.load(), 850, 600);
           Stage st = new Stage();

           st.setScene(sc);
           st.show();
       } catch (IOException ex) {
           Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el fxml");
           a.show();
       }
    }
    
    @FXML
    private void Return(ActionEvent event) {
        Abrir("primary_1");
                Button b = (Button) event.getSource();
        Stage s = (Stage) b.getScene().getWindow();
        s.close();
        
    }
    
    
        private void showAlert(String message) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Información");
            alert.setHeaderText(null);
            alert.setContentText(message);
            alert.showAndWait();
        }
}
