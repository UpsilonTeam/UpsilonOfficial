package me.cameronwitcher.upsilon.sprites;

import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.boards.GameBoard;
import me.cameronwitcher.upsilon.sprites.tools.Bow;
import me.cameronwitcher.upsilon.spriteutils.Entity;
import me.cameronwitcher.upsilon.spriteutils.Keyable;
import me.cameronwitcher.upsilon.spriteutils.Moveable;
import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteSubType;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.utils.AI;
import me.cameronwitcher.upsilon.utils.BoardType;
import me.cameronwitcher.upsilon.utils.DamageReason;
import me.cameronwitcher.upsilon.utils.Direction;
import me.cameronwitcher.upsilon.utils.Utils;

public class Knobber extends Entity implements Moveable,Keyable {
	
	public boolean onground = false;
	private int dy;
	private int dx;
	private int power = 10;
	private boolean shootcooldown = false;
	private boolean walking = false;
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;

    public Knobber(int x, int y) {
        super(x, y);
        init();
        score = 10;
        health = 20;
        maxhealth = 20;
        setDirection(Direction.RIGHT);
    }
    
    @Override
    public SpriteType getType(){
    	return SpriteType.KNOBBER;
    }

    private void init() {
        loadImage("knobber/stand_left.png");
        setImageDimensions(29, 41, -2, -2);
        
    }
    
    public int getPower(){
    	return power;
    }
    
    
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_P) {
        	tool = new Bow(0,0);
            setTool(tool);
        }
    }
	@Override
	public void move() {
		if(!dead){
			if (x >= 650 || x <= -1 || y >= 650 || y <= -1) {
				kill(DamageReason.VOID);
			}
			
			if(!Bridge.getGame().getBoard().getType().equals(BoardType.GAME_BOARD)) return;
			
			try{
				for (Sprite sprite : ((GameBoard)Bridge.getGame().getBoard()).sprites) {
					if(sprite instanceof Knobber) continue;
					if (!getPolygon().intersects(sprite.getPolygon().getBounds())){
						continue;
					}
					
					if (sprite.getSubType().equals(SpriteSubType.PARTIAL_COLLIDEABLE) || sprite.getSubType().equals(SpriteSubType.COLLIDEABLE)){
//						Utils.broadcastMessage("COLLIDES", "");
						onground = true;
					} else {
						onground = false;
					}
					if (sprite.getSubType().equals(SpriteSubType.COLLIDEABLE)) {
//						Utils.broadcastMessage("COLLIDES", "");
						switch(getIntercectingDirection(sprite.getPolygon().getBounds())){
						case LEFT:
							if(!onground) onground = false;
							left = true;
							break;
						case RIGHT:
							if(!onground) onground = false;
							right = true;
							break;
						case DOWN:
							onground = true;
							break;
							default:
								left = false;
								right = false;
								break;
						}
					}

				}
				
				if(onground){
//					Utils.broadcastMessage("ONGROUND", "Knobber.class (116)");
					dy = 0;
				}
				else dy = 4;
				
				if(AI.getFollowSprite(this, Bridge.player) == 1){
					walking = true;
					facing = Direction.RIGHT;
					setDir(Direction.RIGHT);
				}
				if(AI.getFollowSprite(this, Bridge.player) == -1){
					walking = true;
					facing = Direction.LEFT;
					setDir(Direction.LEFT);
				}
						
				
				dx = AI.getFollowSprite(this, Bridge.player);
				if(Math.abs(Bridge.player.x -this.x) <=100){
					dx=0;
					attemptShoot(Bridge.player);
				}
				if(dx==0){
					loadImage("knobber/stand_" + getDirection().toString().toLowerCase() + ".png");
					walking = false;
				}
				else {
					walking = true;
				}
				
				if(left && dx<0)
					dx = 0;
				if(right && dx>0)
					dx = 0;
				
				x = x+dx;
				y = y+dy;
				
				
				

			} catch(NullPointerException ex){}
		}
		else {
			x = x+0;
			y = y+5;
			if(y<500){
				this.remove();
			}
			
		}
	
	}

	private void setDir(Direction d) {
		setDirection(d);
		if(walking){
			loadImage("knobber/walk_" + getDirection().toString().toLowerCase() + ".gif");
		} else {
			loadImage("knobber/stand_" + getDirection().toString().toLowerCase() + ".png");
		}
	}

	private void attemptShoot(Player player) {
		if(!shootcooldown){
			shootcooldown = true;
			if(hasTool()){
				tool.use();
			}
			Timer timer = new Timer();
	    	timer.schedule( new TimerTask() {
	    		@Override
	    		public void run() {
	    			shootcooldown = false;
	    			}
	    		}, 500);
	    	}
	}
	
	@Override
	public void kill(DamageReason reason){
		this.remove();
	}
	

	@Override
	public void disable() {
//		disabled = true;
	}

	@Override
	public void enable() {
//		disabled = false;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	

	

}