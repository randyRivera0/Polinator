/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.polinator;

/**
 *
 * @author Gecko
 */
public abstract class IterableCollectionTree<E> {
    
    public Node<E> root;
    
    public abstract IteratorTreeNode<E> iterator();

    public Node<E> getRoot() {
        return root;
    }
    
}
