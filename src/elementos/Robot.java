package elementos;

public class Robot {
	
	//private bit cromosomas;
	private String nombre;
	private String padres;
	
	public Robot(int pASCII, int pGen) {
		nombre = (char)pASCII + String.valueOf(pGen);
		padres = "";
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
