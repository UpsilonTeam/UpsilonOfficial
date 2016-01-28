package me.cameronwitcher.upsilon.sprites;

import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;

public class Gate extends Sprite {
	
	private int type;

    public Gate(int x, int y, int type) {
        super(x, y-13);
        this.type = type;
        init();
    }
    
    @Override
    public SpriteType getType(){
    	return SpriteType.GATE;
    }

    private void init() {
    	
    	switch(type){
    	case 0:
    		loadImage("gates/flag.gif");
    		break;
    	case 1:
    		loadImage("gates/cave.png");
    	default:
    		break;
    	}
        getImageDimensions();
    }
}