package com.it.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * Die Klasse ist für das animieren des Spielers zuständig, welches durch das abspielen von verschiedenen Bildern geschiet.
 * 
 * @author RealTutsGML
 */
public class Animator {

	private BufferedImage[] images;//alle Bilder, welche abgespielt werden sollen.
	private BufferedImage currentImage;
	
	private int tickSpeed;//Wie schnell die Bilder abgespielt werden sollen.
	private int frames;//Wie viele Bilder es gibt.
	
	private int tickCount=0;//Wie oft das Animator-Objekt aktualisiert wurde, wird als Timer genutzt.
	private int count=0;//Die aktuelle Position im Array, also das aktuelle Bild
	

	public Animator(BufferedImage[] images, int tickSpeed) {
		this.images=images;
		this.tickSpeed = tickSpeed;
		frames = images.length;
		

	}

	/**
	 * Die Animation wird aktualisiert.
	 */
	public void runAnimation(){
		tickCount++;
		if(tickCount > tickSpeed){//Falls die Zeit vorübergeht, welche zwischen Bildern festgelegt wurde.
			tickCount=0;
			nextFrame();
		}
	}
	
	private void nextFrame(){
		for(int i =0;i<frames;i++){
			if(count==i){//Das neue Bild wird aus dem Array geladen
				currentImage=images[i];
			}
		}
		count++;
		
		if(count>=frames){
			count=0;
		}
	}
	/**
	 * 
	 * Die Grafik der Aniamtion wird gemalt.
	 */
	public void renderAnimation(Graphics g,float x,float y,float width, float height) {
		g.drawImage(currentImage, (int)x,(int) y,(int) width,(int)height,null);
	}

}
