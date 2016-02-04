package me.cameronwitcher.upsilon.sprites.tools;

import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.spriteutils.Tool;

public class NinjaCloak extends Tool {
	

	public NinjaCloak(int x, int y) {
		super(x, y);
		init();
	}
	
	public void init(){
		loadImage("tools/ninjacloak.png");
		setImageDimensions(17, 17,0,0);
	}
	
	@Override
	public SpriteType getType(){
		return SpriteType.NINJA_CLOAK;
	}
	
	@Override
	public void use(){
		getEntity().toggleInvisiblility();
		
	}
	

}
