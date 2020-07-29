package gui;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import elementos.Robot;
import otros.IConstants;

public class Ventana extends JFrame implements IConstants{
	
	private JPanel textPanel;
	private JPanel robotsPanel;
    private ArrayList<Robot> robots;

	public Ventana() {
		super("Proyecto 3");
		
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

        JLabel mietiqueta = new JLabel("Prueba");
        mietiqueta.setBounds(100, 100, DEFAULT_LABEL_WIDTH, DEFAULT_LABEL_HEIGHT);
        robotsPanel.add(mietiqueta);
        
        Etiqueta<Robot> miEtiqueta = new Etiqueta<Robot>(new Robot(65, 0), "Gen0");
        miEtiqueta.setBounds(200, 200, DEFAULT_LABEL_WIDTH, DEFAULT_LABEL_HEIGHT);
        robotsPanel.add(miEtiqueta);
        /*robotsPanel.add(new JLabel("Prueba"));
        robotsPanel.add(new Etiqueta<Object>(null, "Prueba"));
        robotsPanel.add(new Etiqueta<Object>(null, "Prueba"));
        robotsPanel.add(new Etiqueta<Object>(null, "Prueba"));
        */
	}
	
}
