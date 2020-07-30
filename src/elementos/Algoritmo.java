package elementos;

import otros.ContenedorCromosomas;

public class Algoritmo {
	public Algoritmo() {
		
	}
	
	public void seleccion() {}
	
	public ContenedorCromosomas cruce(int rango, boolean[] genA, boolean[] genB) {
		int puntoCruce = (int) Math.floor( Math.random() * (genA.length-rango) + rango );
		boolean[] n1 = new boolean[genA.length];
		boolean[] n2 = new boolean[genA.length];
		for (int i = 0; i < puntoCruce; i++) {
			n1[i] = genB[i];
			n2[i] = genA[i];
		}
		while(puntoCruce < genA.length) {
			n1[puntoCruce] = genA[puntoCruce];
			n2[puntoCruce] = genB[puntoCruce];
		}
		
		return new ContenedorCromosomas(n1, n2);
	}
	
	public void primerGeneracion(int pRobot) {
		//while
		boolean[] cromosomas=new boolean[22];
		for(int i=0;i<22;i++) {
			int var =  (int) (Math.random()*2+1);
			if(var==1) {
				cromosomas[i]=true;
			}
			else {
				cromosomas[i]=false;
			}
		}
		Robot robot = new Robot(65 + pRobot ,0);
		robot.setCromosomas(cromosomas);
	}
	
	public boolean[] mutacion(boolean[] gen, int tipo) {
		boolean[] mutado = new boolean[gen.length];
		int inicio =  (int) Math.random()*(gen.length - 4);
		int fin = (int) (Math.random()*(3) + inicio);
		for (int i = 0; i < inicio; i++) {
			mutado[i] = gen[i];
		}
		while(inicio <= fin) {
			switch (tipo) {
			case 1:
				mutado[inicio] = !gen[inicio];
				break;

			default:
				break;
			}
			inicio++;
		}
		for (int j = fin; j < mutado.length; j++) {
			mutado[j] = gen[j];
		}
		return mutado;
	}
	
	
}
