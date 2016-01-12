package me.cameronwitcher.upsilon.sprites;

import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;

public class Ladder extends Sprite {

    public Ladder(int x, int y) {
        super(x, y);
        
        initLadder();
    }
    
    @Override
    public SpriteType getType(){
    	return SpriteType.LADDER;
    }

    private void initLadder() {
        
        loadImage("ladder.png");
        getImageDimensions();
    }
}