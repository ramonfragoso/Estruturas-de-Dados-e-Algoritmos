package adt.stack;

import java.util.Arrays;

public class StackImpl<T> implements Stack<T> {

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if (top < 0) return null;
		return this.array[top];
	}

	@Override
	public boolean isEmpty() {
		
		return this.top() == null;
	}

	@Override
	public boolean isFull() {
		
		return top == this.array.length-1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		
		if(this.isFull()) throw new StackOverflowException();
		
		top++;
		this.array[top] = element;
	}

	@Override
	public T pop() throws StackUnderflowException {
		
		if(this.isEmpty()) throw new StackUnderflowException();
		
		T retorno = this.array[top];
		this.array[top--] = null;
		return retorno;
	}
	
	public static void main(String[] args) throws StackOverflowException, StackUnderflowException {
		
		StackImpl<Integer> stack = new StackImpl<Integer>(5);
		stack.push(5);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.pop();
		stack.pop();
		stack.pop();
		stack.push(3);
		stack.push(4);
		System.out.println(stack.top());
		System.out.println(Arrays.toString(stack.array));
	}
	
	
}
