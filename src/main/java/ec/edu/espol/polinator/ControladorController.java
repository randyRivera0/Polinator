/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.polinator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leoza
 */
public class ControladorController implements Initializable {

    private ComboBox<String> comboBoxTemas;
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
    private Label titulo;
    @FXML
    private Button ButtonSubmit;
    @FXML
    private Button ButtonAnime;
    @FXML
    private Button ButtonAnimals;
    @FXML
    private Button buttonSuperHeroe;
    @FXML
    private HBox hpaneLabel;

 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            vpaneGIF.setStyle("-fx-background-color: #FEF3E2;");  // Esto aplica un color de fondo azul claro
            hpaneTop.setStyle("-fx-background-color: #BEC6A0;");
            hpaneLabel.setStyle("-fx-background-color: #BEC6A0;");
            hpaneBajo.setStyle("-fx-background-color:#BEC6A0 ;");
            vpaneOptions.setStyle("-fx-background-color: #FEF3E2;");
            titulo.setStyle("-fx-text-fill: #1A5319;"); 
            //#BEC6A0
            

            // Cargar el GIF
            Image image = new Image(new FileInputStream("img/"+"turtle"+".gif"));
            ImageView imv = new ImageView(image);
            
            
            imv.setFitWidth(250);
            imv.setFitHeight(250);
            vpaneGIF.getChildren().add(imv);
        } 
        
        catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        
        HiloFlashHBox hilo = new HiloFlashHBox(hpaneLabel);
        hilo.setDaemon(true);
        hilo.start();
        System.out.println(GameSet.getInstance().getGames().size());
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
               FXMLLoader fxml = App.loadFXML("UserGames");
               Scene sc = new Scene(fxml.load(), 850, 600);
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
    
    
      class HiloFlashHBox extends Thread {
            private HBox hbox;

            HiloFlashHBox(HBox hbox) {
                this.hbox = hbox;
                hbox.setStyle("-fx-background-color: #BEC6A0;");
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
    
    private Node<String> cargarArchivoPreguntas(String category) {
        //Path path = Paths.get(category.toLowerCase() + "_preguntas.txt");
        Path path = Paths.get("preguntas.txt");
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
        Path path = Paths.get("respuestas.txt");
        try {
            List<String> questions = Files.readAllLines(path);
            for (String question : questions) {
                root.addChildrenAnswer(question);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    
    /*public void AbrirVentana(String ruta, Node<String> root, int numQuestions) {
       try {
        FXMLLoader fxml = App.loadFXML(ruta);
        Scene sc = new Scene(fxml.load(), 850, 600);
        Stage st = new Stage();
        GameController controller = fxml.getController();
        controller.initData(root, numQuestions); // Pasar la referencia del Stage al controlador del juego
        st.setScene(sc);
        st.show();
    } catch (IOException ex) {
        Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el fxml");
        a.show();
    }
    }*/

     
     public void OpenWindow(Node<String> root, int numQuestions, List<String> answ){
    
         try {
        FXMLLoader fxml = App.loadFXML("Options");
        Scene sc = new Scene(fxml.load(), 850, 600);
        Stage st = new Stage();
        OptionsController controller = fxml.getController();
        controller.initData(root, numQuestions, answ); // Pasar la referencia del Stage al controlador del juego
        st.setScene(sc);
        st.show();
    } catch (IOException ex) {
        Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el fxml");
        a.show();
    }
    }
     

   private void Init(ActionEvent event, String option , int numQuestions) {
           
  
    //List<String> answ=Utility.loadanswers(option+".txt");
    List<String> answ=Utility.loadanswers("respuestas.txt");
    root = cargarArchivoPreguntas(option);
    cargarArchivoRespuestas(root, option);

    // Pasar la información necesaria al controlador del juego
    OpenWindow(root, numQuestions, answ);
    
    System.out.println(numQuestions);

    
        
    }
    
    
    @FXML
    private void Submit(ActionEvent event) {
      
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

    @FXML
    private void Anime(ActionEvent event) {
        


        String input = textFieldNumQuestions.getText();
        int numQuestions;

        try {
            // Intentar convertir el texto a un entero
            numQuestions = Integer.parseInt(input);

            // Verificar si el número es menor o igual a 0
            if (numQuestions <= 0) {
                showAlert("El número de preguntas debe ser mayor a 0");
                return;
            }

            // Continuar con la lógica si el número es válido
            Init(event, "Anime",numQuestions);
            Button b = (Button) event.getSource();
            Stage s = (Stage) b.getScene().getWindow();
            s.close();
        } catch (NumberFormatException e) {
            // Si la conversión falla, mostrar un mensaje de error
            showAlert("Por favor, ingrese un número válido.");
        }

            System.out.println(Utility.loadanswers("respuestas.txt"));
    
    }

    @FXML
    private void Animals(ActionEvent event) {
        
    }

    @FXML
    private void SuperHeroe(ActionEvent event) {
    }
    
    
    
    
     
    
    

}
