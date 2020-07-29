package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import otros.IConstants;

public class Etiqueta<T> extends JLabel implements IConstants{
	private T contenido;
	
	public Etiqueta(T pContent, String text){
		super(text);
		contenido = pContent;
		
	}
	
	public Etiqueta(T pContent){
		super(pContent.toString());
		contenido = pContent;
		
	}

	public T getContenido() {
		return contenido;
	}
	
	
}
