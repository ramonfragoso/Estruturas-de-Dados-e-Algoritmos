package adt.hashtable.closed;

import util.Util;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends
		AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size. For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize,
			HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			realSize = this.getPrimeAbove(desiredSize); // real size must the
														// the immediate prime
														// above
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method,
				realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		while(!util.Util.isPrime(++number));
		return number;
	}

	@Override
	public void insert(T element) {
		
		if(element == null) return;
		int hash = ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element);
		int elementIndex = this.chainIndexOf(element, hash);
		
		if(this.contains(element))
			this.insertNewElement(element, hash);
		else 
			((LinkedList<T>) this.table[hash]).set(elementIndex, element);
	}
	
	private void insertNewElement(T element, int hash) {
		if(this.table[hash] == null)
			this.table[hash] = new LinkedList<T>();
		if(((LinkedList<T>) this.table[hash]).size() > 0) this.COLLISIONS++;
		((LinkedList<T>) this.table[hash]).add(element);
		this.elements++;
	}

	@Override
	public void remove(T element) {
		if(element == null) return;
		int hash = ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element);
		int elementIndex = this.chainIndexOf(element, hash);
		
		if(this.contains(elementIndex)) {
			((LinkedList<T>) this.table[hash]).remove(elementIndex);
			this.elements--;
			if(((LinkedList<T>) this.table[hash]).size() > 0) this.COLLISIONS--;
		}

	}
	
	private int chainIndexOf(T element, int hash) {
		
        if (this.table[hash] == null) {
            return -1;
        }
        for (int index = 0; index < ((LinkedList<T>) this.table[hash]).size(); index++) {
            T tableElement = ((LinkedList<T>) this.table[hash]).get(index);
            if (tableElement.hashCode() == element.hashCode()) {
                return index;
            }
        }

        return -1;
	}

	@Override
	public T search(T element) {
		if(this.contains(element))
			return element;
		return null;
	}
	
	private boolean contains(T element) {
		return contains(indexOf(element));
	}
	
	private boolean contains(int chainIndex) {
		return (chainIndex != -1);
	}

	@Override
	public int indexOf(T element) {
		if(element  == null) return -1;
        int hash = ((HashFunctionClosedAddress<T>) this.hashFunction).hash(element);
        
        if (this.table[hash] == null || !this.contains(element))
        	return -1;
        return hash;
	}

}
