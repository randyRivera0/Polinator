package ec.edu.espol.polinator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;


public class SecondaryController implements GameController {
    
    public Game game;

    @Override
    public void setGame(Game game) {
        this.game = game;
    }
    
    @FXML
    private Text textQuestion;
    @FXML
    private TextField textFieldAnswer;
    @FXML
    private Label textSubject;
    
     
    @FXML
    private void initialize() {
        // Add a key event handler to the TextField
        textFieldAnswer.setOnKeyPressed(this::handleKeyPress);

    }
        

    public void setData(String textFieldValue, String comboBoxValue) {
        textSubject.setText("Text Field: " + textFieldValue);
        textSubject.setText("Combo Box: " + comboBoxValue);
    }
    
    
    public void updateText(String newText) {
        textQuestion.setText(newText); // Update the text of the Text element
    }
    
    
    private void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
           
            game.answer = Integer.parseInt(textFieldAnswer.getText());
            textQuestion.setText(game.preguntarRec());
        }         
    }
    
}
