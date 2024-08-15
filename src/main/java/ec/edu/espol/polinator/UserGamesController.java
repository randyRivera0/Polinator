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
    
    private int questions;
    
    private Node<String> root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gamesContainer = new VBox(10); // VBox con 10px de espacio entre los botones
        gamesContainer.setStyle("-fx-padding: 20; -fx-alignment: center;"); // Centramos los botones
        BorderPane.setCenter(gamesContainer); // Colocar el VBox en el centro del BorderPane
        // displayGames();
        // TODO
        
    // System.out.println(GameSet.getInstance().getGames().size());
    System.out.println(questions);
    }   
    
    
     public void setQuestions(int questions) {
        this.questions = questions;
    }
    
    /*
     private void displayGames() {
        gamesContainer.getChildren().clear(); // Limpiar contenedor de juegos

        for (Round game : GameSet.getInstance().getGames()) {
            Button gameButton = new Button(game.getSubject());
            gameButton.setOnAction(e -> {
                try {
                    startGame(game);
                    Button b = (Button) e.getSource();
                    Stage s = (Stage) b.getScene().getWindow();
                    s.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            gamesContainer.getChildren().add(gameButton);
        }
    }
    */
     
     /*
       private void startGame(Round game) throws IOException {
        
        System.out.println("Iniciando: " + game.getSubject());
        System.out.println(game.getPreguntasFile().getName());
        List<String> s = Utility.loadanswers(game.getRespuestasFile().getAbsolutePath());
        System.out.println(s);
        
        Init(game.getPreguntasFile().getCanonicalPath() , game.getRespuestasFile().getAbsolutePath() , questions  );
    
        
        
    }
    */
       
       

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

   
     public void OpenWindow(Node<String> root, int numQuestions, List<String> answ){

            try {
           FXMLLoader fxml = App.loadFXML("Options");
           Scene sc = new Scene(fxml.load(), 850, 600);
           Stage st = new Stage();
           OptionsController controller = fxml.getController();
           controller.setQuestionsText(); // Pasar la referencia del Stage al controlador del juego
           st.setScene(sc);
           st.show();
       } catch (IOException ex) {
           Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el fxml");
           a.show();
       }
    }
     

   private void Init(String preg, String resp, int numQuestions){
   
    //List<String> answ=Utility.loadanswers(option+".txt");
    List<String> answ=Utility.loadanswers(resp);
    root = cargarArchivoPreguntas(preg);
    cargarArchivoRespuestas(root, resp);

    // Pasar la información necesaria al controlador del juego
    OpenWindow(root, numQuestions, answ);
    
    System.out.println(numQuestions);

   
   }
    
      private Node<String> cargarArchivoPreguntas(String category) {
        //Path path = Paths.get(category.toLowerCase() + "_preguntas.txt");
        Path path = Paths.get(category);
        System.out.println(path);
        try {
            List<String> questions = Files.readAllLines(path);
            Node<String> root = new Node<>(questions.get(0));
            for (int i = 1; i < questions.size(); i++) {
                String question = questions.get(i);
                root.addChildrenQuestion(question);
            }
            return root;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
        

    private void cargarArchivoRespuestas(Node<String> root, String category) {
         //Path path = Paths.get(category.toLowerCase() + "_respuestas.txt");
        Path path = Paths.get(category);
        try {
            List<String> questions = Files.readAllLines(path);
            for (String question : questions) {
                root.addChildrenAnswer(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
