package sorts;

import java.util.Arrays;

public class Sort {
	
	public static int particionamento(int[] array, int ini, int fim) {
		
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
	
	public static void QuickSorting(int[] array, int ini, int fim) {
		
		if(ini < fim) {
			int pos_pivot = particionamento(array, ini, fim);
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

	public static void main(String[] args) {
		
		int[] a = {6,6,6,-1,6,6};		
		countingSorting(a, 0, a.length-1);
	}
}
