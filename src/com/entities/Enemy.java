package com.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.Random;

import com.main.Game;
import com.world.Camera;
import com.world.Map;

public class Enemy extends Entity{
	
	private double speed = 2;
	
	public static int maskX = 20, maskY = 20, maskW = 30, maskH = 30;
	
	public Rectangle enemyRect;
	
	public int hp = 100;
	public double dmg = 2;
	
	public Enemy(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		enemyRect = new Rectangle(this.getX(), this.getY(), Map.WIDTH, Map.HEIGHT);
		
	}
	
	public void tick() {
		//System.out.println(speed);
		if (this.isColidingWithPlayer() == false) {
		if (x < Game.player.getX() && Map.isFree((int)(x+speed), this.getY()) && !isColinding((int)(x+speed), this.getY())){
			
			x += speed;
			
		}else if (x > Game.player.getX() && Map.isFree((int)(x-speed), this.getY()) && !isColinding((int)(x-speed), this.getY())){
			
			x -= speed;
			
		}if (y < Game.player.getY() && Map.isFree(this.getX(), (int) (y+speed)) && !isColinding(this.getX(), (int) (y+speed))){
			
			y += speed;
			
		}else if (y > Game.player.getY() && Map.isFree(this.getX(), (int) (y-speed)) && !isColinding(this.getX(), (int) (y-speed))){
			
			y -= speed;
			
		}
		}else {
			if (Game.rnd.nextInt(100) <10){
			Game.player.hp-= dmg ;
			//System.out.println(Game.player.hp);
			}
			
			
		}
	}
	
	public boolean isColidingWithPlayer () {
		
		Game.player.playerRect = new Rectangle(Game.player.getX() + maskX, Game.player.getY() + maskY, maskW, maskH);
		enemyRect = new Rectangle(this.getX() + maskX, this.getY() + maskY, maskW, maskH);
		
		return Game.player.playerRect.intersects(enemyRect);
	}
	
	public boolean isColinding(int xNext, int yNext) {
		Rectangle colisionRect = new Rectangle(xNext + maskX, yNext + maskY, maskW, maskH);
		
		for (int i = 0; i < Game.enemies.size(); i++) {
			Enemy e = Game.enemies.get(i);
			if (e == this)
				continue;
			Rectangle targetRect = new Rectangle(e.getX() + maskX, e.getY() + maskY, maskW, maskH);
			if (colisionRect.intersects(targetRect)) {
				return true;
				
			}
		}
		return false;
	}
	
	public void render(Graphics g) {
		super.render(g);
		//g.setColor(Color.BLUE);
		//1g.fillRect(this.getX() + maskX - Camera.x, this.getY() + maskY - Camera.y, maskW, maskH);
	}
}
