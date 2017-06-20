package com.it.main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class CharacterMenu {

	private BufferedImage image;
	private BufferedImageLoader imageLoader;
	
	public CharacterMenu(BufferedImageLoader imageLoader){
		this.imageLoader=imageLoader;
		image=imageLoader.loadImage("/images/characterMenu.png");
	}
	
	
	public void tick(){
		
	}
	
	public void render(Graphics g){
		g.drawImage( image , 0, 0, 1920,1080,null);
	}
}
