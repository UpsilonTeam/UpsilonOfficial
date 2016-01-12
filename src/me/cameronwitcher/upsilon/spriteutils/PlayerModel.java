package me.cameronwitcher.upsilon.spriteutils;

public enum PlayerModel {
	
	YELLOW("yellow"),
	GREEN("green"),
	BLUE("blue"),
	PURPLE("purple"),
	PINK("pink"),
	UFO("ufo");
	
	String color;
	private PlayerModel(String color) {
		this.color = color;
	}
	
	public String getColor(){
		return color;
	}
	
	public int getPrice(){
		switch(this){
		case GREEN:
			return 100;
		case BLUE:
			return 150;
		case PURPLE:
			return 200;
		case PINK:
			return 250;
		case UFO:
			return 1000;
		default:
			return -1;
		
			
		}
	}

}
