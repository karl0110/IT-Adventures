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
	private float x,y=0;
	private float width=1920;
	private float height=1080;

	public Menu(BufferedImageLoader imageLoader, Game game,SpriteSheetLoader ssLoader) {
		mainMenuBackground = imageLoader.loadImage("/images/mainMenuBackground.png");
		playMenuBackground = imageLoader.loadImage("/images/play_Background.png");
		this.imageLoader=imageLoader;
		this.ssLoader=ssLoader;

	}

	public void tick(){
		if(animating){
			x-=15f;
			y-=10f;
			width+=30f;
			height+=20f;
		}
		if(width==5760){
			animating=false;
					width=1920;
					height=1080;
					x=0;
					y=0;
					Game.State=Game.STATE.PlayMenu;
		}
	}
	
	public void render(Graphics g) {
		if (Game.State == Game.STATE.MainMenu) {
		
			
			g.drawImage(mainMenuBackground,(int)x,(int)y,(int)width,(int)height, null);
			
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
