package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
	
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length <= 0) return;
		if (leftIndex >= rightIndex) return;
		if (leftIndex > array.length || rightIndex > array.length) return;
		
		int i = leftIndex + 1;
		int j = leftIndex + 2;
		
		while (i <= rightIndex) {
			
			if(array[i].compareTo(array[i-1]) >= 0) {
				i = j;
				j++;
			} else {
				util.Util.swap(array, i, i-1);
				i--;
				if (i == 0) {
					i = 1;
				}
			}
		}
 		
	}
	
}
