package elementos;

import java.util.Random;
import otros.ContenedorCromosomas;
import otros.IConstants;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Algoritmo implements IConstants{
	
	public Algoritmo() {
		
	}
	
	public String[] seleccion(Robot[] robots) {
		String[] seleccionados=new String[robots.length/2];
		
		int pasos[] = new int[robots.length];
		int bateria[] = new int[robots.length];
		int distancia[] = new int[robots.length];
		String nombresP[] = new String[robots.length];
		String nombresB[] = new String[robots.length];
		String nombresD[] = new String[robots.length];
		
		String nombres[] = new String[robots.length];
		
		for (int i = 0; i < robots.length; i++) {
			pasos[i]=(int)Math.sqrt( Math.pow( (robots[i].getPromedioPasos()-OBJETIVO[0]),2) );
			//pasos[i]=robots[i].getPromedioPasos();
			bateria[i]=(int)Math.sqrt( Math.pow( (robots[i].getPromedioBateria()-OBJETIVO[1]),2) );
			//bateria[i]=robots[i].getPromedioBateria();
			distancia[i]=(int)Math.sqrt( Math.pow( (robots[i].getDistancia()-OBJETIVO[2]),2) );
			//distancia[i]=robots[i].getDistancia();
			nombresP[i]=robots[i].getNombre();
			nombresB[i]=robots[i].getNombre();
			nombresD[i]=robots[i].getNombre();
			nombres[i]=robots[i].getNombre();
		}
		
		for (int x = 0; x < robots.length; x++) {
			for (int i = 0; i < robots.length-1; i++) {
				if(pasos[i] < pasos[i+1]){
	                int tmp = pasos[i+1];
	                String tmpS = nombresP[i+1];
	                
	                pasos[i+1] = pasos[i];
	                nombresP[i+1] = nombresP[i];
	                
	                pasos[i] = tmp;
	                nombresP[i] = tmpS;
	            }
				if(bateria[i] < bateria[i+1]){
	                int tmp = bateria[i+1];
	                String tmpS = nombresB[i+1];
	                
	                bateria[i+1] = bateria[i];
	                nombresB[i+1] = nombresB[i];
	                
	                bateria[i] = tmp;
	                nombresB[i] = tmpS;
	            }
				if(distancia[i] < distancia[i+1]){
	                int tmp = distancia[i+1];
	                String tmpS = nombresD[i+1];
	                
	                distancia[i+1] = distancia[i];
	                nombresD[i+1] = nombresD[i];
	                
	                distancia[i] = tmp;
	                nombresD[i] = tmpS;
	            }
			}
		}
	    
		/*String aux1="";
		String auxN="";
		String aux2="";
		String auxN2="";
		String aux3="";
		String auxN3="";
		for (int i = 0; i < robots.length; i++) {
			aux1+=pasos[i]+"-";
			auxN+=nombresP[i]+"-";
		}
		for (int i = 0; i < robots.length; i++) {
			aux2+=bateria[i]+"-";
			auxN2+=nombresB[i]+"-";
		}
		for (int i = 0; i < robots.length; i++) {
			aux3+=distancia[i]+"-";
			auxN3+=nombresD[i]+"-";
		}
		System.out.println("Pasos: "+aux1);
		System.out.println("Pasos: "+auxN);
		System.out.println("Batería: "+aux2);
		System.out.println("Batería: "+auxN2);
		System.out.println("Distancia: "+aux3);
		System.out.println("Distancia: "+auxN3);*/
		
		int aux[] = new int[robots.length];
		for (int i = 0; i < robots.length; i++) {
			for (int j = 0; j < robots.length; j++) {
				if(nombres[i].equals(nombresP[j])) {
					aux[i]+=j;
				}
				if(nombres[i].equals(nombresB[j])) {
					aux[i]+=j;
				}
				if(nombres[i].equals(nombresD[j])) {
					aux[i]+=j;
				}
			}
		}
		
		for (int x = 0; x < robots.length; x++) {
			for (int i = 0; i < robots.length-1; i++) {
				if(aux[i] < aux[i+1]){
	                int tmp = aux[i+1];
	                String tmpS = nombres[i+1];
	                
	                aux[i+1] = aux[i];
	                nombres[i+1] = nombres[i];
	                
	                aux[i] = tmp;
	                nombres[i] = tmpS;
	            }
			}
		}
		
		/*String imp="";
		for (int i = 0; i < robots.length; i++) {
			imp+="("+nombres[i]+","+aux[i]+")-";
		}
		System.out.println("Final: "+imp);*/
		
		for (int i = 0; i < seleccionados.length; i++) {
			seleccionados[i]=nombres[i];
		}
		
		/*String imp2="";
		for (int i = 0; i < seleccionados.length; i++) {
			imp2+="("+seleccionados[i]+","+aux[i]+")-";
		}
		System.out.println("Final: "+imp2);*/
	    
		return seleccionados;
	}
	
	public ContenedorCromosomas cruce(int rango, boolean[] genA, boolean[] genB) {
		int puntoCruce = (int) Math.floor( Math.random() * (genA.length-(rango*2)-1) + rango+1 );

		boolean[] n1 = new boolean[genA.length];
		boolean[] n2 = new boolean[genA.length];
		for (int i = 0; i < puntoCruce; i++) {
			n1[i] = genB[i];
			n2[i] = genA[i];
		}
		while(puntoCruce < genA.length) {
			n1[puntoCruce] = genA[puntoCruce];
			n2[puntoCruce] = genB[puntoCruce];
			puntoCruce++;
		}
		
		Random rand = new Random();
		double mutar=0 + 100 * rand.nextDouble();
		mutar=Math.round(mutar*10)/10d;
		if(mutar<20.0) {
			n1=mutacion(n1,5);
		}
		if(mutar>80.0) {
			n2=mutacion(n2,5);
		}
		//System.out.println(mutar);
		
		/*String auxA="";
		String auxB="";
		String auxH1="";
		String auxH2="";
		for (int i = 0; i < genA.length; i++) {
			auxA+=genA[i]+"-";
			auxB+=genB[i]+"-";
			auxH1+=n1[i]+"-";
			auxH2+=n2[i]+"-";
		}
		System.out.println(auxA);
		System.out.println(auxB);
		System.out.println(auxH1);
		System.out.println(auxH2);*/
		
		return new ContenedorCromosomas(n1, n2);
	}
	
	public boolean[] mutacion(boolean[] gen, int tipo) {
		boolean[] mutado = new boolean[gen.length];
		int inicio =  (int) (Math.random()*(gen.length/2));
		int fin = (int) (Math.random()*(gen.length/2) + gen.length/2);
		/*System.out.println("Inicio: "+inicio);
		System.out.println("Fin: "+fin);*/
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
		
		/*String aux="";
		String auxM="";
		for (int i = 0; i < gen.length; i++) {
			aux+=gen[i]+"-";
			auxM+=mutado[i]+"-";
		}
		System.out.println(aux);
		System.out.println(auxM);
		System.out.println("---");*/
		
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
	
	public void escribir(Robot[] robots) {
		FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {
        	fichero = new FileWriter("Generacion.txt");
            pw = new PrintWriter(fichero);

            for (int i = 0; i < robots.length; i++) {
                pw.println(robots[i].toString());
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
	
	public void cargarTerreno(int[][] matriz) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File ("Terreno.txt");
		    fr = new FileReader (archivo);
		    br = new BufferedReader(fr);

		         // Lectura del fichero
		    String linea;
		    for(int i=0; i<20; i++) {
		    	linea=br.readLine();
		    	for(int j=0; j<20; j++) {
		    		matriz[i][j]=Character.getNumericValue(linea.charAt(j));
		    	}
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
		/*String x;
		for(int i=0; i<20; i++) {
			x="";
	    	for(int j=0; j<20; j++) {
	    		x+=Integer.toString(matriz[i][j]);
	    	}
	    	System.out.println(x);
	    }*/
	}
	
	public int[][] camara(int[][] matriz, Robot robot) {
		int direcciones[][]= new int[DIRECTIONS][robot.getCamera()];
		int tempX=0;
		int tempY=0;
		for(int i=0; i<DIRECTIONS;i++) {
			for(int j=1; j<robot.getCamera()+1;j++) {
				switch(i)
				{
				case 0:
					tempX=robot.getFila()-(1*j);
					tempY=robot.getColumna();
					break;	
				case 1:
					tempX=robot.getFila();
					tempY=robot.getColumna()+(1*j);
					break;
				case 2:
					tempX=robot.getFila();
					tempY=robot.getColumna()-(1*j);
					break;	
				case 3:
					tempX=robot.getFila()+(1*j);
					tempY=robot.getColumna();
					break;
				}
				try {
					direcciones[i][j-1]=matriz[tempX][tempY];
					//System.out.println(tempX+" "+tempY+" "+matriz[tempX][tempY]);
				}catch(Exception e) {
					direcciones[i][j-1]=3;
				}
			}
		}
		/*String x;
		for(int i=0; i<DIRECTIONS; i++) {
			x="";
	    	for(int j=0; j<direcciones[0].length;j++) {
	    		x+=Integer.toString(direcciones[i][j]);
	    	}
	    	System.out.println("Direccion "+i+": "+x);
	    }*/
		return direcciones;
	}
	
}
