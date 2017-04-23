package com.it.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Menu {

	private BufferedImage mainMenuBackground, playMenuBackground;
	private boolean animating,startedAnimating=false;
	private Animator animator=null;
	private BufferedImageLoader imageLoader;
	private SpriteSheetLoader ssLoader;
	private long timer;
	private int index=0;

	public Menu(BufferedImageLoader imageLoader, Game game,SpriteSheetLoader ssLoader) {
		mainMenuBackground = imageLoader.loadImage("/images/mainMenuBackground.png");
		playMenuBackground = imageLoader.loadImage("/images/play_Background.png");
		this.imageLoader=imageLoader;
		this.ssLoader=ssLoader;

	}

	public void tick(){
		if(animating){
			if(!startedAnimating){
				timer=System.currentTimeMillis();
			}
			startedAnimating=true;
			if(timer-System.currentTimeMillis()>=1000/24){
				//Changing the image
				index++;
				timer=System.currentTimeMillis();
			}
		}
	}
	
	public void render(Graphics g) {
		if (Game.State == Game.STATE.MainMenu) {
			g.drawImage(mainMenuBackground, 0, 0, null);
		} else if (Game.State == Game.STATE.PlayMenu) {
			g.drawImage(playMenuBackground, 0, 0, null);
		}
		

	}
	

	public boolean isAnimating() {
		return animating;
	}

	public void setAnimating(boolean animating) {
		this.animating = animating;
	}

}
