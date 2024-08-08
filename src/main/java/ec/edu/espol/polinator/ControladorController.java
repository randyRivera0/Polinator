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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leoza
 */
public class ControladorController implements Initializable {

    @FXML
    private ComboBox<String> comboBoxTemas;
    @FXML
    private TextField textFieldNumQuestions;
    
    private String [] options =  {"ACTORES","ANIMALES","ANIME","DEFAULT"};  
    @FXML
    private Button ButtomGame;
        private Node<String> root;

 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        comboBoxTemas.getItems().addAll(options);
    }    
    
    
    

    @FXML
    private void comboAction(ActionEvent event) {
    }

        @FXML
        private void Inicio(ActionEvent event) {
              String selectedCategory = comboBoxTemas.getValue();
        int numQuestions;

        try {
            numQuestions = Integer.parseInt(textFieldNumQuestions.getText());
        } catch (NumberFormatException e) {
            showAlert("Número de preguntas no válido");
            return;
        }

        if (numQuestions <= 0) {
            showAlert("Número de preguntas debe ser mayor a 0");
            return;
        }
        

    root = cargarArchivoPreguntas(selectedCategory);
    cargarArchivoRespuestas(root, selectedCategory);

    // Pasar la información necesaria al controlador del juego
    AbrirVentana("Game", root, numQuestions);
    
    System.out.println(numQuestions);

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
    
        private Node<String> cargarArchivoPreguntas(String category) {
        //Path path = Paths.get(category.toLowerCase() + "_preguntas.txt");
        Path path = Paths.get("preguntas.txt");
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

    public void AbrirVentana(String ruta, Node<String> root, int numQuestions) {
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
    }
    
    
    /*public void AbrirVentana(String ruta){
            try {
            FXMLLoader fxml = App.loadFXML(ruta);
            Scene sc = new Scene(fxml.load(),850,600);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
            
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo abrir el fxml");
            a.show();
        }
    }*/
    
}
