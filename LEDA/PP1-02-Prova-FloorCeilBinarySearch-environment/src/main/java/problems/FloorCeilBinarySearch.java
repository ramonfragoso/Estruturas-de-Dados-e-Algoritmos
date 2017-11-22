package problems;


/**
 * Calcula o floor e ceil de um numero em um array usando a estrategia de busca
 * binaria.
 * 
 * Restricoes: 
 * - Algoritmo in-place (nao pode usar memoria extra a nao ser variaveis locais) 
 * - O tempo de seu algoritmo deve ser O(log n).
 * 
 * @author Adalberto
 *
 */
public class FloorCeilBinarySearch implements FloorCeil {

	@Override
	
	public Integer floor(Integer[] array, Integer x) {
			
		int indice = buscaBinariaFloor(array, x, 0, array.length-1);
		if(indice != -1) {
			return array[indice];
		}
		return -1;
	}
	@Override
	public Integer ceil(Integer[] array, Integer x) {

		int indice = buscaBinariaCeil(array, x, 0, array.length-1);
		if(indice != -1) {
			return array[indice];
		}
		return -1;
	}
	
	public static int buscaBinariaFloor(Integer[] array, Integer x, int ini, int fim) {
		
		if(x < array[ini]) return -1;
		if(x > array[fim]) return fim;
		if(x == array[ini]) return ini;
		if(x == array[fim]) return fim;
		
		if (ini > fim) return -1;
		int meio = (ini+fim)/2;
		
		if(array[meio] <= x) {
			return meio;
		}

		if(array[meio] > x) {
			return buscaBinariaFloor(array, x, ini, meio-1);
		} 
		else if(array[meio] < x) {
			return buscaBinariaFloor(array, x, meio+1, fim);
		}
		
		return -1;
	}
	
	public static int buscaBinariaCeil(Integer[] array, Integer x, int ini, int fim) {
		
		// Condicoes basicas para retorno imediato da funcao
		if(x <= array[ini]) return ini;
		if(x > array[fim]) return -1;
		if(x == array[ini]) return ini;
		if(x == array[fim]) return fim;
		
		// condicao de parada da chamada recursiva
		if (ini > fim) return -1;
		
		int meio = (ini+fim)/2;
		
		// se o numero 
		if(array[meio] >= x) {
			return meio;
		}

		if(array[meio] > x) {
			return buscaBinariaFloor(array, x, ini, meio-1);
		} 
		else if(array[meio] < x) {
			return buscaBinariaFloor(array, x, meio+1, fim);
		}
		
		return -1;
	}

}
