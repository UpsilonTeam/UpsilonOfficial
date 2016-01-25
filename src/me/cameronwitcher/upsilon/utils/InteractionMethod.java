package me.cameronwitcher.upsilon.utils;
public enum InteractionMethod {
	DISAPPEAR("disappear"),
	APPEAR("appear");
	String interaction;

	InteractionMethod(String interaction){
		this.interaction = interaction;	
	}
	
	public void interact(){
		switch(this){
		case DISAPPEAR:
//			remove();
			break;
		default:
			break;
		}
	}
	
	
}

	
	
