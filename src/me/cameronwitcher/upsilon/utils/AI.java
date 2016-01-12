package me.cameronwitcher.upsilon.utils;

import me.cameronwitcher.upsilon.spriteutils.Sprite;

public class AI {
	
	public static int getFollowSprite(Sprite follower, Sprite followed){
		if(followed.getX() - follower.getX() > 0)
			return 1;
		if(followed.getX() - follower.getX() < 0)
			return -1;
		return 0;	
	}
	public static int moveBackAndForth(Sprite sprite, Direction direction){
		return 0;
	}
	
}