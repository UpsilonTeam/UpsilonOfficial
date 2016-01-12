package me.cameronwitcher.upsilon.utils;

import java.awt.Font;
import java.awt.Shape;
import java.awt.geom.Area;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.boards.GameBoard;
import me.cameronwitcher.upsilon.sprites.Player;
import me.cameronwitcher.upsilon.spriteutils.Tool;
import me.cameronwitcher.upsilon.spriteutils.ToolType;
import res.Audio;

public class Utils {

	private static List<Integer> ids = new ArrayList<>();
	private static File config;
	private static File inventoryFile;
	private static int player_level;
	public static int player_score;
	private static String root;
	private static File rootFile;
	public static HashMap<Integer, Background> backgrounds = new HashMap<>();
	private static ArrayList<String> player_inv = new ArrayList<>();

	public static void init() {
		root = "C://Upsilon/";
		config = new File(root + "/config.txt");
		inventoryFile = new File(root + "/inventory.txt");
		
		
		
		backgrounds.put(1, Background.SKY);
		backgrounds.put(2, Background.SKY);
		backgrounds.put(3, Background.SKY);
		backgrounds.put(4, Background.SKY);
		backgrounds.put(5, Background.SKY);
		backgrounds.put(6, Background.SKY);
		

		int length = (int) config.length();
		byte[] bytes = new byte[length];
		FileInputStream in;
		try {
			in = new FileInputStream(config);
			try {
				in.read(bytes);
			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				try {
					in.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		int invl = (int) inventoryFile.length();
		byte[] invb = new byte[invl];
		FileInputStream invin;
		try {
			invin = new FileInputStream(inventoryFile);
			try {
				invin.read(invb);
			} catch (IOException e) {

				e.printStackTrace();
			} finally {
				try {
					invin.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
			}
		} catch (FileNotFoundException e1) {

			e1.printStackTrace();
		}

		

		String invs = new String(invb);
		String[] invd = invs.split("-");
		for (String info : invd) {
			if (info.equalsIgnoreCase("empty"))
				break;
			if (info.contains("empty"))
				info.replaceAll("empty", "");
			player_inv.add(info);
		}

		String contents = new String(bytes);
		String[] data = contents.split("-");
		for (String info : data) {
			if (info.contains("level"))
				player_level = Integer.parseInt(info.split(":")[1]);
			if (info.contains("score"))
				player_score = Integer.parseInt(info.split(":")[1]);
			
		}

		
	}

	public static void broadcastMessage(String message) {
		System.out.println(message);
		System.out.println();
	}

	public static void displayMessage(int id, String message, int x, int y, int time, String color, int size) {
		((GameBoard)Bridge.getGame().getBoard()).messages.put(id,message + ":"+ ((x - ((GameBoard)Bridge.getGame().getBoard()).getFontMetrics(new Font("Helvetica", Font.BOLD, size)).stringWidth(message)/ 2)) + ":" + y + ":" + time + ":" + color + ":" + size);
	}

	public static void addPlayerMessage(int id, String message, int xmod, int ymod, int time, String color, int size) {
		((GameBoard)Bridge.getGame().getBoard()).messages_player.put(id,
				message + ":" + xmod + ":" + ymod + ":" + time + ":" + color + ":" + size);
	}

	public static int getNextID() {
		try {
			int i = ids.size();
			ids.add(i);
			return i;
		} catch (IndexOutOfBoundsException ex) {
			ids.add(0);
			return 0;
		}
	}

	public static void savePlayerInfo(Player player) {
		config.delete();
		if (!config.getParentFile().mkdirs()) {
		}
		inventoryFile.delete();
		if (!inventoryFile.getParentFile().mkdirs()) {
		}
		try {
			player_level = player.level;
			player_score = player.score;
		} catch (NullPointerException ex) {
		}

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(config, true));
			out.append("level:" + player_level + "-");
			out.append("score:" + player_score);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		try {
			out = new BufferedWriter(new FileWriter(inventoryFile, true));
			try {
				for (Tool tool : player.inventory) {
					out.append(tool.getType() + "-");
				}
			} catch (NullPointerException ex) {
			}
		} catch (IOException ex) {
		} finally {
			try {
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		

	}

	public static void initPlayer(Player player) {
		player.level = player_level;
		player.score = player_score;
		for (String i : player_inv) {
			try {
				player.inventory.add(ToolType.getNewTool(ToolType.valueOf(i)));
			} catch (IllegalArgumentException ex) {
			}
		}

	}

	public static void runInstall() {
		root = "C://Upsilon/";
		rootFile = new File(root);
		if (!rootFile.exists())
			rootFile.mkdirs();
		config = new File(root + "/config.txt");
		inventoryFile = new File(root + "/inventory.txt");

		if (!config.getParentFile().mkdirs()) {
		}
		
		if (!inventoryFile.getParentFile().mkdirs()) {
		}

		BufferedWriter out = null;

		try {
			out = new BufferedWriter(new FileWriter(config, true));
			out.append("level:" + 1 + "-");
			out.append("score:" + 0);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		

		BufferedWriter invout = null;

		try {
			invout = new BufferedWriter(new FileWriter(inventoryFile, true));
			invout.append("empty");
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				invout.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

	}

	public static void checkPlayerInfo(Player player) {
		player.level = player_level;
		player.score = player_score;
	}



	

	public static void playSound(Sound sound) {
		Audio.playSound(sound);
		
	}
	
	public static boolean intersects(Shape shape1, Shape shape2){
		Area a1 = new Area(shape1);
		Area a2 = new Area(shape2);
		a1.intersect(a2);
		return !a1.isEmpty();
	}
	
	public static Background getBackground(int level){
		return backgrounds.get(level);
	}
	
	public static void setPlayerLevel(int level){
		player_level = level;
		savePlayerInfo(Bridge.player);
		try{
			if(Bridge.getGame().getBoard().getType().equals(BoardType.GAME_BOARD)){
				Bridge.getGame().loadLevel();
			}
		} catch(NullPointerException ex){}
		
	}

	public static int getPlayerLevel() {
		return player_level;
	}

}
