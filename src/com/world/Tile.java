package com.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.main.Game;

public class Tile {
	
	public static BufferedImage TILE_GRASS_FLOOR = Game.spritesheet.getSprite(64, 128, 64, 64);
	public static BufferedImage TILE_SAND_FLOOR = Game.spritesheet.getSprite(2*64, 128, 64, 64);
	public static BufferedImage TILE_STONE_FLOOR = Game.spritesheet.getSprite(0, 128, 64, 64);
	public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(3*64, 128, 64, 64);
	
	private BufferedImage sprite;
	private int x, y;
	
	public Tile (int x, int y, BufferedImage sprite) {
		this.x = x;
		this.y = y;
		this.sprite = sprite;
	}
	
	public void render(Graphics g) {
		
		g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
	}
	
}
