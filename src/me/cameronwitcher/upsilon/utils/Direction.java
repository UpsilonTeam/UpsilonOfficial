package me.cameronwitcher.upsilon.utils;

public enum Direction {
	LEFT("left"),
	RIGHT("right"),
	NONE("none"),
	DOWN("down"),
	UP("up");
	
	String direction;
	
	Direction(String direction) {
		this.direction = direction;
	}
	
	public double getAngle(){
		if(direction.equals(Direction.LEFT)) return 180;
		if(direction.equals(Direction.RIGHT)) return 0;
		if(direction.equals(Direction.UP)) return 270;
		if(direction.equals(Direction.DOWN)) return 90;
		return 0;
	}
	

}
