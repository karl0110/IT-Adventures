package com.it.main;

import java.awt.Canvas;

/*
 * Klasse erweitert Canvas, da sie ein Fenster erstellen will welches angezeigt wird.
 * Klasse implementiert Runnable, um die run Funktion zu Nutzen, welche bei dem Start eines Threads aufgerufen wird.
 */

public class Game extends Canvas implements Runnable{

	public static final int WIDTH = 1920;  //Klassenkonstante zum festlegen der Breite des Fensters
	public static final int HEIGHT = WIDTH/16*9;  //Klassenkonstante welche die Höhe des Fensters mithilfe der Breite errechnet
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args){// Initialisierungsmethode der Klasse
		
	}
	
	
}
