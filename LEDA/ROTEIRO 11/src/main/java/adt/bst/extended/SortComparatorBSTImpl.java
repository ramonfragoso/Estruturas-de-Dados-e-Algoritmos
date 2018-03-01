package adt.bst.extended;

import java.util.Comparator;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Implementacao de SortComparatorBST, uma BST que usa um comparator interno em suas funcionalidades
 * e possui um metodo de ordenar um array dado como parametro, retornando o resultado do percurso
 * desejado que produz o array ordenado. 
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class SortComparatorBSTImpl<T extends Comparable<T>> extends BSTImpl<T> implements SortComparatorBST<T> {

	private Comparator<T> comparator;
	
	public SortComparatorBSTImpl(Comparator<T> comparator) {
		super();
		this.comparator = comparator;
	}

	@Override
	public T[] sort(T[] array) {
		
		this.root = new BSTNode<T>();
		for(int i = 0; i < array.length; i ++) {
			this.insert(array[i]);
		}
		return order();
	}

	@Override
	public T[] reverseOrder() {
	
		T[] array = (T[]) new Comparable[size()];
		this.reverseOrder(array,root,0);
		return array;
	}
	
	private int reverseOrder(T[] array, BSTNode<T> node, int index) {
		
		if(!node.isEmpty()) {
			index = this.reverseOrder(array,(BSTNode<T>)node.getRight(),index);
			array[index++] = node.getData();
			index = this.reverseOrder(array,(BSTNode<T>)node.getLeft(),index);
		}
		return index;
	}

	private void insert(BSTNode<T> parent, BSTNode<T> node, T element) {
	
		if (node.isEmpty()) {
		   node.setParent(parent);
		   node.setData(element);
		   node.setLeft(new BSTNode<T>());
		   node.setRight(new BSTNode<T>());
		} else if (this.comparator.compare(element, node.getData()) < 0) {
		   insert(node, (BSTNode<T>) node.getRight(), element);
		} else if (this.comparator.compare(element, node.getData()) > 0) {
		   insert(node, (BSTNode<T>) node.getLeft(), element);
		}
	}
	 
	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}
	
}
