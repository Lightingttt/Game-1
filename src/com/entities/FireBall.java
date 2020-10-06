package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class FireBall extends Entity{

	public FireBall(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	public void render(Graphics g) {
		
		g.setColor(new Color(200, 140, 0));
		g.fillOval(0, 0, Player.power , Player.power);
	}

}
