package me.cameronwitcher.upsilon.utils;

import java.util.Random;

public enum Sound {
	
	
	BUTTON_CLICK("button_click.wav"),
	BOW_SHOOT("bow_shoot.wav"),
	JUMP("jump_1.wav:jump_2.wav"),
	SCORE("score.wav"),
	WIN("win.wav"),
	TEST("test.wav");
	
	String sound;
	
	Sound(String sound){
		this.sound = sound;
	}
	
	public Sound getSound(){
		return this;
	}
	public String getSoundString(){
		if(sound.contains(":")){

			String[] i = sound.split(":");
			return i[new Random().nextInt(i.length)];
		
		}
		return sound;
	}

}
