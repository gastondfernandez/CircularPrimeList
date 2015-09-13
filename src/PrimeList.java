import java.util.SortedSet;
import java.util.TreeSet;


public class PrimeList {
	private Integer first=0;
	private Integer latest;
	private boolean calculated;
	private boolean[] notPrime;
	SortedSet<Integer> primeSet;
	
	
	
	public PrimeList(Integer latest) {
		calculated=false;
		if(first.compareTo(latest)<0){
			this.first=first;
			this.latest=latest;
		}else{
			this.latest=0;
		}

	}
	
	
	public SortedSet<Integer> getList(){
		if(calculated){
			return primeSet;
		}else{
			calculated=true;
			return calculateList();
		}
	}
	
	public boolean[] getBooleanList(){
		if(calculated){
			return notPrime;
		}else{
			calculateList();
			calculated=true;
			return notPrime;
		}		
	}	
		
	/**
	 * Este metodo esta basado en la tecnica de Criba de Eratostenes.
	 * Si verifico que i es primo, entonces todos los x (tal que x=i*[2,3,4,...,limit]) 
	 * no son primos.
	 * @return Lista de Primos
	 */
	private SortedSet<Integer> calculateList(){
		notPrime= new boolean[latest+1]; //Si notPrime[i]=false entonces i es primo, caso contrario i no es primo.
		primeSet = new TreeSet<Integer>(); //conjunto de primos
		notPrime[0] = true;
		if(latest>0){
			notPrime[1] = true;
		}
		for (int i = 2; i < latest; i++) {
			if(!notPrime[i]){
				primeSet.add(i);
				//if(isPrime(i)){
					int cont=2;
					int started=i*cont;
					while (started<=latest){
						notPrime[started]=true;
						cont++;
						started=i*cont;
					}
				//}
				
			}
		}
		return primeSet;
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
