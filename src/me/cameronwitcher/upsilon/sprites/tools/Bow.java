package me.cameronwitcher.upsilon.sprites.tools;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.boards.GameBoard;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.spriteutils.Weapon;
import me.cameronwitcher.upsilon.utils.Sound;
import res.Audio;

public class Bow extends Weapon {
	

	public Bow(int x, int y) {
		super(x, y);
		init();
	}
	
	public void init(){
		damage = 10;
		loadImage("tools/bow.png");
		setImageDimensions(16, 16,0,0);
	}
	
	@Override
	public SpriteType getType(){
		return SpriteType.BOW;
	}
	
	@Override
	public void use(){
		Audio.playSound(Sound.BOW_SHOOT);
		Arrow arrow = new Arrow(
				getEntity().x, 
				getEntity().y,
				getEntity().getFacingDirection(),
				getEntity());
		arrow.direction = getEntity().getFacingDirection();
		((GameBoard)Bridge.getGame().getBoard()).addMoveable(arrow);
		 
	}
	

}
