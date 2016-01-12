package me.cameronwitcher.upsilon.spriteutils;

public enum SpriteSubType {
	
	COLLIDEABLE("collideable"),
	ENEMY("enemy"), 
	CLIMABLE("climbable"), 
	MONEY("money"), 
	PROJECTILE("prjectile"), 
	PARTIAL_COLLIDEABLE("partial_collideable"), 
	NON_COLLIDEABLE("non_collideable"), 
	BAD_THINGS("bad_things"), 
	WEAPON("weapon"),
	TOOL("tool");
	
	String subtype;
	
	private SpriteSubType(String subtype) {
		this.subtype = subtype;
	}
	
	public SpriteSubType getSubType(String subtype){
		return SpriteSubType.valueOf(subtype);
	}

}
