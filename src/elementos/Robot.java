package elementos;

import otros.IConstants;

public class Robot implements IConstants{
	
	private boolean[] cromosomas;
	private String nombre;
	private String padres;
	private int fila;
	private int columna;
	
	//Datos de los recorridos
	
	private int promedioPasos;
	private int promedioBateriaRestante;
	private int distancia;
	
	public Robot(int pASCII, int pGen) {
		cromosomas = new boolean[CANT_CROMOSOMAS];
		//3 de movimiento; 2 de cámara; 2 de batería; 2 de motor
		nombre = (char)pASCII + String.valueOf(pGen);
		padres = "";
		fila=19;
		columna=0;
	}
	
	public int getDistancia() {
		return distancia;
	}
	
	public int getPromedioBateria() {
		return promedioBateriaRestante;
	}
	
	public int getPromedioPasos() {
		return promedioPasos;
	}
	
	public void setRecorrido(int pPromedioPasos, int pPromedioBateriaRestante, int pDistancia) {
		promedioPasos=pPromedioPasos;
		promedioBateriaRestante=pPromedioBateriaRestante;
		distancia=pDistancia;
	}
	
	public boolean[] getMovimiento() {
		boolean[] movimiento=new boolean[3];
		for(int i=0; i<3; i++) {
			movimiento[i]=cromosomas[i];
		}
		return movimiento;
	}
	
	public int getCamera() {
		int camera=0;
		if(cromosomas[3]==true) {
			if(cromosomas[4]==true) {
				camera=3;
			}else {
				camera=2;
			}
		}else {
			camera=1;
		}
		return camera;
	}
	
	public int getBateria() {
		int bateria=0;
		if(cromosomas[5]==true) {
			if(cromosomas[6]==true) {
				bateria=3;
			}else {
				bateria=2;
			}
		}else {
			bateria=1;
		}
		return bateria;
	}
	
	public int getMotor() {
		int motor=0;
		if(cromosomas[7]==true) {
			if(cromosomas[8]==true) {
				motor=3;
			}else {
				motor=2;
			}
		}else {
			motor=1;
		}
		return motor;
	}
	
	public int getComprobacion() {
		int contador = 0;
		if(cromosomas[9]==true) {
			if(cromosomas[10]==true) {
				contador=3;
			}else {
				contador=2;
			}
		}else {
			contador=1;
		}
		return contador;
	}

	/*public void setCamera(int pCamera) {
		this.camera = pCamera;
	}*/
	
	public int getColumna() {
		return columna;
	}

	public void setColumna(int posicion) {
		this.columna = posicion;
	}

	public int getFila() {
		return fila;
	}

	public void setFila(int posicion) {
		this.fila = posicion;
	}
	
	public boolean[] getCromosomas() {
		return cromosomas;
	}
	
	public String getCromosomasSTR() {
		String aux="";
		for(int i=0; i<cromosomas.length; i++) {
			if(cromosomas[i]==true) {
				aux+="1";
			}else {
				aux+="0";
			}
		}
		return aux;
	}

	public void setCromosomas(boolean[] cromosomas) {
		this.cromosomas = cromosomas;
	}

	public String getPadres() {
		return padres;
	}

	public void setPadres(Robot pPadreA, Robot pPadreB) {
		this.padres = pPadreA.getNombre() + ": " + pPadreA.genToString() + ", " + pPadreB.getNombre() + ": " + pPadreB.genToString();
	}

	public String getNombre() {
		return nombre;
	}
	
	private String genToString() {
		String genes = "";
		for (int i = 0; i < cromosomas.length; i++) {
			if(cromosomas[i]) {
				genes += '1';
			}else {
				genes += '0';
			}
		}
		return genes;
	}
	
	public String toString() {
		String enterKey = System.getProperty("line.separator");
		String str = "";
		str += this.nombre+";"+genToString()+";"+this.padres;
		return str;
	}
}
