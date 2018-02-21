package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {
	
    public static final int DESBALANCEADA = 2;
    public static final int DESB_ESQUERDA = 1;
    public static final int DESB_DIREITA = -1;

	@Override
	public void insert(T element) {
		
        super.insert(element);
        BSTNode<T> node = this.search(element);
        this.rebalanceUp(node);
	}
	
	@Override
	public void remove(T element) {
		
        BSTNode<T> node = this.search(element);
        if (!node.isEmpty()) {
            super.remove(node);
            this.rebalanceUp(node);
        }
	}
	
	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		
		if(node.isEmpty()) return 0;
		else return this.height((BSTNode<T>) node.getLeft()) - this.height((BSTNode<T>) node.getRight());
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		
        int balance = this.calculateBalance(node);
        if (balance > DESB_ESQUERDA) {
            BSTNode<T> leftChild = (BSTNode<T>) node.getLeft();
            if (this.calculateBalance(leftChild) <= DESB_DIREITA) {
                this.leftRotation(leftChild);
            }
            this.rightRotation(node);
        } else if (balance < DESB_DIREITA) {
            BSTNode<T> rightChild = (BSTNode<T>) node.getRight();
            if (this.calculateBalance(rightChild) >= DESB_ESQUERDA) {
                this.rightRotation(rightChild);
            }
            this.leftRotation(node);
        }
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		
        int balance = this.calculateBalance(node);
        if (Math.abs(balance) >= DESBALANCEADA) {
            this.rebalance(node);
        }
        if (node.getParent() != null) {
            this.rebalanceUp((BSTNode<T>) node.getParent());
        }
	}
	
    // AUXILIARY
    protected void leftRotation(BSTNode<T> node) {
        BSTNode<T> balancedNode = adt.bt.Util.leftRotation(node);
        if (balancedNode.getParent() == null) {
            this.root = balancedNode;
        }
    }

    // AUXILIARY
    protected void rightRotation(BSTNode<T> node) {
        BSTNode<T> balancedNode = adt.bt.Util.rightRotation(node);
        if (balancedNode.getParent() == null) {
            this.root = balancedNode;
        }
    }
    
}
