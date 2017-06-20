package com.it.main;

public enum BackgroundType {

	Day(300,"/images/Day-Segment 1.png"),
	Night(300,"/images/Night-Segment.png"),
	Castle(300,"/images/Burg-Segment.png"),
	Snow(300,"/images/Berg-Segment.png");
	
	
	public String[] backgroundImageLocations;
	public float width;
	
	BackgroundType(float width,String ...args){
		this.width=width;
		backgroundImageLocations=args;
	}
}
