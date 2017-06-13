package com.it.main;

public enum EnemyType {

	Virus(1,1);
	
	int ssCol,ssRow;
	
	EnemyType(int ssCol,int ssRow){
		this.ssCol=ssCol;
		this.ssRow=ssRow;
	}
	
}
