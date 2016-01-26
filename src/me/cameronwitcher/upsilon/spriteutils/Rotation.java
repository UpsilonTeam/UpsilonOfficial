package me.cameronwitcher.upsilon.spriteutils;

public enum Rotation {
	
	UP(0.0),
	DOWN(180.0),
	LEFT(270.0),
	RIGHT(90.0);
	
	double rotation;
	
	Rotation(double rotation){
		this.rotation = rotation;
	}
	
	public double getRotation(){
		return rotation;
	}

}
