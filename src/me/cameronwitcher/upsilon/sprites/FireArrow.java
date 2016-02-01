package me.cameronwitcher.upsilon.sprites;

import me.cameronwitcher.upsilon.spriteutils.Entity;
import me.cameronwitcher.upsilon.spriteutils.Projectile;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.utils.Direction;

public class FireArrow extends Arrow  {
	


	public FireArrow(int x, int y, Direction direction, Entity shooter) {
        super(x, y, direction, shooter);
        this.direction = direction;
        this.speed = 6;
        damage = 20;
        init();
    }
    @Override
    public SpriteType getType(){
    	return SpriteType.ARROW;
    	
    }

    private void init() {
        loadImage("arrow_red.png");
        setImageDimensions(16, 4, 0, 0);
    }	
}