package com.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.world.Camera;

public class Entity {
	
	public static BufferedImage LIFE_FLAME_EN = Game.spritesheet.getSprite(0, 7*64, 64, 64);
	public static BufferedImage FIRE_AMMO_EN = Game.spritesheet.getSprite(0, 6*64, 64, 64);
	public static BufferedImage WATER_ENEMY_EN = Game.spritesheet.getSprite(0, 8*64, 64, 64);
	public static BufferedImage FIRE_RUNE_EN = Game.spritesheet.getSprite(0, 9*64, 64, 64);

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
	
	public void setX(int newX) {
		this.x = newX;
	}
	
	public void setY(int newY) {
		this.y = newY;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
		
	}

	
}

