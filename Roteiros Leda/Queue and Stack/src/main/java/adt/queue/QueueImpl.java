package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		if(this.isEmpty()) return null;
		return this.array[0];
	}

	@Override
	public boolean isEmpty() {
		return(tail == -1);
	}

	@Override
	public boolean isFull() {
		
		return (this.tail == this.array.length-1);
	}

	private void shiftLeft() {
		
		T aux;
		for(int i = 1; i <= tail; i++ ) {
			aux = this.array[i];
			this.array[i] = this.array[i-1];
			this.array[i-1] = aux;
		}
		this.tail -= 1;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		
		if(this.isFull()) throw new QueueOverflowException();
		this.tail += 1;
		this.array[tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		
		if(this.isEmpty()) throw new QueueUnderflowException();
		
		T retorno = this.head();
		this.shiftLeft();
		this.array[this.array.length-1] = null;
		return retorno;
	}
	
	public T[] getArray() {
		return array;
	}
}
