/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.polinator;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leoza
 */
public class UserGamesController implements Initializable {

    @FXML
    private BorderPane BorderPane;
    private VBox gamesContainer;
    
    private int questions;
    
    private Node<String> root;
    @FXML
    private HBox Htop;
    @FXML
    private VBox hleft;
    @FXML
    private HBox Hbuttom;
    @FXML
    private VBox Vcenter;
    @FXML
    private VBox hright;
    
    public Round round;
    
    public int numQuestions;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.round = App.getRound();
        
        
        gamesContainer = new VBox(10); // VBox con 10px de espacio entre los botones
        gamesContainer.setStyle("-fx-padding: 20; -fx-alignment: center;"); // Centramos los botones
        BorderPane.setCenter(gamesContainer); // Colocar el VBox en el centro del BorderPane
        displayGames();

        Image image = new Image(getClass().getResourceAsStream("/img/fondo2.jpeg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(680);
        imageView.setFitHeight(480);
        BorderPane.getChildren().add(0, imageView); // Agregar imagen al fondo
       
  
    }   
    
    
     public void setQuestions(int questions) {
        this.questions = questions;
    }
    
    
     private void displayGames() {
         
        int count=0;

        
        gamesContainer.getChildren().clear(); // Limpiar contenedor de juegos

        for (Node game : GameSet.getInstance().getGames()) {
 
            Button gameButton = new Button("Game : "+(count+=1));
            gameButton.setStyle(
            "-fx-background-color:  #d07f19;" +   // Color de fondo 
            "-fx-text-fill: white;" +            // Color del texto 
            "-fx-font-family: 'Ravie';" +        // Tipo de letra
            "-fx-font-size: 14px;" +             // Tamaño de la letra
            "-fx-border-color: #854034;" +       // Color del borde 
            "-fx-border-width: 2px;"  +           // Ancho del borde
            "-fx-border-radius: 10px;" +         // Curvatura del borde
            "-fx-background-radius: 3px;" +     // Curvatura del fondo (coincide con el borde)
            "-fx-padding: 20px 41px;"            // Tamaño del botón (padding)
        );
            gameButton.setOnAction(e -> {
               
                round.setIsFinished(false);
                String subject = gameButton.getText();
                round.setSubject(subject);
                round.tree=new TreeNodeDecision(game);
                Abrir("Options");
                Button b = (Button) e.getSource();
                Stage s = (Stage) b.getScene().getWindow();
                s.close();
            });
            gamesContainer.getChildren().add(gameButton);
        }
    }
    
     
    
    private void startGame(Node game) throws IOException {
        

        round.setIsFinished(false);
       
       round.tree=new TreeNodeDecision(game);
        
    
        
        
    }
    
       
       

    public void Abrir(String ruta){

          try {
           FXMLLoader fxml = App.loadFXML(ruta);
           Scene sc = new Scene(fxml.load(), 680, 480);
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
        Abrir("lobby");
        Button b = (Button) event.getSource();
        Stage s = (Stage) b.getScene().getWindow();
        s.close();
    }

  
}
