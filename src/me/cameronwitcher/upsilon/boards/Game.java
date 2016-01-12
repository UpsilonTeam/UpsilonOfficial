package me.cameronwitcher.upsilon.boards;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

import javax.swing.JFrame;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.spriteutils.Tool;
import me.cameronwitcher.upsilon.utils.Board;
import me.cameronwitcher.upsilon.utils.Utils;
import me.cameronwitcher.upsilon.utils.XmlFile;
import res.Texture;

public class Game extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int DEBUG_LEVEL = 1;
	private String version;
	public Board board;
	public XmlFile description = new XmlFile(new File("description.xml"));

	public Game() {
		
		version = description.getProperty("version");
		
		
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

		// Create a new blank cursor.
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
		    cursorImg, new Point(0, 0), "blank cursor");

		// Set the blank cursor to the JFrame.
		setCursor(blankCursor);
		
	}
	
	public XmlFile getDescriptionFile(){
		return description;
	}

	public String getVersion(){
		return version;
	}

	public void init() {
		
		add(new MenuBoard());

		setResizable(false);
		pack();

		setTitle("Upsilon");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setIconImage(Texture.loadTexture("logo.png"));
	}



	

	public void start() {
		clear();
		setTitle("Upsilon");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setIconImage(Texture.loadTexture("logo.png"));
		
		setPreferredSize(new Dimension(640, 640));
		pack();
		
		add(new GameBoard());
		setLocationRelativeTo(null);
		setResizable(false);
		
		if (Utils.getPlayerLevel() == 1) {
			((GameBoard) board).loadHelp();
		}
	}
	
	public void restart(){
		Utils.setPlayerLevel(1);
	}

	public void openLevelDebug(int i) {
		clear();
		setPreferredSize(new Dimension(640, 640));
		pack();
		setVisible(true);

		setBoard(new LevelDebugBoard(i));
		setLocationRelativeTo(null);
	}

	public void loadLevel() {
		((GameBoard) board).loadLevel();
	}

	

	public void openMenu() {
		clear();
		setPreferredSize(new Dimension(960, 640));
		pack();
		setVisible(true);
		
		
		setLocationRelativeTo(null);
	}

	public void clear() {
		if(board!=null)((Board)board).disable();
		removeAll();
		dispose();
	}

	public void openInventory(List<Tool> inv) {

		((GameBoard)Bridge.getGame().getBoard()).inv = true;

	}

	public void closeInventory() {
		((GameBoard)Bridge.getGame().getBoard()).inv = false;
	}

	public void setBoard(Board board) {
		this.board = board;
		add(board);
	}

	public Board getBoard() {
		return board;
	}

}
