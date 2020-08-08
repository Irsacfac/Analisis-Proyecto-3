package elementos;

import otros.IConstants;

public class Programa implements IConstants{
	
	private int genActual;
	public int mapa[][]= new int[20][20];
	public Algoritmo miAlgoritmo;
	private Genetico miGenetico;
	private int[] markovDosBits;
	public int[][] markovTresBits;
	private int[] componente;
	private Robot[] robots;
	
	public Programa() {
		markovDosBits = new int[] {0, 35, 70, 100};
		markovTresBits = new int[][] {{25,34,19,40,70,10,91,52},{25,34,19,40,70,91,10,52},{25,34,19,22,10,10,10,25}};
		componente = new int[] {0, 1, 2};
		robots = new Robot[2*CANT_PAREJAS];
		primerGeneracion();
		miAlgoritmo= new Algoritmo();
		miAlgoritmo.escribir(robots);
		miAlgoritmo.cargarTerreno(mapa);
		miGenetico= new Genetico(this);
		pruebas();
		//miAlgoritmo.camara(mapa, robots[0]);
		
		/*String a="";
		for(int i=0; i<robots[0].getCromosomas().length; i++) {
			if(robots[0].getCromosomas()[i]==true) {
				a+=1;
			}else {
				a+=0;
			}
		}
		System.out.println(a);
		System.out.println("C�mara: "+robots[0].getCamera());
		System.out.println("Bater�a: "+robots[0].getBateria());
		System.out.println("Motor: "+robots[0].getMotor());*/
	}
	
	private void primerGeneracion() {
		for(int k=0;k<robots.length;k++) {
			boolean[] cromosomas=new boolean[CANT_CROMOSOMAS];
			for(int i=0;i<CANT_CROMOSOMAS;i++) {
				int var =  (int) (Math.random()*2+1);
				if(var==1) {
					cromosomas[i]=true;
				}
				else {
					cromosomas[i]=false;
				}
			}
			Robot robot = new Robot(ASCII_A + k, genActual);
			robot.setCromosomas(cromosomas);
			robots[k] = robot;
		}
	}
	
	private void pruebas() {
		for(int i=0;i<robots.length;i++) {
			for(int j=0;j<CANT_PRUEBAS;j++) {
				miGenetico.evaluacion(robots[i]);
				robots[i].setFila(19);
				robots[i].setColumna(0);
			}
		}
	}
	
	public int binaryToDecimal(boolean[] pArray) {
		int result = 0;
		int contador = 0;
		for (int i = pArray.length-1; i >= 0; i--) {
			if(pArray[i]) {
				result += Math.pow(2, contador);
			}
			contador++;
		}
		return result;
		
	}
	
}
