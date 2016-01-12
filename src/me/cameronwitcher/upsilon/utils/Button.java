package me.cameronwitcher.upsilon.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;

import me.cameronwitcher.upsilon.spriteutils.Clickable;
import res.Audio;

public class Button implements Clickable {
	
	String message;
	public int x;
	public int y;
	public int width;
	 public int height;
	 public boolean f = false;
    Color fore;
    Color back;
    Font font;
    public ButtonMethod method;
	
	public Button(String message, int x, int y, int width, int height, Color fore, Color back, Font font, ButtonMethod method){
		this.message = message;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.fore = fore;
		this.back = back;
		this.font = font;
		this.method = method;
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}
	
	@Override
	public void mouseEntered(MouseEvent e) {
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		Audio.playSound(Sound.BUTTON_CLICK);
		method.clicked();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public String getString(){
		return message;
	}

	@Override
	public Polygon getPolygon() {
//		int b = (2);
//		Polygon p = new Polygon(new int[] {x, x+b,x-b}, new int[] {y+2,y-b,y-b}, 3);
		Polygon p = new Polygon(new int[] {x-width/2,x+width/2,x+width/2,x-width/2}, new int[] {y+height/2, y+height/2,y-height/2,y-height/2}, 4);
		return p;
		
	}

	public Font getFont() {
		return font;
	}

	public Color getForeground() {
		return fore;
	}

	

	@Override
	public Polygon drawPolygon(Graphics g) {
		Polygon p = getPolygon();
		g.setColor(back);
		g.fillPolygon(p);
		g.setColor(fore);
		g.drawString(message, x-(g.getFontMetrics(g.getFont()).stringWidth(message)/2), y + (g.getFontMetrics().getHeight()/4));
		return p;
	}

	

}
