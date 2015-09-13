import java.util.SortedSet;
import java.util.TreeSet;


public class PrimeList {
	private Integer latest;
	private boolean calculated;
	private boolean calculatedBoolean;
	private boolean[] notPrime;
	SortedSet<Integer> primeSet;


	/**
	 * Constructor, asigna a 'latest' hasta que numero se va a generar la lista de primos.
	 * @param latest
	 */
	public PrimeList(Integer latest) {
		calculated=false;
		calculatedBoolean=false;
		if(latest>0){
			this.latest=latest;
		}else{
			this.latest=0;
		}

	}

	/**
	 * Verifica si esta calculada la lista de primos y la retorna.
	 * En caso contrario, realiza el calculo antes de retornarla.
	 * @return SortedSet<Integer>
	 */
	public SortedSet<Integer> getList(){
		if(calculated){
			return primeSet;
		}else{
			calculated=true;
			calculatedBoolean=true;
			return calculateList();
		}
	}


	/**
	 * 	Verifica si el areglo esta calculado y lo retorna.
	 *  En caso contrario, realiza el calculo antes de retornarla.
	 * @return boolan[]
	 */
	public boolean[] getBooleanList(){
		if(calculatedBoolean){
			return notPrime;
		}else{
			calculatedBoolean=true;
			return calculateBoolanList();
		}		
	}	

	/**
	 * Este metodo esta basado en la tecnica de Criba de Eratostenes.
	 * Para generar la lista de primos hasta latest.
	 * @return Lista de Primos.
	 */
	private SortedSet<Integer> calculateList(){
		notPrime= new boolean[latest+1]; 
		primeSet = new TreeSet<Integer>();
		notPrime[0] = true;
		if(latest>0){
			notPrime[1] = true;
		}
		for (int i = 2; i < latest; i++) {
			if(!notPrime[i]){
				primeSet.add(i);
				int cont=2;
				int started=i*cont;
				while (started<=latest){
					notPrime[started]=true;
					cont++;
					started=i*cont;
				}	
			}
		}
		return primeSet;
	}

	/**
	 * Este metodo retorna un arreglo de boolean, en donde, 
	 * Si A[i] es true, significa que 'i' NO es primo.
	 * @return boolean[]
	 */
	private boolean[] calculateBoolanList(){
		notPrime= new boolean[latest+1]; //Si notPrime[i]=false entonces i es primo, caso contrario i no es primo.
		notPrime[0] = true;
		if(latest>0){
			notPrime[1] = true;
		}
		for (int i = 2; i < latest; i++) {
			if(!notPrime[i]){
				int cont=2;
				int started=i*cont;
				while (started<=latest){
					notPrime[started]=true;
					cont++;
					started=i*cont;
				}
			}
		}
		return notPrime;
	}
	

	/**
	 * Este metodo devuelve true si num es primo, en caso contrario retorna false.
	 * NO LO UTILIZO MAS AL IMPLEMENTAR EL ALGORITMO DE LA CRIBA ERATOSTENES.
	 * TIEMPO DE EJECUCION DE 800mls a 40mls.
	 * @param num
	 * @return
	 */
	private boolean isPrime(Integer num){ 
		if (num==1 || num==0){
			return false;
		}else{
			Integer numeroAux=2;
			Integer restoDiv;
			while(numeroAux<=Math.sqrt(num)){
				restoDiv=num%numeroAux;
				if (restoDiv==0){
					//Si encuentro un numero 'x' tal que 1<x<numero y es divisor de 'numero', entonces 'numero' no es primo. Por lo tanto retorno false.
					return false;
				}
				numeroAux++;
			}
			//Si llegue hasta aqui, significa que el numero es primo. Retorno true.
			return true;
		}
	}

}
