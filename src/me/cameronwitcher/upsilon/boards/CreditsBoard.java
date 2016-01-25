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

public class CreditsBoard extends Board implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int M_WIDTH = 960;
	public int M_HEIGHT = 640;
	private int mx = 0;
	private int my = 0;
	Timer timer;
	public List<Clickable> clickables = new ArrayList<>();

	public CreditsBoard() {
		setType(BoardType.MENU_BOARD);
		initBoard();
	}

	public void initBoard() {

		timer = new Timer(15, this);
		timer.start();

		addKeyListener(new TAdapter());
		addMouseMotionListener(new MMListener());
		addMouseListener(new MListener());
		
		clickables.add(new Button("Back to menu", 100, 50, 100, 20, Color.GRAY, Color.WHITE, new Font(Font.SANS_SERIF, Font.BOLD, 15), ButtonMethod.MAIN_MENU));


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
		
		g.setColor(Color.WHITE);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 50));
		
		
		g.drawString("Upsilon", (M_WIDTH/2) - (g.getFontMetrics().stringWidth("Upsilon")/2), 55);

		
		g.setFont(new Font(Font.SANS_SERIF, Font.TYPE1_FONT, 15));
		g.drawString("Authors & Developers:", M_WIDTH/2 - (g.getFontMetrics().stringWidth("Authors & Developers:")/2), 100);
		
		String a = Bridge.getGame().description.getProperty("authors");
		g.drawString(a, M_WIDTH/2 - (g.getFontMetrics().stringWidth(a)/2), 120);

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
