package me.cameronwitcher.upsilon.sprites;

import me.cameronwitcher.upsilon.spriteutils.Entity;
import me.cameronwitcher.upsilon.spriteutils.Projectile;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.utils.Direction;
import me.cameronwitcher.upsilon.utils.Images;
import me.cameronwitcher.upsilon.utils.Utils;
import res.Texture;

public class Arrow extends Projectile  {
	


	public Arrow(int x, int y, Direction direction, Entity shooter) {
        super(x, y, shooter);
        this.direction = direction;
        this.speed = 6;
        damage = 10;
        init();
    }
    @Override
    public SpriteType getType(){
    	return SpriteType.ARROW;
    	
    }

    private void init() {
    	Utils.broadcastMessage(direction.toString());
    	Utils.broadcastMessage(direction.getAngle() + "");
        loadImage(Images.rotate(Texture.loadTexture("arrow.png"), direction.getAngle()));
        setImageDimensions(11, 4, 0, 0);
    }	
}