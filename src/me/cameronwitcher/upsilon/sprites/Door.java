package me.cameronwitcher.upsilon.sprites;

import me.cameronwitcher.upsilon.sprites.tools.Key;
import me.cameronwitcher.upsilon.spriteutils.DoorState;
import me.cameronwitcher.upsilon.spriteutils.Interactable;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.spriteutils.Tool;
import res.Texture;

public class Door extends Interactable {
	
	DoorState state;
	int id;
	SpriteType type = SpriteType.DOOR;

    public Door(int x, int y, int id) {
        super(x, y);
        this.id = id;
        init();
    }
    
    @Override
    public SpriteType getType(){
    	return type;
    }

    private void init() {
    	loadImage(Texture.loadTexture("door.png"));
    	setImageDimensions(30, 45);
    }
    
    @Override
    public void interact(Tool tool){
    	if(type == SpriteType.OPEN_DOOR){
    		return;
    	}
    	if(tool instanceof Key){
    		if(((Key) tool).getID() == id || ((Key) tool).getID() == -1){
    			type = SpriteType.OPEN_DOOR;
    			loadImage(Texture.loadTexture("door_open.png"));
    	    	setImageDimensions(2, 45);
    		}
    	}
    }
}