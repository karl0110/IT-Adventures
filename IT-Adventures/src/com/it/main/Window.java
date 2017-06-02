package com.it.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

public class Window extends Canvas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	
	public Window(String title,int width,int height){
		frame = new JFrame(title);
		
		setMinimumSize(new Dimension(width,height));
		setPreferredSize(new Dimension(width,height));
		setMaximumSize(new Dimension(width,height));
		
		frame.add(this);//Das Spiel wird dem JFrame hinzugefügt um Fensterpreferenzen zu übernehmen.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ermöglicht das Beenden des Programmes durch Schließen des Fensters.
		frame.setUndecorated(true);//Entfernt die Menüleiste oben am Bildschirm (Vollbild)
		frame.pack();
		frame.setResizable(false);//Fenster kann nicht vergrößert oder verkleinert werden, für korrekt Skalierung notwendig.
		frame.setLocationRelativeTo(null);//Bewegt Fenster in die Mitte des Bildschirms.
		frame.setVisible(true);//Macht das Fenster sichtbar.
	}

	
}
