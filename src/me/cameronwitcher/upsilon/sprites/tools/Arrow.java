package me.cameronwitcher.upsilon.sprites.tools;

import me.cameronwitcher.upsilon.spriteutils.Entity;
import me.cameronwitcher.upsilon.spriteutils.Projectile;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.utils.Direction;

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
        loadImage("tools/arrow.png");
        setImageDimensions(16, 4, 0, 0);
    }	
}