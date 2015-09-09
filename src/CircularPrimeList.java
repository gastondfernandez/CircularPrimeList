import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;


public class CircularPrimeList{
	private static PrimeList pl;
	private static TreeSet<Integer> cpl;
	private TreeSet<Integer> correntT;
	private static final Integer LATEST=1000000;
	 
	public static void main(String[] args) {
		long TInicio, TFin; 
		TInicio = System.currentTimeMillis(); //Tiempo inicio
		
		CircularPrimeList cl = new CircularPrimeList(LATEST);
		
		TFin = System.currentTimeMillis(); //Tiempo fin
		System.out.println("\nTiempo de ejecucion en milisegundos: " + (TFin - TInicio)); //Mostramos en pantalla el tiempo de ejecución en milisegundos
	}
	

	
	public CircularPrimeList(Integer limit) {
		pl = new PrimeList(limit);
		System.out.println("Desde el 0 a "+LATEST);
		System.out.println("Existen "+pl.getList().size()+" Primos");
		
		calculatorPrimeCircularList();
		System.out.println("Existen "+cpl.size()+" Primos Circulares");
		System.out.println("Estos son "+cpl.toString());
	
	}
	
	private void calculatorPrimeCircularList(){
		cpl = new TreeSet<Integer>(); //Nuevo conjunto de primos circulares.
		cpl.add(2); //Necesito al menos un elemento, agrego el primer primo circular.
		for (Integer corrent : pl.getList()) {
			if(primeCirculator(corrent)){
				TreeSet<Integer> rotations = rotations(corrent);
				//Si un numero es Primo Circular, todas las rotaciones de sus digitos tambien lo son.
				//Por lo tanto agrego todas sus rotaciones al conjunto.
				cpl.addAll(rotations);
			}
		}	
	}
	
	
	/**
	 * 
	 * @param corrent
	 * @return
	 */
	private boolean primeCirculator(Integer current){
		if(cpl.contains(current)){ //Verifico que no este calculado.
			return true;
		}else{
			//Caso contrario lo calculo.
			TreeSet<Integer> currentRotations= rotations(current);
			boolean isPrime= true;
			currentRotations.remove(current);
			while(isPrime && (!currentRotations.isEmpty())){
				if(pl.getList().contains(currentRotations.first())){
					currentRotations.remove(currentRotations.first());
				}else{
					return false;
				}
			}
			return true;
		}
	}
	

	
	/**
	 * @return una lista con las n rotaciones de 'number'.
	 */
	private TreeSet<Integer> rotations(Integer number){
		TreeSet<Integer> numberSet = new TreeSet<Integer>(); 
		Integer length = Integer.toString(number).length();
		Integer numberAux= number;
		for (int i = 1; i <= length; i++) {
			numberSet.add(numberAux);
			numberAux=displace(number,i);
		}
		return numberSet;	
	}
	
	
	/**
	 * @return numero con 'numDes' rotaciones.
	 */
	private Integer displace(Integer numero, Integer numDes){
		String cadena = Integer.toString(numero);
		for (int i = 0; i < numDes; i++) {
			cadena=cadena.substring(1, cadena.length())+cadena.charAt(0);
		}
		return Integer.parseInt(cadena);
	}
	
	

}
