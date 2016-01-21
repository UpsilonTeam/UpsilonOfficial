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

    public Switch(int x, int y, Sprite sprite, ArrayList<Sprite> level, double rotation) {
        super(x, y);
        this.sprite = sprite;
        level.add(sprite);
        this.rotation = rotation;
        initFloor();
    }
    
    @Override
    public SpriteType getType(){
    	return SpriteType.SWITCH;
    }

    private void initFloor() {
        
        loadImage(Images.rotate(Texture.loadTexture("switch.png"), rotation));
        getImageDimensions();
    }
    
    @Override
    public void interact(){
    	this.remove();
    	sprite.remove();
    }
}