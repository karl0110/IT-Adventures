package com.it.main;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

/*
 * Klasse erweitert Canvas um ein Fenster zum anzeigen des Spiels zu erstellen.
 * Klasse implementiert Runnable um die Methode run zu Nutzen, welche beim starten eines neuen Threads aufgerufen wird.
 */

public class Game extends Canvas implements Runnable{

	public static final int WIDTH=1920;//Klassenkonstante zum Festlegen der Breite des Fensters.
	public static final int HEIGHT=WIDTH/16*9;//Klassenkonstante welche mithilfe der Breite die Höhe des Fensters errechnet.
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	public static void main(String args[]){
		Game game = new Game();//erstellt eine neue Intanz der Game Klasse.
		JFrame frame = new JFrame();//ein neuer JFrame wird erstellt.
		
		game.setMinimumSize(new Dimension(WIDTH,HEIGHT));//setzt die Mindestgröße des Spielfensters.
		game.setPreferredSize(new Dimension(WIDTH,HEIGHT));//setzt die bevorzugte Größe des Spielfensters.
		game.setMaximumSize(new Dimension(WIDTH,HEIGHT));//setzt die maximale Größe des Spielfensters.
		
		frame.add(game);//Das Spiel wird dem JFrame hinzugefügt um Fensterpreferenzen zu übernehmen.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ermöglicht das Beenden des Programmes durch Schließen des Fensters.
		frame.setUndecorated(true);//Entfernt die Menüleiste oben am Bildschirm (Vollbild)
		frame.setResizable(false);//Fenster kann nicht vergrößert oder verkleinert werden, für korrekt Skalierung notwendig.
		frame.setLocationRelativeTo(null);//Bewegt Fesnter in die Mitte des Bildschirms.
		frame.setVisible(true);//Macht das Fenster sichtbar.
		
		game.start();//Ruft die Methode start() zum initialisieren des Threads auf.
		
	}
	
	
	private synchronized void start(){
		
	}
}
