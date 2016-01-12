package me.cameronwitcher.upsilon.sprites;

import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;

public class Gate extends Sprite {

    public Gate(int x, int y) {
        super(x, y-13);

        init();
    }
    
    @Override
    public SpriteType getType(){
    	return SpriteType.GATE;
    }

    private void init() {
        
        loadImage("True gate animation.gif");
        getImageDimensions();
    }
}