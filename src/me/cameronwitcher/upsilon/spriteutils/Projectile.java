package me.cameronwitcher.upsilon.spriteutils;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.boards.GameBoard;
import me.cameronwitcher.upsilon.utils.DamageReason;
import me.cameronwitcher.upsilon.utils.Direction;
import me.cameronwitcher.upsilon.utils.Utils;

public class Projectile extends Entity implements Moveable {
	

	public int damage;
	public Direction direction;
	public int speed;
	public Entity shooter;
	
	
	public Projectile(int x, int y, Entity shooter) {
		super(x, y);
		this.shooter = shooter;
	}
	
	public void setDamage(int damage){
		this.damage = damage;
	}
	public int getDamage(){
		return damage;
	}
	public Direction getDirection(){
		return direction;
	}
	public Entity getShooter(){
		return shooter;
	}

	@Override
	public void move() {
		if(direction.equals(Direction.RIGHT))
			x = x+speed;
		if(direction.equals(Direction.LEFT))
			x = x-speed;
		
		if(x > 1000){
			remove();
		}
		if(x < 0){
			remove();
		}
		if(!shooter.getType().equals(SpriteType.PLAYER)){
			if(Utils.intersects(getPolygon(), Bridge.getPlayer().getPolygon())){
				Bridge.getPlayer().damage(damage, this, DamageReason.PROJECTILE);
			}
		}
		for(Sprite sprite : ((GameBoard)Bridge.getGame().getBoard()).sprites){
			if(!this.getPolygon().getBounds().intersects(sprite.getPolygon().getBounds())) continue;
			if(sprite instanceof Entity){
				Utils.broadcastMessage(sprite.getType() + "");
				if(!sprite.getType().equals(shooter.getType())){
					((Entity) sprite).damage(this.damage, this, DamageReason.PROJECTILE);	
				} else {
					continue;
				}
			}
			if(!sprite.getType().equals(SpriteType.LADDER))remove();
		}
	}

	@Override
	public void disable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enable() {
		// TODO Auto-generated method stub
		
	}

}
