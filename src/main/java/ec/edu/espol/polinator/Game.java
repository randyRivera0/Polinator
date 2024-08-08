/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.polinator;

/**
 *
 * @author Gecko
 */
public class Game <E> {
    
    IterableCollectionTree tree;
    public int numQuestions;
    public int answer;

    public Game() {
        Node<String> node1 = Utility.cargarArchivoPreguntas();
        Utility.cargarArchivoRespuestas(node1);
        tree = new TreeNodeDecision(node1);

        
    }

    public void setNumQuestions(int numQuestions) {
        this.numQuestions = numQuestions;
    }
    
    
        
    public String preguntarRec(){

        if(numQuestions<=0){
            return "No suficientes preguntas.\n"
                    + "Nodos: " + tree.getRoot().childrenNodesList();
        }
        
        IteratorTreeNode it = tree.iterator();
  
        Node<E> node = it.getNext(answer);
        
        this.tree = new TreeNodeDecision(node);
        
        numQuestions -= 1;
        
        return (String) node.data;
        
    }
    
    
}
