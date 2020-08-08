package elementos;

import java.util.Random;

import otros.IConstants;

public class Genetico implements IConstants{
	private Programa programa;
	
	public Genetico(Programa pPrograma) {
		programa=pPrograma;
	}
	
	public void evaluacion(Robot robot) {
		//Casilla en que termina, pasos que dio y energía consumida
		int pasos=0;
		boolean fin=false;
		int energia=100*robot.getBateria();
		int movimiento= programa.binaryToDecimal(robot.getMovimiento());
		double[] probabilidades= new double[DIRECTIONS];
		while(!fin) {
			for(int i=0;i<probabilidades.length-1;i++) {
				probabilidades[i]=(100-programa.markovTresBits[i][movimiento])/3;
			}
			probabilidades[DIRECTIONS-2]=probabilidades[DIRECTIONS-2]/2;
			probabilidades[DIRECTIONS-1]=probabilidades[DIRECTIONS-2];
			
			/*System.out.println(probabilidades[0]);
			System.out.println(probabilidades[1]);
			System.out.println(probabilidades[2]);
			System.out.println(probabilidades[3]);
			System.out.println("!!!!!!!!!!!!!!!");*/
			
			int[][] direcciones=programa.miAlgoritmo.camara(programa.mapa, robot);
			for(int i=0;i<probabilidades.length;i++) {
				double varianza=evaluacionAuxiliar(i, direcciones,robot.getMotor());
				if(varianza==-100.0) {
					probabilidades[i]=0;
				}else {
					probabilidades[i]=probabilidades[i]+(probabilidades[i]*varianza);
					//System.out.println("Proba: "+probabilidades[i]+"ProbaIncremento: "+probabilidades[i]*varianza);
				}
			
			}
			
			/*System.out.println(probabilidades[0]);
			System.out.println(probabilidades[1]);
			System.out.println(probabilidades[2]);
			System.out.println(probabilidades[3]);
			System.out.println("---------------");*/
			
			Random rand = new Random();
			double probaEleccion=0 + 100 * rand.nextDouble();
			probaEleccion=Math.round(probaEleccion*10)/10d;
			int eleccion=-1;
			int acumulado=0;
			for(int i=0;i<probabilidades.length;i++) {
				if(probaEleccion>acumulado && probaEleccion<=acumulado+probabilidades[i]) {
					eleccion=i;
				}
				acumulado+=probabilidades[i];
			}
			/*System.out.println("Random: "+probaEleccion);
			System.out.println("Elegido: "+eleccion);*/
			switch(eleccion)
			{
			case -1:
				energia-=1;
				energia-=robot.getCamera();
				break;
			case 0:
				energia-=programa.mapa[robot.getFila()][robot.getColumna()];
				robot.setFila(robot.getFila()-1);
				energia-=1;
				energia-=robot.getCamera();
				pasos+=1;
				break;
			case 1:
				energia-=programa.mapa[robot.getFila()][robot.getColumna()];
				robot.setColumna(robot.getColumna()+1);
				energia-=1;
				energia-=robot.getCamera();
				pasos+=1;
				break;
			case 2:
				energia-=programa.mapa[robot.getFila()][robot.getColumna()];
				robot.setColumna(robot.getColumna()-1);
				energia-=1;
				energia-=robot.getCamera();
				pasos+=1;
				break;
			case 3:
				energia-=programa.mapa[robot.getFila()][robot.getColumna()];
				robot.setFila(robot.getFila()+1);
				energia-=1;
				energia-=robot.getCamera();
				pasos+=1;
				break;
			}
			
			if(energia<1) {
				fin=true;
			}
			if(programa.mapa[robot.getFila()][robot.getColumna()]>robot.getMotor()-1) {
				fin=true;
			}
			if(robot.getFila()==0 && robot.getColumna()==19) {
				fin=true;
			}
			//System.out.println("Casilla: "+"("+robot.getFila()+","+robot.getColumna()+")");
		}
		//System.out.println(movimiento);
		System.out.println("Robot : "+robot.getNombre());
		System.out.println("Casilla final: "+"("+robot.getFila()+","+robot.getColumna()+")");
		System.out.println("Pasos: "+pasos);
		System.out.println("Energía inicial :"+100*robot.getBateria()+" energía final :"+energia);
		System.out.println("---------------");
	}
	
	private double evaluacionAuxiliar(int i, int[][]direcciones, int motor) {
		double varianza=0.00;
		boolean bloqueado=false;
		for(int j=0;j<direcciones[i].length;j++) {
			if(j==0 && direcciones[i][j]==3) {
				varianza=-100;
				//System.out.println("Varianza: "+varianza);
				bloqueado=true;
			}else if(bloqueado!=true){
				switch(direcciones[i][j])
				{
				case 0:
					varianza+=0.06;
					break;
				case 1:
					if(motor-1>=1) {
						varianza+=0.04;
					}else {
						varianza-=0.04;
					}
					break;
				case 2:
					if(motor-1>=2) {
						varianza+=0.02;
					}else {
						varianza-=0.02;
					}
					break;
				}
			}
		}
		//System.out.println("Varianza: "+varianza);
		return varianza;
	}
}
