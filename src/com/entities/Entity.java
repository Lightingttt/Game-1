package com.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;

public class Entity {
	
	public static BufferedImage LIFEFLAME_EN = Game.spritesheet.getSprite(0, 4*64, 64, 64);
	public static BufferedImage FIRE_AMMO_EN = Game.spritesheet.getSprite(0, 3*64, 64, 64);
	public static BufferedImage WATER_ENEMY_EN = Game.spritesheet.getSprite(0, 5*64, 64, 64);
	public static BufferedImage FIRE_RUNE_EN = Game.spritesheet.getSprite(0, 6*64, 64, 64);

	protected int x, y, width, height;
	
	private BufferedImage sprite;
	
	public Entity (int x, int y, int width, int height, BufferedImage sprite) {
	
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
	}
	
	public int getY() {
		return this.y;
	}
	
	public int getX() {
		return this.x;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public void tick() {
	
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX(), this.getY(), null);
		
	}

	
}

