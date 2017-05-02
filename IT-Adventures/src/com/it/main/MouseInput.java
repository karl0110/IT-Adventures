package com.it.main;

import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseInput implements MouseListener {

	private Menu menu;
	
	public MouseInput(Menu menu){
		this.menu=menu;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int x=e.getX();
		int y=e.getY();
		Rectangle mouse =new Rectangle(x,y,1,1);
		
		
		if(Game.State==Game.STATE.MainMenu){
			Rectangle playButton =new Rectangle(0,Game.HEIGHT/4,Game.WIDTH,Game.HEIGHT/4);
			Rectangle exitButton =new Rectangle(0,Game.HEIGHT/2+Game.HEIGHT/4,Game.WIDTH,Game.HEIGHT/4);
			if(playButton.intersects(mouse)){
				
				menu.startPlayAnimation();
				
				
			}
			if(exitButton.intersects(mouse)){
				System.exit(0);
			}
		}
		else if(Game.State==Game.STATE.PlayMenu){
			Rectangle backButton = new Rectangle(0,Game.HEIGHT/2+Game.HEIGHT/4,Game.WIDTH,Game.HEIGHT/4);
			if(backButton.intersects(mouse)){
				Game.State=Game.STATE.MainMenu;
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
		
	
	
	
		
}
