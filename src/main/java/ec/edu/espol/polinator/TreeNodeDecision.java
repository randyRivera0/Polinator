/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.polinator;

/**
 *
 * @author Gecko
 */
public class TreeNodeDecision<E> extends IterableCollectionTree {
    

    public Node<E> root;

    public Node<E> getRoot() {
        return root;
    }
    
    

    public TreeNodeDecision(Node<E> root) {
        this.root = root;
    }
    
    public void insert(int data){
        root = insertRec(root, data);
    }

    private Node<E> insertRec(Node<E> root, int data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    public boolean preguntar(Node<E> root){
        if(root==null){
            System.out.println("No existe ningun animal que coincida con las"
                    + "respuestas provistas ");
           return true;
        }
        
        else if(root.isLeaf()){
            System.out.println("La respuesta es: " + root.data);
            return true;
        }
        else return true;
    }
    

    @Override
    public IteratorTreeNode<E> iterator() {
        return new IteratorTreeNodeDecision(this);
    }
    
}
