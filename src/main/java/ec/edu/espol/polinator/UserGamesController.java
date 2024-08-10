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
import javafx.scene.layout.BorderPane;
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
    @FXML
    private Label label;
    @FXML
    private Label lab;
    private VBox gamesContainer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gamesContainer = new VBox(10); // VBox con 10px de espacio entre los botones
        gamesContainer.setStyle("-fx-padding: 20; -fx-alignment: center;"); // Centramos los botones
        BorderPane.setCenter(gamesContainer); // Colocar el VBox en el centro del BorderPane
        displayGames();
        // TODO
        
    System.out.println(GameSet.getInstance().getGames().size());        
    }    
    
     private void displayGames() {
        gamesContainer.getChildren().clear(); // Limpiar contenedor de juegos

        for (Game game : GameSet.getInstance().getGames()) {
            Button gameButton = new Button(game.getName());
            gameButton.setOnAction(e -> startGame(game));
            gamesContainer.getChildren().add(gameButton);
        }
    }
     
       private void startGame(Game game) {
        // Lógica para iniciar el juego con las preguntas y respuestas específicas
        System.out.println("Iniciando: " + game.getName());
        // Aquí se cargarían los archivos de preguntas y respuestas y se pasaría la información al controlador del juego
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
}
