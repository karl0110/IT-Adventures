package com.it.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/*
 * Author: Jaime Hall
 * 
 * Klasse erweitert Canvas um ein Fenster zum anzeigen des Spiels zu erstellen.
 * Klasse implementiert Runnable um die Methode run zu Nutzen, welche beim starten eines neuen Threads aufgerufen wird.
 */

public class Game extends Canvas implements Runnable{

	public static final int WIDTH=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;//Klassenkonstante für die Breite des Fensters, wird automatisch auf die maximale Breite des jeweiligen Bildschirms gesetzt.
	public static final int HEIGHT=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;//Klassenkonstante für die Höhe des Fensters, wird automatisch auf die maximale Höhe des jeweiligen Bildschirms gesetzt.
	
	
	private static final long serialVersionUID = 1L;
	
	private Thread thread;//Variable zum Speichern des Threads.
	private boolean running;//Boolsche Variable die bestimmt ob das Spiel am laufen ist.
	
	
	private Menu menu;//Speichert das Menu Objekt
	private BufferedImageLoader imageLoader;//Speichert BufferedImageLoader Objekt.
	private GameObjectHandler handler;//Speichert den Handler welcher die GameObjects in einer Liste speichert.
	private Sound sound;//Speichert das Sound objekt, welches Sound-Dateien abspielen kann.
	
	private BufferedImage background;
	
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
		long lastTime = System.nanoTime();//Speichert die aktuelle Zeit in einer long Variable, um als Timer zu agieren.
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
	
	private void init(){//Methode für Initialisierung von diversen Objekten.
		sound = new Sound();//Erstellt ein Objekt der Sound Klasse und speichert es.
		imageLoader=new BufferedImageLoader();//Erstellt ein Objekt der BufferedImageLoader Klasse und speichert es.
		handler=new GameObjectHandler();//Erstellt ein neues Objekt der Klasse GameObjectHandler und speichert es.
		this.addKeyListener(new KeyInput(handler));//Ruft die Methode addKeyListener() aus der Superklasse Canvas aus und übergibt als Parameter eine neues Objekt der KeyInput Klasse. 
		menu=new Menu(imageLoader,this,sound);//Erstellt ein neues Objekt der Klasse Menu und speichert es.
		this.addMouseListener(new MouseInput(menu));//Ruft die Methode addMouseListener() aus der Superklasse Canvas aus und übergibt als Parameter eine neues Objekt der MouseInput Klasse. 
		
		//for(int i = 0;i<60;i++){//For-Schleife zum erstellen einer rudimentären Plattform.
			//handler.addObject(new Block(i*64, 900, handler, imageLoader, ObjectType.Dirt));//Erstellt ein neues Object der Klasse Block und fügt es dem GameObjektHandler zu, welcher es speichert und seine Methode tick() aufruft.
		//}
		//handler.addObject(new Player(100, 0, imageLoader, handler, ObjectType.Player)); //erstellt einen neuen Player
		LevelLoader levelLoader = new LevelLoader(imageLoader, handler);
		levelLoader.loadLevel("jaime", 1);
		//sound.playSound("/sound/jäger.wav");
		
		background=imageLoader.loadImage("/images/castle.png");
		
		
	}
	
	public void tick(){//Methode welche in diversen Objekten die Methode tick() aufruft.
		if(Game.State==Game.STATE.MainMenu){
			menu.tick();//Wenn der Zustand des Spieles das Hauptmenü ist, wird die Methode tick() im Menü aufgerufen.
		}
		else if(Game.State==Game.STATE.PlayMenu)
		{
			menu.tick();//Wenn der Zustand des Spieles das Spielmenü ist, wird die Methode tick() im Menü aufgerufen.
		}
		else if(Game.State==Game.STATE.Game){
			handler.tick();//Wenn der Zustand des Spieles das Spiel selber ist, wird die Methode tick() im GameObjektHandler aufgerufen, welcher in allen SpielObjekten die tick() Methode aufruft.
		}
	}
	
	public void render(){//Methode zum malen von diversen Grafiken.
		BufferStrategy bs = this.getBufferStrategy();//Ruft die Methode getBufferStrategy() in der Superklasse Canvas auf und speichert den returnten Wert in einer Variable.
		if(bs==null){//Falls die BufferStrategy noch nicht erstellt wurde,
			createBufferStrategy(3);//wird eine neue erstellt. Die drei im Parameter steht dafür, dass 2 Bilder im Voraus geladen werden (Buffer (eng.) = Puffer).
			return;
		}
		
		Graphics g = bs.getDrawGraphics();//Von dem BufferStrategy Objekt wird dann ein Graphics Objekt geholt, welches zum eigentlichen malen der Grafiken gebraucht wird.
		////////////////Bereich zum zeichnen von diversen Grafiken
		
		if(Game.State==Game.STATE.MainMenu){
			menu.render(g);//Wenn der Zustand des Spieles das Hauptmenü ist, wird die Methode render() im Menü aufgerufen.
		}
		else if(Game.State==Game.STATE.PlayMenu)
		{
			menu.render(g);//Wenn der Zustand des Spieles das Spielmenü ist, wird die Methode render() im Menü aufgerufen.
		}
		else if(Game.State==Game.STATE.Game){//Wenn der Zustand des Spieles das Spiel selber ist,
			//g.setColor(Color.WHITE);//wird die Farbe für das Graphics Objekt auf weiß gesetzt.
			//g.fillRect(0, 0,WIDTH,HEIGHT);//wird ein gefülltes Rechteck mit der vorher Ausgewählten Farbe gemalt, welches den Hintergrund darstellt.
			g.drawImage(background, 0, 0,1920,1080, null);
			handler.render(g);// wird die Methode render() im GameObjektHandler aufgerufen, welcher in allen SpielObjekten die render() Methode aufruft.
		}
		
		
		///////////////////Ende des Bereiches.
		
		g.dispose();//Graphics Objekt wird gelöscht, ist nicht gebraucht.
		
		bs.show();//Die BufferStrategy, welche mit Hilfe des Graphics Objekts beschmückt wurde, wird angezeigt.
		
		
		
	}

	public static void main(String args[]){//Methode wird automatisch beim Start des Programs aufgerufen.
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
		// Tyrus ist der Boss
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

			e.printStackTrace();//Problembehandlung wird gedruckt.
		}
		System.exit(1);//Das Programm wird beendet
	}
}
