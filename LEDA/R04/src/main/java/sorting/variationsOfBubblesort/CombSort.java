package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {
      if (array.length <= 0)
         return;
      if (leftIndex >= rightIndex)
         return;
      if (leftIndex > array.length || rightIndex > array.length)
         return;

      //define o gap
      int gap = rightIndex;
      while (gap >= 1) {

         int j = gap;
         for (int i = 0; i <= rightIndex; i++) {
            if (i + j < array.length && array[i].compareTo(array[i + j]) > 0) {
               util.Util.swap(array, i, i + j);
            }
         }
         gap = (int) (gap / 1.3);
      }
   }

}
