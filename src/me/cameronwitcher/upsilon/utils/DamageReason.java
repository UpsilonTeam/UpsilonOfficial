package me.cameronwitcher.upsilon.utils;

import java.util.ArrayList;
import java.util.Random;

public enum DamageReason {
	VOID("You fell out of the world!:You're a clumsy one, aren't you?"),
	ENEMY("You were killed by an enemy.:They got you.. Try again? I bet you can get 'em.:Ouch. Beat to death. I say get REVENGE! >=D"),
	PROJECTILE("You were shot!:You took an arrow to the knee! I think.."),
	BAD_THINGS("You probably stepped on a spike. Painful!:Ouch. Looks like you stepped on something painful!");
	
	String reason;
	
	DamageReason(String reason){
		this.reason = reason;
	}
	
	public String getMessage(){
		ArrayList<String> ms = new ArrayList<>();
		for(String m : reason.split(":"))
			ms.add(m);
		return ms.get(new Random().nextInt(ms.size()));
				
	}

}
