package me.cameronwitcher.upsilon.sprites;

import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;

public class Spike extends Sprite {

    public Spike(int x, int y) {
        super(x, y);
        
        init();
    }
    
    @Override
    public SpriteType getType(){
    	return SpriteType.SPIKE;
    }

    private void init() {
        
        loadImage("spike.png");
        getImageDimensions();
    }
}