package com.it.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;



public class Menu {

	private BufferedImage mainMenuBackground,playMenuBackground;
	
	public Menu(BufferedImageLoader imageLoader, Game game){
		mainMenuBackground=imageLoader.loadImage("/images/lul.png");
		playMenuBackground=imageLoader.loadImage("/images/play_Background.png");
		
	}
	
	
	public void render(Graphics g){
		if(Game.State==Game.STATE.MainMenu){
			g.drawImage(mainMenuBackground,0,0,null);
		}
		else if(Game.State==Game.STATE.PlayMenu){
			g.drawImage(playMenuBackground,0,0,null);
		}
		
		
	
	}
}
