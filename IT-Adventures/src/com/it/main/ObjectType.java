package com.it.main;

public enum ObjectType {
	Dirt("/images/dirt.png",false,false),
	Grass("/images/grass.png",false,true);
	
	
	String imagePath;
	boolean affectedByGravity,passable;
	
	private ObjectType(String imagePath,boolean affectedByGravity,boolean passable){
		this.imagePath=imagePath;
		this.affectedByGravity=affectedByGravity;
		this.passable=passable;
	}
}
