package adt.queue;

import java.util.Arrays;

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
		if(tail >= 0) return this.array[0];
		return null;
	}

	@Override
	public boolean isEmpty() {
		return tail < 0;
	}

	@Override
	public boolean isFull() {
		
		return this.tail == this.array.length-1;
	}

	private void shiftLeft() {
		
		T aux;
		for(int i = 0; i < tail; i++) {
			aux = array[i+1];
			array[i] = aux;
		}
		tail--;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		
		if(this.isFull()) throw new QueueOverflowException();
		
		this.tail++;
		this.array[tail] = element;		
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		
		if(this.isEmpty()) throw new QueueUnderflowException();
		
		T retorno = this.head();
		this.shiftLeft();
		this.array[tail + 1] = null;
		return retorno;
	}

	public static void main(String[] args) throws QueueOverflowException, QueueUnderflowException {
		
		QueueImpl<Integer> queu = new QueueImpl<Integer>(5);
		queu.enqueue(5);
		queu.enqueue(2);
		queu.enqueue(4);
		queu.enqueue(1);
		queu.enqueue(3);

		System.out.println(queu.head());
		System.out.println(Arrays.toString(queu.array));
	}
}
