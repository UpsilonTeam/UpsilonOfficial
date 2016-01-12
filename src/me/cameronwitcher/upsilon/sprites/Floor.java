package me.cameronwitcher.upsilon.sprites;

import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;

public class Floor extends Sprite {

    public Floor(int x, int y) {
        super(x, y);
        
        initFloor();
    }
    
    @Override
    public SpriteType getType(){
    	return SpriteType.FLOOR;
    }

    private void initFloor() {
        
        loadImage("floor.png");
        getImageDimensions();
    }
}