package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import com.main.Game;
import com.world.Camera;
import com.world.Map;

public class Entity {
	
	public static BufferedImage LIFE_FLAME_EN = Game.spritesheet.getSprite(0, 7*64, 64, 64);
	public static BufferedImage FIRE_AMMO_EN = Game.spritesheet.getSprite(0, 6*64, 64, 64);
	public static BufferedImage WATER_ENEMY_EN = Game.spritesheet.getSprite(0, 8*64, 64, 64);
	public static BufferedImage FIRE_RUNE_EN = Game.spritesheet.getSprite(0, 9*64, 64, 64);

	protected double x, y;
	protected int width, height;
	protected int maskX, maskY, maskW, maskH;
	
	private BufferedImage sprite;
	
	public Entity (int x, int y, int width, int height, BufferedImage sprite) {
	
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.sprite = sprite;
		
		this.maskX = 0;
		this.maskY = 0;
		this.maskW = width;
		this.maskH = height;
	}
	
	public void setMask (int maskX,int maskY,int maskW,int maskH) {
		this.maskX = maskX;
		this.maskY = maskY;
		this.maskW = maskW;
		this.maskH = maskH;
	}
	
	public int getY() {
		return (int)this.y;
	}
	
	public int getX() {
		return (int)this.x;
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
	
	public static Entity checkItem() {
		Entity en = null;
		for (int i = 0; i < Game.entities.size(); i++) {
			en = Game.entities.get(i);
			//System.out.println("Check");
		}
		return en;
	}
	
	
	public static boolean isColindingWith(Entity e1, Entity e2) {
		Rectangle e1Mask = new Rectangle(e1.getX() + e1.maskX, e1.getY() + e1.maskY, e1.maskW, e1.maskH);
		Rectangle e2Mask = new Rectangle(e2.getX() + e2.maskX, e2.getY() + e2.maskY, e2.maskW, e2.maskH);
			
		//System.out.println("Esta colidindo");
		return e1Mask.intersects(e2Mask);			
		}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(sprite, this.getX() - Camera.x, this.getY() - Camera.y, null);
		g.setColor(new Color(0, 0, 255, 100));
		g.fillRect(this.getX() + maskX - Camera.x, this.getY() + maskY - Camera.y,  maskW, maskH);
	}


	
}

