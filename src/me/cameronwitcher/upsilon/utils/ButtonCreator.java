package me.cameronwitcher.upsilon.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;

import me.cameronwitcher.upsilon.boards.MenuBoard;

public class ButtonCreator {
	JButton btn = new JButton();
	public ButtonCreator(String label, FontMetrics font, int x, int y, String description){
		setDefaults(label, description, font.getFont());
		btn.setBounds(x - (font.stringWidth(label)/2), y - (font.getHeight()/2),(font.stringWidth(label))*2, font.getHeight()+10);
		
		
	}
	
	public ButtonCreator(String label, FontMetrics font, int x, int y, String description, MenuBoard menu){
		setDefaults(label, description, font.getFont());
		btn.setBounds(x - ((menu.M_WIDTH/4)/2), y - ((menu.M_HEIGHT/20)/2), menu.M_WIDTH/4, (menu.M_HEIGHT/20+10));
	}
	
	public ButtonCreator(String label, FontMetrics font, int x, int y, int bwidth, int bheight, int wwidth, int wheight, String description){
		setDefaults(label, description, font.getFont());
		btn.setBounds(x - (bwidth/2), y - (bheight/2), bwidth, bheight);
		
	}
	
	public JButton getButton(){
		return btn;
	}
	private void setDefaults(String label, String description, Font font){
		btn.setText(label);
		btn.setForeground(Color.gray);
		btn.setBackground(Color.decode("#D1D1D1"));
		btn.setFocusPainted(false);
		btn.setBorderPainted(true);
		btn.setContentAreaFilled(true);
		btn.setToolTipText(description);
		btn.setFont(font);	
		btn.addMouseListener(new ButtonDecorator(btn));
		
		Border border = new CompoundBorder(
			    BorderFactory.createMatteBorder(3, 3, 0, 0, Color.decode("#CAD7E0")),
			    BorderFactory.createMatteBorder(0, 0, 3, 3, Color.decode("#ACB7BF")));
		
		btn.setBorder(border);
	}
	

}
