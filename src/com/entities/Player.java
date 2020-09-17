package com.entities;

import java.awt.image.BufferedImage;

public class Player extends Entity{
	
	public boolean right, left, up, down;
	
	private double speed = 3;

	public Player(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		// TODO Auto-generated constructor stub
	}
	
	public void tick() {
		if (right) {
			x+=speed;
			
		}else if (left) {
			x-=speed;
		
		}if (up) {
			y+=speed;
		
		}else if (down) {
			y-=speed;
	}
		
	}

}
