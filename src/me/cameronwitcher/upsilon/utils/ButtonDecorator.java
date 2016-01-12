package me.cameronwitcher.upsilon.utils;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

public class ButtonDecorator extends MouseAdapter {
	JButton btn;
	
	public ButtonDecorator(JButton btn) {
		this.btn = btn;
	}
	public void mouseEntered(MouseEvent e) {
		btn.setBackground(Color.decode("#BCC9D1"));
    	btn.setForeground(Color.white);
    	Border border = new CompoundBorder(
			    BorderFactory.createMatteBorder(3, 3, 0, 0, Color.decode("#CAD7E0")),
			    BorderFactory.createMatteBorder(0, 0, 3, 3, Color.decode("#ACB7BF")));
		
		btn.setBorder(border);
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
    	btn.setBackground(Color.decode("#D1D1D1"));
    	btn.setForeground(Color.gray);
    	Border border = new CompoundBorder(
			    BorderFactory.createMatteBorder(3, 3, 0, 0, Color.decode("#CAD7E0")),
			    BorderFactory.createMatteBorder(0, 0, 3, 3, Color.decode("#ACB7BF")));
		
		btn.setBorder(border);
    }
    
    public void mousePressed(MouseEvent e){
    	btn.setForeground(Color.YELLOW);
    	btn.setBackground(Color.lightGray);
    }
    public void mouseReleased(MouseEvent e){
    	btn.setForeground(Color.white);
    	btn.setBackground(Color.decode("#D1D1D1"));
    }
}