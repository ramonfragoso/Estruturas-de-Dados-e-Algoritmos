package adt.linkedList;

import java.lang.reflect.Array;
import java.util.Arrays;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		
		return this.head.isNIL();
	}

	@Override
	public int size() {
		
		if (this.isEmpty()) return 0;
		
		int tamanho = 0;
		SingleLinkedListNode<T> aux = this.head;
		while(!aux.isNIL()) {
			tamanho++;
			aux = aux.next;
		}
		return tamanho;
	}

	@Override
	public T search(T element) {
		
		if (this.isEmpty()) return null;
		
		SingleLinkedListNode<T> aux = this.head;
		while(!aux.isNIL()) {
			if(aux.data.equals(element)) return aux.getData();
			aux = aux.next;
		}
		return null;
	}

	@Override
	public void insert(T element) {
		
		SingleLinkedListNode<T> aux = this.head;
		
		if (this.head.isNIL()) {
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>(element, this.head);
			this.head = newHead;
		} else {
			while(!aux.next.isNIL()) {
				aux = aux.next;
			}
			SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, aux.next);
			aux.next = newNode;
		}
	}

	@Override
	public void remove(T element) {
		
		if(isEmpty()) return;
		if(this.head.getData().equals(element)) {
			this.head = this.head.next;
		} else {
			SingleLinkedListNode<T> aux = this.head;
			while(!aux.next.isNIL()) {
				if(aux.next.getData().equals(element)) {
					aux.next = aux.next.next;
					break;
				}
				aux = aux.next;
			}
		}

	}

	@Override
	public T[] toArray() {
		
		T[] array = (T[]) new Object[this.size()];
		
		if(isEmpty()) return array;
		
		int i = 0;
		SingleLinkedListNode<T> aux = this.head;
		while(!aux.isNIL()) {
			array[i++] = aux.getData();
			aux = aux.next;
		}
		return array;		
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
	
	public static void main(String[] args) {
		
		SingleLinkedListImpl<Integer> ll = new SingleLinkedListImpl<Integer>();
		ll.insert(5);
		ll.insert(4);
		ll.insert(3);
		ll.insert(2);
		
		ll.remove(5);
		System.out.println(Arrays.toString(ll.toArray()));
		
		
	}

}
