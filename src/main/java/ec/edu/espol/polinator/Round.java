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
                
                /*
                controller.showAlert("No suficientes preguntas. Nodo: " + getTree().getRoot().childrenNodesList());
                
                
                try {
                    App.setRoot("possibles");
                }
                catch (IOException ex) {
                    Alert a = new Alert(Alert.AlertType.ERROR, "No se pudo abrir el fxml");
                    a.show();
                }
                

                List<Node<String>> possibleNodes = currentNode.childrenNodesList();
                StringBuilder nodesText = new StringBuilder("POSIBLEMENTE SEA UNO DE ESTOS:\n");

                for (Node<String> node : possibleNodes) {
                    nodesText.append(node.data).append("\n");
                }

                controller.buttonYesVisible=false;
                controller.buttonNoVisible=false;
                    VBox v= new VBox();
                    v.setStyle("-fx-background-color: #E0E5B6;");
                    Label a= new Label();
                    a.setTextAlignment(TextAlignment.CENTER);
                     a.setFont(new Font("Bookman Old Style", 24)); 
                    a.setText(nodesText.toString());
                    StackPane root = new StackPane();
                    root.getChildren().add(a);
                    v.getChildren().add(root);
                    v.setAlignment(Pos.CENTER);
                    v.setFillWidth(true);
                    borderPane.setLeft(v);

                    return;
                */
            }
            
            else if (numQuestions>0){
                IteratorTreeNode it = tree.iterator();

                Node<E> node = it.getNext(decision);
                
                if(node==null){
                    //showAlert("No existe ningún animal que coincida con las respuestas provistas.");
                controller.setQuestion("");
                controller.CargarGif("img/sad.gif");

                controller.setResult("PARECE SER QUE NO ENCONTRE EL PERSONAJE :(");
                controller.buttonYesVisible=false;
                controller.buttonNoVisible=false;

                return;
                }
                else if(node.isLeaf()){
                    controller.setQuestion("");
                    System.out.println(getClass().getResource("/img/" + (String) node.getData() + ".jpg"));
                    controller.CargarImagen((String) node.getData());
                    controller.setResult("EL PERSONAJE EN EL QUE PENSASTE ES:  "+ node.getData() +"!");
                    controller.buttonYesVisible=false;
                    controller.buttonNoVisible=false;
                    isFinished = true;
                }
                else{ // preguntar normal
                    // numQuestions -= 1;
                    // Si las preguntas restantes son 0 después de decrementar, mostramos alerta
                    if (numQuestions == 1) {
                        controller.setQuestion("");
                        controller.CargarGif("img/"+"surprise"+".gif");
                        /*
                        List<Node<String>> possibleNodes = currentNode.childrenNodesList();
                        StringBuilder nodesText = new StringBuilder("POSIBLEMENTE SEA "+"\n"+"UNO DE ESTOS:\n");

                          for (Node<String> node : possibleNodes) {

                              nodesText.append(" - ").append(node.data).append("\n");

                          }


                        VBox v= new VBox();
                        v.setStyle("-fx-background-color: #E0E5B6;");
                        Label a= new Label();
                        a.setTextAlignment(TextAlignment.CENTER);
                         a.setFont(new Font("Bookman Old Style", 24)); 
                        ButtonSi.setVisible(false);
                        ButtonNo.setVisible(false);
                        a.setText(nodesText.toString());
                        StackPane root = new StackPane();
                        root.getChildren().add(a);
                        v.getChildren().add(root);
                        v.setAlignment(Pos.CENTER);
                        v.setFillWidth(true);
                        borderPane.setLeft(v);
                        return;
    `               */
                    } 
                    else {
                            //updateQuestion();
                            this.tree = new TreeNodeDecision(node);
                            controller.setQuestion((String) node.data);

                    }
                }

            }
            
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
                
    
    public String getStringQuestions(){
        
        StringBuilder sb = new StringBuilder();
        
        List<Node<String>> lista = tree.getRoot().childrenNodesList();
        
        for(Node<String> node : lista ){
            sb.append(node.data).append("\n");
        }
        
        return sb.toString();
        
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
