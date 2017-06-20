package com.it.main;

public enum TileType {
	
	Player(true,32,64,0,0),
	Enemy(true,32,64,0,0),
	Shot(true,32,32,0,0),
	HealthBar(true,32,5,0,0),
	Stone(false,32,32,3,1),
	Dirt(false,32,32,1,1),
	Grass(false,32,32,2,1),
	Lava(false,32,32,5,1),
	Ice(false,32,32,8,1),
	IceBottom(false,32,32,6,1),
	IceTop(false,32,32,7,1),
	Brick(false,32,32,9,1),
	HangingGrass(true,32,32,4,1),
	USB(false,32,32,1,2),
	Sign(true,32,32,10,1);
	
	boolean passable;
	float width,height;
	int ssCol,ssRow;
	
	TileType(boolean passable,float width,float height,int ssCol,int ssRow){
		this.passable=passable;
		this.width=width;
		this.height=height;
	}
}
