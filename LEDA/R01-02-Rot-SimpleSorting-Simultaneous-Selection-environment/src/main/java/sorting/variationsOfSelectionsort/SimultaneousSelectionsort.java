package sorting.variationsOfSelectionsort;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * This algorithm applies two selection sorts simultaneously. In a same
 * iteration, a selection sort pushes the greatest elements to the right and
 * another selection sort pushes the smallest elements to the left. At the end
 * of the first iteration, the smallest element is in the first position (index
 * 0) and the greatest element is the last position (index N-1). The next
 * iteration does the same from index 1 to index N-2. And so on. The execution
 * continues until the array is completely ordered.
 */
public class SimultaneousSelectionsort<T extends Comparable<T>> extends AbstractSorting<T> {
	
	public void sort(T[] array, int leftIndex, int rightIndex) {
		
		int maior, menor;
		
		for (int i = 0; i < rightIndex; i++) {
			maior = i;
			menor = i;
			for (int j = i+1; j <= rightIndex; j++) {
				if(array[j].compareTo(array[maior]) > 0) {
					maior = j;
				}
				if(array[j].compareTo(array[menor]) < 0) {
					menor = j;
				}
			}
			util.Util.swap(array, i, menor);
			util.Util.swap(array, array.length - 1 - i, i);
		}
	
	}
	
	
	
	
	public static void main(String[] args) {
		
		Integer[] a = {6,5,3,4,2,1};
		SimultaneousSelectionsort<Integer> b = new SimultaneousSelectionsort<Integer>();
		
		b.sort(a);
		
		System.out.println(Arrays.toString(a));
	}
	
	
}
