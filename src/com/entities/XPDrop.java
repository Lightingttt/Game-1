package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.world.Camera;

public class XPDrop extends Entity{

	public XPDrop(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void render(Graphics g) {
		
		g.setColor (Color.blue);
		g.fillOval (this.getX() - 28 - Camera.x, this.getY() + 28 - Camera.y, 36, 36);
		
	}
	

}
