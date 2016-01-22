package me.cameronwitcher.upsilon.spriteutils;

public enum SpriteType {
	
	FLOOR("floor"),
	FALLING_FLOOR("falling_floor"),
	KNOBBER("knobber"),
	GATE("gate"),
	PLAYER("player"),
	LADDER("ladder"),
	WALL("wall"),
	GOLD("gold"),
	ARROW("arrow"),
	BOW("bow"),
	NINJA_CLOAK("ninja_cloak"),
	FALLING_WALL("falling_floor"),
	SWITCH("switch"),
	TRIGGERED_SWITCH("triggered_switch"),
	SPIKE("spike");
	
	SpriteType(String type) {
		
	}
	
	public SpriteSubType getSubType(){
		if(this == FLOOR) return SpriteSubType.PARTIAL_COLLIDEABLE;
		if(this == FALLING_FLOOR) return SpriteSubType.PARTIAL_COLLIDEABLE;
		if(this == KNOBBER) return SpriteSubType.ENEMY;
		if(this == SPIKE) return SpriteSubType.BAD_THINGS;
		if(this == GATE) return SpriteSubType.NON_COLLIDEABLE;
		if(this == LADDER) return SpriteSubType.CLIMABLE;
		if(this == WALL) return SpriteSubType.COLLIDEABLE;
		if(this == GOLD) return SpriteSubType.MONEY;
		if(this == ARROW) return SpriteSubType.PROJECTILE;
		if(this == BOW) return SpriteSubType.WEAPON;
		if(this == NINJA_CLOAK) return SpriteSubType.TOOL;
		if(this == SWITCH) return SpriteSubType.INTERACTABLE;
		if(this == TRIGGERED_SWITCH) return SpriteSubType.INTERACTABLE;
		if(this == FALLING_WALL) return SpriteSubType.COLLIDEABLE;
		else return null;
	}
}