package ec.edu.espol.polinator;

import java.io.IOException;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class PrimaryController {
    
    @FXML
    private TextField textFieldNumQuestions;
    @FXML
    private ComboBox<String> comboBoxTemas;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
    
    
     @FXML
    private void initialize() {
        // Add a key event handler to the TextField
        textFieldNumQuestions.setOnKeyPressed(this::handleKeyPress);
        List<String> temas = Utility.cargarTemas("C:\\Users\\spupi\\Downloads\\Polinator");
        updateComboBox(temas);
    }
    
    
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            // Trigger the logic and switch to the secondary FXML
            try {
                // Perform your logic here (e.g., validate input)
                // For example:
                String input = textFieldNumQuestions.getText();
                System.out.println("Input received: " + input);
                
                // Switch to the secondary view
                App.setRoot("secondary");
            } catch (IOException e) {
                e.printStackTrace();
                // Handle the exception appropriately
            }
        }
    }

    public void updateComboBox(List<String> temas) {
        comboBoxTemas.getItems().clear(); // Clear existing items
        comboBoxTemas.getItems().addAll(temas); // Add new items
    }
}
