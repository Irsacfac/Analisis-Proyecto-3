package elementos;

import otros.IConstants;

public class Robot implements IConstants{
	
	private boolean[] cromosomas;
	private String nombre;
	private String padres;
	
	public Robot(int pASCII, int pGen) {
		cromosomas = new boolean[CANT_CROMOSOMAS];
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
