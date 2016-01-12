package me.cameronwitcher.upsilon.spriteutils;

import java.awt.Image;
import java.awt.event.MouseEvent;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.boards.GameBoard;
import me.cameronwitcher.upsilon.utils.Direction;

public class Tool extends Sprite {

	public Entity entity;
	public Image left;
	public Image right;
	
	public Tool(int x, int y) {
		super(x, y);
	}
	
	
	
	public void setRightImage(Image image){
		right = image;
	}
	public void setLeftImage(Image image){
		left = image;
	}
	public void setImage(Image image, Direction direction){
		if(direction.equals(Direction.LEFT)) left = image;
		if(direction.equals(Direction.RIGHT)) right = image;
	}
	public Image getImage(Direction direction){
		if(direction.equals(Direction.LEFT)) return left;
		if(direction.equals(Direction.RIGHT)) return right;
		return null;
	}
	
	public void setEntity(Entity entity){
		this.entity = entity;
		try{
			((GameBoard)Bridge.getGame().getBoard()).removeSprite(this);
		} catch(Exception ex){};
		
		
	}
	
	
	
	public Entity getEntity(){
		return entity;
	}
	
	public boolean isAttached(){
		if(entity == null) return false;
		else return true;
	}

	public void use() {
		
	}



	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
