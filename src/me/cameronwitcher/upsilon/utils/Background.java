package me.cameronwitcher.upsilon.utils;

import java.awt.Image;

import res.Texture;

public enum Background {
	SKY("sky-background.png"),
	HELL("hell-background.gif"),
	TEST("test-background.gif"),
	WIN("win-background.png");
	
	String background;
	
	Background(String background){
		this.background = background;
	}
	
	public Image getImage(){
		return Texture.loadTexture(background);
	}
}
