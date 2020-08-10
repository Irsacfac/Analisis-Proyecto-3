package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import elementos.Robot;
import otros.DatosGenetico;
import otros.IConstants;

public class Ventana extends JFrame implements IConstants, ItemListener, ActionListener{
	
	private JPanel textPanel;
	private JPanel robotsPanel;
	private JTextArea infoRobot;
	private JComboBox<String> lista;
	private JButton seguir;
	private JButton atras;
	
	private JLabel etiquetaGen;
	private JLabel etiquetaRobot;
	private JTextArea etiquetaRecorridos;
	
	private int estado;
	private int genActual;
    private String[] robots;
    private String[] datosRobots;
    private String[] generaciones;

	public Ventana(int pGenActual) {
		super("Proyecto 3");
		
		estado=0;
		genActual=pGenActual;
		this.setLayout(null);
		this.setSize(PANTALLA_WIDTH, PANTALLA_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        
        initComponents();
        
        this.setVisible(true);
	}

	private void initComponents() {
		textPanel = new JPanel();
        textPanel.setBounds(PANTALLA_WIDTH-PANTALLA_WIDTH/3, 0, PANTALLA_WIDTH/3, PANTALLA_HEIGHT);
        //textPanel.setBackground(Color.BLACK);
        textPanel.setLayout(null);
        this.getContentPane().add(textPanel);
		
        robotsPanel = new JPanel();
        robotsPanel.setBounds(0, 0, PANTALLA_WIDTH-PANTALLA_WIDTH/3, PANTALLA_HEIGHT);
        robotsPanel.setLayout(null);
        //robotsPanel.setBackground(Color.CYAN);
        this.getContentPane().add(robotsPanel);
        
        /*
        infoRobot = new JTextArea();
        infoRobot.setBounds(0, 0, PANTALLA_WIDTH/3, PANTALLA_HEIGHT);
        infoRobot.setEditable(false);
        infoRobot.setText("Prueba");
        textPanel.add(infoRobot);

        JLabel mietiqueta = new JLabel("Prueba");
        mietiqueta.setBounds(100, 100, DEFAULT_LABEL_WIDTH, DEFAULT_LABEL_HEIGHT);
        robotsPanel.add(mietiqueta);
        
        Etiqueta<Robot> miEtiqueta = new Etiqueta<Robot>(new Robot(65, 0), "Gen0");
        miEtiqueta.setBounds(200, 200, DEFAULT_LABEL_WIDTH, DEFAULT_LABEL_HEIGHT);
        robotsPanel.add(miEtiqueta);*/
        
        
        
        etiquetaGen = new JLabel();
        etiquetaGen.setBounds(20, 20, 200, DEFAULT_LABEL_HEIGHT);
        //etiquetaGen.setText("ETIQUETAG");
        robotsPanel.add(etiquetaGen);
        
        etiquetaRobot = new JLabel();
        etiquetaRobot.setBounds(20, 70, 200, DEFAULT_LABEL_HEIGHT);
        //etiquetaRobot.setText("ETIQUETAR");
        robotsPanel.add(etiquetaRobot);
        
        etiquetaRecorridos = new JTextArea();
        etiquetaRecorridos.setBounds(20, 100, 600, 200);
        //etiquetaRecorridos.setText("ETIQUETARE");
        etiquetaRecorridos.setEditable(false);
        etiquetaRecorridos.setOpaque(false);
        robotsPanel.add(etiquetaRecorridos);
        
        
        seguir = new JButton("Seguir");
        seguir.setBounds(410, 300, 100, DEFAULT_LABEL_HEIGHT);
        seguir.setText("OK");
        robotsPanel.add(seguir);
        seguir.addActionListener(this);
        
        atras = new JButton("Atras");
        atras.setBounds(30, 300, 100, DEFAULT_LABEL_HEIGHT);
        atras.setText("Atras");
        robotsPanel.add(atras);
        atras.addActionListener(this);
        atras.setVisible(false);
        
        
        
        generacionesCargar();
        
        lista = new JComboBox<>();
		lista.setModel(new DefaultComboBoxModel(generaciones));
		lista.setBounds(350, 30, 150, DEFAULT_LABEL_HEIGHT);
		robotsPanel.add(lista);
        
	}
	
	private void modifyInfoRobot(String pText) {
		infoRobot.setText(pText);
	}
	
	private void recorridosCargar(String name) {
		datosRobots= new String[2*CANT_PAREJAS*CANT_PRUEBAS];
		String auxiliar="";
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File ("DatosGen.txt");
		    fr = new FileReader (archivo);
		    br = new BufferedReader(fr);

		         // Lectura del fichero
		    String linea;
		    int i=0;
		    linea=br.readLine();
		    boolean bandG=false;
		    String[] corte;
		    while((linea=br.readLine())!=null) {
		    	corte=linea.split(";");
		    	if(linea.substring(0,1).equals("G") || linea.substring(0,1).equals("R") || linea.substring(0,1).equals("")){
		    		bandG=false;
		    	}
		    	if(bandG) {
		    		//System.out.println(linea);
		    		switch(i) {
			    	case 0:
			    		auxiliar+="Posición final: ("+linea+",";
			    		i++;
			    		break;
			    	case 1:
			    		auxiliar+=linea+")                ";
			    		i++;
			    		break;
			    	case 2:
			    		auxiliar+="Batería: "+linea+"\n";
			    		i++;
			    		break;
			    	case 3:
			    		auxiliar+="Pasos: "+linea+"                   ";
			    		i++;
			    		break;
			    	case 4:
			    		auxiliar+="                    Tiempo: "+linea+"\n\n";
			    		i=0;
			    		break;
			    	}
		    	}
		    	if(corte[0].substring(1).equals(name)){
		    		bandG=true;
		    		auxiliar+="Cromosomas: "+corte[1];
		    		try {
		    			//auxiliar+="         Padres: "+corte[2].split(",")[0]+" "+corte[2].split(",")[1]+"\n";
		    			auxiliar+="         Padres: "+corte[2].split(",")[0].split(":")[0]+" "+corte[2].split(",")[1].split(":")[0]+"\n";
		    		}catch (Exception e) {
		    			auxiliar+="         Es de la primera generación\n";
		    		}
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
		etiquetaRecorridos.setText(auxiliar);
	}
	
	private void robotsCargar(String gen) {
		robots= new String[2*CANT_PAREJAS];
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File ("DatosGen.txt");
		    fr = new FileReader (archivo);
		    br = new BufferedReader(fr);

		         // Lectura del fichero
		    String linea;
		    int i=0;
		    linea=br.readLine();
		    boolean bandG=false;
		    while((linea=br.readLine())!=null) {
		    	if(linea.equals(gen) || bandG){
		    		bandG=true;
		    		if(linea.substring(0,1).equals("R")){
			    		robots[i]=linea.substring(1);
			    		i++;
			    	}
		    		if(linea.substring(0, 1).equals("G") && i>0) {
		    			bandG=false;
		    		}
		    	}
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
		
		String[] robotsAux= new String[2*CANT_PAREJAS];;
		for(int i=0;i<robots.length;i++) {
			robotsAux[i]=robots[i].split(";")[0];
		}
		lista.setModel(new DefaultComboBoxModel(robotsAux));
	}
	
	private void generacionesCargar() {
		generaciones= new String[genActual];
		//System.out.println(genActual);
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File ("DatosGen.txt");
		    fr = new FileReader (archivo);
		    br = new BufferedReader(fr);

		         // Lectura del fichero
		    String linea;
		    int i=0;
		    linea=br.readLine();
		    while((linea=br.readLine())!=null) {
		    	//System.out.println(linea);
		    	//System.out.println(linea.substring(0,1));
		    	if(linea.substring(0,1).equals("G")){
		    		generaciones[i]=linea;
		    		//System.out.println(linea);
		    		
		    		i++;
		    	}
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

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "OK":
			if(estado==1){
				etiquetaRobot.setText(lista.getSelectedItem().toString());
				recorridosCargar(lista.getSelectedItem().toString());
				
				estado+=1;
				seguir.setVisible(false);
				lista.setVisible(false);
			}else {
				etiquetaGen.setText(lista.getSelectedItem().toString());
				robotsCargar(lista.getSelectedItem().toString());
				atras.setVisible(true);
				estado+=1;
			}
			break;
		case "Atras":
			if(estado==1){
				etiquetaGen.setText("");
				lista.setModel(new DefaultComboBoxModel(generaciones));
				atras.setVisible(false);
				//lista.setModel(new DefaultComboBoxModel(robots));
				estado-=1;
				
			}else {
				etiquetaRobot.setText("");
				etiquetaRecorridos.setText("");
				seguir.setVisible(true);
				lista.setVisible(true);
				estado-=1;
			}
			break;
		}
		//System.out.println(estado);
	}
	
}
