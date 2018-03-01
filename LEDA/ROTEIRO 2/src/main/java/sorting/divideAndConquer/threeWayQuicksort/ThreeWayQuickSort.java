package sorting.divideAndConquer.threeWayQuicksort;

import sorting.AbstractSorting;

public class ThreeWayQuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

   /**
    * No algoritmo de quicksort, selecionamos um elemento como pivot,
    * particionamos o array colocando os menores a esquerda do pivot 
    * e os maiores a direita do pivot, e depois aplicamos a mesma estrategia 
    * recursivamente na particao a esquerda do pivot e na particao a direita do pivot. 
    * 
    * Considerando um array com muitoe elementos repetidos, a estrategia do quicksort 
    * pode ser otimizada para lidar de forma mais eficiente com isso. Essa melhoria 
    * eh conhecida como quicksort tree way e consiste da seguinte ideia:
    * - selecione o pivot e particione o array de forma que
    *   * arr[l..i] contem elementos menores que o pivot
    *   * arr[i+1..j-1] contem elementos iguais ao pivot.
    *   * arr[j..r] contem elementos maiores do que o pivot. 
    *   
    * Obviamente, ao final do particionamento, existe necessidade apenas de ordenar
    * as particoes contendo elementos menores e maiores do que o pivot. Isso eh feito
    * recursivamente. 
    **/
   @Override
   public void sort(T[] array, int leftIndex, int rightIndex) {

      if (rightIndex <= leftIndex)
         return;

      int lt = leftIndex;
      int gt = rightIndex;
      int i = leftIndex + 1;

      int pivot_pos = leftIndex;
      T pivot_valor = array[pivot_pos];

      while (i <= gt) {
         if (array[i].compareTo(pivot_valor) < 0) {
            util.Util.swap(array, i++, lt++);
         } else if (pivot_valor.compareTo(array[i]) < 0) {
            util.Util.swap(array, i, gt--);
         } else {
            i++;
         }
      }
      sort(array, leftIndex, lt - 1);
      sort(array, gt + 1, rightIndex);
   }

//   	public static void main(String[] args) {
//   		Integer[] array = {5,3,2,2,3,45,6,5,4,3,3};
//   		ThreeWayQuickSort<Integer> b = new ThreeWayQuickSort<Integer>();
//   		
//   		b.sort(array);
//   		
//   		System.out.println(Arrays.toString(array));
//   	}

}
