/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.polinator;

import java.io.IOException;
import java.net.URL;
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
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leoza
 */
public class OptionsController implements Initializable {

    @FXML
    private Label labelOptions;
    @FXML
    private Label labelCentral;
    
    private Node<String> Node;
    
    private int questions;
    List<String> list;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
    }    
    
    
     public void initData(Node<String> root, int numQuestions, List<String> list) {
       
        this.Node = root;
        this.questions=numQuestions;
        this.list=list;
        
         StringBuilder nodesText = new StringBuilder("\n");

                      for (String node : list) {
                          nodesText.append(node).append("\n");
                      }

                       labelCentral.setText(nodesText.toString());
        //labelCentral.setText(list.get(0));
       
       
    }
     
     
     public void AbrirVentana( Node<String> root, int numQuestions) {
       try {
        FXMLLoader fxml = App.loadFXML("Game");
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


    @FXML
    private void Return(ActionEvent event) {
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

    @FXML
    private void Iniciar(ActionEvent event) {
        AbrirVentana(this.Node, this.questions);
        Button b = (Button) event.getSource();
        Stage s = (Stage) b.getScene().getWindow();
        s.close();
    }
    
}
