package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		
		return height(this.root);
	}

	private int height(BSTNode<T> node) {
		
		if(node.isEmpty()) return -1;
		else {
			return 1 + Math.max(height((BSTNode<T>) node.getLeft()), height((BSTNode<T>) node.getRight()));
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		
		return search(this.root, element);
	}
	
	private BSTNode<T> search(BSTNode<T> node, T element) {
		
		if(node.isEmpty() || node.getData().equals(element)) return node;
		
		if(node.getData().compareTo(element) < 0) {
			return search((BSTNode<T>) node.getRight(), element);
		} else {
			return search((BSTNode<T>) node.getLeft(), element);
		}
	}

	@Override
	public void insert(T element) {
		if(element != null) {
			insert(null, this.root, element);
		}
	}
	
	private void insert(BSTNode<T> parent, BSTNode<T> node, T element) {
		
		if(node.isEmpty()) {
			node.setParent(parent);
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
		}else if(node.getData().compareTo(element) < 0) {
			insert(node, (BSTNode<T>) node.getRight(), element);
		} else if (node.getData().compareTo(element) > 0) {
			insert(node, (BSTNode<T>) node.getLeft(), element);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		
		return maximum(this.root);
	}
	
	private BSTNode<T> maximum(BSTNode<T> node) {
		
		if(node.isEmpty()) return null;
		else if (node.getRight().isEmpty()) return node;
		else return maximum((BSTNode<T>) node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		
		return minimum(this.root);
	}
	
	private BSTNode<T> minimum(BSTNode<T> node) {
		
		if(node.isEmpty()) return null;
		else if (node.getLeft().isEmpty()) return node;
		else return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (node.isEmpty()) return null;
		else return sucessor(node);
	}

	private BSTNode<T> sucessor(BSTNode<T> node) {
		
		BSTNode<T> sucessor = minimum((BSTNode<T>) node.getRight());
		
		if(sucessor != null) return sucessor;
		else {
			sucessor = (BSTNode<T>) node.getParent();
			while(sucessor != null && sucessor.getData().compareTo(node.getData()) < 0) {
				sucessor = (BSTNode<T>) sucessor.getParent();
			}
			return sucessor;
		}
	}
	
	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		if (node.isEmpty()) return null;
		else return predecessor(node);
	}

	private BSTNode<T> predecessor(BSTNode<T> node) {
		
		BSTNode<T> predecessor = maximum((BSTNode<T>) node.getLeft());
		
		if(predecessor != null) return predecessor;
		else {
			predecessor = (BSTNode<T>) node.getParent();
			while(predecessor != null && predecessor.getData().compareTo(node.getData()) > 0) {
				predecessor = (BSTNode<T>) predecessor.getParent();
			}
			return predecessor;
		}
	}

	@Override
	public void remove(T element) {
		
		if(element != null) {
			BSTNode<T> node = this.search(element);
			if(!node.isEmpty()) {
				remove(node);
			}
		}
	}
	
	private void remove(BSTNode<T> node) {
		
		if(node.isLeaf()) {
			node.setData(null);
		} else if (hasOneChild(node)) {
			removeOneChild(node);
		} else {
			removeTwoChildren(node);
		}
	}
	
	private void removeOneChild(BSTNode<T> node){
		
		BSTNode<T> child;
		if(onlyLeft(node)) child = (BSTNode<T>) node.getLeft();
		else child = (BSTNode<T>) node.getRight();
		
        if (node.getParent() != null) {
            if (isLeft(node)) {
                node.getParent().setLeft(child);
            } else {
                node.getParent().setRight(child);
            }
            child.setParent(node.getParent());
        } else {
            child.setParent(null);
            this.root = child;
        }
	}
	
	private boolean hasOneChild(BSTNode<T> node){
		return (this.onlyLeft(node) || this.onlyRight(node));
	}
	
	private boolean onlyLeft(BSTNode<T> node) {
		return (node.getRight().isEmpty() && !node.getLeft().isEmpty());
	}
	
	private boolean onlyRight(BSTNode<T> node) {
		return (!node.getRight().isEmpty() && node.getLeft().isEmpty());
	}
	
    public boolean isLeft(BSTNode<T> node) {
        return !node.getParent().isEmpty()
                && !node.getParent().getLeft().isEmpty() &&
                node.getParent().getLeft().getData().equals(node.getData());
    }
	
	private void removeTwoChildren(BSTNode<T> node){
		
	    BSTNode<T> auxNode = minimum((BSTNode) node.getRight());
	    if (auxNode.isEmpty())
	        maximum((BSTNode) node.getLeft());
	
	    T aux = node.getData();
	    node.setData(auxNode.getData());
	    auxNode.setData(aux);
	    remove(auxNode);
	}
	
	@Override
	public T[] preOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		preOrder(this.root, array, 0);
		return array;
	}

	private int preOrder(BSTNode<T> node, T[] array, int index) {
		
		if(!node.isEmpty()) {
			array[index++] = node.getData();
			index = preOrder((BSTNode<T>) node.getLeft(), array, index);
			index = preOrder((BSTNode<T>) node.getRight(), array, index);
		}
		return index;		
	}
	
	@Override
	public T[] order() {
		T[] array = (T[]) new Comparable[this.size()];
		order(this.root, array, 0);
		return array;
	}

	private int order(BSTNode<T> node, T[] array, int index) {
		
		if(!node.isEmpty()) {
			index = order((BSTNode<T>) node.getLeft(), array, index);
			array[index++] = node.getData();
			index = order((BSTNode<T>) node.getRight(), array, index);
		}
		return index;		
	}

	@Override
	public T[] postOrder() {
		T[] array = (T[]) new Comparable[this.size()];
		postOrder(this.root, array, 0);
		return array;
	}

	private int postOrder(BSTNode<T> node, T[] array, int index) {
		
		if(!node.isEmpty()) {
			index = postOrder((BSTNode<T>) node.getLeft(), array, index);
			index = postOrder((BSTNode<T>) node.getRight(), array, index);
			array[index++] = node.getData();
		}
		return index;		
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
