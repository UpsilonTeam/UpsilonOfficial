package me.cameronwitcher.upsilon.boards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.swing.Timer;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.sprites.Arrow;
import me.cameronwitcher.upsilon.sprites.FakeInteractable;
import me.cameronwitcher.upsilon.sprites.FallingFloor;
import me.cameronwitcher.upsilon.sprites.FallingWall;
import me.cameronwitcher.upsilon.sprites.Floor;
import me.cameronwitcher.upsilon.sprites.Gate;
import me.cameronwitcher.upsilon.sprites.Gold;
import me.cameronwitcher.upsilon.sprites.Knobber;
import me.cameronwitcher.upsilon.sprites.Ladder;
import me.cameronwitcher.upsilon.sprites.Player;
import me.cameronwitcher.upsilon.sprites.Spike;
import me.cameronwitcher.upsilon.sprites.Switch;
import me.cameronwitcher.upsilon.sprites.Wall;
import me.cameronwitcher.upsilon.spriteutils.Clickable;
import me.cameronwitcher.upsilon.spriteutils.Entity;
import me.cameronwitcher.upsilon.spriteutils.GateType;
import me.cameronwitcher.upsilon.spriteutils.Interaction;
import me.cameronwitcher.upsilon.spriteutils.Keyable;
import me.cameronwitcher.upsilon.spriteutils.Moveable;
import me.cameronwitcher.upsilon.spriteutils.Rotation;
import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.spriteutils.SpriteType;
import me.cameronwitcher.upsilon.spriteutils.State;
import me.cameronwitcher.upsilon.spriteutils.Tool;
import me.cameronwitcher.upsilon.utils.Background;
import me.cameronwitcher.upsilon.utils.Board;
import me.cameronwitcher.upsilon.utils.BoardType;
import me.cameronwitcher.upsilon.utils.Button;
import me.cameronwitcher.upsilon.utils.ButtonMethod;
import me.cameronwitcher.upsilon.utils.Direction;
import me.cameronwitcher.upsilon.utils.InteractionMethod;
import me.cameronwitcher.upsilon.utils.Utils;
import res.Texture;

public class GameBoard extends Board implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Timer timer;
	public boolean debug;
	public boolean won;
	public boolean loaded = false;
	public boolean ingame;
	public boolean inv = false;
	public String gameStatus;
	public final int B_WIDTH = Bridge.getGameBoardSize(0);
	public final int B_HEIGHT = Bridge.getGameBoardSize(1);
	public final int DELAY = 10;
	private boolean hitboxes = false;
	public double i = 0.9;
	public int l = 0;
	private int mx = 0;
	private int my = 0;
	public Sprite sprite = null;
	public boolean in = false;
	private boolean fs = false;
	private double extra = 1;
	private int e = 0;
	
	private GraphicsDevice vc;

	public boolean paused = false;
	public Map<Integer, String> messages = new HashMap<>();
	public Map<Integer, String> messages_player = new HashMap<>();
	public ArrayList<Sprite> sprites = new ArrayList<>();
	public List<Clickable> clickables = new ArrayList<>();
	public List<Moveable> moveables = new ArrayList<>();

	public List<Moveable> moveables_temp = new ArrayList<>();
	public List<Sprite> removedSprites = new ArrayList<>();
	public List<Sprite> sprite_temp = new ArrayList<>();
	public List<String> strings_temp = new ArrayList<>();
	public List<String> strings_temp_player = new ArrayList<>();
	public List<Sprite> tools = new ArrayList<>();
	
	ArrayList<Sprite> level1 = new ArrayList<>();
	ArrayList<Sprite> level2 = new ArrayList<>();
	ArrayList<Sprite> level3 = new ArrayList<>();
	ArrayList<Sprite> level4 = new ArrayList<>();
	ArrayList<Sprite> level5 = new ArrayList<>();
	ArrayList<Sprite> level6 = new ArrayList<>();
	ArrayList<Sprite> level7 = new ArrayList<>();
	ArrayList<Sprite> level8 = new ArrayList<>();
	ArrayList<Sprite> level9 = new ArrayList<>();
	private HashMap<Integer, ArrayList<Sprite>> levels = new HashMap<>();

	public GameBoard() {
		setType(BoardType.GAME_BOARD);

	}
	
	public void startDebug(int i){
		ingame = true;
		gameStatus = "ingame";
		loadLevels(true, i);
	}

	public void init() {

		ingame = true;

		gameStatus = "ingame";

		Utils.savePlayerInfo(Bridge.getPlayer());

		addKeyListener(new TAdapter());
		addMouseMotionListener(new MMListener());
		addMouseListener(new MListener());
		setFocusable(true);
		setBackground(Color.RED);

		loadLevels(false, 0);

		setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));

		timer = new Timer(DELAY, this);
		timer.start();

		won = false;

		update();
		loadLevel();
		
		vc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();

	}
	
	@Override
	public void disable(){
		timer.stop();
		timer = null;
		
		debug = false;
		won = false;
		loaded = false;
		ingame = false;
		gameStatus = "";
		i = 0.9;
		l = 0;
		messages.clear();
		messages_player.clear();
		sprites.clear();
		clickables.clear();
		moveables.clear();
		moveables_temp.clear();
		removedSprites.clear();
		strings_temp.clear();
		strings_temp_player.clear();
		tools.clear();
		level1.clear();
		level2.clear();
		level3.clear();
		level4.clear();
		level5.clear();
		level6.clear();
		level7.clear();
		level8.clear();
		level9.clear();
		levels.clear();
		
		
	}
	
	

	public void loadLevels(boolean debug, int i) {
		levels.clear();
		level1.clear();
		level2.clear();
		level3.clear();
		level4.clear();
		level5.clear();
		level6.clear();	
		level7.clear();
		level8.clear();
		level9.clear();
		
		if(debug){
			if(i==1) loadLevel1(true);
			if(i==2) loadLevel2(true);
			if(i==3) loadLevel3(true);
			if(i==4) loadLevel4(true);
			if(i==5) loadLevel5(true);
			if(i==6) loadLevel6(true);
			if(i==7) loadLevel7(true);
			if(i==8) loadLevel8(true);
			if(i==9) loadLevel9(true);
			
			return;
			
		}
		
		loadLevel1(false);
		loadLevel2(false);
		loadLevel3(false);
		loadLevel4(false);
		loadLevel5(false);
		loadLevel6(false);
		loadLevel7(false);
		loadLevel8(false);
		loadLevel9(false);

		
		

		

		

		
	
		
		
		
		
		
		

	}

	private void loadLevel1(boolean debug) {
		
		for(int x=0;x!=32;x++){
			level1.add(new Wall(x*30, 35*15-(x/2), 30, State.HORIZONTAL));
		}
		
		for(int x=0;x!=10;x++){
			level1.add(new Gold((x*32)+13, 510-(x/2)));
		}
		
		for(int y=11;y!=17;y++){
			level1.add(new Ladder(31*30,y*30));
		
		}
		
		for(int x=2;x!=31;x++){
			level1.add(new Floor(x*30, 12*30));
		}
		
		for(int y=5;y!=12;y++){
			level1.add(new Ladder(2*30, (y*30)));
		}
	
		for(int x=3;x!=20;x++){
			level1.add(new Floor(x*30,6*30));
		}
		
		
		
//		level1.add(new Door(18 * 15, (7* 15)+10, 1));
//		level1.add(new Bow(9 * 30, 20 * 15));
//		level1.add(new Key(15 * 30, 20 * 15, 1));
		level1.add(new Gate(19*30, (5*30)-1, GateType.FLAG));
		if(!debug) level1.add(Bridge.getPlayer());

		levels.put(1, level1);
	}
	
	private void loadLevel2(boolean debug) {
		
		for(int x=0;x!=20;x++){
			level2.add(new Wall(x*40, 450-(x*15), 30, State.HORIZONTAL));
		}
		if(!debug) level2.add(Bridge.getPlayer());
		
		level2.add(new Gate(760,(5*30)-17, GateType.FLAG));
		
		levels.put(2, level2);
		
	}
	private void loadLevel3(boolean debug) {
		
		for(int x=0;x!=15;x++){
			level3.add(new FallingFloor(x*35, 100+(x*25)));
		
		}
		
		level3.add(new Wall((17*30)+11, 15*30, 90, State.HORIZONTAL));
		level3.add(new Gate((19 * 30)+10, (14* 30)-2, GateType.FLAG));
		if(!debug) level3.add(Bridge.getPlayer());

		levels.put(3, level3);
	}
	private void loadLevel4(boolean debug) {
		for(int x=0;x!=32;x++){
			if(x>=8 && x<=17)
				level4.add(new Floor((x*30)-2, (13*30)-2));
			else
			level4.add(new Floor((x*30)-2, 533));
			
		}
		
		for(int y=13;y!=18;y++){
			level4.add(new Ladder((7*30)+4,(y*30)-9));
		}
		for(int y=13;y!=18;y++){
			level4.add(new Ladder((18*30)-2,(y*30)-9));
		}
		for(int y=8;y!=18;y++){
			level4.add(new Ladder((31*30),(y*30)-9));
		}
		for(int x=3;x!=31;x++){
			level4.add(new Floor((x*30),(8*30)-2));
		}
		
		level4.add(new Wall(1 * 30, 0, 450, State.VERTICAL));
        
		for(int y=3;y!=8;y++){
			level4.add(new Ladder((3*30), (y*30)-4));
		}
		for(int x=4;x!=15;x++){
			level4.add(new Floor((x*30)-3,(4*30)-2));
		}
		
		level4.add(new Wall((3*30)-3,86,159,State.VERTICAL));
		
		level4.add(new Gate((14 * 30)-4, (3 * 30)-4,GateType.FLAG));
		level4.add(new Knobber(11 * 15, 13 * 15));
		if(!debug) level4.add(Bridge.getPlayer());

		levels.put(4, level4);
		
	}
	private void loadLevel5(boolean debug) {
		for(int x=0;x!=18;x++){
			level5.add(new FallingWall(x*40, 450-(x*5), 30, State.HORIZONTAL));
		}
		for(int y=7;y!= 12;y++){
			level5.add(new Ladder((24*30)+2, (y*30)));
		}
		for(int x=5;x!=24;x++){
			level5.add(new FallingWall((x*30), (8*30), 30, State.HORIZONTAL));
		}
		level5.add(new Wall((24*30),(12*30), 30,State.HORIZONTAL));
		
		level5.add(new Gate(5*30, 7*30,GateType.FLAG));
		if(!debug) level5.add(Bridge.getPlayer());

		levels.put(5, level5);
		
	}
	private void loadLevel6(boolean debug) {
		
		level6.add(new Wall(0 * 30, 2 * 30, (30*3)+30, State.HORIZONTAL));

		for (int  i= 4; i != 17; i++) {
			level6.add(new Wall(i * 30, (i * 30)/2, 32, State.HORIZONTAL));
		}
		

		for (int x = 0; x != 19; x++) {
			level6.add(new Floor((x * 30), 16 * 30));
		}
		for (int y = 8; y!= 16; y++) {
			level6.add(new Ladder((17 * 30), (y*30)-3));
		}
		for (int x=2; x!= 17;x++){
			if(x==1|| x==2 || x==3 || x==5 || x==7 || x==9 || x==11 || x==13 || x== 14 || x==15 || x==16)
				level6.add(new Floor((x*30), 11*30));
			else
				continue;
		}
		level6.add(new Switch((2*30)+2, (10*30), new Wall(2*30, (14*30), 60, State.VERTICAL), level6, Rotation.RIGHT, InteractionMethod.DISAPPEAR));
		level6.add(new Wall ((2*30),2*30,12*30,State.VERTICAL));
		level6.add(new Gate(0, (15* 30)-2,GateType.FLAG));

		if(!debug) level6.add(Bridge.getPlayer());

		levels.put(6, level6);
		
	}
	private void loadLevel7(boolean debug) {
		
		for(int x=0;x!=32;x++){
			if(x==14||x==15||x==16)
				level7.add(new Spike(x*30, 6*30));
			else
				level7.add(new Floor(x*30, 6*30));
		}
		
		
		level7.add(new Gate(31*30,5*30-1,GateType.FLAG));
		
		
		if(!debug) level7.add(Bridge.getPlayer());
		levels.put(7, level7);
		
		
	}
	
	private void loadLevel8(boolean debug){
		
		for(int x=0;x!=32;x++){
			if (x==14||x==15)
				continue;
			else
				level8.add(new Floor((x*30),(9*30)));
		}	
		for(int y=0;y!=7;y++){
			level8.add(new Wall(16*30,y*30,30,State.VERTICAL));
		}
		for(int y=0;y!=10;y++){
			level8.add(new Wall(32*30,y*30,30,State.VERTICAL));	
		}
		

		Switch s =  
				new Switch(35*30, 35*30,new Sprite[]{ 
						new Wall(16*30,6*30,90, State.VERTICAL)
						
				}, level8, Rotation.LEFT, InteractionMethod.DISAPPEAR);
		

	
		if(!debug) s.interact();

		

		Switch s2 = new Switch(35*30, 35*30, new Sprite[]{
				new Wall(14*30,9*30,60, State.HORIZONTAL)
		},level8, Rotation.LEFT, InteractionMethod.DISAPPEAR);
		
			
		
											
		level8.add(new Switch((32*30)-15, (8*30)-7,new Sprite[] {
				s,
				s2
		},level8,Rotation.LEFT,InteractionMethod.TRIGGER));
		
				
		
		
				
		for (int x=14;x!=30;x++){
			level8.add(new Floor(x*30,15*30));
		
		}
		level8.add(new Gate(29*30,14*30,GateType.FLAG));
		
		
		if(!debug) level8.add(Bridge.getPlayer());
		
		levels.put(8, level8);
		

		}
	
	
	private void loadLevel9(boolean debug){
		
		level9.add(new Wall(0, 10*30, 15*30,State.HORIZONTAL));
		level9.add(new Wall(17*30, 10*30, 15*30,State.HORIZONTAL));
		
		level9.add(new Wall(15*30,10*30,8*30,State.VERTICAL));
		level9.add(new Wall(17*30,10*30,8*30,State.VERTICAL));
		
		level9.add(new Gate(16*30-12, 37*15, 0));
		if(!debug) level9.add(Bridge.getPlayer());
		
		levels.put(9, level9);
		
	}
	

	public ArrayList<Sprite> getLevel(int level) {
		

		try{
			return levels.get(level);
		} catch(NullPointerException ex){
			return level1;
		}
		
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (inv) {
			drawInventory(g);
			return;
		}

		if (won) {
			drawWin(g);
		
			
			
			return;
		}

		if (ingame){
				drawObjects(g);
			} else {
				if(loaded)
				drawGameOver(g);
		}

		Toolkit.getDefaultToolkit().sync();

	}

	public void loadLevel() {
		levels.clear();
		level1.clear();
		level2.clear();
		level3.clear();
		level4.clear();
		level5.clear();
		level6.clear();
		level7.clear();
		level8.clear();
		loadLevels(false, 0);
		ingame = false;
		loaded = false;
		if (Utils.getPlayerLevel() > levels.size()) {
			won = true;
			return;
		}
		sprite_temp.clear();
		moveables_temp.clear();
		sprites.clear();
		moveables.clear();
		Bridge.setPlayerLocation(0, 0);
		tools.clear();
		clickables.clear();
		try {
			for (Sprite sprite : levels.get(Utils.getPlayerLevel())) {
				sprites.add(sprite);

				if (sprite instanceof Moveable) {
					sprites.remove(sprite);
					if (!(sprite instanceof Player))
						moveables.add((Moveable) sprite);
				}

				if (sprite instanceof Player) {
					sprites.remove(sprite);

				}
				if (sprite instanceof Tool) {
					tools.add(sprite);
				}

			}
		} catch (NullPointerException ex) {
			for (Sprite sprite : getLevel(1)) {
				sprites.add(sprite);

				if (sprite instanceof Moveable) {
					sprites.remove(sprite);
					if (!(sprite instanceof Player))
						moveables.add((Moveable) sprite);
				}

				if (sprite instanceof Player) {
					sprites.remove(sprite);

				}
				if (sprite instanceof Tool) {
					sprites.remove(sprite);
					tools.add(sprite);
				}

			}
		}

		ingame = true;

		loaded = true;

	}

	public void loadHelp() {

		paused = true;
		Utils.displayMessage(1, "Welcome to Upsilon. Here is some help for you.", B_WIDTH / 2, 40, -1, "#FFFFFF", 25);
		Utils.displayMessage(13, "(Press ESC to play)", B_WIDTH / 2, 100, -1, "#FFFFFF", 20);

		Utils.displayMessage(2, "These are coins. Pick them up to gain points!", 272, 578, -1, "#FFFFFF", 15);

		Utils.displayMessage(3, "This a bow. If you pick it up, it'll appear in your \"tool\" slot.", 9 * 15,
				14 * 15, -1, "#FFFFFF", 15);

		Utils.displayMessage(4, "<--- This is your HUD --->", B_WIDTH / 2, 15, -1, "#FFFFFF", 10);

		Utils.displayMessage(5, "Controls", B_WIDTH / 2, (B_HEIGHT / 2) - 30, -1, "#FFFFFF", 10);
		Utils.displayMessage(6, "Left-> \"A\" or the left arrow", B_WIDTH / 2, (B_HEIGHT / 2) - 20, -1, "#FFFFFF", 10);
		Utils.displayMessage(7, "Right -> \"D\" or the right arrow", B_WIDTH / 2, (B_HEIGHT / 2) - 10, -1, "#FFFFFF",
				10);
		Utils.displayMessage(8, "Up -> \"W\" or the up arrow", B_WIDTH / 2, B_HEIGHT / 2, -1, "#FFFFFF", 10);
		Utils.displayMessage(9, "Down -> \"S\" or the down arrow", B_WIDTH / 2, (B_HEIGHT / 2) + 10, -1, "#FFFFFF", 10);
		Utils.displayMessage(10, "Jump -> SPACE", B_WIDTH / 2, (B_HEIGHT / 2) + 20, -1, "#FFFFFF", 10);
		Utils.displayMessage(11, "Open Inventory -> \"E\"", B_WIDTH, 30, -1, "#FFFFFF", 10);
		Utils.displayMessage(12, "Use tool -> SHIFT", B_WIDTH / 2, (B_HEIGHT / 2) + 40, -1, "#FFFFFF", 10);
		

	}

	private void drawInventory(Graphics g) {
		try {
			g.drawImage(Background.WIN.getImage(), 0, 0, Bridge.getGameBoardSize(0), Bridge.getGameBoardSize(1), null);

			String inv = "Inventory";

			String close = "Close";

			String select = "Select";

			Font small = new Font("Helvetica", Font.BOLD, 14);

			Font large = new Font("Helvetica", Font.BOLD, 25);

			FontMetrics fmlarge = getFontMetrics(large);

			g.setColor(Color.WHITE);

			g.setFont(small);

			g.drawString(inv, (B_WIDTH / 2) - (fmlarge.stringWidth(inv) / 2), 30);

			g.drawImage(Bridge.getPlayer().inventory.get(l).getImage(), B_WIDTH / 2, B_HEIGHT / 2, 50, 50, this);

			clickables.add(new Button(close, B_WIDTH / 4, (B_HEIGHT / 2 + B_HEIGHT) / 2, B_WIDTH / 6, 18, Color.GRAY,
					Color.WHITE, small, ButtonMethod.CLOSE_INVENTORY));

			clickables.add(new Button(select, (B_WIDTH / 2 + B_WIDTH) / 2, (B_HEIGHT / 2 + B_HEIGHT) / 2, B_WIDTH / 6,
					18, Color.GRAY, Color.WHITE, small, ButtonMethod.SELECT_TOOL));

			for (Clickable clickable : clickables) {
				clickable.drawPolygon(g);
				
			}
			
			g.drawImage(Texture.loadTexture("pointer.png"), mx, my, this);

		} catch (IndexOutOfBoundsException ex) {
			inv = false;
			Utils.addPlayerMessage(new Random().nextInt(), "Your inventory is empty", 0, 15, 10, "#FFFFFF", 10);
		}

	}

	private void drawWin(Graphics g) {
		Button restart = new Button("Restart", B_WIDTH / 2, B_HEIGHT / 2, B_WIDTH / 6, B_HEIGHT / 10,
				Color.decode("#44cc44"), Color.white, new Font("Helvetica", Font.PLAIN, 15), ButtonMethod.RESTART);
		clickables.clear();
		clickables.add(restart);

		g.drawImage(Background.WIN.getImage(), 0, 0, Bridge.getGameBoardSize(0), Bridge.getGameBoardSize(1), this);

		String won = "You won!";

		Font small = new Font("Helvetica", Font.BOLD, 14);
		Font large = new Font("Helvetica", Font.BOLD, 25);
		// FontMetrics fmsmall = getFontMetrics(small);
		FontMetrics fmlarge = getFontMetrics(large);

		g.setColor(Color.white);
		g.setFont(large);
		g.drawString(won, (B_WIDTH - fmlarge.stringWidth(won)) / 2, B_HEIGHT / 3);
		g.setColor(Color.red);
		g.setFont(small);
		for (Clickable clickable : clickables) {
			clickable.drawPolygon(g);
		}
		
		g.drawImage(Texture.loadTexture("pointer.png"), mx, my, this);

		

	}

	private void drawObjects(Graphics g) {

		g.drawImage(Utils.getBackground(Utils.getPlayerLevel()).getImage(), 0, 0, 960, 540, this);
		g.setColor(Color.black);
		g.setFont(new Font("Helvetica", Font.BOLD, 20));
		g.drawString("Lives: " + Bridge.getPlayer().lives, B_WIDTH / 4, 40);
		g.drawString("Score: " + Bridge.getPlayer().getScore(), (B_WIDTH / 2 + B_WIDTH) / 2, 20);
		g.drawString("Tool:", (B_WIDTH / 2 + B_WIDTH) / 2, 40);
		
		for (Sprite sprite : sprites) {
			
			if(sprite instanceof Entity){
				e = e+1;
				continue;
			}
			g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), (int) (sprite.getWidth()*extra), (int) (sprite.getHeight()*extra), this);
			
			if (debug && hitboxes)
				g.drawPolygon(sprite.getPolygon());
		}

		for (Moveable s : moveables) {
			e = e+1;
			Sprite sprite = (Sprite) s;
			SpriteType type = (sprite).getType();
			switch (type) {
			case KNOBBER:
				Knobber sk = (Knobber) s;
				g.drawImage(sprite.getImage(), (sprite.getX()), sprite.getY(), (int) (Bridge.getPlayer().getWalkingWidth()*extra), (int) (Bridge.getPlayer().getWalkingHeight()*extra), this);
				((Knobber) s).drawHealthBar(g, sk.x - (50 / 2), sk.y - 20, 50, 5);
				break;
			case ARROW:
				if(((Arrow) sprite).getDirection().equals(Direction.LEFT)) g.drawImage(sprite.getImage(), sprite.x + 16, sprite.y, -16, 4, this);
				else g.drawImage(sprite.getImage(), (sprite.getX()), sprite.getY(), (int) (16*extra), (int) (4*extra), this);
				break;
			default:
				g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), (int) (sprite.getWidth()*extra), (int) (sprite.getHeight()*extra), this);
				break;
			}

			if (debug && hitboxes)
				g.drawPolygon(sprite.getPolygon());
		}
		Player player = Bridge.getPlayer();

		Bridge.getPlayer().drawHealthBar(g, player.x - (100 / 2), player.y - 20, 100, 5);
		
		
		
		if (!Bridge.getPlayer().walking) {
			if(player.getFacingDirection().equals(Direction.LEFT))
				g.drawImage(player.getImage(), player.x + player.getRestingWidth(), player.y, -(player.getRestingWidth()), player.getRestingHeight(), this);
			if(player.getFacingDirection().equals(Direction.RIGHT))
				g.drawImage(player.getImage(), player.x, player.y, player.getRestingWidth(), player.getRestingHeight(), this);
		} else {
			if(player.getFacingDirection().equals(Direction.LEFT))
				g.drawImage(player.getImage(), player.x + player.getWalkingWidth(), player.y, -(player.getWalkingWidth()), player.getWalkingHeight(), this);
			if(player.getFacingDirection().equals(Direction.RIGHT))
				g.drawImage(player.getImage(), player.x, player.y, player.getWalkingWidth(), player.getWalkingHeight(), this);
		}
		
		
		if (Bridge.getPlayer().hasTool()) {
			Tool tool = ((Player) Bridge.getPlayer()).getTool();
			g.drawImage(tool.getImage(), ((B_WIDTH / 2 + B_WIDTH) / 2 + g.getFontMetrics().stringWidth("Tool:")), 25, this);
		}
		if (debug && hitboxes)
			g.drawPolygon(Bridge.getPlayer().getPolygon());

		for (Sprite sprite : tools) {
			g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), this);
		}

		if (debug) {
			g.setColor(Color.black);
			g.setFont(new Font("Helvetica", Font.BOLD, 10));
			g.drawString("Version: " + Bridge.getGame().getVersion(), 0, 10);
			g.drawString("Gravity: " + player.gravity, 0, 20);
			g.drawString("Falling: " + player.falling, 0, 30);
			g.drawString("Onground: " + player.onground, 0, 40);
			g.drawString("Jumping: " + player.jumping, 0, 50);
			g.drawString("Speed: " + player.speedboost, 0, 60);
			g.drawString("Location: " + player.x + ":" + player.y, 0, 70);
			g.drawString("Left: " + player.left, 0, 80);
			g.drawString("Right: " + player.right, 0, 90);
			g.drawString("Up: " + player.up, 0, 100);
			g.drawString("Climbing: " + player.climbing, 0, 110);
			g.drawString("Level: " + player.level, 0, 120);
			g.drawString("Entities: " + e, 0, 130);
		}

		

		if (paused) {
			g.drawImage(Texture.loadTexture("darken.png"), 0, 0, null);
		}

		for (Entry<Integer, String> entry : messages.entrySet()) {
			String[] info = entry.getValue().split(":");
			int time = Integer.parseInt(info[3]);
			if (time != -1) {
				g.setColor(Color.decode(info[4]));
				g.setFont(new Font("Helvetica", Font.BOLD, Integer.parseInt(info[5])));
				if (info[0].contains("//n")) {
					String[] m = info[0].split("//n");
					for (int i = 0; i != m.length; i++) {
						g.drawString(m[i], Integer.parseInt(info[1]),
								Integer.parseInt(info[2]) + (i * Integer.parseInt(info[5])));
					}
				} else
					g.drawString(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]));
				if (time != 0)
					strings_temp.add(entry.getKey() + "~" + info[0] + ":" + info[1] + ":" + info[2] + ":" + (time - 1)
							+ ":" + info[4] + ":" + info[5]);

			}
			if (time == -1) {
				g.setColor(Color.decode(info[4]));
				g.setFont(new Font("Helvetica", Font.BOLD, Integer.parseInt(info[5])));
				if (info[0].contains("//n")) {
					String[] m = info[0].split("//n");
					for (int i = 0; i != m.length; i++) {
						g.drawString(m[i], Integer.parseInt(info[1]) * 2,
								Integer.parseInt(info[2]) + (i * Integer.parseInt(info[5])));
					}
				} else
					g.drawString(info[0], Integer.parseInt(info[1]), Integer.parseInt(info[2]));
				strings_temp.add(entry.getKey() + "~" + info[0] + ":" + info[1] + ":" + info[2] + ":" + time + ":"
						+ info[4] + ":" + info[5]);
			}
		}
		messages.clear();
		for (String string : strings_temp) {
			String[] info = string.split("~");

			messages.put(Integer.parseInt(info[0]), info[1]);

		}
		strings_temp.clear();

		for (Entry<Integer, String> entry : messages_player.entrySet()) {
			String[] info = entry.getValue().split(":");
			int time = Integer.parseInt(info[3]);
			if (time != -1) {
				g.setColor(Color.decode(info[4]));
				g.setFont(new Font("Helvetica", Font.BOLD, Integer.parseInt(info[5])));
				g.drawString(info[0], player.x + Integer.parseInt(info[1]), player.y + Integer.parseInt(info[2]));
				if (entry.getKey().equals(2))
					if (time != 0)
						strings_temp_player.add(entry.getKey() + "~" + info[0] + ":" + info[1] + ":" + info[2] + ":"
								+ (time - 1) + ":" + info[4] + ":" + info[5]);

			}
			if (time == -1) {
				g.setColor(Color.decode(info[1]));
				g.setFont(new Font("Helvetica", Font.BOLD, Integer.parseInt(info[5])));
				g.drawString(info[0], Integer.parseInt(info[2]), Integer.parseInt(info[3]));
				if (entry.getKey().equals(2))
					strings_temp_player.add(entry.getKey() + "~" + info[0] + ":" + info[1] + ":" + info[2] + ":"
							+ (time) + ":" + info[4] + ":" + info[4]);
			}
		}
		messages_player.clear();
		for (String string : strings_temp_player) {
			String[] info = string.split("~");
			messages_player.put(Integer.parseInt(info[0]), info[1]);

		}
		strings_temp_player.clear();

		for (Clickable clickable : clickables) {
			g.drawPolygon(clickable.drawPolygon(g));
		}
		
		if(paused || debug) g.drawImage(Texture.loadTexture("pointer.png"), mx, my, this);
		
		if(in && debug) this.sprite.drawInfo(mx, my, g);
		
		e=0;

	}

	private void drawGameOver(Graphics g) {

		if (gameStatus.contains("gameover")) {
			
			
			g.drawImage(Background.GAMEOVER.getImage(), 0, 0, Bridge.getGameBoardSize(0), Bridge.getGameBoardSize(1), this);
			g.setColor(Color.black);
			
			String gameover = "Game Over";
			String reason = gameStatus.split(":")[1];
			reason = reason.replaceAll("_", " ");

			Button restart = new Button("Restart", B_WIDTH / 2, B_HEIGHT / 2, B_WIDTH / 6, B_HEIGHT / 10,
					Color.decode("#950000"), Color.white, new Font("Helvetica", Font.PLAIN, 15), ButtonMethod.RESTART);
			
			
			clickables.clear();
			clickables.add(restart);

			g.drawPolygon(restart.drawPolygon(g));
			

			g.setFont(new Font("Helvetica", Font.BOLD, 20));
			g.drawString(gameover, (B_WIDTH - g.getFontMetrics().stringWidth(gameover)) / 2, B_HEIGHT / 4);
			
			g.setFont(new Font("Helvetica", Font.BOLD, 15));
			g.drawString(reason, (B_WIDTH - g.getFontMetrics().stringWidth(reason)) / 2, B_HEIGHT / 3);
			

			g.drawImage(Texture.loadTexture("pointer.png"), mx, my, this);
			
		}
		if (gameStatus.contains("won")) {

			g.drawImage(Background.WIN.getImage(), 0, 0, Bridge.getGameBoardSize(0), Bridge.getGameBoardSize(1), this);
			g.setColor(Color.black);
			String won = "You won level " + gameStatus.split(":")[1] + "!";

			Button levelup = new Button("Next Level", B_WIDTH / 2, B_HEIGHT / 2, B_WIDTH / 6, B_HEIGHT / 10,
					Color.decode("#44cc44"), Color.white, new Font("Helvetica", Font.PLAIN, 15), ButtonMethod.LEVEL_UP);
			clickables.add(levelup);

			g.drawPolygon(levelup.drawPolygon(g));
			

			Font small = new Font("Helvetica", Font.BOLD, 14);
			FontMetrics fm = getFontMetrics(small);
			g.setFont(small);
			g.drawString(won, (B_WIDTH - fm.stringWidth(won)) / 2, B_HEIGHT / 3);
			

			g.drawImage(Texture.loadTexture("pointer.png"), mx, my, this);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		// if(loaded)

		if (!paused && Bridge.getPlayer().ready)
			update();

		repaint();

	}

	private void update() {
		if(!paused && !inv){
			
			for(Moveable sprite : moveables){
				sprites.remove(sprite);
				sprite.move();
				moveables_temp.add(sprite);	
				
			}
			moveables.clear();
			for (Moveable sprite : moveables_temp) {
				moveables.add(sprite);
				sprites.add(((Sprite)sprite));
			}
			moveables_temp.clear();
			Bridge.getPlayer().move();
			for (Sprite sprite : removedSprites){
				if (moveables.contains(sprite))
					moveables.remove(sprite);
				if (tools.contains(sprite))
					tools.remove(sprite);
				if (sprites.contains(sprite)) {
					sprites.remove(sprite);
				}
			}
			removedSprites.clear();
			
			for(Sprite sprite : sprite_temp){
				if(sprite instanceof Moveable){
					moveables.add(((Moveable) sprite));
				} else 
					sprites.add(sprite);
			}
			sprite_temp.clear();

		}
	}
	
	public void toggleGravity() {
		if (Bridge.player.gravity){
			Bridge.player.gravity = false;
			Bridge.player.dx=0;
			Bridge.player.dy=0;
		} else
			Bridge.player.gravity = true;
	}

	public void toggleHitboxes() {
		if (hitboxes)
			hitboxes = false;
		else
			hitboxes = true;
	}

	public String getLocation(int x, int y) {
		return x + ":" + y;
	}

	public void toggleDebugMode() {
		if (debug)
			debug = false;
		else
			debug = true;
	}

	public int getLevels() {
		return levels.size();
	}

	public void start() {
		this.init();
	}

	public void pause() {
		paused = true;

		clickables.add(new Button("Resume", B_WIDTH / 4, B_HEIGHT / 2, B_WIDTH / 6, 50, Color.gray, Color.white,
				new Font("Helvetica", Font.BOLD, 25), ButtonMethod.RESUME));
		clickables.add(new Button("Main Menu", (B_WIDTH / 2 + B_WIDTH) / 2, B_HEIGHT / 2, B_WIDTH / 4, 50, Color.gray,
				Color.white, new Font("Helvetica", Font.BOLD, 25), ButtonMethod.MAIN_MENU));

	}

	public void resume() {
		clickables.clear();
		for (Entry<Integer, String> entry : messages.entrySet()) {
			String[] split = entry.getValue().split(":");
			if (Integer.parseInt(split[3]) == -1) {
				strings_temp.add(entry.getKey() + "~" + split[0] + ":" + split[1] + ":" + split[2] + ":" + "1" + ":"
						+ split[4] + ":" + split[5]);
			} else
				strings_temp.add(entry.getKey() + "~" + split[0] + ":" + split[1] + ":" + split[2] + ":" + split[3]
						+ ":" + split[4] + ":" + split[5]);
		}
		messages.clear();
		for (String string : strings_temp) {
			String[] info = string.split("~");

			messages.put(Integer.parseInt(info[0]), info[1]);

		}
		strings_temp.clear();

		for (Entry<Integer, String> entry : messages_player.entrySet()) {
			String[] split = entry.getValue().split(":");
			if (Integer.parseInt(split[3]) == -1) {
				strings_temp_player.add(entry.getKey() + "~" + split[0] + ":" + split[1] + ":" + split[2] + ":" + "1"
						+ ":" + split[4] + ":" + split[5]);
			} else
				strings_temp_player.add(entry.getKey() + "~" + split[0] + ":" + split[1] + ":" + split[2] + ":"
						+ split[3] + ":" + split[4] + ":" + split[5]);
		}
		for (String string : strings_temp_player) {
			String[] info = string.split("~");

			messages_player.put(Integer.parseInt(info[0]), info[1]);

		}
		strings_temp_player.clear();
		paused = false;

	}

	public void drawRectangle(Rectangle point) {
		// recs.add(point);
	}

	public void addMoveable(Moveable moveable) {
		moveables_temp.add(moveable);
	}

	public void removeSprite(Sprite sprite) {
		removedSprites.add(sprite);
	}



	private class TAdapter extends KeyAdapter {

		@Override
		public void keyReleased(KeyEvent e) {
			for (Sprite sprite : getLevel(Bridge.player.level))
				if (sprite instanceof Keyable)
					((Keyable) sprite).keyReleased(e);

		}

		@Override
		public void keyPressed(KeyEvent e) {
			
			if(inv){

				if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					if (l == 0) {
						l = Bridge.player.inventory.size() - 1;
					} else {
						l = l - 1;
					}
				}
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					if (l == Bridge.player.inventory.size() - 1) {
						l = 0;
					} else {
						l = l + 1;
					}
				}
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					ButtonMethod.SELECT_TOOL.clicked();
				}
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					ButtonMethod.CLOSE_INVENTORY.clicked();
					return;
				}
				
				

				

			}
			
			if(!ingame && gameStatus.contains("won")){
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					ButtonMethod.LEVEL_UP.clicked();
				}
			}
			
			if(e.getKeyCode() == KeyEvent.VK_F11){
				if(!fs){
					Bridge.getGame().dispose();
					Bridge.getGame().setUndecorated(true);
					vc.setFullScreenWindow(Bridge.getGame());
					fs = true;
					extra = ((double) Bridge.getGame().getWidth())/((double) Bridge.getGameBoardSize(0));
					Utils.broadcastMessage((double)(extra) + "");
					return;
				}
				else {
					fs = false;
					Bridge.getGame().dispose();
					vc.setFullScreenWindow(null);
					Bridge.getGame().setUndecorated(false);
					Bridge.getGame().setVisible(true);
					
				}
				
			}
			
			if(e.getKeyCode() == KeyEvent.VK_1){
				Utils.setPlayerLevel(1);
				loadLevel();
			}
			
			if(e.getKeyCode() == KeyEvent.VK_2){
				Utils.setPlayerLevel(2);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_3){
				Utils.setPlayerLevel(3);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_4){
				Utils.setPlayerLevel(4);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_5){
				Utils.setPlayerLevel(5);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_6){
				Utils.setPlayerLevel(6);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_7){
				Utils.setPlayerLevel(7);
			}
			
			if(e.getKeyCode() == KeyEvent.VK_8){
				Utils.setPlayerLevel(8);
			}
			for (Sprite sprite : getLevel(Bridge.player.level))
				if (sprite instanceof Keyable)
					((Keyable) sprite).keyPressed(e);
		}
	}

	private class MMListener extends MouseMotionAdapter {
		
		public void mouseDragged(MouseEvent e){
			
			mx = e.getX();
			my = e.getY();
			if(debug)
				for(Sprite ssprite : sprites){
					if(ssprite.getPolygon().contains(new Point(mx, my))){
						in = true;
						sprite = ssprite;
						return;
					}
					sprite = null;
					in = false;
				}
			
		
			for (Clickable clickable : clickables) {
				if (clickable.getPolygon().contains(e.getPoint())) {
					clickable.mouseMoved(e);
				}
			}

		}

		public void mouseMoved(MouseEvent e) {
			
			mx = e.getX();
			my = e.getY();
			if(debug)
				for(Sprite ssprite : sprites){
					if(ssprite.getPolygon().contains(new Point(mx, my))){
						in = true;
						sprite = ssprite;
						return;
					}
					sprite = null;
					in = false;
				}
			
		
			for (Clickable clickable : clickables) {
				if (clickable.getPolygon().contains(e.getPoint())) {
					clickable.mouseMoved(e);
				}
			}

		}
	}

	private class MListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			try {
				for (Clickable clickable : clickables)
					if (clickable.getPolygon().contains(e.getPoint())) {
						clickable.mousePressed(e);
					}
			} catch (ConcurrentModificationException ex) {
			}
		}

		public void mouseReleased(MouseEvent e) {
			for (Clickable clickable : clickables)
				if (clickable.getPolygon().contains(e.getPoint()))
					clickable.mouseReleased(e);
		}

	}

	public boolean addSprite(Sprite sprite2) {
		if(moveables.contains(sprite2) || sprites.contains(sprite2))
			return false;
		else sprite_temp.add(sprite2);
		return true;
	}
	

	
	
	

}
