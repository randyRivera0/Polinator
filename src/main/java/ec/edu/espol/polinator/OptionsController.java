/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.polinator;

import java.io.File;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author leoza
 */
public class OptionsController implements Initializable {
    
    public Round round;
    
    @FXML
    private Label labelOptions;
    @FXML
    private Button buttonStart;
    @FXML
    private Button buttonPrevScene;
    @FXML
    private Label labelCentral;
       
    @FXML
    private HBox hpaneTop;
    @FXML
    private VBox vpaneLeft;
    @FXML
    private HBox hpaneBottom;
    @FXML
    private FlowPane central;
    @FXML
    private ScrollPane sp;
    @FXML
    private VBox vapenRight;
    @FXML
    private BorderPane bp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        this.round = App.getRound();
        cargarPersonajes();
        Image image = new Image(getClass().getResourceAsStream("/img/fondo.jpeg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(680);
        imageView.setFitHeight(480);
        bp.getChildren().add(0, imageView); // Agregar imagen al fondo
        
    }
    

    public void cargarPersonajes() {
        List<String> namesList = round.getQuestionsList();
        
        central.setHgap(10);
        central.setVgap(10);
        central.setPadding(new Insets(10));
        
        /*for (String name : namesList) {
                   try {
            Image image = new Image(getClass().getResourceAsStream("/img/" + name + ".jpg"));
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100);  
            imageView.setPreserveRatio(true); 

            VBox vbox = new VBox(10); 
            vbox.setAlignment(Pos.CENTER);
            Label nameLabel = new Label(name);
            nameLabel.setAlignment(Pos.CENTER); 
            nameLabel.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12)); 
            nameLabel.setWrapText(true);
            vbox.getChildren().addAll(imageView, nameLabel);

            central.getChildren().add(vbox);
        } catch (Exception e) {
           
            VBox vbox = new VBox(10); 
            vbox.setAlignment(Pos.CENTER);
            Label nameLabel = new Label(name+ " - ");
            nameLabel.setAlignment(Pos.CENTER); 
            nameLabel.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 50)); 
            nameLabel.setWrapText(true);
            vbox.getChildren().addAll(nameLabel);

            central.getChildren().add(vbox);
        }
        }*/
        
        VBox column = new VBox(10); // Crear un VBox para organizar los nombres en columna
        column.setAlignment(Pos.CENTER); 
        for (int i = 0; i < namesList.size(); i++) {
            String name = namesList.get(i);
            try {
                Image image = new Image(getClass().getResourceAsStream("/img/" + name + ".jpg"));
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(100);  
                imageView.setPreserveRatio(true); 

                VBox vbox = new VBox(10); 
                vbox.setAlignment(Pos.CENTER);
                Label nameLabel = new Label(name);
                nameLabel.setAlignment(Pos.CENTER); 
                nameLabel.setFont(Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 12)); 
                nameLabel.setWrapText(true);
                vbox.getChildren().addAll(imageView, nameLabel);

                central.getChildren().add(vbox);
            } catch (Exception e) {
                VBox vbox = new VBox(10); 
                vbox.setAlignment(Pos.CENTER);

                // Si no es el último elemento, añadir el guion
                String displayName = (i < namesList.size() - 1) ? "-" + name  : name;

                Label nameLabel = new Label(displayName);
                nameLabel.setAlignment(Pos.CENTER); 
                nameLabel.setFont(Font.font("Kristen ITC", 18)); 
                nameLabel.setWrapText(true);
                nameLabel.setStyle("-fx-text-fill:  white;"); 
                vbox.getChildren().addAll(nameLabel);

                //central.getChildren().add(vbox);
                column.getChildren().add(vbox);
            }
        }
        
        central.getChildren().add(column);
        sp.setFitToWidth(true);
        sp.setContent(central);

    }
      
    @FXML
    public void AbrirVentana(ActionEvent event) {
       /*try {
            App.setRoot("round");
        } 
        catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el fxml");
            a.show();
        }*/
       Abrir("round");
       Button b = (Button) event.getSource();
       Stage s = (Stage) b.getScene().getWindow();
       s.close();
    }


    @FXML
    private void prevScene(ActionEvent event) {
        /*try {
            App.setRoot("lobby");
        } catch (IOException ex) {
            Alert a = new Alert(Alert.AlertType.ERROR,"No se pudo abrir el fxml");
            a.show();
        }*/
        Abrir("lobby");
        Button b = (Button) event.getSource();
        Stage s = (Stage) b.getScene().getWindow();
        s.close();
        
        
    }
    
    
    @FXML
    private void uploadQuestions(ActionEvent event) {
      
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
