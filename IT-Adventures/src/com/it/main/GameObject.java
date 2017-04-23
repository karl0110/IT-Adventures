package com.it.main;

//Punchie
import java.awt.Graphics;
import java.awt.image.BufferedImage;


public abstract class GameObject {

	protected float x,y,velX,velY;
	protected int width,height;
	
	private BufferedImage image;
	
	public GameObject(BufferedImageLoader imageLoader) {
		
		image = imageLoader.loadImage("/images/6b0fd161bf4a8340a9a9461576be53a5.jpg");
	}
	
	public abstract void render(Graphics g);
	
	public abstract void tick();
		
	
	
}
