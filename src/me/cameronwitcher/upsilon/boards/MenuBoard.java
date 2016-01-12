package me.cameronwitcher.upsilon.boards;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Timer;


import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.sprites.Player;
import me.cameronwitcher.upsilon.spriteutils.Clickable;
import me.cameronwitcher.upsilon.utils.Background;
import me.cameronwitcher.upsilon.utils.Board;
import me.cameronwitcher.upsilon.utils.BoardType;
import me.cameronwitcher.upsilon.utils.Button;
import me.cameronwitcher.upsilon.utils.ButtonMethod;
import me.cameronwitcher.upsilon.utils.Utils;
import res.Texture;

public class MenuBoard extends Board implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int M_WIDTH = 960;
	public int M_HEIGHT = 640;
	public int LEVEL_DEBUG = 6;
	private int mx = 0;
	private int my = 0;
	Timer timer;
	public List<Clickable> clickables = new ArrayList<>();

	public MenuBoard() {
		setType(BoardType.MENU_BOARD);
		initBoard();
	}

	public void initBoard() {

		timer = new Timer(15, this);
		timer.start();

		addKeyListener(new TAdapter());
		addMouseMotionListener(new MMListener());
		addMouseListener(new MListener());

		clickables.add(new Button("Play", M_WIDTH / 4, (M_HEIGHT / 2 + M_HEIGHT) / 2, M_WIDTH / 4, (M_HEIGHT / 20 + 10),
				Color.gray, Color.white, new Font("Helvetica", Font.BOLD, 25), ButtonMethod.START));

		clickables.add(new Button("Quit", ((M_WIDTH / 2) + M_WIDTH) / 2, (M_HEIGHT / 2 + M_HEIGHT) / 2, M_WIDTH / 4,
				(M_HEIGHT / 20 + 10), Color.gray, Color.white, new Font("Helvetica", Font.BOLD, 25),
				ButtonMethod.QUIT));

		setLayout(null);

		setFocusable(true);

		setPreferredSize(new Dimension(M_WIDTH, M_HEIGHT));
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawMenu(g);
		Toolkit.getDefaultToolkit().sync();
	}

	public void drawMenu(Graphics g) {
		
		

		g.drawImage(Background.WIN.getImage(), 0, 0, M_WIDTH, M_HEIGHT, null);
		
		

		
		g.drawImage(Texture.loadTexture("playermodels/" + Bridge.getPlayer().getPlayerModel() + "/stand_right.png"),
		
				(M_WIDTH / 2) - ((13 * 5) / 2), (M_HEIGHT / 2) - ((44 * 5) / 2), 13 * 5, 44 * 5, null);

		g.setColor(Color.white);

		for (Clickable clickable : clickables) {
			g.drawPolygon(clickable.drawPolygon(g));
		}
		
		
		g.drawImage(Texture.loadTexture("pointer.png"), mx, my, this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}

	private class TAdapter extends KeyAdapter {
		
		boolean g = false;
		boolean r = false;
		boolean n = false;

		@Override
		public void keyReleased(KeyEvent event) {
			
			if(event.getKeyCode() == KeyEvent.VK_G){
				g = false;
			}
			
			if(event.getKeyCode() == KeyEvent.VK_R){
				r = false;
			}
			
			
			if(event.getKeyCode() == KeyEvent.VK_N){
				n = false;
			}
			
		}

		@Override
		public void keyPressed(KeyEvent event) {

			// Cameron
			// Quit.
			if (event.getKeyCode() == KeyEvent.VK_ESCAPE) {
				Bridge.quit();
			}

			// Cameron
			if(event.getKeyCode() == KeyEvent.VK_1){
				Utils.setPlayerLevel(1);
				LEVEL_DEBUG = 1;
			}
			
			if(event.getKeyCode() == KeyEvent.VK_2){
				Utils.setPlayerLevel(2);
				LEVEL_DEBUG = 2;
			}
			
			if(event.getKeyCode() == KeyEvent.VK_3){
				Utils.setPlayerLevel(3);
				LEVEL_DEBUG = 3;
			}
			
			if(event.getKeyCode() == KeyEvent.VK_4){
				Utils.setPlayerLevel(4);
				LEVEL_DEBUG = 4;
			}
			
			if(event.getKeyCode() == KeyEvent.VK_5){
				Utils.setPlayerLevel(5);
				LEVEL_DEBUG = 5;
			}
			
			if(event.getKeyCode() == KeyEvent.VK_6){
				Utils.setPlayerLevel(6);
				LEVEL_DEBUG = 6;
			}
			
			if(event.getKeyCode() == KeyEvent.VK_7){
				Utils.setPlayerLevel(7);
				LEVEL_DEBUG = 7;
			}
			
			if(event.getKeyCode() == KeyEvent.VK_8){
				Utils.setPlayerLevel(8);
				LEVEL_DEBUG = 8;
			}
			
			if(event.getKeyCode() == KeyEvent.VK_D){
				Bridge.openLevelDebug(LEVEL_DEBUG);
			}
			
			//Cameron
			//Hidden feature.
			
			if(event.getKeyCode() == KeyEvent.VK_G){
				g = true;
//				Utils.broadcastMessage("G true");
			}
			
			if(event.getKeyCode() == KeyEvent.VK_R){
				r = true;
//				Utils.broadcastMessage("R true");
			}
			
			
			
			if(event.getKeyCode() == KeyEvent.VK_N){
				n = true;
//				Utils.broadcastMessage("N true");
			}
			
			//Check if user is pressing "g", "r", and "n"
			
			if(g&&r&&n){
				if(Bridge.getPlayer().getPlayerModel().equalsIgnoreCase("yellow")) Bridge.getPlayer().setPlayerModel("green");
				else Bridge.getPlayer().setPlayerModel("yellow");
				g=false;
				r=false;
				n=false;
			}
		}
	}

	private class MMListener extends MouseMotionAdapter {

		public void mouseMoved(MouseEvent e) {
			mx = e.getX();
			my = e.getY();
			for (Clickable clickable : clickables) {
				if (clickable.getPolygon().contains(e.getPoint())) {
					clickable.mouseMoved(e);
				}
			}

		}
	}

	private class MListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			for (Clickable clickable : clickables)
				if (clickable.getPolygon().contains(e.getPoint())) {
					clickable.mousePressed(e);
					break;
				}
		}

		public void mouseReleased(MouseEvent e) {
			for (Clickable clickable : clickables)
				if (clickable.getPolygon().contains(e.getPoint()))
					clickable.mouseReleased(e);
		}
	}

}
