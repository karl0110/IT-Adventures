package com.it.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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
	
	private Thread thread;//Variable zum Speichern des Threads.
	private boolean running;//Boolsche Variable die bestimmt ob das Spiel am laufen ist.
	
	private BufferedImage background;

	@Override
	public void run() {
		init();
		long lastLoopTime = System.nanoTime();
		final int TARGET_FPS = 60;
		final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
		double delta =0;
		
		while(running){
			long now = System.nanoTime();
		    delta+=(now-lastLoopTime)/OPTIMAL_TIME;
		    lastLoopTime=now;
		    if(delta>=1){
		    	tick();
		    	delta--;
		    }
		    render();
		    
		    
		    
		}
		stop();
		
	}
	
	private void init(){
		try {
			background = ImageIO.read(getClass().getResource("/images/play_background.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void tick(){
		
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		////////////////
		
		g.drawImage(background, 0, 0, this);
		
		/////////////////
		
		g.dispose();
		
		bs.show();
		
		
		
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
		frame.pack();
		frame.setResizable(false);//Fenster kann nicht vergrößert oder verkleinert werden, für korrekt Skalierung notwendig.
		frame.setLocationRelativeTo(null);//Bewegt Fesnter in die Mitte des Bildschirms.
		frame.setVisible(true);//Macht das Fenster sichtbar.
		
		game.start();//Ruft die Methode start() zum initialisieren des Threads auf.
		
	}
	
	
	private synchronized void start(){
		if (running)//Falls das Spiel bereits läuft, muss es nicht nochmal gestartet werden.
			return;

		running = true;//Das Spiel startet jetzt.
		thread = new Thread(this);//Ein neuer Thread wird erstellt.
		thread.start();//Der erstellte Thread wird gestartet.(Die Methode run() wird ausgeführt.
	}
	
	public synchronized void stop() {
		if (!running)//Falls das Spiel bereits gestoppt wurde, muss es dies nicht nochmal tuen.
			return;
		running = false;//Das Spiel wird als gestoppt angezeigt.
		try {
			thread.join();//Der Thread wird beendet.
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.exit(1);//Das Programm wird beendet
	}
}
