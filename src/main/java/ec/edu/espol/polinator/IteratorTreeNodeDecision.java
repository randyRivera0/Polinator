/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.polinator;

/**
 *
 * @author Gecko
 */
public class IteratorTreeNodeDecision<E> implements IteratorTreeNode{
    
    IterableCollectionTree tree;

    public IteratorTreeNodeDecision(IterableCollectionTree tree) {
        this.tree = tree;
    }
    

    @Override
    public boolean hasNext(int step) {

        System.out.println(this.tree.getRoot());
        // System.out.println(this.tree.root);
        return (this.tree.getRoot().getLeft()!=null || this.tree.getRoot().getRight()!=null);

    }

    @Override
    public Node<E> getNext(int step) {
        
        if(hasNext(step)){
            if(step==1){
                return tree.getRoot().getLeft();
            }
            if (step==0){
                return tree.getRoot().getRight();
            }
        }
        
        return null;
    }
  
    
}
