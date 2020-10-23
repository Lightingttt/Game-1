package com.entities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.world.Camera;

public class DmgText extends Entity{
	
	public double dmgdff = Enemy.dmg, heal = LifeFlame.heal;
	private int txtLife = 0, txtMaxLife = 30, t = 255;
	private double spd = 1;
	public int rnd = Game.rnd.nextInt(20);
	
	public DmgText(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick() {
		txtLife++;
		t = t -(255/txtMaxLife);
		y-=spd;
		if (txtLife > txtMaxLife)
			Game.dmgTexts.remove(this);
		
	}
	
	public void render(Graphics g) {
		g.setColor(new Color(255, 0, 0, t));
		g.setFont(new Font("arial", Font.BOLD, 15));
		g.drawString(String.valueOf((int)dmgdff), this.getX() - Camera.x + 20 + rnd, this.getY() - Camera.y);
		//Graphics2D g2 = (Graphics2D) g;
		
	}
}
