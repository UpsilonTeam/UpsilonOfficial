package me.cameronwitcher.upsilon.sprites.tools;

import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.spriteutils.Tool;

public class Key extends Tool {
	

	private int id;
	
	public Key(int x, int y, int id) {
		super(x, y);
		this.id = id;
		init();
		
		if(id==-1){
			loadImage("magic_key.png");
		}
	}
	
	public void init(){
		loadImage("key.png");
		setImageDimensions(17, 17,0,0);
	}
	
	@Override
	public SpriteType getType(){
		return SpriteType.KEY;
	}
	
	@Override
	public void use(){
		//Code to open door
		
	}

	public int getID() {
		return id;
	}
	

}
