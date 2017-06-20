package com.it.main;

public abstract class LivingTileEntity extends TileEntity {

	protected float health;
	protected boolean facingRight;
	protected HealthBar healthBar;
	
	public LivingTileEntity(float x, float y, BufferedImageLoader imageLoader, TileType type, TileHandler handler) {
		super(x, y, imageLoader, type, handler);
		isLivingTileEntity=true;
	}

	
	public void addHealth(int healthToAdd){
		health+=healthToAdd;
		
	}

	public void removeHealth(int healthToRemove){
		health-=healthToRemove;
	}


	public float getHealth(){
		return health;
	}

}
