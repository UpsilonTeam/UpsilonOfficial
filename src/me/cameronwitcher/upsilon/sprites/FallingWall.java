
	package me.cameronwitcher.upsilon.sprites;

	import java.util.Timer;
	import java.util.TimerTask;

	import me.cameronwitcher.upsilon.spriteutils.Entity;
	import me.cameronwitcher.upsilon.spriteutils.Moveable;
	import me.cameronwitcher.upsilon.spriteutils.SpriteType;

	public class FallingWall extends Entity implements Moveable {
		
		public boolean t;

	    public FallingWall(int x, int y) {
	        super(x, y);
	        initFloor();
	    }
	    
	    @Override
	    public SpriteType getType(){
	    	return SpriteType.FALLING_WALL;
	    }

	    private void initFloor() {
	        
	        loadImage("falling-floor.png");
	        getImageDimensions();
	    }
	    
	    public void startFalling(){
	    	Timer timer = new Timer();
	    	timer.schedule( new TimerTask() {
	    		@Override
	    		public void run() {
	    			t = true;
	    			}
	    		}, 750);
	    	}

		@Override
		public void move() {
			
			if(t){
				y = y+2;
			}
		}

		@Override
		public void disable() {
//			disabled = true;
		}

		@Override
		public void enable() {
			
		}
	}

