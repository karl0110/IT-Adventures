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

	public static final int WIDTH=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;//Klassenkonstante f�r die Breite des Fensters, wird automatisch auf die maximale Breite des jeweiligen Bildschirms gesetzt.
	public static final int HEIGHT=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;//Klassenkonstante f�r die H�he des Fensters, wird automatisch auf die maximale H�he des jeweiligen Bildschirms gesetzt.
	
	private static final long serialVersionUID = 1L;
	private boolean running;//Boolsche Variable die bestimmt ob das Spiel am laufen ist.
	
	private Thread thread;
	private Menu menu;
	private BufferedImageLoader imageLoader;
	private GameObjectHandler handler;
	private Sound sound;
	private Camera camera;
	private Background background;
	
	public enum STATE{//Enum zum Speichern der Verschiedenen Zust�nde des Spieles.
		MainMenu,PlayMenu,Game
	};
	public static STATE State=STATE.MainMenu;//Der aktuelle Zustand des Spiels, ist am Anfang das Hauptmen�.
	
	/*
	 * Diese Methode wird beim starten des Threads aufgerufen. Sie beeinhaltet die Initilaisierung und die Haup Spiel-Schleife.
	 * 
	 */
	public void run() {
		init();
		long lastTime = System.nanoTime();//Wird f�r einen Timer ben�tigt.
		final double amountOfTicks = 60.0;//Wie oft die Methode tick() in einer Sekunde aufgerufen werden soll.
		double ns = 1000000000 / amountOfTicks;//Berechnet wie viel Zeit vergeht bis die Methode tick() aufgerufen wird.
		double delta = 0;//Variable welche Berechnet, wann die tick() Methode aufgerufen werden soll.
		int updates = 0;//Wie oft das Progam die tick() Methode in einer Sekunde aufrufen hat.
		int frames = 0;//Wie oft das Program die Methode render() in einer Sekunde aufgerufen hat.
		long timer = System.currentTimeMillis();//Eine Variable um die Zeit zu Z�hlen. Ist f�r die Berechnung der "TicksPerSecond" und "FramesPerSecond" notwendig.

		while (running) {//Solange das Spiel l�uft.
			long now = System.nanoTime();//Timer Variable f�r die aktuelle Zeit.
			delta += (now - lastTime) / ns;//Berechnet mithilfe der Timer und ns Variablen, wann die tick() Methode aufgerufen werden soll.
			lastTime = now;//Stellt den Timer wieder zur�ck.
			if (delta >= 1) {//Guckt ob die Methode tick() jetzt aufgerufen werden soll.
				tick();//ruft die Methode tick() auf.
				updates++;//Addiert zum Update-Z�hler 1 dazu.
				delta--;//Setzt den tick() Aufruf-Timer zur�ck.
			}
			render();//ruft die render() Methode auf.
			frames++;////Addiert zum Frame-Z�hler 1 dazu.

			if (System.currentTimeMillis() - timer > 1000) {//Wenn eine Sekunde vergangen ist.
				timer += 1000;//addiert zum Timer eine Sekunde dazu.
				System.out.println("Ticks: " + updates + " FPS: " + frames);//Druckt die "TicksPerSecond" und "FramesPerSecond" aus.
				updates = 0;//setzt den Tick-Z�hler zur�ck.
				frames = 0;//setzt den Frame-Z�hler zur�ck.
			}
		    
		    
		    
		}
		stop();//Wenn das Spiel beendet werden soll, wird die Methode stop() aufgerufen.
		
	}
	
	/*
	 * Diese Methode Initialisiert diverse Objekte, welche f�r das Programm notwendig sind.
	 */
	private void init(){
		sound = new Sound();//Sound Klasse ist f�r Wiedergabe von diversen Ger�uschen da.
		imageLoader=new BufferedImageLoader();//Die BufferedImageLoader Klasse ist da um Bilder zu laden.
		handler=new GameObjectHandler();//Der GameObjectHandler ist f�r das speichern aller Spielobjekte zust�ndig.
		this.addKeyListener(new KeyInput(handler));//Klasse welche bei Tastendr�cken �berpr�ft, ob diese relevant f�r das Spiel sind und reagiert entsprechend.
		menu=new Menu(imageLoader,this,sound);//Menu Klasse ist f�r das aktualisieren und rendern des Hauptmen�s zust�ndig.
		this.addMouseListener(new MouseInput(menu));//Klasse welche bei Maus-Klicks �berpr�ft, ob diese relevant f�r das Spiel sind und reagiert entsprechend.
		LevelLoader levelLoader = new LevelLoader(imageLoader, handler);//Klasse zum erstellen von den Spielobjekten einzelner Level, diese werden durch ein Bild geladen, um einfaches Leveldesign zu erm�glichen.
		camera = levelLoader.loadLevel("jaime", 1);//L�dt das erste Level vom "Jaime" Charakter
		background= new Background(BackgroundType.Day, imageLoader);
	
		
		
	}
	
	/*
	 * Methode welche alle Spielobjekte aktualisiert(z.B. Schwerkraft des Spielers)
	 */
	public void tick(){
		if(Game.State==Game.STATE.MainMenu){
			menu.tick();//Wenn der Zustand des Spieles das Hauptmen� ist, wird das Men� aktualisiert.
		}
		else if(Game.State==Game.STATE.PlayMenu)
		{
			menu.tick();//Wenn der Zustand des Spieles das Spielmen� ist, wird das Men� aktualisiert.
		}
		else if(Game.State==Game.STATE.Game){
			handler.tick();//Wenn der Zustand des Spieles das Spiel selber ist, werden alle Spielobjekte �ber den GameObjectHandler aktualisiert.
			camera.tick();
		}
	}
	
	/*
	 * //Methode zum malen von diversen Grafiken.
	 */
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();//Es wird eine Strategie geladen, welche es dem Programm erm�glicht Grafiken im Voraus vorzumalen, um effizienter mit den Resourcen des Computers umzugehen.
		if(bs==null){
			createBufferStrategy(3);//Falls noch keine Strategie vorhanden ist, wird eine neue erstellt. Die 3 im Parameter bedeutet, dass zwei bilder im voraus gemalt werden.
			return;
		}
		
		Graphics g = bs.getDrawGraphics();//Von dem BufferStrategy Objekt wird dann ein Graphics Objekt geholt, welches zum eigentlichen malen der Grafiken gebraucht wird.
		Graphics2D g2d=(Graphics2D)g;
		////////////////Bereich zum zeichnen von diversen Grafiken
		
		if(Game.State==Game.STATE.MainMenu){
			menu.render(g);//Wenn der Zustand des Spieles das Hauptmen� ist, werden die Grafiken des Men�s geladen.
		}
		else if(Game.State==Game.STATE.PlayMenu)
		{
			menu.render(g);//Wenn der Zustand des Spieles das Spielmen� ist, werden die Grafiken des Men�s geladen.
		}
		else if(Game.State==Game.STATE.Game){
			
			
			background.render(g);
			g2d.translate(camera.getX(), camera.getY());
			handler.render(g);////Wenn der Zustand des Spieles das Spiel selber ist, werden die Grafiken von allen Spielobjekten �ber den GameObjectHandler geladen.
			g2d.translate(-camera.getX(), -camera.getY());
		}
		
		
		///////////////////Ende des Bereiches.
		
		g.dispose();//Graphics Objekt wird gel�scht, ist nicht gebraucht.
		
		bs.show();//Die BufferStrategy, welche mit Hilfe des Graphics Objekts beschm�ckt wurde, wird angezeigt.
		
		
		
	}

	public static void main(String args[]){
		Game game = new Game();
		JFrame frame = new JFrame();//Das Fenster in dem alles angezeigt wird.
		
		game.setMinimumSize(new Dimension(WIDTH,HEIGHT));
		game.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		game.setMaximumSize(new Dimension(WIDTH,HEIGHT));
		
		frame.add(game);//Das Spiel wird dem JFrame hinzugef�gt um Fensterpreferenzen zu �bernehmen.
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Erm�glicht das Beenden des Programmes durch Schlie�en des Fensters.
		frame.setUndecorated(true);//Entfernt die Men�leiste oben am Bildschirm (Vollbild)
		frame.pack();
		frame.setResizable(false);//Fenster kann nicht vergr��ert oder verkleinert werden, f�r korrekt Skalierung notwendig.
		frame.setLocationRelativeTo(null);//Bewegt Fenster in die Mitte des Bildschirms.
		frame.setVisible(true);//Macht das Fenster sichtbar.
		game.start();//Ruft die Methode start() zum initialisieren des Threads auf.
		
		
	}
	
	/*
	 * Startet das Spiel.
	 */
	private synchronized void start(){//Startet das Programm �ber einen Thread, welcher alle Berechnungen und Grafiken l�dt.
		running = true;
		thread = new Thread(this);
		thread.start();//Der erstellte Thread wird gestartet.(Die Methode run() wird ausgef�hrt.
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
