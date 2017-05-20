package com.it.main;

public enum BackgroundType {

	Day(300,"/images/Day-Segment 1.png","/images/Day-Segment 2.png");
	
	public String[] backgroundImageLocations;
	public float width;
	
	BackgroundType(float width,String ...args){
		this.width=width;
		backgroundImageLocations=args;
	}
}
