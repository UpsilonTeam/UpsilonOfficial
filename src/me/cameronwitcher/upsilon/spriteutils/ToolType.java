package me.cameronwitcher.upsilon.spriteutils;

import me.cameronwitcher.upsilon.sprites.tools.Bow;
import me.cameronwitcher.upsilon.sprites.tools.NinjaCloak;

public enum ToolType {
	
	BOW("bow"),
	NINJA_CLOAK("ninja_cloak");
	
	String type;
	
	ToolType(String type){
		this.type = type;
	}
	

	public static Tool getNewTool(ToolType tool) {
		if(tool.equals(BOW)){
			return new Bow(-1,-1);
		}
		if(tool.equals(NINJA_CLOAK)){
			return new NinjaCloak(-1, -1);
		}
		else return null;
	}

}
