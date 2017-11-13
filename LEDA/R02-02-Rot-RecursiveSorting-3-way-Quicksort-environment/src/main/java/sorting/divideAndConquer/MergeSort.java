package sorting.divideAndConquer;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {


	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex) {
			int meio = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, meio);
			sort(array, meio + 1, rightIndex);
			merge(array, leftIndex, meio, rightIndex);
		}		
	}
	
	public void merge(T[] array, int ini, int meio, int fim) {
		
		T[] helper = (T[]) new Comparable[array.length];
		
		for(int i = 0; i < array.length; i++) {
			helper[i] = array[i];
		}
		
		int i = ini;
		int j = meio + 1;
		int k = ini;
		
		while(i <= meio && j <= fim) {
			if(helper[i].compareTo(helper[j]) < 0) {
				array[k] = helper[i++];
			} else array[k] = helper[j++];
			k++;
		}	
		
		
		while(i <= meio)
			array[k++] = helper[i++];
		
		while(j <= fim)
			array[k++] = helper[j++];
	}
	
	
	public static void main(String[] args) {
		
		Integer[] a = {55,66,77,88,99,1,2,3,4,5,6};
		MergeSort<Integer> b = new MergeSort<Integer>();
		
		b.sort(a, 0,a.length-1);
		
		System.out.println(Arrays.toString(a));
	}
	
}
