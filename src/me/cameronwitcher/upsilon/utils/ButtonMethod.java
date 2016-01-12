package me.cameronwitcher.upsilon.utils;

import me.cameronwitcher.upsilon.Bridge;
import me.cameronwitcher.upsilon.boards.GameBoard;
import me.cameronwitcher.upsilon.spriteutils.PlayerModel;

public enum ButtonMethod {
	
	LEVEL_UP("level_up"),
	MAIN_MENU("main_menu"),
	LEVEL_SELECT("level_select"),
	RESTART("restart"),
	RESUME("resume"),
	CLOSE_INVENTORY("close_inventory"),
	SELECT_TOOL("select_tool"),
	QUIT("quit"),
	START("start");
	
	String method;
	
	private ButtonMethod(String method) {
		this.method = method;
	}
	
	
	
	public void clicked(){
		if(this.equals(LEVEL_UP)){
			Bridge.getPlayer().levelUp();
		}
		if(this.equals(MAIN_MENU)){
			Bridge.openMenu();
		}
		
		if(this.equals(LEVEL_SELECT)){
			Bridge.getGame().openLevelDebug(Bridge.getGame().DEBUG_LEVEL);
		}
		if(this.equals(RESTART)){
			Bridge.restart();
		}
		if(this.equals(START)){
			Bridge.start();
		}
		//Cameron
		//Terminates program from Runtime.
		if(this.equals(QUIT)){
			Bridge.quit();
		}
		if(this.equals(RESUME)){
			((GameBoard)Bridge.getGame().getBoard()).resume();
		}
		if(this.equals(CLOSE_INVENTORY)){
			Bridge.getGame().closeInventory();
			((GameBoard)Bridge.getGame().getBoard()).clickables.clear();
		}
		if(this.equals(SELECT_TOOL)){
			GameBoard b = ((GameBoard)Bridge.getGame().getBoard());
			Bridge.player.setTool(Bridge.player.inventory.get(b.l));
			Bridge.getGame().closeInventory();
			((GameBoard)Bridge.getGame().getBoard()).clickables.clear();
			String t = "You have equiped " + Bridge.player.inventory.get(b.l).getType().toString();
			t = t.replaceAll("_", " ");
			Utils.displayMessage(10, t, b.getGraphics().getFontMetrics().stringWidth(t)/2, 32, 100, "#FF0000", 12);
			
		}
		
	}

}
