
package com.it.main;

import java.awt.Graphics;
import java.util.LinkedList;



public class GameObjectHandler {

	public LinkedList<Tile> object = new LinkedList<Tile>();
	
	public void tick(){
		for (int i = 0; i < object.size(); i++) {
			Tile tempObject = object.get(i);

			
			if(tempObject.getType()==ObjectType.Player){
				tempObject.tick();
			}
			
		}
	}
	
	public void render(Graphics g)
	{
		for (int i = 0; i < object.size(); i++) {
			Tile tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void addObject(Tile object){
		this.object.add(object);
	}
	
	public void removeObject(Tile object){
		this.object.remove(object);
	}
	
	public void removeAllObjects(){
		for(int i=0;i<object.size();i++){
			removeObject(object.get(i));
		}
	}
}
