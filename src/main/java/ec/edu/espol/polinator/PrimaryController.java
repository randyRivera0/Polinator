package ec.edu.espol.polinator;

import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class PrimaryController {
    
    @FXML
    private TextField textFieldNumQuestions;
    @FXML
    private ComboBox<String> comboBoxTemas;

    
    
     @FXML
    private void initialize() {
        // Add a key event handler to the TextField
        //textFieldNumQuestions.setOnKeyPressed(this::handleKeyPress);
        //List<String> temas = Utility.cargarTemas("C:\\Users\\spupi\\Downloads\\Polinator");
        //updateComboBox(temas);
        
    }
 
    
}
