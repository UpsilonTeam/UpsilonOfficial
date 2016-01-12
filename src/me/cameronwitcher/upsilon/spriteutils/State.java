package me.cameronwitcher.upsilon.spriteutils;

public enum State {
	
	LEFT_UPPER_CORNER("left_upper_corner"),
	LEFT_LOWER_CORNER("left_lower_corner"),
	RIGHT_UPPER_CORNER("right_upper_corner"),
	RIGHT_LOWER_CORNER("right_lower_corner"),
	HORIZONTAL("horizontal"),
	VERTICAL("vertical"),
	LARGE_VERTICAL("vertical_large");
	
	String state;
	
	State(String state){
		this.state = state;
	}

}
