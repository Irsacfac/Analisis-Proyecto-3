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

	public void setPadres(String padres) {
		this.padres = padres;
	}

	public String getNombre() {
		return nombre;
	}
	
	
}
