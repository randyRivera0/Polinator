/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.polinator;

/**
 *
 * @author leoza
 */
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class Round <E>{
    
    IterableCollectionTree tree;
    public int numQuestions;
    private String subject;
    public IteratorTreeNode iterator;
    private boolean isFinished;
    
    public Round(){}

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public void turn(RoundController controller){
        String decision = controller.decision;
        if(!isFinished){
            if(numQuestions<=0){
                controller.setQuestion("No suficientes preguntas.\n"
                + "Nodos: " + getTree().getRoot().childrenNodesList());
                
                isFinished = true;   
                
                controller.setQuestion("");
                controller.CargarGif("img/surprise.gif");

            }
            
            else if (numQuestions>0){
                //numQuestions -= 1;
                IteratorTreeNode it = tree.iterator();

                Node<E> node = it.getNext(decision);
                //numQuestions--;
                if(node==null){
                //showAlert("No existe ningún animal que coincida con las respuestas provistas.");
                controller.setQuestion("");
                controller.CargarGif("img/sad.gif");

                controller.setResult("Parece que no encontré el personaje :(");
                controller.buttonYesVisible=false;
                controller.buttonNoVisible=false;

                return;
                }
               
                else if(node.isLeaf()){
                    controller.setQuestion("");
                    controller.CargarImagen((String) node.getData());
                    controller.setResult("El personaje en el que pensaste es: "+ node.getData() +"!");
                    controller.buttonYesVisible=false;
                    controller.buttonNoVisible=false;
                    isFinished = true;
                }
                else{ // preguntar normal
                    //numQuestions -= 1;
                    // Si las preguntas restantes son 0 después de decrementar, mostramos alerta
                    
                    if (numQuestions == 1) {
                        
                        List<Node<E>> possibleNodes = node.childrenNodesList();
                        StringBuilder nodesText = new StringBuilder("Posiblemente sea "+"uno de estos:\n");

                        for (Node<E> n : possibleNodes) {

                              nodesText.append(" - ").append(n.data).append("\n");

                          }
                        
               
                        controller.setQuestion(nodesText.toString());
                        StackPane root = new StackPane();

                        controller.buttonYesVisible=false;
                        controller.buttonNoVisible=false;


                   
                    } 
                    else {

                            this.tree = new TreeNodeDecision(node);
                            controller.setQuestion((String) node.data);

                    }
                }

            }
           numQuestions -= 1; 
        }
 
    }
    
    
    public void changeQuestions(){
        Node<String> node1 = Utility.cargarArchivoPreguntas(subject);
        Utility.cargarArchivoRespuestas(node1, subject);
        tree = new TreeNodeDecision(node1);
    }
    
    public String getRelativePath(String data){
        return "/img/" + data + ".jpg";
    }
    
    public InputStream getInputStream(String relativePath){
        try{
            return getClass().getResourceAsStream(relativePath);
        }
        catch (NullPointerException e) {
            return getClass().getResourceAsStream("/img/error.gif"); // En caso extremo, si el GIF tampoco se encuentra
        }
    }
    
    private List<InputStream> createInputStreamList(){
        List<InputStream> inputStreamList = new ArrayList<>();
        List<Node<E>> lista = tree.root.childrenNodesList();
        for(Node<E> node : lista){
            String data = (String) node.data;
            String relativePath = getRelativePath(data);
            InputStream imagePath = getInputStream(relativePath);
            inputStreamList.add(imagePath);
            
        }
        
        return inputStreamList;
        
    }
                
    
    public List<String> getQuestionsList() {
    List<String> questionsList = new ArrayList<>();
    List<Node<String>> lista = tree.getRoot().childrenNodesList();
    
        for(Node<String> node : lista) {
            questionsList.add(node.data);
        }
    
    return questionsList;
    }

    public String getSubject() {
        return subject;
    }

    public IterableCollectionTree getTree() {
        return tree;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

}
