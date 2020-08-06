package elementos;

import otros.IConstants;

public class Programa implements IConstants{
	
	private int genActual;
	private Algoritmo miAlgoritmo;
	private int[] markovDosBits;
	private int[] componente;
	private Robot[] robots;
	
	public Programa(int n) {
		markovDosBits = new int[] {0, 35, 70, 100};
		componente = new int[] {0, 1, 2};
		robots = new Robot[2*n];
		primerGeneracion();
		miAlgoritmo.escribir(robots);
		
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
	
	private int binaryToDecimal(boolean[] pArray) {
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