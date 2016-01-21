package me.cameronwitcher.upsilon.sprites;

import java.util.ArrayList;

import me.cameronwitcher.upsilon.spriteutils.Interactable;
import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.utils.Images;
import res.Texture;

public class Switch extends Interactable {
	
	Sprite sprite;
	double rotation;
	SpriteType type;

    public Switch(int x, int y, Sprite sprite, ArrayList<Sprite> level, double rotation) {
        super(x, y);
        type = SpriteType.SWITCH;
        this.sprite = sprite;
        level.add(sprite);
        this.rotation = rotation;
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
    public void interact(){
    	sprite.remove();
    	loadImage(Images.rotate(Texture.loadTexture("triggered-switch.png"), rotation));
    	getImageDimensions();
    	type = SpriteType.TRIGGERED_SWITCH;
    }
}