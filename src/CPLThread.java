import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;


public class CPLThread implements Runnable {
	/**
	 * Variables en comun entre todas las instancias de la clase.
	 */
	private static volatile SortedSet<Integer> allCPL; 
	private static volatile long totalTime=0;
	private static PrimeList pL;
	
	/**
	 * Varables propias de cada objeto.
	 */
	private Thread t;
	private SortedSet<Integer> cPL;
	private long time,sTime,fTime;
	private Integer first,latest;
	private String name;
	
	/**
	 * Constructor de la clase.
	 * En caso de ser la primer instancia, inicializa allCPL.
	 * @param name 
	 * @param first 
	 * @param latest
	 * @param pL
	 */
	public CPLThread(String name,Integer first, Integer latest, PrimeList pL) {
		this.pL= pL;
		this.first=first;
		this.latest=latest;
		this.name=name;
		cPL = new TreeSet<Integer>();
		if(allCPL==null){
			allCPL = new TreeSet<Integer>();
		}
		t = new Thread(this);		
	}
	
	public void start(){
		this.t.run();
	}
	
	public void join() throws InterruptedException{
		this.t.join();
	}

	@Override
	public void run() {
		sTime= System.currentTimeMillis(); //Tiempo inicio
		for (int i =first; i < latest; i++) {
			if (!pL.getBooleanList()[i]) {
				if(primeCirculator(i)){
					allCPL.add(i);
					cPL.add(i);
				}
			}
		}
		fTime = System.currentTimeMillis(); //Tiempo fin
		totalTime+=(fTime - sTime);
		report();
	}
	
	private void report(){
		System.out.println("- - - - - -  - - - - - - - - -");
		System.out.println("REPORTE THREAD "+name);
		System.out.println("Hay "+cPL.size()+" Primos Circulares de "+first+" hasta "+latest);
		System.out.println("El tiempo de ejecucion de este Thread es de " + (fTime - sTime)+"mls");
		time= time+(fTime - sTime);
		System.out.println("El tiempo total hasta el momento es de "+totalTime+"mls");
	}	
	
	/**
	 * 
	 * @param current
	 * @return True si un numero es Primo Circular, False en caso contrario.
	 */
	private boolean primeCirculator(Integer current){
		if(allCPL.contains(current)){
			return true;
		}else{
			TreeSet<Integer> currentRotations= rotations(current);
			boolean isPrime= true;
			currentRotations.remove(current);
			while(isPrime && (!currentRotations.isEmpty())){
				if(!pL.getBooleanList()[currentRotations.first()]){
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
	
	
	public static void main(String[] args) {
		Integer LATEST=1000000;
		PrimeList pL= new PrimeList(LATEST);
		CPLThread hilo1 = new CPLThread("Hilo 1",1, 200000, pL);
		CPLThread hilo2 = new CPLThread("Hilo 2",200000, 600000, pL);
		CPLThread hilo3 = new CPLThread("Hilo 3",600000, 1000000, pL);
		hilo1.start();
		hilo2.start();
		hilo3.start();
		try {
			hilo1.join();
			hilo2.join();
			hilo3.join();
			
		} catch (Exception e) {
			System.out.println("¡Ojo! Algo anda mal.");
		}
		System.out.println("\nLa lista total de Primos Ciculares es: "+allCPL);
		System.out.println("Calculada en "+totalTime+"mls");
	}

}
