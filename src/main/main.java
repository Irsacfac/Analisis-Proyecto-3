package main;

import elementos.Algoritmo;
import gui.Ventana;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*int[] myNum = {10, 20, 30, 40};
		System.out.println( (char)65 );
		System.out.println( (char)90 );
		System.out.println( (char)65 + String.valueOf(0) );
		boolean[] cromosomas = new boolean[22];
		for (int i = 0; i < cromosomas.length-1; i++) {
			System.out.println( cromosomas[i] );
		}
		String enterKey = System.getProperty("line.separator");
		System.out.println("Hola" + enterKey + "mundo");
		*/
		
		Ventana miVentana = new Ventana();
		//miVentana.modifyInfoRobot("A0");
		
		Algoritmo algoritmo=new Algoritmo();
		algoritmo.primerGeneracion(3);
		algoritmo.escribir();
		algoritmo.leer();
	}

}
