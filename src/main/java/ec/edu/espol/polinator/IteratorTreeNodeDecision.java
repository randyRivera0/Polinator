/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.polinator;

/**
 *
 * @author Gecko
 */
public class IteratorTreeNodeDecision<E> implements IteratorTreeNode {
    
    IterableCollectionTree tree;

    public IteratorTreeNodeDecision(IterableCollectionTree tree) {
        this.tree = tree;
    }
    

    @Override
    public boolean hasNext(String step) {
        
        System.out.println(this.tree.getRoot());
        
        if(step.equals("no")){
            return (this.tree.getRoot().getRight()!=null);
        }
        else{
            return (this.tree.getRoot().getLeft()!=null);
        }


    }

    @Override
    public Node<E> getNext(String step) {
        
        if(hasNext(step)){
            if(step.equals("no")){
                return tree.getRoot().getRight();   
            }
            else{
                return tree.getRoot().getLeft();
            }
        }
        
        return null;
    }
    
}
