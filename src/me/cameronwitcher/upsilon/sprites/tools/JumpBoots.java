package me.cameronwitcher.upsilon.sprites.tools;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.spriteutils.Tool;


public class JumpBoots extends Tool {
	

	public JumpBoots(int x, int y) {
		super(x, y);
		init();
	}
	
	public void init(){
		
		loadImage("boots.png");
		setImageDimensions(16, 16,0,0);
	}
	
	@Override
	public SpriteType getType(){
		return SpriteType.BOOTS;
	}
	
	@Override
	public void use(){
		Bridge.getPlayer().toggleJumpBoost();
		 
	}
	

}
