package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void insert(T element) {

		DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<T>(element, new DoubleLinkedListNode<T>(), last);
		
		if(this.head.isNIL())
			super.head = last = aux;
		else {
			last.next = aux;
			last = aux;
		}
	}
	
	@Override
	public void remove(T element) {
		
		if(isEmpty()) return;
		
		if(this.head.getData().equals(element))
			this.removeFirst();
		else if (this.last.getData().equals(element))
			this.removeLast();
		else {
			DoubleLinkedListNode<T> aux = this.getLast();
			DoubleLinkedListNode<T> toRemove = null;
			
			while(!aux.isNIL() && aux.getPrevious() != null) {
				if(aux.getData().equals(element))
					toRemove = aux;
				aux = aux.getPrevious();
			}
			if (toRemove != null) {
				toRemove.getPrevious().setNext(toRemove.getNext());
				((DoubleLinkedListNode<T>) toRemove.getNext()).setPrevious(toRemove.getPrevious());
			}	
		}
	}
	
	
	@Override
	public void insertFirst(T element) {
		
		DoubleLinkedListNode<T> aux = new DoubleLinkedListNode<T>(element, (DoubleLinkedListNode<T>) this.head, null);
		((DoubleLinkedListNode<T>) this.head).setPrevious(aux);
		this.head = aux;
		
		if(this.last.isNIL())
			this.setLast(aux);
		else if (this.last.previous == null)
			this.last.setPrevious(aux);
	}

	@Override
	public void removeFirst() {
		
		if(this.isEmpty()) return;
		if(!this.head.next.isNIL()) {
			this.setHead(this.head.next);
			((DoubleLinkedListNode<T>) this.head).setPrevious(null);
		} else 
			this.setHead(new DoubleLinkedListNode<T>());
	}

	@Override
	public void removeLast() {
		
		if(this.isEmpty()) return;
		if(this.size() == 1) 
			super.head = last = (DoubleLinkedListNode<T>) super.head.next;
		else {
			this.last.getPrevious().setNext(new DoubleLinkedListNode<T>());
			this.last = this.last.getPrevious();
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
