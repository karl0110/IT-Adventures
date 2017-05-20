package com.it.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

/*
 * Author: Jaime Hall(angeblich)
 * 
 * Klasse erweitert Canvas um ein Fenster zum anzeigen des Spiels zu erstellen.
 * Klasse implementiert Runnable um die Methode run zu Nutzen, welche beim starten eines neuen Threads aufgerufen wird.
 */

public class Game extends Canvas implements Runnable{

	public static final int WIDTH=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;//Klassenkonstante für die Breite des Fensters, wird automatisch auf die maximale Breite des jeweiligen Bildschirms gesetzt.
	public static final int HEIGHT=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;//Klassenkonstante für die Höhe des Fensters, wird automatisch auf die maximale Höhe des jeweiligen Bildschirms gesetzt.
	
	private static final long serialVersionUID = 1L;
	private boolean running;//Boolsche Variable die bestimmt ob das Spiel am laufen ist.
	
	private Thread thread;
	private Menu menu;
	private BufferedImageLoader imageLoader;
	private GameObjectHandler handler;
	private Sound sound;
	private Camera camera;
	private Background background;
	
	public enum STATE{//Enum zum Speichern der Verschiedenen Zustände des Spieles.
		MainMenu,PlayMenu,Game
	};
	public static STATE State=STATE.MainMenu;//Der aktuelle Zustand des Spiels, ist am Anfang das Hauptmenü.
	
	/*
	 * Diese Methode wird beim starten des Threads aufgerufen. Sie beeinhaltet die Initilaisierung und die Haup Spiel-Schleife.
	 * 
	 */
	public void run() {
		init();
		long lastTime = System.nanoTime();//Wird für einen Timer benötigt.
		final double amountOfTicks = 60.0;//Wie oft die Methode tick() in einer Sekunde aufgerufen werden soll.
		double ns = 1000000000 / amountOfTicks;//Berechnet wie viel Zeit vergeht bis die Methode tick() aufgerufen wird.
		double delta = 0;//Variable welche Berechnet, wann die tick() Methode aufgerufen werden soll.
		int updates = 0;//Wie oft das Progam die tick() Methode in einer Sekunde aufrufen hat.
		int frames = 0;//Wie oft das Program die Methode render() in einer Sekunde aufgerufen hat.
		long timer = System.currentTimeMillis();//Eine Variable um die Zeit zu Zählen. Ist für die Berechnung der "TicksPerSecond" und "FramesPerSecond" notwendig.

		while (running) {//Solange das Spiel läuft.
			long now = System.nanoTime();//Timer Variable für die aktuelle Zeit.
			delta += (now - lastTime) / ns;//Berechnet mithilfe der Timer und ns Variablen, wann die tick() Methode aufgerufen werden soll.
			lastTime = now;//Stellt den Timer wieder zurück.
			if (delta >= 1) {//Guckt ob die Methode tick() jetzt aufgerufen werden soll.
				tick();//ruft die Methode tick() auf.
				updates++;//Addiert zum Update-Zähler 1 dazu.
				delta--;//Setzt den tick() Aufruf-Timer zurück.
			}
			render();//ruft die render() Methode auf.
			frames++;////Addiert zum Frame-Zähler 1 dazu.

			if (System.currentTimeMillis() - timer > 1000) {//Wenn eine Sekunde vergangen ist.
				timer += 1000;//addiert zum Timer eine Sekunde dazu.
				System.out.println("Ticks: " + updates + " FPS: " + frames);//Druckt die "TicksPerSecond" und "FramesPerSecond" aus.
				updates = 0;//setzt den Tick-Zähler zurück.
				frames = 0;//setzt den Frame-Zähler zurück.
			}
		    
		    
		    
		}
		stop();//Wenn das Spiel beendet werden soll, wird die Methode stop() aufgerufen.
		
	}
	
	/*
	 * Diese Methode Initialisiert diverse Objekte, welche für das Programm notwendig sind.
	 */
	private void init(){
		sound = new Sound();//Sound Klasse ist für Wiedergabe von diversen Geräuschen da.
		imageLoader=new BufferedImageLoader();//Die BufferedImageLoader Klasse ist da um Bilder zu laden.
		handler=new GameObjectHandler();//Der GameObjectHandler ist für das speichern aller Spielobjekte zuständig.
		this.addKeyListener(new KeyInput(handler));//Klasse welche bei Tastendrücken überprüft, ob diese relevant für das Spiel sind und reagiert entsprechend.
		menu=new Menu(imageLoader,this,sound);//Menu Klasse ist für das aktualisieren und rendern des Hauptmenüs zuständig.
		this.addMouseListener(new MouseInput(menu));//Klasse welche bei Maus-Klicks überprüft, ob diese relevant für das Spiel sind und reagiert entsprechend.
		LevelLoader levelLoader = new LevelLoader(imageLoader, handler);//Klasse zum erstellen von den Spielobjekten einzelner Level, diese werden durch ein Bild geladen, um einfaches Leveldesign zu ermöglichen.
		camera = levelLoader.loadLevel("jaime", 1);//Lädt das erste Level vom "Jaime" Charakter
		background= new Background(BackgroundType.Day, imageLoader);
	
		
		
	}
	
	/*
	 * Methode welche alle Spielobjekte aktualisiert(z.B. Schwerkraft des Spielers)
	 */
	public void tick(){
		if(Game.State==Game.STATE.MainMenu){
			menu.tick();//Wenn der Zustand des Spieles das Hauptmenü ist, wird das Menü aktualisiert.
		}
		else if(Game.State==Game.STATE.PlayMenu)
		{
			menu.tick();//Wenn der Zustand des Spieles das Spielmenü ist, wird das Menü aktualisiert.
		}
		else if(Game.State==Game.STATE.Game){
			handler.tick();//Wenn der Zustand des Spieles das Spiel selber ist, werden alle Spielobjekte über den GameObjectHandler aktualisiert.
			camera.tick();
		}
	}
	
	/*
	 * //Methode zum malen von diversen Grafiken.
	 */
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();//Es wird eine Strategie geladen, welche es dem Programm ermöglicht Grafiken im Voraus vorzumalen, um effizienter mit den Resourcen des Computers umzugehen.
		if(bs==null){
			createBufferStrategy(3);//Falls noch keine Strategie vorhanden ist, wird eine neue erstellt. Die 3 im Parameter bedeutet, dass zwei bilder im voraus gemalt werden.
			return;
		}
		
		Graphics g = bs.getDrawGraphics();//Von dem BufferStrategy Objekt wird dann ein Graphics Objekt geholt, welches zum eigentlichen malen der Grafiken gebraucht wird.
		Graphics2D g2d=(Graphics2D)g;
		////////////////Bereich zum zeichnen von diversen Grafiken
		
		if(Game.State==Game.STATE.MainMenu){
			menu.render(g);//Wenn der Zustand des Spieles das Hauptmenü ist, werden die Grafiken des Menüs geladen.
		}
		else if(Game.State==Game.STATE.PlayMenu)
		{
			menu.render(g);//Wenn der Zustand des Spieles das Spielmenü ist, werden die Grafiken des Menüs geladen.
		}
		else if(Game.State==Game.STATE.Game){
			
			
			background.render(g);
			g2d.translate(camera.getX(), camera.getY());
			handler.render(g);////Wenn der Zustand des Spieles das Spiel selber ist, werden die Grafiken von allen Spielobjekten über den GameObjectHandler geladen.
			g2d.translate(-camera.getX(), -camera.getY());
		}
		
		
		///////////////////Ende des Bereiches.
		
		g.dispose();//Graphics Objekt wird gelöscht, ist nicht gebraucht.
		
		bs.show();//Die BufferStrategy, welche mit Hilfe des Graphics Objekts beschmückt wurde, wird angezeigt.
		
		
		
	}

	public static void main(String args[]){
		Game game = new Game();
		JFrame frame = new JFrame();//Das Fenster in dem alles angezeigt wird.
		
		game.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		
		frame.add(game);//Das Spiel wird dem JFrame hinzugefügt um Fensterpreferenzen zu übernehmen.
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Ermöglicht das Beenden des Programmes durch Schließen des Fensters.
		frame.setUndecorated(true);//Entfernt die Menüleiste oben am Bildschirm (Vollbild)
		frame.pack();
		frame.setResizable(false);//Fenster kann nicht vergrößert oder verkleinert werden, für korrekt Skalierung notwendig.
		frame.setLocationRelativeTo(null);//Bewegt Fenster in die Mitte des Bildschirms.
		frame.setVisible(true);//Macht das Fenster sichtbar.
		game.start();//Ruft die Methode start() zum initialisieren des Threads auf.
		
		
	}
	
	/*
	 * Startet das Spiel.
	 */
	private synchronized void start(){//Startet das Programm über einen Thread, welcher alle Berechnungen und Grafiken lädt.
		running = true;
		thread = new Thread(this);
		thread.start();//Der erstellte Thread wird gestartet.(Die Methode run() wird ausgeführt.
	}
	
	/*
	 * Beendet das Spiel.
	 */
	public synchronized void stop() {//Stoppt das Programm und den Thread.
		running = false;
		try {
			thread.join();//Der Thread wird beendet.
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		System.exit(1);
	}
}
