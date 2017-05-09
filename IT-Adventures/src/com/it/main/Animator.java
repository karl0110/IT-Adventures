package com.it.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animator {

	private BufferedImage[] images;
	private BufferedImage currentImage;
	
	private int tickSpeed;
	private int frames;
	
	private int tickCount=0;
	private int count=0;
	

	public Animator(BufferedImage[] images, int tickSpeed) {
		this.images=images;
		this.tickSpeed = tickSpeed;
		frames = images.length;
		

	}

	public void runAnimation(){
		tickCount++;
		if(tickCount > tickSpeed){
			tickCount=0;
			nextFrame();
		}
	}
	
	private void nextFrame(){
		for(int i =0;i<frames;i++){
			if(count==i){
				currentImage=images[i];
			}
		}
		count++;
		
		if(count>=frames){
			count=0;
		}
	}
	
	public void renderAnimation(Graphics g,float x,float y,float width, float height) {
		g.drawImage(currentImage, (int)x,(int) y,(int) width,(int)height,null);
	}

}
