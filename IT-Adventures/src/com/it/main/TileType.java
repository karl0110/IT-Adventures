package com.it.main;

public enum TileType {
	
	Player("",true,false,32,64,0,0),
	Enemy("",true,false,32,64,0,0),
	Shot("",true,false,32,32,0,0),
	HealthBar("",false,true,32,5,0,0),
	Stone("/images/block_sprite_sheet.png",false,false,32,32,3,1),
	Dirt("/images/block_sprite_sheet.png",false,false,32,32,1,1),
	Grass("/images/block_sprite_sheet.png",false,false,32,32,2,1),
	Lava("/images/block_sprite_sheet.png",true,false,32,32,5,1),
	Eis("/images/block_sprite_sheet.png",false,false,32,32,8,1),
	EisUnten("/images/block_sprite_sheet.png",false,false,32,32,6,1),
	EisOben("/images/block_sprite_sheet.png",false,false,32,32,7,1),
	Burg("/images/block_sprite_sheet.png",false,false,32,32,9,1),
	HangingGrass("/images/block_sprite_sheet.png",false,true,32,32,4,1);
	
	
	
	String imagePath;
	boolean affectedByGravity,passable;
	float width,height;
	int ssCol,ssRow;
	
	TileType(String imagePath,boolean affectedByGravity,boolean passable,float width,float height,int ssCol,int ssRow){
		this.affectedByGravity=affectedByGravity;
		this.passable=passable;
		this.width=width;
		this.height=height;
	}
}
