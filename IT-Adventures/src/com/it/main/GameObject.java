package com.it.main;

import java.awt.Rectangle;


/**
 * @author vincent wiechmann, karl mattes
 *
 */
public abstract class GameObject extends Tile {

	protected Player player;
	
	
	
	public GameObject(float x,float y,BufferedImageLoader imageLoader,ObjectType type,Player player) {
		super(x,y,imageLoader,type);
		this.player=player;
		
			
		
	}
	
	/**
	 * Es folgen alle Get-und Set Methoden, sowie die abstrakten Methoden für die Vererbung
	 */
	

	
}
