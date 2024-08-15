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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author PERSONAL
 */
public class StartController implements Initializable {
    Button buttton;
    Round round;

    @FXML
    private Pane pane;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Agregar la imagen programáticamente
        Image image = new Image(getClass().getResourceAsStream("/img/inicio.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(680);
        imageView.setFitHeight(480);
        pane.getChildren().add(0, imageView); // Agregar imagen al fondo

    }    
    
    public void abrirPrimary(ActionEvent event) {
        try{
            App.setRoot("lobby");
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
  
}
