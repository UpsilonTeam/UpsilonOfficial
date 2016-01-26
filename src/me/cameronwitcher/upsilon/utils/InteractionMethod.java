package me.cameronwitcher.upsilon.utils;

import me.cameronwitcher.upsilon.spriteutils.Sprite;

public enum InteractionMethod {
	DISAPPEAR("disappear");
	String interaction;

	InteractionMethod(String interaction){
		this.interaction = interaction;	
	}
	
	public void interact(Sprite sprite){
		switch(this){
		case DISAPPEAR:
			if(sprite.exists()){
				sprite.remove();
			} else sprite.add();
			break;
		default:
			break;
		}
	}
	
	
}

	
	
