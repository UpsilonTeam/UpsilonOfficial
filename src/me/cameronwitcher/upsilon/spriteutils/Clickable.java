package me.cameronwitcher.upsilon.spriteutils;

import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;

public interface Clickable {

	void mouseMoved(MouseEvent e);
	void mouseEntered(MouseEvent e);
	void mouseExited(MouseEvent e);
	void mousePressed(MouseEvent e);
	void mouseReleased(MouseEvent e);
	Polygon getPolygon();
	Polygon drawPolygon(Graphics g);

}
