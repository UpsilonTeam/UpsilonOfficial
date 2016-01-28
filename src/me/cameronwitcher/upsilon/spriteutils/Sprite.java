package me.cameronwitcher.upsilon.spriteutils;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.boards.GameBoard;
import me.cameronwitcher.upsilon.utils.Direction;
import res.Texture;

public class Sprite {

    public int x;
    public int y;
    public int bw=0;
    public int bh=0;
    protected int width;
    protected int height;
    protected boolean vis;
    private Image image;
    public String imagePath = "";
    private Sprite sprite;
    public Polygon bounds;
    private boolean exists = false;

    public Sprite(int x, int y) {
    	this.sprite = this;
        this.x = x;
        this.y = y;
        vis = true;
        exists = true;
       
        
        
    }
    

	public void remove(){
		((GameBoard)Bridge.getGame().getBoard()).removeSprite(this);
		exists = false;
    }
    
    public SpriteType getType(){return null;}
    
    

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }
    
    protected void setImageDimensions(int width, int height, int bw, int bh) {

        this.width = width;
        this.height = height;
        this.bw = bw;
        this.bh = bh;
        
        
    }
    
    protected void setImageDimensions(int width, int height) {

        this.width = width;
        this.height = height;
        
        
    }

    protected void loadImage(String imageName) {
    	
    	imagePath = imageName;
    	
    	image = Texture.loadTexture(imageName);
    	

    	
    }
    
    public Direction getIntercectingDirection(Rectangle r){
    	
    	
    	if (bounds.getBounds().getMaxY() - r.getY() >= 0 && bounds.getBounds().getMaxY() - r.getY() <= 10) {
    		return Direction.DOWN;
    	}
    	
    	if(bounds.getBounds().getMaxX() - r.getX() >= 1.0 && bounds.getBounds().getMaxX() - r.getX() <= 17.0){
    		return Direction.RIGHT;
    	}
    	if(bounds.getBounds().getX() - r.getMaxX() <= -1.0 && bounds.getBounds().getX() - r.getMaxX() >= -17.0){
    		return Direction.LEFT;
    	}
    	if(bounds.getBounds().getY() - r.getMaxY() <= -1.0){
    		return Direction.UP;
    	}
    	return null;
    }
    
    protected void loadImage(Image image){
    	this.image = image;
    }
    
    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    public Polygon getPolygon() {
    	bounds = new Polygon(new int[] {x,x+width,x+width,x}, new int[] {y+height,y+height,y,y}, 4);
    	return bounds;
    }
    
    public String getLocation(){
    	return x+":"+y;
    }

	public SpriteSubType getSubType() {
		return getType().getSubType();
	}


	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}


	public void drawInfo(int x, int y, Graphics g) {
		String name = sprite.getType().name();
		g.drawString(name, x, y);
		
	}
	
	public boolean exists(){
		return exists;
	}


	public void add() {
		exists = ((GameBoard)Bridge.getGame().getBoard()).addSprite(this);
		
	}


	

	

}