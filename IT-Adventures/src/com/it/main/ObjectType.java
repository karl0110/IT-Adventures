package com.it.main;

public enum ObjectType {
	
	Player("/images/jimmieHero.png",true,false,64,128),
	Dirt("/images/Erde_mit_gras.png",false,false,64,64),
	Grass("/images/grass.png",false,true,32,32);
	
	
	
	String imagePath;
	boolean affectedByGravity,passable;
	float width,height;
	
	private ObjectType(String imagePath,boolean affectedByGravity,boolean passable,float width,float height){
		this.imagePath=imagePath;
		this.affectedByGravity=affectedByGravity;
		this.passable=passable;
		this.width=width;
		this.height=height;
	}
}
