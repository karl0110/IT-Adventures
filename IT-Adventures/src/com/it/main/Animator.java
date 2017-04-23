package com.it.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animator {

	private BufferedImage[] images;
	private int speed;
	private int frames;
	private int index;
	private boolean started;
	private long lastTime;

	public Animator(BufferedImage[] images, int speed) {
		this.images=images;
		this.speed = speed;
		frames = images.length;
		index = 0;

	}

	public boolean renderAnimation(Graphics g) {
		boolean finished=false;
		
			if (!started) {
				lastTime = System.currentTimeMillis();
			}
			started = true;
			if (lastTime - System.currentTimeMillis() > 1000 / speed) {
				g.drawImage(images[index], 0, 0, null);
				index++;
				lastTime=System.currentTimeMillis();
			}
		
			if(index>=frames){
				finished=true;
			}
			return finished;
	}

}
