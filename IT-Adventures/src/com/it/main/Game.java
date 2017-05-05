package com.it.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/*
 * Klasse erweitert Canvas um ein Fenster zum anzeigen des Spiels zu erstellen.
 * Klasse implementiert Runnable um die Methode run zu Nutzen, welche beim starten eines neuen Threads aufgerufen wird.
 */

public class Game extends Canvas implements Runnable{

	public static final int WIDTH=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;//Klassenkonstante für die Breite des Fensters, wird automatisch auf die maximale Breite des jeweiligen Bildschirms gesetzt.
	public static final int HEIGHT=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;//Klassenkonstante für die Höhe des Fensters, wird automatisch auf die maximale Höhe des jeweiligen Bildschirms gesetzt.
	
	
	private static final long serialVersionUID = 1L;
	
	private Thread thread;//Variable zum Speichern des Threads.
	private boolean running;//Boolsche Variable die bestimmt ob das Spiel am laufen ist.
	
	private BufferedImage background=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	
	private Menu menu;//Speichert das Menu Objekt
	private BufferedImageLoader imageLoader;//Speichert BufferedImageLoader Objekt.
	private GameObjectHandler handler;//Speichert den Handler welcher die GameObjects in einer Liste speichert.
	
	public enum STATE{//Enum zum Speichern der Verschiedenen Zustände des Spieles.
		MainMenu,PlayMenu,Game
	};
	public static STATE State=STATE.Game;//Der aktuelle Zustand des Spiels, ist am Anfang das Hauptmenü.
	
	/*
	 * This Method is run when the Thread is started. It contains the initilization of things needed for the Game and the Main Game Loop.
	 * 
	 */
	public void run() {
		init();
		long lastTime = System.nanoTime();//Saves the current Time in a long Variable to count time.
		final double amountOfTicks = 60.0;//The Amount of times the Method tick() is run in 1 second.
		double ns = 1000000000 / amountOfTicks;//Calculates the time that passes before the tick() Method is run again.
		double delta = 0;//
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();

		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			if (delta >= 1) {
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("Ticks: " + updates + " FPS: " + frames);
				updates = 0;
				frames = 0;
			}
		    
		    
		    
		}
		stop();
		
	}
	
	private void init(){
		Sound sound = new Sound();
		imageLoader=new BufferedImageLoader();
		this.addKeyListener(new KeyInput());
		menu=new Menu(imageLoader,this,sound);
		this.addMouseListener(new MouseInput(menu));
		handler=new GameObjectHandler();
		for(int i = 0;i<30;i++){
			handler.addObject(new Block(i*32, 900, handler, imageLoader, ObjectType.Dirt));
		}
		
		//sound.playSound("/sound/jäger.wav");
		
		
	}
	
	public void tick(){
		if(Game.State==Game.STATE.MainMenu){
			menu.tick();
		}
		else if(Game.State==Game.STATE.PlayMenu)
		{
			menu.tick();
		}
		else if(Game.State==Game.STATE.Game){
			handler.tick();
		}
	}
	
	public void render(){
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		////////////////
		if(Game.State==Game.STATE.MainMenu){
			menu.render(g);
		}
		else if(Game.State==Game.STATE.PlayMenu)
		{
			menu.render(g);
		}
		else if(Game.State==Game.STATE.Game){
			g.drawImage(background,0,0,null);
			handler.render(g);
		}
		
		
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

			e.printStackTrace();
		}
		System.exit(1);//Das Programm wird beendet
	}
}
