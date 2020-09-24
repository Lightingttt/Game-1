package com.world;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.entities.Entity;
import com.entities.FireAmmo;
import com.entities.FireRune;
import com.entities.LifeFlame;
import com.entities.WaterEnemy;
import com.main.Game;

public class Map {
	
	private Tile[] tiles;
	
	private static int WIDTH, HEIGHT;
	
	private static int blockSize = 64;
	
	public Map(String path) {
		try {
			BufferedImage map= ImageIO.read(getClass().getResource(path));
			
			int[] pixel = new int[map.getWidth() * map.getHeight()];
			
			WIDTH = map.getWidth();
			HEIGHT = map.getHeight();
			
			tiles = new Tile [map.getWidth() * map.getHeight()];
			
			map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixel, 0, map.getWidth());
			
			for (int xx = 0; xx < WIDTH; xx++) {
				for (int yy = 0; yy < HEIGHT; yy++) {
					int pixelAtual = pixel[xx + (yy * map.getWidth())];
					tiles [xx + (yy * WIDTH)] = new GrassTile(xx*blockSize, yy*blockSize, Tile.TILE_GRASS_FLOOR);
				switch(pixelAtual) {
				case 0xFFFFFF00://PIXEL AMARELO
					tiles [xx + (yy * WIDTH)] = new GrassTile(xx*blockSize, yy*blockSize, Tile.TILE_SAND_FLOOR);
					break;
				case 0xFF808080://PIXEL CINZA
					tiles [xx + (yy * WIDTH)] = new GrassTile(xx*blockSize, yy*blockSize, Tile.TILE_STONE_FLOOR);
					break;
				case 0xFFFFFFFF://PIXEL BRANCO
					tiles [xx + (yy * WIDTH)] = new GrassTile(xx*blockSize, yy*blockSize, Tile.TILE_WALL);
					break;
				case 0xFF00FFFF://PIXEL CIANO
					Game.entities.add(new FireRune(xx*64, yy*64, 64, 64, Entity.FIRE_RUNE_EN));
					break;
				case 0xFF4CFF00://PIXEL VERDE
					Game.entities.add(new LifeFlame(xx*64, yy*64, 64, 64, Entity.LIFE_FLAME_EN));
					break;
				case 0xFF0026FF://PIXEL AZUL
					Game.player.setX(xx*64);
					Game.player.setY(yy*64);
					break;
				case 0xFFFF00FF://PIXEL ROSA
					Game.entities.add(new FireAmmo(xx*64, yy*64, 64, 64, Entity.FIRE_AMMO_EN));
					break;
				case 0xFFFF0000://PIXEL VERMELHO
					//tiles [xx + (yy * WIDTH)] = new GrassTile(xx*blockSize, yy*blockSize, Tile.TILE_GRASS_FLOOR);
					//break;
					Game.entities.add(new WaterEnemy(xx*64, yy*64, 64, 64, Entity.WATER_ENEMY_EN));
				}
				
			}
				
				
		} 
		}catch (IOException e) {
			e.printStackTrace();
		}
	
	}public void render(Graphics g) {
		int xstart = Camera.x / 64;
		int ystart = Camera.y / 64;
		
		int xfinal = xstart + (Game.WIDTH / 64);
		int yfinal = ystart + (Game.HEIGHT / 64);
		
		for (int xx = xstart; xx <= xfinal; xx++) {
			for (int yy = ystart; yy <= yfinal; yy++) {
				if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT) 
					continue;
				
				Tile tile = tiles [xx + (yy * WIDTH)];
				tile.render(g);}
		}
	}
}	
