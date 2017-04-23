package com.it.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

/*
 * Klasse erweitert Canvas um ein Fenster zum anzeigen des Spiels zu erstellen.
 * Klasse implementiert Runnable um die Methode run zu Nutzen, welche beim starten eines neuen Threads aufgerufen wird.
 */

public class Game extends Canvas implements Runnable{

	public static final int WIDTH=1920;//Klassenkonstante zum Festlegen der Breite des Fensters.
	public static final int HEIGHT=WIDTH/16*9;//Klassenkonstante welche mithilfe der Breite die H�he des Fensters errechnet.
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Thread thread;//Variable zum Speichern des Threads.
	private boolean running;//Boolsche Variable die bestimmt ob das Spiel am laufen ist.
	
	private Menu menu;
	private BufferedImageLoader imageLoader;
	private SpriteSheetLoader ssLoader;
	
	public enum STATE{
		MainMenu,PlayMenu
	};
	public static STATE State=STATE.MainMenu;
	
	@Override
	public void run() {
		init();
		long lastTime = System.nanoTime();
		final double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
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
		imageLoader=new BufferedImageLoader();
		ssLoader=new SpriteSheetLoader(imageLoader);
		this.addKeyListener(new KeyInput());
		menu=new Menu(imageLoader,this,ssLoader);
		this.addMouseListener(new MouseInput(menu));
		
		
	}
	
	public void tick(){
		if(Game.State==Game.STATE.MainMenu){
			menu.tick();
		}
		else if(Game.State==Game.STATE.PlayMenu)
		{
			menu.tick();
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
		
		
		/////////////////
		
		g.dispose();
		
		bs.show();
		
		
		
	}

	public static void main(String args[]){
		Game game = new Game();//erstellt eine neue Intanz der Game Klasse.
		JFrame frame = new JFrame();//ein neuer JFrame wird erstellt.
		
		game.setMinimumSize(new Dimension(WIDTH,HEIGHT));//setzt die Mindestgr��e des Spielfensters.
		game.setPreferredSize(new Dimension(WIDTH,HEIGHT));//setzt die bevorzugte Gr��e des Spielfensters.
		game.setMaximumSize(new Dimension(WIDTH,HEIGHT));//setzt die maximale Gr��e des Spielfensters.
		
		frame.add(game);//Das Spiel wird dem JFrame hinzugef�gt um Fensterpreferenzen zu �bernehmen.
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Erm�glicht das Beenden des Programmes durch Schlie�en des Fensters.
		frame.setUndecorated(true);//Entfernt die Men�leiste oben am Bildschirm (Vollbild)
		frame.pack();
		frame.setResizable(false);//Fenster kann nicht vergr��ert oder verkleinert werden, f�r korrekt Skalierung notwendig.
		frame.setLocationRelativeTo(null);//Bewegt Fesnter in die Mitte des Bildschirms.
		frame.setVisible(true);//Macht das Fenster sichtbar.
		// Tyrus ist der Boss
		game.start();//Ruft die Methode start() zum initialisieren des Threads auf.
		
	}
	
	
	private synchronized void start(){
		if (running)//Falls das Spiel bereits l�uft, muss es nicht nochmal gestartet werden.
			return;

		running = true;//Das Spiel startet jetzt.
		thread = new Thread(this);//Ein neuer Thread wird erstellt.
		thread.start();//Der erstellte Thread wird gestartet.(Die Methode run() wird ausgef�hrt.
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
