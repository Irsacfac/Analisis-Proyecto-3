package main;

import elementos.Algoritmo;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Algoritmo algoritmo=new Algoritmo();
		algoritmo.cargarTerreno();
		algoritmo.primerGeneracion(3);
		algoritmo.escribir();
		algoritmo.leer();
	}

}
