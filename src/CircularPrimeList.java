
import java.util.TreeSet;


public class CircularPrimeList{
	private static PrimeList pl;
	private static TreeSet<Integer> cpl;
	private TreeSet<Integer> correntT;
	private static final Integer LATEST=1000000;



	public CircularPrimeList(Integer limit) {
		pl = new PrimeList(limit);
		System.out.println("Desde el 0 a "+LATEST);
		System.out.println("Existen "+pl.getList().size()+" Primos");

		calculatorPrimeCircularList();

		System.out.println("Existen "+cpl.size()+" Primos Circulares");
		System.out.println("Estos son "+cpl.toString());

	}


	/**
	 * Este metodo genera el conjunto de primos circulares.
	 */
	private void calculatorPrimeCircularList(){
		cpl = new TreeSet<Integer>(); 
		cpl.add(2); 
		for (Integer corrent : pl.getList()) {
			if(primeCirculator(corrent)){
				TreeSet<Integer> rotations = rotations(corrent);
				cpl.addAll(rotations);
			}
		}	
	}


	/**
	 * Este metodo calcula si un numero es primo circular.
	 * @param current
	 * @return True en caso de que sea Primo Circular, o false en caso contrario.
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
	 * @return conjunto con las n rotaciones de 'number'.
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
	private Integer displace(Integer number, Integer numDes){
		String cadena = Integer.toString(number);
		for (int i = 0; i < numDes; i++) {
			cadena=cadena.substring(1, cadena.length())+cadena.charAt(0);
		}
		return Integer.parseInt(cadena);
	}


	public static void main(String[] args) {
		long sTime, fTime; 
		sTime = System.currentTimeMillis(); //Tiempo inicio

		CircularPrimeList cl = new CircularPrimeList(LATEST);

		fTime = System.currentTimeMillis(); //Tiempo fin
		System.out.println("El Tiempo de ejecucion es de " + (fTime - sTime)+"mls"); //Mostramos en pantalla el tiempo de ejecución en milisegundos
	}	


}
