package me.cameronwitcher.upsilon.utils;

import me.cameronwitcher.upsilon.spriteutils.Interactable;
import me.cameronwitcher.upsilon.spriteutils.Sprite;

public enum InteractionMethod {
	DISAPPEAR("disappear"),
	TRIGGER("trigger");
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
		case TRIGGER:
			((Interactable) sprite).interact();
			break;
		default:
			break;
		}
	}
	
	
}

	
	
