package sorts;

import java.util.Arrays;

public class Sort {
	
	public static int particionamentoLomuto(int[] array, int ini, int fim) {
		
		// cria variaveis
		// j elemento a ser trocado antes do pivot
		// pivot primeiro elemento
		// aux variavel pra swap
		int j = ini;
		int pivot = array[ini];
		int aux;
		
		// varre o array iniciando do segundo elemento, se o valor for menor que o pivor
		// troca com j
		for(int i = ini+1; i <= fim; i++) {
			if(array[i] < pivot) {
				j++;
				aux = array[i];
				array[i] = array[j];
				array[j] = aux;
			}
		}
		
		//troca o pivot com j em seguida retorna j que sera a posicao do pivot para efetuar novos particionamentos
		aux = array[j];
		array[j] = pivot;
		array[ini] = aux;
		return j;
	}
	
	public static int particionamentoHoare(int[] array, int ini, int fim) {
		
		// inicializa dois ponteiros onde esq eh o inicio e dir o fim do array
		int pivot = array[ini];
		int esq = ini - 1;
		int dir = fim + 1;
		
		while(true) {
		
			// aumenta esq ate que array[esq] seja maior ou igual ao pivot
			do {
				esq++;
			}while(array[esq] < pivot);

			// diminui dir ate que array[dir] seja menor ou igual ao pivot
			do {
				dir--;
			}while(array[dir] > pivot);
			
			// se esq for menor que dir troca a posicao de array
			if(esq < dir) {
				int aux = array[esq];
				array[esq] = array[dir];
				array[dir] = aux;
			} else {
				// se nao retorna a posicao de dir que sera a posicao do novo pivot
				return dir;
			}
		}
	}
	
	public static void QuickSorting(int[] array, int ini, int fim) {
		
		if(ini < fim) {
			int pos_pivot = particionamentoHoare(array, ini, fim);
			QuickSorting(array, ini, pos_pivot);
			QuickSorting(array, pos_pivot+1, fim);
		}
	}
	
	public static void merge(int[] array, int ini, int meio, int fim) {
		
		int esq = ini;
		int dir = meio+1;
		int indexArray = ini;
		
		// cria array auxiliar identico ao original
		int[] helper = new int[array.length];
		
		for(int i = 0; i < array.length; i++) {
			helper[i] = array[i];
		}
		
		// divide o array no meio e vai adicionando os menores de cada lado ao array original
		while(esq <= meio && dir <= fim) {
			if(helper[esq] < helper[dir]) {
				array[indexArray] = helper[esq];
				esq++;
				indexArray++;
			} else {
				array[indexArray] = helper[dir];
				dir++;
				indexArray++;
			}
		}
		
		// o que sobrar é adicionado ao array origem now
		while(esq <= meio) {
			array[indexArray++] = helper[esq++];
		}
		
		while(dir <= fim) {
			array[indexArray++] = helper[dir++];
		}
	}
	
	public static void mergeSorting(int[] array, int ini, int fim) {
		
		if(ini < fim) {
			int meio = (ini + fim)/2;
			mergeSorting(array, ini, meio);
			mergeSorting(array, meio+1, fim);
			merge(array, ini, meio, fim);
		}
	}
	
	public static int[] countingSorting(int[] array, int ini, int fim) {
		
		// encontra o menor e maior elementos
		int maior = array[ini], menor = array[ini];
		
		for(int i = ini; i <= fim; i++) {
			if(maior < array[i]) {
				maior = array[i];
			}
			if(menor > array[i]) {
				menor = array[i];
			}
		}
		
		// cria um array de contagem e os elementos de array nele de acordo com o indice
		//somando um
		int[] c = new int[maior - menor + 1];
		
		for (int i = ini; i <= fim;i++) {
			c[array[i] - menor]++;
		}
		
		// faz a cumulativa de c
		for(int i = 1; i < c.length; i++) {
			c[i] = c[i] + c[i-1];
		}

		// cria um array de retorno ordenado
		int[] b = new int[array.length];
		
		// varre array de tras pra frente onde verifica a posicao em c[array[i]] e passa pra b
		for(int i = array.length-1; i >= 0; i--) {
			b[c[array[i] -menor] - 1] = array[i];
			c[array[i]-menor]--;
		}
		
		System.out.println(Arrays.toString(b));
		return b;
	}
	
	public static void bubbleSort(int[] array, int ini, int fim) {
		
		int aux;
		for (int i = ini; i <= fim; i++) {
			for (int j = ini; j <= fim-1; j++) {
				if(array[j] > array[j+1]) {
					aux = array[j];
					array[j] = array[j+1];
					array[j+1] = aux;
				}
			}
		}
		
	}
	
	public static void selectionSort(int[] array, int ini, int fim) {
		
		for (int i = ini; i <= fim; i++) {
			int menor = i;
			for (int j = i + 1; j <= fim; j++) {
				if (array[menor] > array[j]) {
					menor = j;
				}
			}
			int aux = array[i];
			array[i] = array[menor];
			array[menor] = aux;	
		}
	}

	public static void insertionSort(int[] array, int ini, int fim) {
		
		int aux, j;
		
		for (int i = ini + 1; i <= fim; i++) {

			aux = array[i];
			j = i;
			
			while(j > ini && array[j-1] > aux) {
				array[j] = array[j-1];
				j--;
			}
			array[j] = aux;		
		}
		
		
	}

	public static void main(String[] args) {
		
		int[] array1 = {6,6,6,6,6,6};
		int[] array2 = {-1,-5,-2,-5,-4,-3,-2};		
		int[] array3 = {5,-1,-1,2,3};		
		int[] array4 = {6,5,4,3,2,1};		
		int[] array5 = {1,2,3,4,5};		
		int[] array6 = {6,6,6,6,6,5,3,2,4,1,2,5,5,5};		

		insertionSort(array1, 0, array1.length-1);
		insertionSort(array2, 0, array2.length-1);
		insertionSort(array3, 0, array3.length-1);
		insertionSort(array4, 0, array4.length-1);
		insertionSort(array5, 0, array5.length-1);
		insertionSort(array6, 5, 9);

		
		System.out.println(Arrays.toString(array1));
		System.out.println(Arrays.toString(array2));
		System.out.println(Arrays.toString(array3));
		System.out.println(Arrays.toString(array4));
		System.out.println(Arrays.toString(array5));
		System.out.println(Arrays.toString(array6));

	}
}
