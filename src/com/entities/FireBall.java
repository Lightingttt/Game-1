package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.world.Camera;

public class FireBall extends Entity{
	
	public double dy, dx;
	public double spd = 3;
	
	
	public FireBall(int x, int y, int width, int height, BufferedImage sprite, double dx, double dy) {
		super(x, y, width, height, sprite);
			this.dx = dx*spd;
			this.dy = dy*spd;
	}
	
	public void tick() {
		x += dx*spd;
		y += dy*spd;
		
	}
	public void render(Graphics g) {
		
		g.setColor(new Color(230, 160, 0));
		g.fillOval(this.getX() - Camera.x, this.getY() - Camera.y, 30 , 30);
		
	}

}
