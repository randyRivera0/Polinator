/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.polinator;

/**
 *
 * @author Gecko
 */
public interface IteratorTreeNode<E> {
    
    boolean hasNext(String step);
    
    Node getNext(String decision);
    
}
