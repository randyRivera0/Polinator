/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.polinator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
    @FXML
    private Label textFINAL;
    @FXML
    private Button buttonReturn;
    @FXML
    private VBox vpaneCentral;


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
                //showAlert("No existe ningún animal que coincida con las respuestas provistas.");
                 TextoPregunta.setText("");
                    CargarImagen("sad");
                    
                    
                    System.out.println(currentNode.childrenNodesList());
                    textFINAL.setText("PARECE SER QUE NO ENCONTRE EL PERSONAJE :(");
                    ButtonSi.setVisible(false);
                    ButtonNo.setVisible(false);

                    return;
            } else if (currentNode.isLeaf()) {
                TextoPregunta.setText("");
                if(currentNode.left==null){
                    
                CargarImagen(currentNode.data);
                textFINAL.setText("EL PERSONAJE EN EL QUE PENSASTE ES:  "+currentNode.data+"!");
                ButtonSi.setVisible(false);
                ButtonNo.setVisible(false);
                
                }
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
                       TextoPregunta.setText("");
                      CargarImagen("confuse");

                            List<Node<String>> possibleNodes = currentNode.childrenNodesList();
                      StringBuilder nodesText = new StringBuilder("POSIBLEMENTE SEA UNO DE ESTOS:\n");

                      for (Node<String> node : possibleNodes) {
                          nodesText.append(node.data).append("\n");
                      }

                      textFINAL.setText(nodesText.toString());
                     ButtonSi.setVisible(false);
                    ButtonNo.setVisible(false);

                      return;
                } 
                else {
                    if (currentNode != null && !currentNode.isLeaf()) {
                        showQuestion();
                    }
                }
            } else {
                //showAlert("No suficientes preguntas. Nodo: " + currentNode.childrenNodesList());
                //AbrirVentana("Possibles");
                TextoPregunta.setText("");
                    CargarImagen("confuse");
                    
                    
                    System.out.println(currentNode.childrenNodesList());
                    
                           List<Node<String>> possibleNodes = currentNode.childrenNodesList();
                      StringBuilder nodesText = new StringBuilder("POSIBLEMENTE SEA UNO DE ESTOS:\n");

                      for (Node<String> node : possibleNodes) {
                          nodesText.append(node.data).append("\n");
                      }

                      textFINAL.setText(nodesText.toString());
                    ButtonSi.setVisible(false);
                    ButtonNo.setVisible(false);

                    return;
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
    
    private void CargarImagen(String data){
        
        try {
                
                Image image = new Image(new FileInputStream("img/"+data+".jpg"));
                ImageView imv = new ImageView(image);
                
              
                imv.setFitWidth(350);
                imv.setFitHeight(350);
                vpaneCentral.getChildren().add(imv);
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            }
                
        
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

    @FXML
    private void Regreso(ActionEvent event) {
        
          try {
            FXMLLoader fxml = App.loadFXML("primary_1");
            Scene sc = new Scene(fxml.load(),850,600);
            Stage st = new Stage();
            st.setScene(sc);
            st.show();
            
            Button b = (Button)event.getSource();
            Stage s = (Stage) b.getScene().getWindow();
            s.close();
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo abrir el fxml");
            a.show();
        }
        
    }
}
