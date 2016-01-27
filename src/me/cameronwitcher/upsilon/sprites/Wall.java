package me.cameronwitcher.upsilon.sprites;

import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.spriteutils.State;

public class Wall extends Sprite {
	
	State state;
	int size;

    public Wall(int x, int y, int size, State state) {
        super(x, y);
        this.state = state;
        this.size = size;
        init();
    }
    
    @Override
    public SpriteType getType(){
    	return SpriteType.WALL;
    }

    private void init() {
    	
    	switch(state){
    	case LEFT_LOWER_CORNER:
    		loadImage("wall/lowerLeftCorner.png");
    		break;
    	case LEFT_UPPER_CORNER:
    		loadImage("wall/upperLeftCorner.png");
    		break;
    	case RIGHT_LOWER_CORNER:
    		loadImage("wall/lowerRightCorner.png");
    		break;
    	case RIGHT_UPPER_CORNER:
    		loadImage("wall/upperRightCorner.png");
    		break;
    	case VERTICAL:
    		loadImage("wall/vertical.png");
    		height = size;
    		setImageDimensions(2, size);
    		break;
    	case LARGE_VERTICAL:
    		loadImage("wall/vertical_large.png");
    		break;
    		
    	case HORIZONTAL:
    		loadImage("wall/horizontal.png");
    		setImageDimensions(size, 2);
    		width = size;
    		break;
    		default:
    			loadImage("wall/vertical.png");
    			break;
    	}
    }
}