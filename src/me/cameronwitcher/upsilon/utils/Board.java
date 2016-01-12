package me.cameronwitcher.upsilon.utils;

import javax.swing.JPanel;

public class Board extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BoardType type;
	
	public Board(){
		
	}
	
	public BoardType getType(){
		return type;
	}
	public void setType(BoardType type){
		this.type = type;
	}

	public void disable() {
		// TODO Auto-generated method stub
		
	}
	
}
