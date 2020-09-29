package com.entities;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.main.Game;
import com.world.Map;

public class WaterEnemy extends Entity{
	
	private double speed = (rand.nextInt(2) + 1);
	private int rSpeed;
	private int rightPos = 0, leftPos = 1;
	
	public static Random rand = new Random();
	
	
	
	public WaterEnemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
	}
	
	public void tick() {
		//System.out.println(speed);
		//rSpeed = rand.nextInt(2);
		if (x < Game.player.getX() && Map.isFree((int)(x+speed), y)){
			
			x += speed;
			
		}else if (x > Game.player.getX() && Map.isFree((int)(x-speed), y)){
			
			x -= speed;
			
		}if (y < Game.player.getY() && Map.isFree(x, (int) (y+speed))){
			
			y += speed;
			
		}else if (y > Game.player.getY() && Map.isFree(x, (int) (y-speed))){
			
			y -= speed;
			
		}
		
	}
	public boolean isColinding(int xNext, int yNext) {
		Rectangle ColisionArea = new Rectangle(xNext, yNext);
		for (int i = 0; i < Game.entities.size(); i++) {
			WaterEnemy e = Game.enemies.get(i);
			if (e == this) {
				continue;
				
			}
		}return false;
		
	}
}
