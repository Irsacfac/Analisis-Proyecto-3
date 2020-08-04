package elementos;

import otros.ContenedorCromosomas;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Algoritmo {
	
	public ArrayList<Robot> robots = new ArrayList<Robot>();
	
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
		for(int k=0;k<10;k++) {
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
			Robot robot = new Robot(65 + pRobot+k ,0);
			robot.setCromosomas(cromosomas);
			robots.add(robot);
		}
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
				int var =  (int) (Math.random()*100);
				if(var%3==0) {
					mutado[inicio]=true;
				}
				else {
					mutado[inicio]=false;
				}
				break;
			}
			inicio++;
		}
		for (int j = fin; j < mutado.length; j++) {
			mutado[j] = gen[j];
		}
		return mutado;
	}
	
	public void leer() {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File ("Generacion.txt");
		    fr = new FileReader (archivo);
		    br = new BufferedReader(fr);

		         // Lectura del fichero
		    String linea;
		    while((linea=br.readLine())!=null) {
		    	//System.out.println(linea);
		    	String[] parts = linea.split(";");
		    	//
		    }
		}
		catch(Exception e){
		         e.printStackTrace();
		}finally{
			try{
				if( null != fr ){
					fr.close();     
		         }
			}catch (Exception e2){
		         e2.printStackTrace();
		    }
		}
	}
	
	public void escribir() {
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
        	fichero = new FileWriter("Generacion.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < robots.size(); i++) {
                pw.println(robots.get(i).toString());
            }
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
	}
	
}
