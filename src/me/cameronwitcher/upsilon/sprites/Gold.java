package me.cameronwitcher.upsilon.sprites;

import me.cameronwitcher.upsilon.spriteutils.Money;
import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;

public class Gold extends Sprite implements Money {
	
	int value;

    public Gold(int x, int y) {
        super(x, y);
        value = 10;
        initFloor();
    }
    
    @Override
    public SpriteType getType(){
    	return SpriteType.GOLD;
    }

    private void initFloor() {
        
        loadImage("objects/gold.gif");
        getImageDimensions();
    }

	@Override
	public int getValue() {
		return value;
	}
	

}