package me.cameronwitcher.upsilon.utils;

public enum BoardType {
	MENU_BOARD("menu_board"),
	LEVEL_DEBUG("level_debug"),
	GAME_BOARD("game_board");

	String type;
	
	BoardType(String type){
		this.type = type;
	}
}
