package me.cameronwitcher.upsilon.sprites;

import java.util.ArrayList;

import me.cameronwitcher.upsilon.spriteutils.Interactable;
import me.cameronwitcher.upsilon.spriteutils.Rotation;
import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.spriteutils.Tool;
import me.cameronwitcher.upsilon.utils.Images;
import me.cameronwitcher.upsilon.utils.InteractionMethod;
import res.Texture;

public class Switch extends Interactable {
	
	Sprite sprite;
	Sprite[] sprites;
	Rotation rotation;
	SpriteType type;
	boolean triggered = false;
	private boolean multiple = false;
	private InteractionMethod method;

	public Switch(int x, int y, Sprite sprite, ArrayList<Sprite> level, Rotation rotation, InteractionMethod method) {
        super(x, y);
        type = SpriteType.SWITCH;
        this.sprite = sprite;
        level.add(sprite);
        this.method = method;
        this.rotation = rotation;
        initFloor();
    }
	
	public Switch(int x, int y, Sprite[] sprites, ArrayList<Sprite> level, Rotation rotation, InteractionMethod method) {
        super(x, y);
        type = SpriteType.SWITCH;
        this.sprites = sprites;
        for(Sprite sprite : sprites)
        	level.add(sprite);
        this.rotation = rotation;
        multiple = true;
        this.method = method;
        initFloor();
    }
    
    @Override
    public SpriteType getType(){
    	return type;
    }

    private void initFloor() {
        
        loadImage(Images.rotate(Texture.loadTexture("objects/switch.png"), rotation.getRotation()));
        getImageDimensions();
    }
    
    
    @Override
    public void interact(Tool tool){
    	interact();
    }
    @Override
    public void interact(){
    	if(!multiple) 
    		method.interact(sprite);
    	 else 
    		for(Sprite sprite : sprites) method.interact(sprite);
    	
    	if(triggered){
    		if(rotation.equals(Rotation.LEFT)) x = x-5;
    		if(rotation.equals(Rotation.UP)) y = y-5;
    		triggered = false;
    		loadImage(Images.rotate(Texture.loadTexture("objects/switch.png"), rotation.getRotation()));
        	getImageDimensions();
        	type = SpriteType.SWITCH;
        	return;
    		
    	} else {
    		if(rotation.equals(Rotation.LEFT)) x = x+5;
    		if(rotation.equals(Rotation.UP)) y = y+5;

    		loadImage(Images.rotate(Texture.loadTexture("objects/triggered-switch.png"), rotation.getRotation()));
        	getImageDimensions();
        	type = SpriteType.TRIGGERED_SWITCH;
        	triggered = true;
    	}
    	
    }
}