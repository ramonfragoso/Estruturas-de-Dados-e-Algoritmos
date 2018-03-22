package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {

		if(this.elements == this.table.length) throw new HashtableOverflowException();
		int probing = 0;
		int hash;
		
		while (true) {
			hash = Math.abs((((HashFunctionQuadraticProbing) this.hashFunction).hash(((HashtableElement) element).hashCode(), probing)));
			
			if(probing == this.table.length) return;
			if(this.table[hash] == null) {
				this.table[hash] = element;
				this.elements++;
				return;
			}
			if(this.table[hash].equals(deletedElement)) {
				this.table[hash] = element;
				this.elements++;
				return;
			}
			probing++;
			this.COLLISIONS++;
		}	
	
	}

	@Override
	public void remove(T element) {

		int probing = 0;
		int hash;
		
		while (true) {
			
			hash = Math.abs((((HashFunctionQuadraticProbing) this.hashFunction).hash(((HashtableElement) element).hashCode(), probing)));
			if(probing == this.table.length) return;
			if(this.table[hash] == null) return;
			if(this.table[hash].equals(element)) {
				this.table[hash] = deletedElement;
				this.elements--;
				return;
			}
			probing++;
		}
	}

	@Override
	public T search(T element) {
		int probing = 0;
		int hash;
		
		while (true) {
			
			hash = Math.abs((((HashFunctionQuadraticProbing) this.hashFunction).hash(((HashtableElement) element).hashCode(), probing)));
			if(probing == this.table.length) return null;
			if(this.table[hash] == null) return null;
			if(this.table[hash].equals(element)) {
				return element;
			}
			probing++;
		}
	}

	@Override
	public int indexOf(T element) {
		int probing = 0;
		int index = -1;
		int hash;
		
		while (true) {
			
			hash = Math.abs((((HashFunctionQuadraticProbing) this.hashFunction).hash(((HashtableElement) element).hashCode(), probing)));
			if(probing == this.table.length) return -1;
			if(this.table[hash] == null) return -1;		
			if(this.table[hash].equals(element)) {
				index = hash;
				break;
			}
			probing++;
		}
		return index;
	}
}
