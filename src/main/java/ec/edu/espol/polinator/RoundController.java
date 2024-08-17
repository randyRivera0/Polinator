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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
public class RoundController implements Initializable {

    @FXML
    private Button ButtonSi;
    @FXML
    private Button ButtonNo;
    @FXML
    private Text textQuestion;
    @FXML
    private Label textResult;
    @FXML
    private Button buttonPrevScene;
    @FXML
    public VBox vpaneCentral;
    @FXML
    private HBox hpaneTop;
    @FXML
    private HBox hpaneBottom;
    @FXML
    private BorderPane borderPane;
    
    private Round round;
    public String question;
    public String decision;
    public String result;
    public boolean buttonYesVisible;
    public boolean buttonNoVisible;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.round = App.getRound();
        textQuestion.setText((String) round.getTree().getRoot().getData());
        
        hpaneBottom.setStyle("-fx-background-color: #CCD5AE;");
        hpaneTop.setStyle("-fx-background-color: #CCD5AE;");
        vpaneCentral.setStyle("-fx-background-color: #E0E5B6;");
        // text= new TextArea();
        buttonYesVisible=true;
        buttonNoVisible=true;

    }    
    
    @FXML
    public void buttonTurn(ActionEvent event){
        Button button = (Button) event.getSource();
        decision = button.getText().toLowerCase();
        round.turn(this);
        textQuestion.setText(question);
        textResult.setText(result);
        ButtonSi.setVisible(buttonYesVisible);
        ButtonNo.setVisible(buttonNoVisible);
        
    }
      

    public void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
              
    }
    
    public void CargarImagen(String data){
        
        try {
            Image image = new Image(getClass().getResourceAsStream("/img/"+data+".jpg"));
            ImageView imv = new ImageView(image);
            imv.setFitWidth(350);
            imv.setFitHeight(350);
            VBox vbox = new VBox();
            vbox.setSpacing(5);
            vbox.setPadding(new Insets(10)); 
            vbox.getChildren().add(imv);
            vbox.setAlignment(Pos.CENTER);
            vbox.setFillWidth(true);

            vpaneCentral.getChildren().add(vbox);
                
        } 
        catch (NullPointerException ex) {
                    
            // Si no se encuentra la imagen, cargar el GIF de error
            
            Image errorImage = new Image(getClass().getResourceAsStream("/img/error.gif"));
            ImageView imvError = new ImageView(errorImage);

            // Configurar las propiedades del GIF
            imvError.setFitWidth(250);
            imvError.setFitHeight(250);

            // Crear un VBox y agregar el GIF
            VBox vboxError = new VBox();
            vboxError.setSpacing(5);
            vboxError.setPadding(new Insets(10));
            vboxError.getChildren().add(imvError);
            vboxError.setAlignment(Pos.CENTER);
            vboxError.setFillWidth(true);

            // Agregar el VBox con el GIF al contenedor central
            vpaneCentral.getChildren().add(vboxError);

        }  
    }
    

    public void CargarGif(String ruta){
        try {
            Image image = new Image(getClass().getResourceAsStream("/"+ruta));
            ImageView imv = new ImageView(image);
            imv.setFitWidth(200);
            imv.setFitHeight(200);
            vpaneCentral.getChildren().add(imv);
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
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

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setResult(String result) {
        this.result = result;
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
