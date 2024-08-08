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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leoza
 */
public class GameController implements Initializable {

    @FXML
    private Button ButtonSi;
    @FXML
    private Button ButtonNo;
    @FXML
    private Label TextoPregunta;
    
    private Node<String> currentNode;
    private int remainingQuestions;
    private boolean encontrado;
    private boolean coincidencias;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }    
    
    
    public void initData(Node<String> root, int numQuestions) {
       
        this.currentNode = root;
        this.remainingQuestions = numQuestions;
        encontrado=false;
        coincidencias=false;
        // Si numQuestions es 0, mostramos una alerta y terminamos el juego
        if (numQuestions <= 0) {
            showAlert("Número de preguntas debe ser mayor a 0");
           return;

        } else {
            showQuestion();
        }
    }


    @FXML
    private void ActionSI(ActionEvent event) {
       
        handleAnswer(true);
        System.out.println(currentNode);
        

        
    }

    @FXML
    private void ActionNo(ActionEvent event) {
        
        handleAnswer(false);
        System.out.println(currentNode);
  
    }
    
     private void handleAnswer(boolean affirmative) {
            System.out.println(currentNode.childrenNodesList());
            if (currentNode == null) {
                showAlert("No existe ningún animal que coincida con las respuestas provistas.");
                AbrirVentana("Lose");
                return;
            } else if (currentNode.isLeaf()) {
                AbrirVentana("Winner");
                return;
            } else if (remainingQuestions > 0) {
                remainingQuestions -= 1;

                if (affirmative) {
                    currentNode = currentNode.left;
                } else {
                    currentNode = currentNode.right;
                }

                // Si las preguntas restantes son 0 después de decrementar, mostramos alerta
                if (remainingQuestions == 0) {
                    showAlert("No suficientes preguntas. Nodo: " + currentNode.childrenNodesList());
                    AbrirVentana("Possibles");

                    return;
                } else {
                    showQuestion();
                }
            } else {
                showAlert("No suficientes preguntas. Nodo: " + currentNode.childrenNodesList());
                AbrirVentana("Possibles");
            }
    }

    private void showQuestion() {
        if (currentNode != null) {
            TextoPregunta.setText(currentNode.data);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
              
    }
    

    
    
   public void AbrirVentana(String ruta) {
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
}
