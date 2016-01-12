package me.cameronwitcher.upsilon.boards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Timer;
import java.util.TimerTask;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.spriteutils.Sprite;
import me.cameronwitcher.upsilon.utils.Board;
import me.cameronwitcher.upsilon.utils.BoardType;
import me.cameronwitcher.upsilon.utils.Utils;
import res.Texture;

public class LevelDebugBoard extends Board implements ActionListener {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public int L_WIDTH = Bridge.getGameBoardSize(0);
	public int L_HEIGHT = Bridge.getGameBoardSize(1);
	int i;
	private int mx = 0;
	private int my = 0;
	private GameBoard testing = new GameBoard();
	
	public LevelDebugBoard(int i){
		setType(BoardType.LEVEL_DEBUG);
		this.i = i;
		init();

		testing.startDebug();
	}
	
	public void init(){
		setLayout(null);
		
		startTimer();
		
		addKeyListener(new TAdapter());
		addMouseMotionListener(new MMListener());
        setFocusable(true);
        
        
        setPreferredSize(new Dimension(Bridge.getGameBoardSize(0), Bridge.getGameBoardSize(1)));
        
        
        
      
	}
	
	public void startTimer(){
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				repaint();
			}
		}, 50, 500);
	}
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawMenu(g);
        Toolkit.getDefaultToolkit().sync();
    }
	
	public void drawMenu(Graphics g){
		
		g.drawRect(0, 0, Bridge.getGameBoardSize(0), Bridge.getGameBoardSize(1));
		
		
		
		g.drawImage(Utils.getBackground(i).getImage(), 0, 0, L_WIDTH, L_HEIGHT, this);
		
		for(Sprite sprite : testing.getLevel(i)){
			g.drawImage(sprite.getImage(), sprite.getX(), sprite.getY(), this);
			g.setColor(Color.BLUE);
			g.drawRect(sprite.getX(), sprite.getY(), sprite.getWidth(), sprite.getHeight());
		}
		
		testing.loadLevels(true);
		

		g.drawImage(Texture.loadTexture("pointer.png"), mx, my, this);
		
		
		g.drawString("X: " + mx, 0, 10);
		g.drawString("Y: " + my, 0, 20);
		
	}
	
	private class MMListener extends MouseMotionAdapter {

		public void mouseMoved(MouseEvent e) {
			
			mx = e.getX();
			my = e.getY();
			

		}
	}
	
	

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
	private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {}
    }
	
	

}
