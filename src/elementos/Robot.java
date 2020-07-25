package elementos;

public class Robot {
	
	private boolean[] cromosomas;
	private String nombre;
	private String padres;
	
	public Robot(int pASCII, int pGen) {
		cromosomas = new boolean[22];
		nombre = (char)pASCII + String.valueOf(pGen);
		padres = "";
	}

	public boolean[] getCromosomas() {
		return cromosomas;
	}

	public void setCromosomas(boolean[] cromosomas) {
		this.cromosomas = cromosomas;
	}

	public String getPadres() {
		return padres;
	}

	public void setPadres(String pPadreA, String pPadreB) {
		this.padres = pPadreA + ", " + pPadreB;
	}

	public String getNombre() {
		return nombre;
	}
	
	public String toString() {
		String enterKey = System.getProperty("line.separator");
		String str = "";
		str += "Nombre: " + this.nombre + enterKey;
		str += "Padres: " + this.padres;
		return str;
	}
}
