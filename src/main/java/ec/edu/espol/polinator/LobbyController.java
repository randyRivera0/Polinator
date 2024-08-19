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
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leoza
 */
public class LobbyController implements Initializable {

    @FXML
    private TextField textFieldNumQuestions;
     
        private Node<String> root;
    @FXML
    private VBox vpaneGIF;
    @FXML
    private VBox vpaneOptions;
    @FXML
    private HBox hpaneTop;
    @FXML
    private HBox hpaneBajo;
    @FXML
    private Text titulo;
    @FXML
    private HBox hpaneLabel;
    
    public Round round;
    @FXML
    private Button ButtonSubmit;
    @FXML
    private Button ButtonSitcoms;
    @FXML
    private Button ButtonAnimals;
    @FXML
    private Button buttonSuperHeroes;
    @FXML
    private BorderPane bp;

 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        try {
            
            this.round = App.getRound();
            
            Image image = new Image(getClass().getResourceAsStream("/img/principal.jpeg"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(680);
            imageView.setFitHeight(480);
            bp.getChildren().add(0, imageView); // Agregar imagen al fondo

        
        } 
        
        catch (NullPointerException ex) {
            ex.printStackTrace();
        }
        
        HiloFlashHBox hilo = new HiloFlashHBox(hpaneLabel);
        hilo.setDaemon(true);
        hilo.start();
        //System.out.println(GameSet.getInstance().getGames().size());
    }    

    @FXML
    private void MyGames(ActionEvent event) {
        String input = textFieldNumQuestions.getText();
        int numQuestions;
         try {
             
             numQuestions = Integer.parseInt(input);
            if (numQuestions <= 0) {
                showAlert("El número de preguntas debe ser mayor a 0");
                return;
            }
            try {   
               round.setNumQuestions(numQuestions); 
               FXMLLoader fxml = App.loadFXML("UserGames");
               Scene sc = new Scene(fxml.load(), 680, 480);
               Stage st = new Stage();
               UserGamesController controller = fxml.getController();
              controller.setQuestions(numQuestions);
               st.setScene(sc);
               st.show();
           } catch (IOException ex) {
               Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el fxml");
               a.show();
           }
          
            Button b = (Button) event.getSource();
            Stage s = (Stage) b.getScene().getWindow();
            s.close();
        } catch (NumberFormatException e) {
            // Si la conversión falla, mostrar un mensaje de error
            showAlert("Por favor, ingrese un número válido.");
        }
     
        
   
        
    }

    @FXML
    private void submit(ActionEvent event) {
        
        Abrir("Usertxt");
                Button b = (Button) event.getSource();
        Stage s = (Stage) b.getScene().getWindow();
        s.close();
    }
 
    
    class HiloFlashHBox extends Thread {
        private HBox hbox;

        HiloFlashHBox(HBox hbox) {
            this.hbox = hbox;
        }

        @Override
        public void run() {
            boolean visible = true;

            while (true) {
                visible = !visible;
                boolean finalVisible = visible;
                Platform.runLater(() -> {
                    hbox.setVisible(finalVisible);
                });
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
        
    }
    

    @FXML
    private void playRound(ActionEvent event) {
        
        round.setIsFinished(false);
        int numQuestions = 0;
        
        try{
            numQuestions = Integer.parseInt(textFieldNumQuestions.getText());
            if (numQuestions <= 0 ) {
                showAlert("Por favor, ingrese un numero mayor a 0 ");
                return;
                //throw new InvalidNumQuestionsException("El número de preguntas debe ser mayor a 0");
                
            }
        }
      
        catch(NumberFormatException numberFormatException){
            showAlert("Por favor, ingrese un numero valido");
            return;
        }
        
        round.setNumQuestions(numQuestions);
        Button b = (Button) event.getSource();
        String subject = b.getText();
        //System.out.println(Paths.get("questions" + subject + ".txt"));
        round.setSubject(subject);
        round.changeQuestions();
        
   
        Abrir("Options");
        
        Stage s = (Stage) b.getScene().getWindow();
        s.close();
        
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
 
}
