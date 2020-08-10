package elementos;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

import gui.Ventana;
import otros.ContenedorCromosomas;
import otros.DatosGenetico;
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
	private DatosGenetico[] datosRobots;
	
	
	public Programa() {
		FileWriter fichero = null;
	    PrintWriter pw = null;
	    try
	    {
	    	fichero = new FileWriter("DatosGen.txt");
	        pw = new PrintWriter(fichero);
	        pw.println("");

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	       try {
	       if (null != fichero)
	          fichero.close();
	       } catch (Exception e2) {
	          e2.printStackTrace();
	       }
	    }
		markovDosBits = new int[] {0, 35, 70, 100};
		markovTresBits = new int[][] {{25,34,19,40,70,10,91,52},{25,34,19,40,70,91,10,52},{25,34,19,22,10,10,10,25}};
		componente = new int[] {0, 1, 2};
		robots = new Robot[2*CANT_PAREJAS];
		datosRobots = new DatosGenetico[2*CANT_PAREJAS];
		primerGeneracion();
		miAlgoritmo= new Algoritmo();
		miAlgoritmo.cargarTerreno(mapa);
		miGenetico= new Genetico(this);
		
		
		boolean salir=false;
		while(!salir) {
			//imprimirRobots();
			pruebas();
			funcionAdaptabilidad();
			//System.out.println("Generacion "+genActual+" porcentaje de exito: "+porcentajeExito());
			if(porcentajeExito()>70.0) {
				salir=true;
				miAlgoritmo.escribir(genActual, robots, datosRobots);
				System.out.println("Generacion "+genActual+" porcentaje de exito: "+porcentajeExito());
				genActual++;
			}else {
				//if(porcentajeExito()==0.0) {
					//imprimirRobots();
				//}
				siguienteGen(miAlgoritmo.seleccion(robots));
			}
		}
		
		
		
		
		/*String a="";
		for(int i=0; i<robots[0].getCromosomas().length; i++) {
			if(robots[0].getCromosomas()[i]==true) {
				a+=1;
			}else {
				a+=0;
			}
		}
		System.out.println(a);
		System.out.println("Cámara: "+robots[0].getCamera());
		System.out.println("Batería: "+robots[0].getBateria());
		System.out.println("Motor: "+robots[0].getMotor());*/
		
		Ventana miVentana = new Ventana(genActual);
		//miVentana.modifyInfoRobot("A0");
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
	
	private double porcentajeExito() {
		double porc=0.0;
		int temp=0;
		for (int i = 0; i < robots.length; i++) {
			if(robots[i].getExito()==true) {
				temp+=1;
			}
		}
		porc=(double)temp/robots.length*100;
		return porc;
	}
	
	private void siguienteGen(String[] elegidos) {
		//ESCRIBIR LA GENERACION ACTUAL EN EL TXT;
		miAlgoritmo.escribir(genActual, robots, datosRobots);
		//--------------------------------------
		
		Robot[] siguienteGen= new Robot[2*CANT_PAREJAS];
		genActual++;
		
		int temp1=0;
		int temp2=0;
		int k=0;
		for (int i = 0; i < elegidos.length; i+=2) {
			temp1=1+1;
			temp2=i+2;
			if(temp1>=elegidos.length) {
				temp1-=elegidos.length;
			}
			if(temp2>=elegidos.length) {
				temp2-=elegidos.length;
			}
			
			ContenedorCromosomas genes;
			int padres=0;
			for (int i2 = 0; i2 < 2; i2++) {
				if(i2==0) {
					genes=miAlgoritmo.cruce(CANT_CROMOSOMAS/3, buscarRobot(elegidos[i]).getCromosomas(), buscarRobot(elegidos[temp1]).getCromosomas());
					padres=temp1;
				}else {
					genes=miAlgoritmo.cruce(CANT_CROMOSOMAS/3, buscarRobot(elegidos[i]).getCromosomas(), buscarRobot(elegidos[temp2]).getCromosomas());
					padres=temp2;
				}
				for (int i3 = 0; i3 < 2; i3++) {
					Robot robot = new Robot(ASCII_A + k, genActual);
					if(i3==0) {
						robot.setCromosomas(genes.getCromosomaA());
					}else {
						robot.setCromosomas(genes.getCromosomaB());
					}
					robot.setPadres(buscarRobot(elegidos[i]), buscarRobot(elegidos[padres]));
					siguienteGen[k]=robot;
					k+=1;
				}
			}
		}
		
		//imprimirRobots();
		robots=siguienteGen;
		//imprimirRobots();
		
	}
	
	private Robot buscarRobot(String name) {
		for (int i = 0; i < robots.length; i++) {
			if(name.equals(robots[i].getNombre())) {
				return robots[i];
			}
		}
		return null;
	}
	
	private void imprimirRobots() {
		String aux;
		for (int i = 0; i < robots.length; i++) {
			aux="";
			aux+=robots[i].getNombre()+": ";
			aux+=robots[i].getCromosomasSTR();
			System.out.println(aux);
		}
	}
	
	private void funcionAdaptabilidad() {
		int promedioPasos = 0;
		int promedioBateriaRestante = 0;
		double distancia = 0.0;
		for (int i = 0; i < datosRobots.length; i++) {
			promedioPasos = 0;
			promedioBateriaRestante = 0;
			distancia = 0.0;
			DatosGenetico datoActual = datosRobots[i];
			for (int j = 0; j < CANT_PRUEBAS; j++) {
				promedioPasos += datoActual.getPasosDados()[j];
				promedioBateriaRestante += datoActual.getBateriaRestante()[j];
				distancia +=Math.sqrt( Math.pow(19-datoActual.getColumnas()[j],2) + Math.pow(0-datoActual.getFilas()[j],2) );
				
				double distanciaAux=Math.sqrt( Math.pow(19-datoActual.getColumnas()[j],2) + Math.pow(0-datoActual.getFilas()[j],2) );
				//System.out.println("("+datoActual.getFilas()[j]+","+datoActual.getColumnas()[j]+") : "+distanciaAux);
			}
			promedioPasos = promedioPasos/CANT_PRUEBAS;
			promedioBateriaRestante = promedioBateriaRestante/CANT_PRUEBAS;
			distancia = distancia/CANT_PRUEBAS;
			//System.out.println(distancia);
			//distancia = Math.round(distancia*100)/100d;
			//System.out.println("INT: "+(int)distancia);
			
			robots[i].setRecorrido(promedioPasos, promedioBateriaRestante, (int)distancia);
			
			/*System.out.println(promedioPasos);
			System.out.println(promedioBateriaRestante);
			System.out.println(distancia);*/
		}
		
	}
	
	private void pruebas() {
		for(int i=0;i<robots.length;i++) {
			DatosGenetico datos = new DatosGenetico();
			for(int j=0;j<CANT_PRUEBAS;j++) {
				datos.add(j, miGenetico.evaluacion(robots[i]));
				robots[i].setFila(19);
				robots[i].setColumna(0);
			}
			datosRobots[i] = datos;
		}
	}
	
	public int getProbaDosBits(int proba) {
		return markovDosBits[proba];
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
