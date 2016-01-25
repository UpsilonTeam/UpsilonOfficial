package me.cameronwitcher.upsilon.sprites;

import java.util.ArrayList;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.boards.GameBoard;
import me.cameronwitcher.upsilon.spriteutils.Interactable;
import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.spriteutils.Tool;
import me.cameronwitcher.upsilon.utils.Images;
import res.Texture;

public class Switch extends Interactable {
	
	Sprite sprite;
	Sprite[] sprites;
	double rotation;
	SpriteType type;
	boolean triggered = false;
	private boolean multiple = false;

	public Switch(int x, int y, Sprite sprite, ArrayList<Sprite> level, double rotation) {
        super(x, y);
        type = SpriteType.SWITCH;
        this.sprite = sprite;
        level.add(sprite);
        this.rotation = rotation;
        initFloor();
    }
	
	public Switch(int x, int y, Sprite[] sprites, ArrayList<Sprite> level, double rotation) {
        super(x, y);
        type = SpriteType.SWITCH;
        this.sprites = sprites;
        for(Sprite sprite : sprites)
        	level.add(sprite);
        this.rotation = rotation;
        multiple = true;
        initFloor();
    }
    
    @Override
    public SpriteType getType(){
    	return type;
    }

    private void initFloor() {
        
        loadImage(Images.rotate(Texture.loadTexture("switch.png"), rotation));
        getImageDimensions();
    }
    
    
    @Override
    public void interact(Tool tool){
    	interact();
    }
    @Override
    public void interact(){
    	if(triggered){
    		triggered = false;
    		if(multiple){
    			for(Sprite sprite : sprites)
    				((GameBoard) Bridge.getGame().getBoard()).addSprite(sprite);
    		} else 
    		((GameBoard) Bridge.getGame().getBoard()).addSprite(sprite);
    		loadImage(Images.rotate(Texture.loadTexture("switch.png"), rotation));
        	getImageDimensions();
        	type = SpriteType.SWITCH;
        	return;
    		
    	} else {
    		if(multiple)
    			for(Sprite sprite : sprites)
    				sprite.remove();
    		else sprite.remove();
    		loadImage(Images.rotate(Texture.loadTexture("triggered-switch.png"), rotation));
        	getImageDimensions();
        	type = SpriteType.TRIGGERED_SWITCH;
        	triggered = true;
    	}
    	
    }
}