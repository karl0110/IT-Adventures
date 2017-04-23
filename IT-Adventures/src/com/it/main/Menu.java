package com.it.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Menu {

	private BufferedImage mainMenuBackground, playMenuBackground;
	private boolean animating=false;
	private Animator animator=null;
	private BufferedImageLoader imageLoader;
	private SpriteSheetLoader ssLoader;

	public Menu(BufferedImageLoader imageLoader, Game game,SpriteSheetLoader ssLoader) {
		mainMenuBackground = imageLoader.loadImage("/images/mainMenuBackground.png");
		playMenuBackground = imageLoader.loadImage("/images/play_Background.png");
		this.imageLoader=imageLoader;
		this.ssLoader=ssLoader;

	}

	public void render(Graphics g) {
		if (Game.State == Game.STATE.MainMenu) {
			g.drawImage(mainMenuBackground, 0, 0, null);
		} else if (Game.State == Game.STATE.PlayMenu) {
			g.drawImage(playMenuBackground, 0, 0, null);
		}
		if(animating){
			if(animator==null){
				
				animator=new Animator(ssLoader.getImageSet(imageLoader.loadImage("/images/lul.png"), 24, 1920, 1080),24);
			}
			if(animator.renderAnimation(g)==true){
				animating=false;
				Game.State=Game.STATE.PlayMenu;
				animator=null;
				
			}
		
		}

	}
	

	public boolean isAnimating() {
		return animating;
	}

	public void setAnimating(boolean animating) {
		this.animating = animating;
	}

}
