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
        /*
        Hbuttom.setStyle("-fx-background-color: #CCD5AE;");
        Htop.setStyle("-fx-background-color: #CCD5AE;");
        hleft.setStyle("-fx-background-color: #E0E5B6;");
        
        hright.setStyle("-fx-background-color: #E0E5B6;");
        */
        
        Image image = new Image(getClass().getResourceAsStream("/img/fondo2.jpeg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(680);
        imageView.setFitHeight(480);
        BorderPane.getChildren().add(0, imageView); // Agregar imagen al fondo
        
        /*
        Image image = new Image(getClass().getResourceAsStream("/img/games.gif"));
            ImageView imv = new ImageView(image);
            imv.setFitWidth(150);
            imv.setFitHeight(150);
            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10)); 
            vbox.getChildren().add(imv);
            vbox.setAlignment(Pos.CENTER);
            vbox.setFillWidth(true);

            hright.getChildren().add(vbox);
            */
  
    }   
    
    
     public void setQuestions(int questions) {
        this.questions = questions;
    }
    
    
     private void displayGames() {
         
        int count=0;
        //gamesContainer.getChildren().clear(); // Limpiar contenedor de juegos

        /*for (Round game : GameSet.getInstance().getGames()) {
            game.setNumQuestions(30);
            Button gameButton = new Button(game.getSubject());
            gameButton.setOnAction(e -> {
                try {
                    
                    App.round=game;
                    startGame(game);
                    Button b = (Button) e.getSource();
                    Stage s = (Stage) b.getScene().getWindow();
                    s.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            Vcenter.getChildren().add(gameButton);
        }*/
        
        gamesContainer.getChildren().clear(); // Limpiar contenedor de juegos

        for (Node game : GameSet.getInstance().getGames()) {
            System.out.println(game.data);
            System.out.println(game.left);
            Button gameButton = new Button("Game :"+(count+=1));
            gameButton.setOnAction(e -> {
                //startGame(game);
                
                /*Button b = (Button) e.getSource();
                Stage s = (Stage) b.getScene().getWindow();
                s.close();*/
                System.out.println(round.numQuestions);
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
        
        
        //round.setNumQuestions(90);
        round.setIsFinished(false);
        //Round<String> round = newRound<>();
        // Generar el nuevo nombre para los archivos
       //String name="Game " + (GameSet.getInstance().getGames().size() + 1);
       //round.setSubject(name);
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

   
   /*public void OpenWindow(Node<String> root, int numQuestions, List<String> answ){

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
    }*/
}
