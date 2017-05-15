package com.it.main;

public enum ObjectType {
	
	Player("/images/character_sprite_sheet.png",true,false,32,64,1,2),
	Stone("/images/block_sprite_sheet.png",false,false,32,32,3,1),
	Dirt("/images/block_sprite_sheet.png",false,false,32,32,1,1),
	Grass("/images/block_sprite_sheet.png",false,false,32,32,2,1);
	
	
	
	String imagePath;
	boolean affectedByGravity,passable;
	float width,height;
	int ssCol,ssRow;
	
	private ObjectType(String imagePath,boolean affectedByGravity,boolean passable,float width,float height,int ssCol,int ssRow){
		this.imagePath=imagePath;
		this.affectedByGravity=affectedByGravity;
		this.passable=passable;
		this.width=width;
		this.height=height;
		this.ssCol=ssCol;
		this.ssRow=ssRow;
	}
}
