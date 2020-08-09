package otros;

public class DatosGenetico implements IConstants{
	
	private int[] filas;
	private int[] columnas;
	private int[] bateriaRestante;
	private int[] pasosDados;
	
	public DatosGenetico() {
		filas = new int[CANT_PRUEBAS];
		columnas = new int[CANT_PRUEBAS];
		bateriaRestante = new int[CANT_PRUEBAS];
		pasosDados = new int[CANT_PRUEBAS];
	}
	
	public void add(int pos, int pFila, int pCol, int pBateria, int pPasos) {
		filas[pos] = pFila;
		columnas[pos] = pCol;
		bateriaRestante[pos] = pBateria;
		pasosDados[pos] = pPasos;
	}
	
	public int[] getFilas() {
		return filas;
	}

	public int[] getColumnas() {
		return columnas;
	}

	public int[] getBateriaRestante() {
		return bateriaRestante;
	}

	public int[] getPasosDados() {
		return pasosDados;
	}

	public int[] getData(int pos) {
		int[] result = {filas[pos], columnas[pos], bateriaRestante[pos], pasosDados[pos]};
		return result;
	}
}
